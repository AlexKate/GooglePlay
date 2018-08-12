package com.hualing.googleplay.fragment;

import com.hualing.googleplay.fragment.tab.AppFragment;
import com.hualing.googleplay.fragment.tab.CategoryFragment;
import com.hualing.googleplay.fragment.tab.GameFragment;
import com.hualing.googleplay.fragment.tab.HomeFragment;
import com.hualing.googleplay.fragment.tab.RecommendFragment;
import com.hualing.googleplay.fragment.tab.TopFragment;
import com.hualing.googleplay.fragment.tab.TopicFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * 2018/8/12 DuckJing <p>
 * 生成Fragment的工厂
 */
public class FragmentFactory {
	private static Map<Integer, BaseFragment> mFragment = new HashMap<>();

	public static BaseFragment createFragment(int position) {

		//先从Map中拿，没有再去New
		BaseFragment fragment = mFragment.get(position);
		if (fragment == null) {

			switch (position) {
				case 0:

					fragment = new HomeFragment();
					break;
				case 1:
					fragment = new AppFragment();
					break;
				case 2:
					fragment = new GameFragment();
					break;
				case 3:
					fragment = new TopicFragment();
					break;
				case 4:
					fragment = new RecommendFragment();
					break;
				case 5:
					fragment = new CategoryFragment();
					break;
				case 6:
					fragment = new TopFragment();
					break;


			}
			mFragment.put(position, fragment);
		}
		return fragment;
	}
}
