package com.hualing.googleplay.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hualing.googleplay.R;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.fragment.FragmentFactory;
import com.hualing.googleplay.util.UIUtils;

/**
 * 2018/8/12 DuckJing <p>
 * 给主界面ViewPager的Adapter
 */
public class TabPagerVpAdapter extends FragmentPagerAdapter {

	private String[] mData = UIUtils.getStringArray(R.array.tab_names);

	public TabPagerVpAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		BaseFragment fragment = FragmentFactory.createFragment(position);
		return fragment;
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return mData[position];
	}


	@Override
	public int getCount() {
		return mData.length;
	}
}
