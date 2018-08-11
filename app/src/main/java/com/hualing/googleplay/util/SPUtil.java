package com.hualing.googleplay.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 2018/8/1 DuckJing <p>
 * 封装了SharedPreferences，让其更易用
 */
public class SPUtil {
	/**
	 * 获取SP中的某个值
	 *
	 * @param key      唯一性的标识
	 * @param defValue 没有key时的默认值
	 * @return 返回这个key在sp存储的值（没有key则用defValue）
	 */
	public static int get(String key, int defValue) {
		SharedPreferences sp = JingUtil.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getInt(key, defValue);

	}

	/**
	 * 获取SP中的某个值
	 *
	 * @param key      唯一性的标识
	 * @param defValue 没有key时的默认值
	 * @return 返回这个key在sp存储的值（没有key则用defValue）
	 */
	public static boolean get(String key, boolean defValue) {
		SharedPreferences sp = JingUtil.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);

	}

	/**
	 * 获取SP中的某个值
	 *
	 * @param key      唯一性的标识
	 * @param defValue 没有key时的默认值
	 * @return 返回这个key在sp存储的值（没有key则用defValue）
	 */
	public static String get(String key, String defValue) {
		SharedPreferences sp = JingUtil.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getString(key, defValue);

	}


	/**
	 * 往sp中插入一个值
	 *
	 * @param key   唯一性的标识
	 * @param value 要存储的值
	 */
	public static void put(String key, String value) {
		SharedPreferences sp = JingUtil.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putString(key, value).apply();
	}

	/**
	 * 往sp中插入一个值
	 *
	 * @param key   唯一性的标识
	 * @param value 要存储的值
	 */
	public static void put(String key, int value) {
		SharedPreferences sp = JingUtil.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).apply();
	}

	/**
	 * 往sp中插入一个值
	 *
	 * @param key   唯一性的标识
	 * @param value 要存储的值
	 */
	public static void put(String key, boolean value) {
		SharedPreferences sp = JingUtil.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).apply();
	}

}
