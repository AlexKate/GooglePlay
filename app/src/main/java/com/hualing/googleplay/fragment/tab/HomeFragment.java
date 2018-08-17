package com.hualing.googleplay.fragment.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.hualing.googleplay.R;
import com.hualing.googleplay.adapter.HomeRecyclerAdapter;
import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.json.HomeJson;
import com.hualing.googleplay.other.ConstantValue;
import com.hualing.googleplay.util.CacheUtil;
import com.hualing.googleplay.util.JingUtil;
import com.hualing.googleplay.util.ToastUtil;
import com.hualing.googleplay.util.UIUtils;
import com.hualing.googleplay.view.BaseTabPager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/8/12 DuckJing <p>
 * 首页
 */
public class HomeFragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

	private PullLoadMoreRecyclerView mRecyclerHome;
	private HomeRecyclerAdapter      mAdapter;
	private String httpData;//网络加载的数据
	private List<HomeJson.HomeAppInfo> mRecyclerData=new ArrayList<>();


	@Override
	public View onCreateSuccessView() {
		View view = UIUtils.inflate(R.layout.fragment_home);

		mRecyclerHome = view.findViewById(R.id.recycler_home);


	/*	TextView view = new TextView(getContext());
		view.setText("Fuck you ");
		view.setTextSize(30);
		view.setTextColor(Color.RED);*/
		initRecycler();
		return view;
	}



	/**
	 * 初始化滑动控件
	 */
	private void initRecycler() {

		mRecyclerHome.setLinearLayout();
		mAdapter = new HomeRecyclerAdapter(mRecyclerData);

		mRecyclerHome.setAdapter(mAdapter);
		mRecyclerHome.setPullRefreshEnable(false);//禁用上拉加载更多
		mRecyclerHome.setFooterViewText("正在加载中...");
		mRecyclerHome.setFooterViewTextColor(R.color.black);
		mRecyclerHome.setOnPullLoadMoreListener(this);//设置监听

	}

	//第一次加载数据，父类调用。要用callback回调，告知父类结果
	@Override
	public void loadData(final onPageCallback callback) {
		if (!mRecyclerData.isEmpty()){
			callback.onCall(BaseTabPager.ResultState.stateSuccess);
			return;
		}

		String cache = CacheUtil.getCache(ConstantValue.URL + "home?index=0");
		//没有缓存才加载数据
		if (cache!=null){
			httpData = cache;
			//解析数据
			Gson gson = new Gson();
			Log.i("DuckLing", "loadData: cache:"+cache);
			HomeJson json = gson.fromJson(cache, HomeJson.class);

			mRecyclerData.addAll(json.list);


			Log.i("DuckLing", "数据：" + httpData);
			//回调数据
			callback.onCall(BaseTabPager.ResultState.stateSuccess);

		}else {

			OkGo.<String>get(ConstantValue.URL + "home?index=0").execute(new StringCallback() {
				@Override
				public void onSuccess(Response<String> response) {


					Log.i("DuckLing", "onSuccess: 加载成功");

					httpData = response.body();
					//解析数据
					Gson gson = new Gson();
					HomeJson json = gson.fromJson(httpData, HomeJson.class);

					mRecyclerData.addAll(json.list);


					Log.i("DuckLing", "数据：" + httpData);
					//回调数据
					callback.onCall(BaseTabPager.ResultState.stateSuccess);
					CacheUtil.putCache(ConstantValue.URL + "home?index=0",httpData);


				}

				@Override
				public void onError(Response<String> response) {
					callback.onCall(BaseTabPager.ResultState.stateError);
					Log.i("DuckLing", "onError: " + response.message());
					Log.i("DuckLing", "onError: home加载失败");
				}

			});
		}

	}

	@Override
	public void onRefresh() {

	}

	/**
	 * 加载更多的回调
	 */
	@Override
	public void onLoadMore() {

//TODO:加载数据不到20条，应该回调？
//		Log.i("DuckLing", "onLoadMore: ");
		String cache = CacheUtil.getCache(ConstantValue.URL + "home?index=" + mAdapter.getItemCount());
		//没有缓存才加载数据
		if (cache!=null){
			String moreData = cache;
			//解析数据
			Gson gson = new Gson();
			HomeJson moreJson = gson.fromJson(moreData, HomeJson.class);

			mRecyclerData.addAll(moreJson.list);
			mAdapter.notifyDataSetChanged();
			JingUtil.getHandler().post(new Runnable() {
				@Override
				public void run() {
					mRecyclerHome.setPullLoadMoreCompleted();
				}
			});

		}else {
			OkGo.<String>get(ConstantValue.URL + "home?index=" + mAdapter.getItemCount()).execute(new StringCallback() {
				@Override
				public void onSuccess(Response<String> response) {
					Log.i("DuckLing", "onSuccess: 加载更多:" + mAdapter.getItemCount());
					String moreData = response.body();
					CacheUtil.putCache(ConstantValue.URL + "home?index=" + mAdapter.getItemCount(),moreData);
					//解析数据
					Gson gson = new Gson();
					HomeJson moreJson = gson.fromJson(moreData, HomeJson.class);

					mRecyclerData.addAll(moreJson.list);
					mAdapter.notifyDataSetChanged();

/*
				new Thread(new Runnable() {
					@Override
					public void run() {
						SystemClock.sleep(200);
						JingUtil.getHandler().post(new Runnable() {
							@Override
							public void run() {
								mRecyclerHome.setPullLoadMoreCompleted();//刷新完成

							}
						});
					}
				}).start();
*/
					mRecyclerHome.setPullLoadMoreCompleted();//刷新完成


				}

				@Override
				public void onError(Response<String> response) {
					mRecyclerHome.setPullLoadMoreCompleted();
				}
			});
		}
	}
}
