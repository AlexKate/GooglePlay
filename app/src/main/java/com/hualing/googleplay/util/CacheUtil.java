package com.hualing.googleplay.util;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 2018/8/14 DuckJing <p>
 * 缓存工具类 有效期十分钟
 */
public class CacheUtil {
	/**
	 * 写入缓存 十分钟有效期
	 *
	 * @param key 文件名 要经过Base64
	 * @param data 要写人的数据
	 */
	public static void putCache(String key, String data) {

		File cacheDir = JingUtil.getContext().getCacheDir();
//		String encodeKey = Base64.encodeToString(key.getBytes(), Base64.NO_WRAP);
//		Log.i("DuckLing", "putCache: encodeKey:"+encodeKey);
		File cacheFile = new File(cacheDir, getStringMD5(key));
		try {


			BufferedWriter writer = new BufferedWriter(new FileWriter(cacheFile));
			writer.write((System.currentTimeMillis() + 10 * 60 * 1000) + "\n");
			writer.write(data);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 读缓存
	 * @param key 文件名
	 * @return 缓存文件的数据
	 */
	public static String getCache(String key){

		File cacheDir = JingUtil.getContext().getCacheDir();
//		String encodeKey = Base64.encodeToString(key.getBytes(), Base64.NO_WRAP);
		File cacheFile = new File(cacheDir, getStringMD5(key));
		try {
			BufferedReader reader=new BufferedReader(new FileReader(cacheFile));
			String dieDate = reader.readLine();
			if (System.currentTimeMillis()<Long.parseLong(dieDate)){
				Log.i("DuckLing", "getCache: 缓存有效");

				//缓存有效
				String str;
				StringBuffer sb=new StringBuffer();
				while ((str=reader.readLine())!=null){
					sb.append(str);
				}
				return sb.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 获取一个字符串的Md5值（每个字符串都是唯一的）
	 *
	 * @param string 要被加密的内容
	 * @return 加密好的MD5字符串
	 */
	public static String getStringMD5(String string) {
		if (TextUtils.isEmpty(string)) {
			return "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(string.getBytes());
			String result = "";
			for (byte b : bytes) {
				String temp = Integer.toHexString(b & 0xff);
				if (temp.length() == 1) {
					temp = "0" + temp;
				}
				result += temp;
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}




















