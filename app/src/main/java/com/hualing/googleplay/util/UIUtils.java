package com.hualing.googleplay.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * 2018/8/12 DuckJing <p>
 *     一些乱七八糟的方法
 */
public class UIUtils {
	/**
	 * dp转px
	 */
	public static int dip2px(float dp) {
		float density = JingUtil.getContext().getResources().getDisplayMetrics().density;
		return (int) (density * dp + 0.5);
	}

	/**
	 * px转dp
	 */
	public static float px2dip(float px) {
		float density = JingUtil.getContext().getResources().getDisplayMetrics().density;
		return px / density;
	}

	public static Drawable getDrawable(int id) {
		return JingUtil.getContext().getResources().getDrawable(id);

	}
	/**
	 * 获取颜色状态集合
	 */
	public static ColorStateList getColorStateList(int id) {
		return JingUtil.getContext().getResources().getColorStateList(id);
	}
	/**
	 * 根据id获取字符串数组
	 */
	public static String[] getStringArray(int id) {
		return JingUtil.getContext().getResources().getStringArray(id);
	}
	public static View inflate(int id){
		return View.inflate(JingUtil.getContext(),id,null);
	}
}
