package com.hualing.googleplay.fragment.tab;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.fragment.BaseFragment;
import com.hualing.googleplay.other.ConstantValue;
import com.hualing.googleplay.util.DrawableUtils;
import com.hualing.googleplay.util.ToastUtil;
import com.hualing.googleplay.util.UIUtils;
import com.hualing.googleplay.view.BaseTabPager;
import com.hualing.googleplay.view.FlowLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Random;

/**
 * 2018/8/12 DuckJing <p>
 * 排行
 */
public class TopFragment extends BaseFragment {
	private ArrayList<String> mData = new ArrayList<>();

	@Override
	public View onCreateSuccessView() {

		int padding = UIUtils.dip2px(10);
		// 为了使布局可以上下滑动,需要用ScrollView包装起来
		ScrollView scrollView = new ScrollView(getContext());
		// 设置ScrollView边距
		scrollView.setPadding(padding, padding, padding, padding);

		// 初始化自定义控件
		FlowLayout flow = new FlowLayout(getContext());
		// 水平间距
		//flow.setHorizontalSpacing(UIUtils.dip2px(6));
		// 竖直间距
		//flow.setVerticalSpacing(UIUtils.dip2px(10));

		// 根据接口返回的数据个数,动态添加TextView
		for ( String str : mData) {
			TextView view = new TextView(getContext());
			view.setText(str);
			view.setTextColor(Color.WHITE);
			view.setGravity(Gravity.CENTER);
			view.setPadding(padding, padding, padding, padding);
			view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

			// 设置随机文字颜色
			Random random = new Random();
			int r = 30 + random.nextInt(210);
			int g = 30 + random.nextInt(210);
			int b = 30 + random.nextInt(210);

			int color = 0xffcecece;// 按下后偏白的背景色

			// 根据默认颜色和按下颜色, 生成圆角矩形的状态选择器
			Drawable selector = DrawableUtils.getStateListDrawable(Color.rgb(r, g, b), color, UIUtils.dip2px(6));

			// 给TextView设置背景
			view.setBackgroundDrawable(selector);

			// 必须设置点击事件, TextView按下后颜色才会变化
			view.setOnClickListener(v -> ToastUtil.show("点击你麻痹:"+str));

			// 给自定义控件添加view对象
			flow.addView(view);
		}

		scrollView.addView(flow);

		return scrollView;
	}

	@Override
	public void loadData(onPageCallback callback) {
		OkGo.<String>get(ConstantValue.URL + "hot?index=0").execute(new StringCallback() {
			@Override
			public void onSuccess(Response<String> response) {
				String result = response.body();
				Log.i("DuckLing", "推荐数据：" + result);

				try {
					JSONArray ja = new JSONArray(result);
					for (int i = 0; i < ja.length(); i++) {
						String str = ja.getString(i);

						mData.add(str);

					}
					callback.onCall(BaseTabPager.ResultState.stateSuccess);

				} catch (Exception e) {

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
