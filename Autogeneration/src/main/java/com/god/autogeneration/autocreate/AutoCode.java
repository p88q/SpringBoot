package com.god.autogeneration.autocreate;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成代码工具类
 */
public class AutoCode {

    /**
     * 自动生成代码
     * @throws Exception
     */
    public void generator() throws Exception {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        // 获取当前文件所在目录
        System.out.println(this.getClass().getResource("/generatorConfig.xml").getPath());
        File configFile = new File("E:/IDEAProject/SpringBoot/Autogeneration/target/classes/generatorConfig.xml");
//        File configFile = new File(this.getClass().getResource("/generatorConfig.xml").getPath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }

    /**
     * 自动生成代码
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            AutoCode generatorSqlmap = new AutoCode();
            generatorSqlmap.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
