package com.god.util.img;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/13 18:06
 * @ClassName: ReadFontByteProperties
 * @Description:
 */
public class ReadFontByteProperties {
    static private String fontByteStr = null;

    static {
        loads();
    }

    synchronized static public void loads() {
        if (fontByteStr == null) {
            InputStream is = ReadFontByteProperties.class.getResourceAsStream("/fontByte.properties");
            Properties dbProperties = new Properties();

            try {
                dbProperties.load(is);
                fontByteStr = dbProperties.getProperty("font");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("不能读取属性配置文件：fontByte.properties");
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String getFontByteStr() {
        if (fontByteStr == null) {
            loads();
        }
        return fontByteStr;
    }

}
