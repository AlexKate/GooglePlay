package com.hualing.googleplay.other;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * 2018/8/11 DuckJing <p>
 *     Activity生命周期的回调
 */
public class DuckActivityLifeCallback implements Application.ActivityLifecycleCallbacks {

	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

	}

	@Override
	public void onActivityStarted(Activity activity) {

	}

	@Override
	public void onActivityResumed(Activity activity) {

		Log.i("DuckLing", "当前获取焦点的Activity："+activity.getClass().getSimpleName());
	}

	@Override
	public void onActivityPaused(Activity activity) {


	}

	@Override
	public void onActivityStopped(Activity activity) {

	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

	}

	@Override
	public void onActivityDestroyed(Activity activity) {

	}
}
