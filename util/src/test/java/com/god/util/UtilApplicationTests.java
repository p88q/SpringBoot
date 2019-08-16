package com.god.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        requestUrlToBase64("http://47.90.107.70:8000/api-lable/pdf/20190812/4d76ceef-6ccd-4fca-b7bf-2afe050aea55.pdf");
    }
    public static String requestUrlToBase64(String imgUrl) {
        String result = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(imgUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 获取请求输入流
            InputStream inputStream = connection.getInputStream();
            // inputStream 转为 byreArrayOutPutStream
            int len = -1;
            byte[] buffer = new byte[2048];
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            // ByteArrayOutPutStream 编码转为 base64字符串
            result = new String(Base64.getEncoder().encode(outputStream.toByteArray()));
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        System.out.println(result);
        return result;
    }


}
