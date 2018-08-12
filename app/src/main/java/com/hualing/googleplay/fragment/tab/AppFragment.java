package com.hualing.googleplay.fragment.tab;

import android.view.View;

import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.view.BaseTabPager;

/**
 * 2018/8/12 DuckJing <p>
 *     软件
 */
public class AppFragment extends BaseFragment {
	@Override
	public View onCreateSuccessView() {
		return null;
	}

	@Override
	public void loadData(onPageCallback callback) {

		callback.onCall(BaseTabPager.ResultState.stateEmpty);
	}
}
