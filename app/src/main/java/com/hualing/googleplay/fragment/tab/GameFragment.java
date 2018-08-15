package com.hualing.googleplay.fragment.tab;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.util.JingUtil;
import com.hualing.googleplay.view.BaseTabPager;

/**
 * 2018/8/12 DuckJing <p>
 *     游戏
 */
public class GameFragment extends BaseFragment {
	@Override
	public View onCreateSuccessView() {
		TextView tv = new TextView(getContext());
		tv.setTextSize(25);
		tv.setTextColor(Color.BLACK);
		tv.setText(getClass().getSimpleName());
		return tv;
	}

	@Override
	public void loadData(onPageCallback callback) {

		callback.onCall(BaseTabPager.ResultState.stateSuccess);
	}
}
