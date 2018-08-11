package com.hualing.googleplay.other;

import android.app.Application;

import com.hualing.googleplay.util.JingUtil;

/**
 * 2018/8/11 DuckJing <p>
 *     自定义Application 用于做一些操作
 */
public class DuckApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		//注册Activity生命周期的回调
		registerActivityLifecycleCallbacks(new DuckActivityLifeCallback());
		JingUtil.init(getApplicationContext());
	}
}
