package com.god.util.img;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/6 10:10
 * @ClassName: SCaptcha
 * @Description: Java实现图片验证码生成器
 */
public class SCaptcha {

    /** 图片宽度 */
    private int width = 120;
    /** 图片高度 */
    private int height = 40;
    /** 验证码字符个数 */
    private int codeCount = 4;
    /** 验证码干扰线数 */
    private int lineCount = 50;
    /** 验证码 */
    private String code = null;
    /** 验证码图片Bufffer */
    private BufferedImage buffImg = null;
    /** 随机生成字符集，可以去除0和1，防止和O、I字母相同 */
    private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    /** 生成随机数 */
    private Random random = new Random();

    public SCaptcha() {
        this.createCode();
    }

    public SCaptcha(int width, int height) {
        this.width = width;
        this.height = height;
        this.createCode();
    }

    public SCaptcha(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode();
    }

    public void createCode() {
        int codeX = 0;
        int fontHeight = 0;
        // 字体的高度
        fontHeight = height - 5;
        // 每个字符的宽度
        codeX = width / (codeCount + 3);

        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 设置图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 设置干扰数
        for (int i = 0; i < lineCount; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = x1 + random.nextInt(width / 8);
            int y2 = y1 + random.nextInt(height / 8);
            g.setColor(getRandomColor());
            g.drawLine(x1, y1, x2, y2);
        }
        // 创建字体的两种方式，字体的大小应该根据图片的高度来定。
        // 方式1：
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 方式2：
        ImgFontByte imgFontByte = new ImgFontByte();
        Font font1 = imgFontByte.getFont(fontHeight);
        // 设置字体
        g.setFont(font);

        StringBuffer randomCode = new StringBuffer();

        // 随机产生验证码字符
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 设置字体颜色
            g.setColor(getRandomColor());
            // 设置字体位置
            g.drawString(strRand, (i + 1) * codeX, getRandomNumber(height / 2) + 25);
            randomCode.append(strRand);
        }
        // 画干扰线
        for (int i = 0; i < lineCount; i++) {

        }
        code = randomCode.toString();
    }

    /**
     * 获取随机颜色
     *
     * @return
     */
    private Color getRandomColor() {
        int r = getRandomNumber(255);
        int g = getRandomNumber(255);
        int b = getRandomNumber(255);
        return new Color(r, g, b);
    }

    /**
     * 获取随机数
     *
     * @param number
     * @return
     */
    private int getRandomNumber(int number) {
        return random.nextInt(number);
    }

    public void write(String path) throws IOException {
        OutputStream sos = new FileOutputStream(path);
        this.write(sos);
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code;
    }

    class ImgFontByte {
        public Font getFont(int fontHeight) {
            try {
                Font baseFont = Font.createFont(Font.HANGING_BASELINE, new ByteArrayInputStream(
                        hax2byte(getFontByteStr())));
                return baseFont.deriveFont(Font.PLAIN, fontHeight);
            } catch (Exception e) {
                return new Font("Arial", Font.PLAIN, fontHeight);
            }
        }

        private byte[] hax2byte(String str) {
            if (str == null) {
                return null;
            }
            str = str.trim();
            int len = str.length();
            if (len == 0 || len %2 == 1) {
                return null;
            }
            byte[] b = new byte[len / 2];
            try {
                for (int i = 0; i < str.length(); i += 2) {
                    b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
                }
                return b;
            } catch (Exception e) {
                return null;
            }
        }

        // 字体文件的十六进制字符串
        private String getFontByteStr() {
            // 防止报字符串长度过长错误，改为从配置文件读取
            return ReadFontByteProperties.getFontByteStr();
        }
    }
}
