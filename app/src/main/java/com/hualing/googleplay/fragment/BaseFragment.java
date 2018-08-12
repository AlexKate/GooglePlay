package com.hualing.googleplay.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.view.BaseTabPager;

/**
 * 2018/8/12 DuckJing <p>
 *     Fragment的基类
 */
public abstract class BaseFragment extends Fragment {

	private BaseTabPager tabPager;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		/*TextView view = new TextView(getContext());
		view.setText("Fuck you ");*/
		tabPager = new BaseTabPager(getContext()){
			@Override
			public View onCreateSuccessView() {
				return BaseFragment.this.onCreateSuccessView();
			}

			@Override
			public void onLoad(onPageCallback callback) {

				BaseFragment.this.loadData(callback);
			}
		};

		return tabPager;
	}
	public abstract View onCreateSuccessView();
	public abstract void loadData(onPageCallback callback);
	public void loadData(){

		if (tabPager!=null){
			tabPager.loadData();
		}
	}
}
