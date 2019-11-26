package com.god.autogeneration.autocreate;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/25 18:03
 * @ClassName: CommentGenerator
 * @Description:
 */

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Date;
import java.util.Properties;

/**
 * 自定义注释生成器
 */
public class CommentGenerator1 extends DefaultCommentGenerator {
    private boolean addRemarkComments ;

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    /**
     * 给字段添加注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        //字段备注
        String remarks = introspectedColumn.getRemarks();
        //根据参数和备注信息判断是否添加备注信息
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
//            StringBuffer swaggerAnnotation=new StringBuffer();
            //文档注释开始
            field.addJavaDocLine("/**");
            //获取数据库字段的备注信息
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                // 添加自定义字段注释
                field.addJavaDocLine(" * " + remarkLine);
                field.addJavaDocLine(" * @date " + new Date());
//                swaggerAnnotation.append(' '+remarkLine+' ');
            }
            //addJavadocTag(field, false);
            //文档注释结束
            field.addJavaDocLine(" */");
            //swagger注解
//            field.addJavaDocLine(""+'"'+swaggerAnnotation+'"'+")");
        }
    }
    /**
     * 给实体类添加注释
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //表备注
        String remarks = introspectedTable.getRemarks();
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            //文档注释开始
            topLevelClass.addJavaDocLine("/**");
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                topLevelClass.addJavaDocLine(" * " + remarkLine);
            }
            //文档注释结束
            topLevelClass.addJavaDocLine(" */");
        }
    }
}