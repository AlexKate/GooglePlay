package com.hualing.googleplay.fragment.tab;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.view.BaseTabPager;

/**
 * 2018/8/12 DuckJing <p>
 *     首页
 */
public class HomeFragment extends BaseFragment {
	@Override
	public View onCreateSuccessView() {
		TextView view = new TextView(getContext());
		view.setText("Fuck you ");
		view.setTextSize(30);
		view.setTextColor(Color.RED);
		return view;
	}

	@Override
	public void loadData(onPageCallback callback) {

		callback.onCall(BaseTabPager.ResultState.stateSuccess);
	}
}
