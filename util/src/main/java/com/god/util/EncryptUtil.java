package com.god.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 加密工具类
 */
public class EncryptUtil {

	// 加密类型不会改变，使用final修饰提高性能
	public static final String SHA1 = "SHA-1";

	public static final String SHA256 = "SHA-256";

	public static final String SHA512 = "SHA-512";
	// 加密的编码格式_不区分大小写
	public static final String ENCODING = "UTF-8";
	// 公钥
	public static final String PUBKEY = "publicKey"; 
	// 私钥
	public static final String PRIKEY = "privateKey";

	/* RSA最大加密明文大小 */
	private static final int MAX_ENCRYPT_BLOCK = 117;
	/* RSA最大解密密文大小 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * Hash算法
	 * 		MD5单向加密
	 * @param msg
	 * 		需要加密的信息
	 * @return
	 * 		十六进制的信息
	 */
	public static String md5Enc(String msg) {
		try {
			// 加密方式有MD1,MD2,MD3,MD4,MD5
			// 使用实例方式创建对象getInstance
			MessageDigest digest = MessageDigest.getInstance("MD5");
			// 设置编码格式，防止乱码
			digest.update(msg.getBytes(ENCODING));
			// 得到一个数组，转为16进制
			return new BigInteger(1, digest.digest()).toString(16);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Hash算法
	 * 		SHA单向加密
	 * @param type
	 * 		加密的类型-SHA-1 SHA-256 SHA-512
	 * @param msg
	 * 		需要加密的信息
	 * @return
	 * 		十六进制的信息
	 */
	public static String SHAEnc(String type, String msg) {
		try {
			// 加密方式有MD1,MD2,MD3,MD4,MD5
			// 使用实例方式创建对象getInstance
			MessageDigest digest = MessageDigest.getInstance(type);
			// 设置编码格式，防止乱码
			digest.update(msg.getBytes(ENCODING));
			// 得到一个数组，转为16进制
			return new BigInteger(1, digest.digest()).toString(16);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对称加密
	 * 		创建AES的密钥
	 * @return
	 * 		返回默认128位的密钥
	 */
	public static byte[] createAESKey() {
		try {
			// 创建密钥生成器
			KeyGenerator generator = KeyGenerator.getInstance("AES");
			// 初始化密钥,AES默认的128位
			generator.init(128);
			// 生成密钥
			SecretKey key = generator.generateKey();
			// 返回密钥
			return key.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对称加密
	 * 		AES加密
	 * @param key
	 * 		密钥
	 * @param msg
	 * 		需要加密的信息
	 * @return
	 * 		加密后的结果
	 */
	public static String AESEnc(byte[] key, String msg) {
		// 二级制数组转换密钥对
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

		try {
			Cipher cipher = Cipher.getInstance("AES");
			// 第一个参数表示加密解密，第二个参数密钥对
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			// 得到一个byte数组
			byte[] r = cipher.doFinal(msg.getBytes(ENCODING));
			// 返回Base64的格式
			return Base64Util.base64Enc(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对称加密
	 * 		AES解密
	 * @param key
	 * 		密钥
	 * @param msg
	 * 		需要解密的信息
	 * @return
	 * 		解密完成的信息
	 */
	public static String AESDec(byte[] key, String msg) {
		// 二级制数组转换密钥对
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		try {
			Cipher cipher = Cipher.getInstance("AES");
			// 第一个参数表示加密解密，第二个参数密钥对
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			// 需要解码的是Base64格式的，所以用Base64转换为原本形式
			byte[] r = cipher.doFinal(Base64Util.base64Dec(msg));
			// 返回解密后的的格式
			return new String(r, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 非对称加密
	 * 		创建RSA公钥和私钥
	 * @return Base64格式
	 * 		map集合,PUBKEY存储公钥，PRIKEY存储私钥
	 */
	public static Map<String, String> createRSAKey() {
		try {
			// 创建密钥生成器
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			// RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024
			generator.initialize(1024);
			// 生成密钥
			KeyPair keyPair = generator.generateKeyPair();
			// linkedHashMap保证添加顺序
			Map<String, String> map = new LinkedHashMap<String, String>();
			// 获取公钥
			map.put(PUBKEY, Base64Util.base64Enc(keyPair.getPublic().getEncoded()));
			// 获取私钥
			map.put(PRIKEY, Base64Util.base64Enc(keyPair.getPrivate().getEncoded()));
			// 返回密钥
			return map;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取RSA公钥
	 * @param key Base64格式
	 * 		通过创建密钥返回值map，获取公钥密钥信息
	 * 			map.get(EncryptUtil.PRIKEY)
	 * @return
	 * 		解密后的公钥
	 */
	public static PublicKey getPubKey(String key) {
		try {
			// 通过工厂模式，生成密钥
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			// Base64格式反解成对象
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Util.base64Dec(key));
			// 返回父类
			return (PublicKey)keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取RSA私钥
	 * @param key Base64格式
	 *		通过创建密钥返回值map，获取私钥密钥信息
	 * 			map.get(EncryptUtil.PRIKEY)
	 * @return
	 * 		解密后的私钥信息
	 */
	public static PrivateKey getPriKey(String key) {
		try {
			// 通过工厂模式，生成密钥
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			// Base64格式反解成对象
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Util.base64Dec(key));
			// 返回父类
			return (PrivateKey)keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 私钥加密，非对称加密
	 * @param key
	 * 		私钥密钥
	 * @param msg
	 * 		需要加密的信息
	 * @return Base64格式
	 * 		返回加密后的内容
	 */
	public static String RSAEnc(String key, String msg) {
		try {  
			Cipher cipher = Cipher.getInstance("RSA");
			// 第一个参数表示加密解密，第二个参数私钥加密
			cipher.init(Cipher.ENCRYPT_MODE, getPriKey(key));
			// 得到一个byte数组
			byte[] b = msg.getBytes(ENCODING);

			int inputLen = b.length;  
			ByteArrayOutputStream out = new ByteArrayOutputStream();  
			int offSet = 0;  
			byte[] cache;  
			int i = 0;  
			// 对数据分段解密  
			while (inputLen - offSet > 0) {  
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
					cache = cipher.doFinal(b, offSet, MAX_ENCRYPT_BLOCK);  
				} else {  
					cache = cipher.doFinal(b, offSet, inputLen - offSet);  
				}  
				out.write(cache, 0, cache.length);  
				i++;  
				offSet = i * MAX_ENCRYPT_BLOCK;  
			}  
			byte[] decryptedData = out.toByteArray();  
			out.close();  
			// 返回Base64格式
			return Base64Util.base64Enc(decryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 公钥解密，非对称加密
	 * @param key
	 * 		公钥密钥
	 * @param msg
	 * 		需要解密的信息，Base64格式
	 * @return Base64格式
	 * 		返回解密后的信息
	 */
	public static String RSADec(String key, String msg) {

		try {
			Cipher cipher = Cipher.getInstance("RSA");
			// 第一个参数表示加密解密，第二个参数公钥解密
			cipher.init(Cipher.DECRYPT_MODE, getPubKey(key));
			// 得到一个byte数组
			byte[] b = Base64Util.base64Dec(msg);

			int inputLen = b.length;  
			ByteArrayOutputStream out = new ByteArrayOutputStream();  
			int offSet = 0;  
			byte[] cache;  
			int i = 0;  
			// 对数据分段解密  
			while (inputLen - offSet > 0) {  
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
					cache = cipher.doFinal(b, offSet, MAX_DECRYPT_BLOCK);  
				} else {  
					cache = cipher.doFinal(b, offSet, inputLen - offSet);  
				}  
				out.write(cache, 0, cache.length);  
				i++;  
				offSet = i * MAX_DECRYPT_BLOCK;  
			}  
			byte[] decryptedData = out.toByteArray();  
			out.close();  
			// 返回原本格式
			return new String(decryptedData,ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
