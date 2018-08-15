package com.hualing.googleplay.fragment.tab;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hualing.googleplay.R;
import com.hualing.googleplay.adapter.TopicRecyclerAdapter;
import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.json.TopicJson;
import com.hualing.googleplay.other.ConstantValue;
import com.hualing.googleplay.util.JingUtil;
import com.hualing.googleplay.util.ToastUtil;
import com.hualing.googleplay.util.callback.onLoadMoreListener;
import com.hualing.googleplay.view.BaseTabPager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/8/12 DuckJing <p>
 * 专题
 */
public class TopicFragment extends BaseFragment {
	private List<TopicJson> mData = new ArrayList<>();
	private TopicRecyclerAdapter mAdapter;
	private PullLoadMoreRecyclerView recycler;

	@Override
	public View onCreateSuccessView() {

		recycler = new PullLoadMoreRecyclerView(getContext());

		recycler.setLinearLayout();
		mAdapter = new TopicRecyclerAdapter(mData);

		recycler.setAdapter(mAdapter);
		recycler.setPullRefreshEnable(false);//禁用上拉加载更多
		recycler.setFooterViewText("正在加载中...");
		recycler.setFooterViewTextColor(R.color.black);
		recycler.setOnPullLoadMoreListener(new onLoadMoreListener() {
			@Override
			public void onLoadMore() {
				OkGo.<String>get(ConstantValue.URL + "subject?index=" + mAdapter.getItemCount()).execute(new StringCallback() {

					@Override
					public void onSuccess(Response<String> response) {
						String result = response.body();

						List<TopicJson> jsonData = new ArrayList<>();
						try {
							JSONArray ja = new JSONArray(result);
							for (int i = 0; i < ja.length(); i++) {
								TopicJson json = new TopicJson();
								JSONObject jo = ja.getJSONObject(i);
								json.url = jo.getString("url");
								json.des = jo.getString("des");

								jsonData.add(json);
							}
							if (jsonData.isEmpty()) {
								ToastUtil.show("没有更多数据了");
								JingUtil.getHandler().post(() -> recycler.setPullLoadMoreCompleted());
							} else {
								mData.addAll(jsonData);
								JingUtil.getHandler().post(() -> {
									mAdapter.notifyDataSetChanged();
									recycler.setPullLoadMoreCompleted();
								});
							}


						} catch (JSONException e) {
							e.printStackTrace();
						}
						/*Gson gson = new Gson();
						List<TopicJson> data = gson.fromJson(result, new TypeToken<List<TopicJson>>() {
						}.getType());*/
					}
				});
				/*if (data.isEmpty()) {
					ToastUtil.show("没有更多数据了");
				} else {
					callback.onCall(BaseTabPager.ResultState.stateSuccess);
				}*/

			}
		});//设置监听

		return recycler;
	}

	@Override
	public void loadData(final onPageCallback callback) {

		OkGo.<String>get(ConstantValue.URL + "subject?index=0").execute(new StringCallback() {
			@Override
			public void onSuccess(Response<String> response) {


				String result = response.body();
				Log.i("DuckLing", "专题加载成功 数据：" + result);

				/*JSONArray ja = null;
				List<TopicJson> data = new ArrayList<>();
				try {
					ja = new JSONArray(result);
					for (int i = 0; i < ja.length(); i++) {
						TopicJson json = new TopicJson();
						JSONObject jo = ja.getJSONObject(i);
						json.url = jo.getString("url");
						json.des = jo.getString("des");

						data.add(json);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}*/

				Gson gson = new Gson();
				List<TopicJson> data = gson.fromJson(result, new TypeToken<ArrayList<TopicJson>>() {
				}.getType());
				mData.addAll(data);
				callback.onCall(BaseTabPager.ResultState.stateSuccess);

			}

			@Override
			public void onError(Response<String> response) {

				Log.i("DuckLing", "专题加载失败");
				callback.onCall(BaseTabPager.ResultState.stateError);
			}
		});
	}
}
