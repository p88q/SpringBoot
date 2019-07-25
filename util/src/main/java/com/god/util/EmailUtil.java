package com.god.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/24 18:07
 * @ClassName: EmailUtil
 * @Description: 发送邮件工具类
 */
@Configuration
@PropertySource(value = "classpath:config/EmailConfig.properties", ignoreResourceNotFound = false, encoding = "UTF-8", name = "config/EmailConfig.properties")
public class EmailUtil {

    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    /** 发件人邮件 */
    private static String from;
    /** SMTP邮件服务器 */
    private static String host;
    /** 端口号 */
    private static int port;
    /** 发件人用户名 */
    private static String userName;
    /** 发件人授权码 */
    private static String password;
    /** 是否验证用户名密码 */
    private static boolean auth;
    /** 链接协议 */
    private static String protocol;
    /** 是否使用ssl安全连接 */
    private static boolean enable;
    /** 设置465端口ssl连接 */
    private static String socketFactory;
    /** 是否打印debug信息 */
    private static boolean debug;
    /** Email的session会话 */
    private static Session session;
    /** 邮件发送的格式 */
    private static String contentType = "text/html;charset=utf8";

    /**
     * 发送邮件
     * @param subject 邮件标题
     * @param recipients 收件地址
     * @param content 邮件内容
     * @return
     */
    public static Result sendMail(String subject, String recipients, String content) {
        try {
            // 获取Session
            Session session = getSession();
            // 如果邮件没有内容，赋值默认内容
            if(StringUtils.isEmpty(content)){
                content = "This mail has no content!";
            }
            // 创建默认的 MimeMessage 对象
            MimeMessage mimeMessage = new MimeMessage(session);
            // 设置发件人邮箱
            mimeMessage.setFrom(new InternetAddress(from));
            // 设置主题
            mimeMessage.setSubject(subject);
            // 设置发件人用户名
            mimeMessage.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(userName));
            // 设置收件人邮箱
            mimeMessage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            // 设置消息体
            mimeMessage.setContent(content, contentType);
            // 发送消息
            Transport.send(mimeMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败, 邮件标题为:{}, 邮件收件地址为:{}, 邮件错误信息为:{}!", subject, recipients, e.getMessage());
            return Result.getFail(Constants.MESSAGE_FAIL, String.format("邮件发送失败,失败信息为:%s", e.getMessage()));
        }
        return Result.getSuccess();
    }

    /**
     * 获取session会话
     * @return
     * @throws IOException
     */
    private static Session getSession() throws IOException {
        if (session != null) {
            return session;
        }
        // 获取系统属性
        Properties properties = new Properties();
        // 两种方式都可以
        // 1、读取properties文件
//        properties.load(new InputStreamReader(Objects.requireNonNull(EmailUtil.class.getClassLoader().getResourceAsStream("config/EmailConfig.properties")), StandardCharsets.UTF_8));
        // 2、手动赋值
        // 设置链接协议
        properties.put("mail.transport.protocol", properties);
        // 是否使用ssh协议
        properties.put("mail.smtp.ssl.enable", enable);
        // 设置邮件服务器
        properties.put("mail.smtp.host", host);
        // 设置发件端口
        properties.put("mail.smtp.port", port);
        // 开启ssl加密
        properties.put("mail.smtp.auth", auth);
        // 465端口ssl连接
        properties.put("mail.smtp.socketFactory.class", socketFactory);
        // 3、获取默认session对象
        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮件用户名、密码
                return new PasswordAuthentication(userName, password);
            }
        });
        // 4、是否打印debug信息
        session.setDebug(debug);
        return session;
    }

    public String getFrom() {
        return from;
    }

    @Value("${mail.from}")
    public void setFrom(String from) {
        EmailUtil.from = from;
    }

    public String getHost() {
        return host;
    }

    @Value("${mail.host}")
    public void setHost(String host) {
        EmailUtil.host = host;
    }

    public int getPort() {
        return port;
    }

    @Value("${mail.smtp.port}")
    public void setPort(int port) {
        EmailUtil.port = port;
    }

    public String getUserName() {
        return userName;
    }

    @Value("${mail.userName}")
    public void setUserName(String userName) {
        EmailUtil.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    @Value("${mail.password}")
    public void setPassword(String password) {
        EmailUtil.password = password;
    }

    public boolean isAuth() {
        return auth;
    }

    @Value("${mail.smtp.auth}")
    public void setAuth(boolean auth) {
        EmailUtil.auth = auth;
    }

    public String getProtocol() {
        return protocol;
    }

    @Value("${mail.transport.protocol}")
    public void setProtocol(String protocol) {
        EmailUtil.protocol = protocol;
    }

    public boolean isEnable() {
        return enable;
    }

    @Value("${mail.smtp.ssl.enable}")
    public void setEnable(boolean enable) {
        EmailUtil.enable = enable;
    }

    public String getSocketFactory() {
        return socketFactory;
    }

    @Value("${mail.smtp.socketFactory.class}")
    public void setSocketFactory(String socketFactory) {
        EmailUtil.socketFactory = socketFactory;
    }

    public boolean isDebug() {
        return debug;
    }

    @Value("${mail.debug}")
    public void setDebug(boolean debug) {
        EmailUtil.debug = debug;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EmailUtil.class.getSimpleName() + "[", "]").toString();
    }
}
