package com.hualing.googleplay.fragment.tab;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hualing.googleplay.R;
import com.hualing.googleplay.adapter.AppRecyclerAdapter;
import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.json.AppJson;
import com.hualing.googleplay.other.ConstantValue;
import com.hualing.googleplay.util.CacheUtil;
import com.hualing.googleplay.util.JingUtil;
import com.hualing.googleplay.util.ToastUtil;
import com.hualing.googleplay.view.BaseTabPager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/8/12 DuckJing <p>
 * 软件（拷贝首页
 */
public class AppFragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
	private AppRecyclerAdapter mAdapter;
	private String             httpData;
	private List<AppJson> mRecyclerData = new ArrayList<>();
	private PullLoadMoreRecyclerView recycler;

	@Override
	public View onCreateSuccessView() {
		recycler = new PullLoadMoreRecyclerView(getContext());

		recycler.setLinearLayout();
		mAdapter = new AppRecyclerAdapter(mRecyclerData);

		recycler.setAdapter(mAdapter);
		recycler.setPullRefreshEnable(false);//禁用上拉加载更多
		recycler.setFooterViewText("正在加载中...");
		recycler.setFooterViewTextColor(R.color.black);
		recycler.setOnPullLoadMoreListener(this);//设置监听

		return recycler;
	}

	@Override
	public void loadData(final onPageCallback callback) {

		//		Gson gson = new Gson();


		if (!mRecyclerData.isEmpty()) {
			callback.onCall(BaseTabPager.ResultState.stateSuccess);
			return;
		}

		String cache = CacheUtil.getCache(ConstantValue.URL + "app?index=0");
		//没有缓存才加载数据
		if (cache != null) {
			httpData = cache;
			//解析数据
			Gson gson = new Gson();
			List<AppJson> data = gson.fromJson(httpData, new TypeToken<List<AppJson>>() {
			}.getType());
			//			HomeJson json = gson.fromJson(httpData, HomeJson.class);

			mRecyclerData.addAll(data);


			Log.i("DuckLing", "数据：" + httpData);
			//回调数据
			callback.onCall(BaseTabPager.ResultState.stateSuccess);

		} else {

			OkGo.<String>get(ConstantValue.URL + "app?index=0").execute(new StringCallback() {
				@Override
				public void onSuccess(Response<String> response) {


					Log.i("DuckLing", "onSuccess: 加载成功");

					httpData = response.body();
					//解析数据
					Gson gson = new Gson();
//					HomeJson json = gson.fromJson(httpData, HomeJson.class);
					List<AppJson> data = gson.fromJson(httpData, new TypeToken<List<AppJson>>() {
					}.getType());
					mRecyclerData.addAll(data);


					Log.i("DuckLing", "数据：" + httpData);
					//回调数据
					callback.onCall(BaseTabPager.ResultState.stateSuccess);
					CacheUtil.putCache(ConstantValue.URL + "app?index=0", httpData);


				}

				@Override
				public void onError(Response<String> response) {
					callback.onCall(BaseTabPager.ResultState.stateError);
					Log.i("DuckLing", "onError: " + response.message());
					Log.i("DuckLing", "onError: home加载失败");
				}

			});
		}

		//		callback.onCall(BaseTabPager.ResultState.stateEmpty);
	}

	@Override
	public void onRefresh() {

	}

	@Override
	public void onLoadMore() {

		String cache = CacheUtil.getCache(ConstantValue.URL + "app?index=" + mAdapter.getItemCount());
		//没有缓存才加载数据
		if (cache != null) {
			String moreData = cache;
			//解析数据
			Gson gson = new Gson();
			List<AppJson> data = gson.fromJson(moreData, new TypeToken<List<AppJson>>() {
			}.getType());
			//			HomeJson moreJson = gson.fromJson(moreData, HomeJson.class);

			mRecyclerData.addAll(data);
			mAdapter.notifyDataSetChanged();
			JingUtil.getHandler().post(new Runnable() {
				@Override
				public void run() {
					recycler.setPullLoadMoreCompleted();
				}
			});

		} else {
			OkGo.<String>get(ConstantValue.URL + "app?index=" + mAdapter.getItemCount()).execute(new StringCallback() {
				@Override
				public void onSuccess(Response<String> response) {
					Log.i("DuckLing", "onSuccess: 加载更多:" + mAdapter.getItemCount());
					String moreData = response.body();
					CacheUtil.putCache(ConstantValue.URL + "app?index=" + mAdapter.getItemCount(), moreData);
					//解析数据
					Gson gson = new Gson();
//					HomeJson moreJson = gson.fromJson(moreData, HomeJson.class);

					List<AppJson> data = gson.fromJson(moreData, new TypeToken<List<AppJson>>() {
					}.getType());

					if (!data.isEmpty()) {
						//不为空
						mRecyclerData.addAll(data);
					} else {
						//为空
						ToastUtil.show("没有更多数据了");

					}
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
					recycler.setPullLoadMoreCompleted();//刷新完成


				}

				@Override
				public void onError(Response<String> response) {
					recycler

							.setPullLoadMoreCompleted();
				}
			});
		}


	}
}
