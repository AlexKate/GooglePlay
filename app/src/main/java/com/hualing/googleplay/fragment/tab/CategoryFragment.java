package com.hualing.googleplay.fragment.tab;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hualing.googleplay.R;
import com.hualing.googleplay.adapter.CategoryRecyclerAdapter;
import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.json.CategoryJSon;
import com.hualing.googleplay.other.ConstantValue;
import com.hualing.googleplay.util.UIUtils;
import com.hualing.googleplay.view.BaseTabPager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/8/12 DuckJing <p>
 *     分类
 */
public class CategoryFragment extends BaseFragment {

	private RecyclerView recycler;
	private ArrayList<CategoryJSon> mData;


	@Override
	public View onCreateSuccessView() {

		View view = UIUtils.inflate(R.layout.fragment_category);
		recycler = view.findViewById(R.id.recycler_category);

		CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(mData);
		return view;
	}

	@Override
	public void loadData(onPageCallback callback) {
		OkGo.<String>get(ConstantValue.URL+"category").execute(new StringCallback() {
			@Override
			public void onSuccess(Response<String> response) {
				String httpData = response.body();

				Log.i("DuckLing", "分类数据："+httpData);
				Gson gson = new Gson();
				mData = gson.fromJson(httpData, new TypeToken<List<CategoryJSon>>() {
				}.getType());
				callback.onCall(BaseTabPager.ResultState.stateSuccess);
			}

			@Override
			public void onError(Response<String> response) {

				callback.onCall(BaseTabPager.ResultState.stateError);
			}
		});

	}
}
