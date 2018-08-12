package com.hualing.googleplay.util;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.hualing.googleplay.other.DuckApplication;


/**
 * 2018/8/1 DuckJing <p>
 *     所有工具类的头，里面有Context对象
 */
public class JingUtil {


	private static Context mContext;

//	private static Application

	/**
	 * 初始化的方法
	 * @param context 传ApplicationContext
	 */
	public static void init(Context context){
		mContext=context;
	}

	/**
	 * 获取Context对象
	 * @return
	 */
	public static Context getContext() {
		return mContext;
	}

	/**
	 * 获取一个Handler对象，用于回调主线程
	 * @return Application定义的Handler
	 */
	public static Handler getHandler(){
		return DuckApplication.getHandler();
	}
}
