package com.hualing.googleplay.fragment.tab;

import android.view.View;

import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.view.BaseTabPager;

/**
 * 2018/8/12 DuckJing <p>
 *     游戏
 */
public class GameFragment extends BaseFragment {
	@Override
	public View onCreateSuccessView() {
		return null;
	}

	@Override
	public void loadData(onPageCallback callback) {

		callback.onCall(BaseTabPager.ResultState.stateError);
	}
}
