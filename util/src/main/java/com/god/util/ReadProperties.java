package com.god.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/13 19:04
 * @ClassName: ReadProperties
 * @Description: 读取properties文件工具类
 * 注意：
 * *      配置文件路径：如果配置文件在当前类所在包下则要使用包名限定
 * *      如：config.properties在com.god.config包下，则要使用com/god/config/config.properties(通过Properties发那个是获取)
 * *      或者com/god/config/config(通过ResurceBundle来获取);
 * *      属性文件在src根目录下，则直接使用config.properties或config即可
 */
public class ReadProperties {

    /**
     * 1、基于ClassLoder读取配置文件
     * <p>
     * 注意：该方式只能读取类路径下的配置文件，有局限但是如果配置文件在类路径下比较方便
     *
     * @throws IOException
     */
    public static void test1() throws IOException {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = ReadProperties.class.getClassLoader().getResourceAsStream("config/config.properties");
        // 使用properties对象加载输入流
        properties.load(in);
        //获取key对应的value值
        String property = properties.getProperty("123");
        System.out.println(property);
    }

    /**
     * 2、基于 InputStream 读取配置文件
     * <p>
     * 注意：该方式的优点在于可以读取任意路径下的配置文件
     */
    public static void test2() throws IOException {
        Properties properties = new Properties();
        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader("E:/config.properties"));
        properties.load(bufferedReader);
        // 获取key对应的value值
        properties.getProperty("123");
    }

    /**
     * 3.通过java.util.ResurceBundle类来读取，这种方式比使用Properties方便些，只需要文件名即可
     */
    public static void test3() {
        // 1、通过Resource.getBundle()静态方法获取properties文件，不需要添加.properties后缀名
        ResourceBundle resource = ResourceBundle.getBundle("config");
        String key = resource.getString("123");
        System.out.println(key);
    }

    /**
     * 4、通过 java.util.ResourceBundle 类来读取，这种方式比使用 Properties 要方便一些
     */
    public static void test4() throws IOException {
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = ReadProperties.class.getClassLoader().getResourceAsStream("config/config.properties");
        // 2>从 InputStream 中读取，获取 InputStream 的方法和上面一样，不再赘述
        ResourceBundle resource = new PropertyResourceBundle(in);
        String string = resource.getString("123");
        System.out.println(string);
    }

    /**
     * @param props
     * @return void
     * @throws
     * @Title: printAllProperty
     * @Description: 输出所有配置信息
     */
    private static void printAllProperty(Properties props) {
        @SuppressWarnings("rawtypes")
        Enumeration en = props.propertyNames();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String value = props.getProperty(key);
            System.out.println(key + " : " + value);
        }
    }

    /**
     * 根据key读取value
     *
     * @param filePath
     * @param keyWord
     * @return String
     * @Title: getProperties_1
     * @Description: 第一种方式：根据文件名使用spring中的工具类进行解析
     * filePath是相对路劲，文件需在classpath目录下
     * 比如：config.properties在包com.test.config下，
     * 路径就是com/test/config/config.properties
     */
    public static String getProperties_1(String filePath, String keyWord) {
        Properties prop = null;
        String value = null;
        try {
            // 通过Spring中的PropertiesLoaderUtils工具类进行获取
            prop = PropertiesLoaderUtils.loadAllProperties(filePath);
            // 根据关键字查询相应的值
            value = prop.getProperty(keyWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 读取配置文件所有信息
     *
     * @param filePath
     * @return void
     * @throws
     * @Title: getProperties_1
     * @Description: 第一种方式：根据文件名使用Spring中的工具类进行解析
     * filePath是相对路劲，文件需在classpath目录下
     * 比如：config.properties在包com.test.config下，
     * 路径就是com/test/config/config.properties
     */
    public static void getProperties_1(String filePath) {
        Properties prop = null;
        try {
            // 通过Spring中的PropertiesLoaderUtils工具类进行获取
            prop = PropertiesLoaderUtils.loadAllProperties(filePath);
            printAllProperty(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key读取value
     *
     * @param filePath
     * @param keyWord
     * @return String
     * @throws
     * @Title: getProperties_2
     * @Description: 第二种方式：使用缓冲输入流读取配置文件，然后将其加载，再按需操作
     * 绝对路径或相对路径， 如果是相对路径，则从当前项目下的目录开始计算，
     * 如：当前项目路径/config/config.properties,
     * 相对路径就是config/config.properties
     */
    public static String getProperties_2(String filePath, String keyWord) {
        Properties prop = new Properties();
        String value = null;
        try {
            // 通过输入缓冲流进行读取配置文件
            InputStream InputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
            // 加载输入流
            prop.load(InputStream);
            // 根据关键字获取value值
            value = prop.getProperty(keyWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 读取配置文件所有信息
     *
     * @param filePath
     * @return void
     * @throws
     * @Title: getProperties_2
     * @Description: 第二种方式：使用缓冲输入流读取配置文件，然后将其加载，再按需操作
     * 绝对路径或相对路径， 如果是相对路径，则从当前项目下的目录开始计算，
     * 如：当前项目路径/config/config.properties,
     * 相对路径就是config/config.properties
     */
    public static void getProperties_2(String filePath) {
        Properties prop = new Properties();
        try {
            // 通过输入缓冲流进行读取配置文件
            InputStream InputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
            // 加载输入流
            prop.load(InputStream);
            printAllProperty(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key读取value
     *
     * @param filePath
     * @param keyWord
     * @return String
     * @throws
     * @Title: getProperties_3
     * @Description: 第三种方式：
     * 相对路径， properties文件需在classpath目录下，
     * 比如：config.properties在包com.test.config下，
     * 路径就是/com/test/config/config.properties
     */
    public static String getProperties_3(String filePath, String keyWord) {
        Properties prop = new Properties();
        String value = null;
        try {
            InputStream inputStream = ReadProperties.class.getResourceAsStream(filePath);
            prop.load(inputStream);
            value = prop.getProperty(keyWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 读取配置文件所有信息
     *
     * @param filePath
     * @return
     * @throws
     * @Title: getProperties_3
     * @Description: 第三种方式：
     * 相对路径， properties文件需在classpath目录下，
     * 比如：config.properties在包com.test.config下，
     * 路径就是/com/test/config/config.properties
     */
    public static void getProperties_3(String filePath) {
        Properties prop = new Properties();
        try {
            InputStream inputStream = ReadProperties.class.getResourceAsStream(filePath);
            prop.load(inputStream);
            printAllProperty(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

//        test1();
//        test3();
        test4();
        // 注意路径
        // 问题
//        String properties_1 = getProperties_1("com/test/config/config.properties", "wechat_appid");
//        System.out.println("wechat_appid = " + properties_1);
//        getProperties_1("com/test/config/config.properties");
//        System.out.println("*********************************************");
//        // 注意路径问题
//        String properties_2 = getProperties_2("configure/configure.properties", "jdbc.url");
//        System.out.println("jdbc.url = " + properties_2);
//        getProperties_2("configure/configure.properties");
//        System.out.println("*********************************************");
//        // 注意路径问题
//        String properties_3 = getProperties_3("/com/test/config/config.properties", "wechat_appid");
//        System.out.println("wechat_appid = " + properties_3);
//        getProperties_3("/com/test/config/config.properties");
    }

}
