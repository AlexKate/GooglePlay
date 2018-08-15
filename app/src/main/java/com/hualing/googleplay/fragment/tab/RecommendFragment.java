package com.hualing.googleplay.fragment.tab;

import android.util.Log;
import android.view.View;

import com.hualing.googleplay.adapter.RecommendAdapter;
import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.other.ConstantValue;
import com.hualing.googleplay.util.UIUtils;
import com.hualing.googleplay.view.BaseTabPager;
import com.hualing.googleplay.view.fly.StellarMap;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 2018/8/12 DuckJing <p>
 *     推荐
 */
public class RecommendFragment extends BaseFragment {
	List<String> mData=new ArrayList<>();
	@Override
	public View onCreateSuccessView() {
		StellarMap stellar = new StellarMap(getContext());

		int padding= UIUtils.dip2px(10);
		stellar.setInnerPadding(padding,padding,padding,padding);

		RecommendAdapter adapter = new RecommendAdapter(mData);
		stellar.setAdapter(adapter);
		stellar.setRegularity(7,5);//行和列数
		stellar.setGroup(0,true);//设置默认第零组 带动画

		return stellar;
	}






	@Override
	public void loadData(onPageCallback callback) {
		OkGo.<String>get(ConstantValue.URL+"recommend?index=0").execute(new StringCallback() {
			@Override
			public void onSuccess(Response<String> response) {

				String result = response.body();
				Log.i("DuckLing", "推荐数据："+result);

				try {
					JSONArray ja=new JSONArray(result);
					for (int i=0;i<ja.length();i++){
						String str = ja.getString(i);

						mData.add(str);

					}
					callback.onCall(BaseTabPager.ResultState.stateSuccess);
				} catch (JSONException e) {
					e.printStackTrace();

				}

			}

			@Override
			public void onError(Response<String> response) {

				callback.onCall(BaseTabPager.ResultState.stateError);
			}
		});


	}
}
