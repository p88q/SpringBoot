package com.god.example.dozerMapper;


import com.god.example.model.UserB;
import com.god.example.model.UserBean;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.dozer.classmap.RelationshipType;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.dozer.loader.api.FieldsMappingOptions.collectionStrategy;
import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;
import static org.dozer.loader.api.FieldsMappingOptions.customConverter;
import static org.dozer.loader.api.FieldsMappingOptions.customConverterId;
import static org.dozer.loader.api.FieldsMappingOptions.hintA;
import static org.dozer.loader.api.FieldsMappingOptions.hintB;
import static org.dozer.loader.api.FieldsMappingOptions.useMapId;
import static org.dozer.loader.api.TypeMappingOptions.mapId;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/14 15:43
 * @ClassName: DozerMapperTest
 * @Description:
 */
public class DozerMapperTest {

    public static void main(String[] args) {
//        Mapper mapper = new DozerBeanMapper();
        UserBean userBean = new UserBean();
        userBean.setId(123);
        userBean.setDeptId(120001);
        userBean.setMail("1219192817@qq.com");
        userBean.setOperateIp("192.168.0.1");
        userBean.setOperateTime(new Date());
        userBean.setOperator("操作者");
        userBean.setPassword("密码");
        userBean.setRemark("备注");
        userBean.setStatus(1001);
        userBean.setTelephone("12345678978");
        userBean.setUsername("管理员");
        UserB userB = new UserB();
//        mapper.map(userBean, userB);
        System.out.println(userB);

        // 方式二读取配置文件
//        List<String> myMappingFiles = new ArrayList<>();
////        myMappingFiles.add("dozerBeanMapping.xml");
//        DozerBeanMapper beanMapper = new DozerBeanMapper();
//        beanMapper.setMappingFiles(myMappingFiles);
//        UserB userB1 = new UserB();
//        beanMapper.map(userBean, userB1);
//        System.out.println(userB1);
        // 另一种实例化方法，如果没有使用单例或者spring IOC则使用该例即可
        Mapper instance = DozerBeanMapperSingletonWrapper.getInstance();
        UserB userB2 = new UserB();
        instance.map(userBean, userB2);
        System.out.println(userB2);
    }

    public DozerBeanMapper getInstance() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {

            @Override
            protected void configure() {
                mapping(Bean.class, Bean.class,
                        TypeMappingOptions.oneWay(),
                        mapId("A"),
                        mapNull(true)
                )
                        .exclude("excluded")
                        .fields("src", "dest",
                                copyByReference(),
                                collectionStrategy(true,
                                        RelationshipType.NON_CUMULATIVE),
                                hintA(String.class),
                                hintB(Integer.class),
                                FieldsMappingOptions.oneWay(),
                                useMapId("A"),
                                customConverterId("id")
                        )
                        .fields("src", "dest",
                                customConverter("org.dozer.CustomConverter")
                        );
            }
        };
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(builder);
        return mapper;
    }

}
