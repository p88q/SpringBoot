package com.god.easypoi.listener;

import cn.afterturn.easypoi.cache.ImageCache;
import cn.afterturn.easypoi.cache.manager.POICacheManager;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.poi.util.IOUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 17:19
 * @ClassName: ExcelListener
 * @Description: 在初始化spring容器后设置自己的loadingCache和fileLoder
 */
@Component
public class ExcelListener implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * 重写方法
     * 设置setLoadingCache，获取图片最后边的后缀名
     * 原因：
     *      easypoi加载图片的话以小数点分割，如果图片名称中存在小数点，则会出现图片无法加载的问题
     *      设置setFileLoder，解决文件获取不到的问题
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LoadingCache<String, byte[]> loadingCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS)
                .maximumSize(2000).build(new CacheLoader<String, byte[]>() {
                    @Override
                    public byte[] load(String imagePath) throws Exception {
                        InputStream is = POICacheManager.getFile(imagePath);
                        BufferedImage bufferImg = ImageIO.read(is);
                        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                        try {
                            ImageIO.write(bufferImg,
                                    //重写获取后缀面的方法
                                    imagePath.substring(imagePath.lastIndexOf(".") + 1),
                                    byteArrayOut);
                            return byteArrayOut.toByteArray();
                        } finally {
                            IOUtils.closeQuietly(is);
                            IOUtils.closeQuietly(byteArrayOut);
                        }
                    }
                });
        ImageCache.setLoadingCache(loadingCache);
        POICacheManager.setFileLoder(new EasypoiFileLoader());
    }
}
