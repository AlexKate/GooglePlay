package com.hualing.googleplay.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.hualing.googleplay.R;
import com.hualing.googleplay.adapter.TestRecyclerAdapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

	private PullLoadMoreRecyclerView mRecycler;
	private List<String>             mData;
	private TestRecyclerAdapter      mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		initData();
		mRecycler = findViewById(R.id.recycler);

		mRecycler.setLinearLayout();
		mAdapter = new TestRecyclerAdapter(mData);
		mRecycler.setAdapter(mAdapter);



		mRecycler.setPullRefreshEnable(false);
		mRecycler.setFooterViewText("正在边日你老婆边加载中...");
//		mRecycler.setFooterViewTextColor(Color.BLACK);
		mRecycler.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
			@Override
			public void onRefresh() {

			}

			@Override
			public void onLoadMore() {

				mData.add("加载更多了：");

				new Thread(new Runnable() {
					@Override
					public void run() {
						SystemClock.sleep(3000);
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								mAdapter.notifyDataSetChanged();
								mRecycler.setPullLoadMoreCompleted();

							}
						});
					}
				}).start();

			}
		});



	}

	private void initData() {
		mData = new ArrayList<>();
		for (int i=0;i<30;i++){
			mData.add("Fuck:"+i);
		}
	}
}
