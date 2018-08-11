package com.hualing.googleplay.util;

import android.widget.Toast;

/**
 * 2018/8/1 DuckJing <p>
 *     显示Toast的工具类
 */
public class ToastUtil {
	private static Toast mToast;

	/**
	 * 显示Toast（默认为短时间）
	 * @param msg 要被显示的信息
	 */
	public static void show(String msg){
		if (mToast==null){

			mToast=Toast.makeText(JingUtil.getContext(),msg,Toast.LENGTH_SHORT);
			mToast.show();
		}else {
			mToast.setText(msg);
			mToast.show();
		}

	}
}
