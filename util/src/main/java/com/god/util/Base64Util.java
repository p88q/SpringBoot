package com.god.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

/**
 * 关于Base64格式转换的工具类
 */
public class Base64Util {

	/**
	 * 文件读取缓冲区大小
	 */
	private static final int CACHE_SIZE = 1024;


	/**
	 * 二进制数据编码为Base64字符串
	 * @param msg
	 * 		需要转码的二进制数据,参数类型byte[]
	 * @return
	 * 		转换完成的Base64格式的信息,返回值类型Base64字符串
	 */
	public static String base64Enc(byte[] msg) {
		// 创建Base64的编码器，将byte数组转为String
		return Base64.getEncoder().encodeToString(msg);
	}

	/**
	 * Base64字符串解码为二进制数据
	 * @param msg
	 * 		Base64格式的信息，参数类型Base64字符串
	 * @return
	 * 		转码完成的二进制数据，返回值类型byte[]
	 */
	public static byte[] base64Dec(String msg) {
		// 创建Base64的编码器，将byte数组转为String
		return Base64.getDecoder().decode(msg);
	}

	/**
	 * 将文件编码为BASE64字符串
	 * 大文件慎用，可能会导致内存溢出
	 * @param filePath 
	 * 		文件绝对路径
	 * @return
	 * 		BASE64字符串
	 * @throws Exception
	 */
	public static String encodeFile(String filePath) throws Exception {
		byte[] bytes = fileToByte(filePath);
		return base64Enc(bytes);
	}

	/**
	 * Base64字符串转回文件
	 * @param filePath 
	 * 		文件绝对路径
	 * @param base64 
	 * 		编码字符串
	 * @throws Exception
	 */
	public static void decodeToFile(String filePath, String base64) throws Exception {
		byte[] bytes = base64Dec(base64);
		byteArrayToFile(bytes, filePath);
	}

	/**
	 * 文件转换为二进制数组
	 * @param filePath 
	 * 		文件路径
	 * @return
	 * 		二进制数组
	 * @throws Exception
	 */
	public static byte[] fileToByte(String filePath) throws Exception {
		byte[] data = new byte[0];
		File file = new File(filePath);
		if (file.exists()) {
			FileInputStream in = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
			byte[] cache = new byte[CACHE_SIZE];
			int nRead = 0;
			while ((nRead = in.read(cache)) != -1) {
				out.write(cache, 0, nRead);
				out.flush();
			}
			out.close();
			in.close();
			data = out.toByteArray();
		}
		return data;
	}

	/**
	 * 二进制数据写文件
	 * @param bytes 
	 * 		二进制数据
	 * @param filePath 
	 * 		文件生成目录
	 */
	public static void byteArrayToFile(byte[] bytes, String filePath) throws Exception {
		InputStream in = new ByteArrayInputStream(bytes);   
		File destFile = new File(filePath);
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}
		destFile.createNewFile();
		OutputStream out = new FileOutputStream(destFile);
		byte[] cache = new byte[CACHE_SIZE];
		int nRead = 0;
		while ((nRead = in.read(cache)) != -1) {   
			out.write(cache, 0, nRead);
			out.flush();
		}
		out.close();
		in.close();
	}

}
