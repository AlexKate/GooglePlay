package com.hualing.googleplay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hualing.googleplay.R;
import com.hualing.googleplay.adapter.TabPagerVpAdapter;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.fragment.FragmentFactory;
import com.hualing.googleplay.util.JingUtil;
import com.hualing.googleplay.util.ToastUtil;
import com.hualing.googleplay.view.PagerTab;


/**
 * 主界面的Activity
 */
public class MainActivity extends BaseActivity {

	private PagerTab pagerTab;
	private ViewPager vp;
//	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		bt=findViewById(R.id.bt);
//		bt.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(JingUtil.getContext(),TestActivity.class));
//			}
//		});


		pagerTab = findViewById(R.id.pager_tab);
		vp = findViewById(R.id.vp);
		vp.setAdapter(new TabPagerVpAdapter(getSupportFragmentManager()));
		pagerTab.setViewPager(vp);
		pagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {

				Log.i("DuckLing", "onPageSelected: "+position);
				BaseFragment fragment = FragmentFactory.createFragment(position);
				fragment.loadData();
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});


		//		throw new OutOfMemoryError("你个傻逼用户，你没内存了");

		//ToastUtil.show("Fuck");

	}
}
