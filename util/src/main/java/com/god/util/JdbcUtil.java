package com.god.util;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/22 16:47
 * @ClassName: JdbcUtil
 * @Description:
 */
public class JdbcUtil {

    private static final Logger logger = LoggerFactory.getLogger(JdbcUtil.class);

    private static DataSource dataSource;

//    static {
//        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
//                    .addScript("classpath:/H2_TYPE.sql")
//                    .addScript("classpath:/INIT_TABLE.sql")
//                    .build();
//    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 增删改的通用方法
     *
     * @param sql 要执行的sql
     * @param parameters 对象类型的数组，需要和sql中的占位符一一对应
     * @return
     */
    public static int executeUpdate(String sql, Object... parameters) {
        int count = 0;
        Connection conn = null;
        PreparedStatement st =null;

        conn = JdbcUtil.getConnection();

        try {
            st = conn.prepareStatement(sql);
            int parameterCount = st.getParameterMetaData().getParameterCount();

            if (null !=parameters && parameterCount == parameters.length) {
                for (int i = 0; i <= parameterCount; i++) {
                    st.setObject(i, parameters[i - 1]);
                }
            }
            count = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st);
        }
        return count;
    }

    /**
     * 修改
     * @param sql
     * @return
     */
    public static int update(String sql) {
        int count = 0;
        Connection conn = null;
        Statement statement = null;
        conn = JdbcUtil.getConnection();

        try {
            statement = conn.createStatement();
            count = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, statement);
        }
        return count;
    }

    // 查询一条记录，返回对应的对象
    public static <T> T get(Class<T> clazz, String sql, Object... args) {
        List<T> result = getForList(clazz, sql, args);
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    /**
     * 查询记录，获取返回结果string
     * @param sql
     * @return
     */
    public static String get(String sql) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer stringBuffer = new StringBuffer();
        // 1. 得到结果集
        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<Map<String, Object>> maps = handleResultSetToMapList(resultSet);
            for (Map<String, Object> str : maps) {
                stringBuffer.append("[");
                for (Map.Entry<String, Object> s : str.entrySet()) {
                    stringBuffer.append(s.getKey()).append(":").append(s.getValue()).append(",");
                }
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                stringBuffer.append("]");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }
        return stringBuffer.toString();
    }

    /**
     * 传入SQL语句和class对象，查询多条记录，返回对应的对象的集合
     *
     * @param clazz  对象的类型
     * @param sql    SQL语句
     * @param args   填充SQL语句的占位符可变参数
     * @return  对象的集合
     */
    public static <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {

        List<T> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. 得到结果集
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();

            // 2.处理结果集，得到Map的List，其中一个Map对象就是一条记录
            // Map的key为resultSet中的列名，value为列的值
            List<Map<String, Object>> values = handleResultSetToMapList(resultSet);

            // 3.把Map的List转为clazz对应的List集合
            // 其中Map的key即为clazz对应的propertyName
            // Map的value即为clazz对应的propertyValue
            list = transferMapListToBeanList(clazz, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }

        return list;
    }

    public static <T> List<T> transferMapListToBeanList(Class<T> clazz, List<Map<String, Object>> values)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {
        List<T> result = new ArrayList<>();
        T bean = null;
        if (values.size() > 0) {
            // 遍历Map中的键值
            for (Map<String, Object> m : values) {
                bean = clazz.newInstance();
                for (Map.Entry<String, Object> entry : m.entrySet()) {
                    String propertyName = entry.getKey();
                    Object value = entry.getValue();

                    BeanUtils.setProperty(bean, propertyName, value);
                }
                // 将object对象放入list中
                result.add(bean);
            }
        }
        return result;
    }

    /**
     * 处理结果集，得到含有Map的一个List，其中一个Map对象对应一条记录
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> handleResultSetToMapList(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> values = new ArrayList<>();

        List<String> columnLabels = getColumnLabels(resultSet);
        Map<String, Object> map = null;

        // 处理ResultSet，使用while循环
        while (resultSet.next()) {
            map = new HashMap<>();

            for (String columnLabel : columnLabels) {
                Object value = resultSet.getObject(columnLabel); // 获得列的值

                map.put(columnLabel, value); // 将列的别名与列的值放入map集合中
            }
            values.add(map); // 将map集合放入List中
        }
        return values;
    }

    /**
     * 获取结果集的ColumnLabel对应的List
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static List<String> getColumnLabels(ResultSet rs) throws SQLException {
        List<String> labels = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 0; i < rsmd.getColumnCount(); i++) {
            labels.add(rsmd.getColumnLabel(i + 1));
        }
        return labels;
    }

    // 返回某条记录的某个字段的值或一个统计的值
    public static <E> E getForValue(String sql, Object ...args) {

        // 1.得到结果集：该结果集只有一行，一列
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return (E) resultSet.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    /**
     * 关闭数据库连接对象的方法
     * @param conn
     * 		传入的数据库连接对象
     */
    public static void close(Connection conn) {
        close(conn, null, null);
    }

    public static void close(Connection conn, Statement st) {
        close(conn, st, null);
    }

    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != st) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
