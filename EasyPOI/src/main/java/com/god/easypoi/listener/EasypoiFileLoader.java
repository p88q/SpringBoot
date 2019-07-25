package com.god.easypoi.listener;

import cn.afterturn.easypoi.cache.manager.FileLoaderImpl;
import cn.afterturn.easypoi.cache.manager.IFileLoader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 17:20
 * @ClassName: EasypoiFileLoader
 * @Description:
 *      自定义fileloader解决文件获取不到的问题
 *      网络图片使用httpclient替换urlconnection
 */
public class EasypoiFileLoader implements IFileLoader {

    /**
     * 使用httpClient下载网络上的图片
     * @param url
     * @return
     */
    @Override
    public byte[] getFile(String url) {
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            if (url.startsWith("http")) {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet();
                httpGet.setURI(new URI(url));
                HttpResponse response = httpClient.execute(httpGet);
                is = response.getEntity().getContent();
            } else {
                ///先用绝对路径查询,再查询相对路径
                try {
                    is = new FileInputStream(url);
                } catch (FileNotFoundException e) {
                    //获取项目文件
                    is = FileLoaderImpl.class.getClassLoader().getResourceAsStream(url);
                    if (is == null) {
                        is = FileLoaderImpl.class.getResourceAsStream(url);
                    }
                }
            }
            bos = new ByteArrayOutputStream();
            byte[] bts = new byte[1024];
            int len;
            while((len = is.read(bts)) > -1) {
                bos.write(bts, 0, len);
            }
            bos.flush();
            return bos.toByteArray();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(bos);
        }
        return null;
    }
}
