package com.hualing.googleplay.callback;

import com.hualing.googleplay.view.BaseTabPager;

/**
 * 2018/8/12 DuckJing <p>
 *     帧布局调用子类加载数据的回调
 */
public interface onPageCallback {
	void onCall(BaseTabPager.ResultState state);

}
