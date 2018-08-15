package com.hualing.googleplay.adapter;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.hualing.googleplay.util.JingUtil;
import com.hualing.googleplay.util.UIUtils;
import com.hualing.googleplay.view.fly.StellarMap;

import java.util.List;
import java.util.Random;

/**
 * 2018/8/15 DuckJing <p>
 */
public class RecommendAdapter implements StellarMap.Adapter {
	private List<String> mData;

	public RecommendAdapter(List<String> mData) {

		this.mData = mData;
	}

	@Override
	public int getGroupCount() {
		return 2;
	}

	@Override
	public int getCount(int group) {
		int count = mData.size() / getGroupCount();// 用总数除以组个数就是每组应该展示的孩子的个数
		if (group == getGroupCount() - 1) {// 由于上一行代码不一定整除, 最后一组,将余数补上
			count += mData.size() % getGroupCount();
		}

		return count;
	}

	@Override
	public View getView(int group, int position, View convertView) {
		if (group > 0) {// 如果发现不是第一组,需要更新position, 要加上之前几页的总数,才是当前组的准确位置
			position = position + getCount(group - 1) * group;
		}

		TextView view = new TextView(JingUtil.getContext());
		view.setText(mData.get(position));

		// 设置随机文字大小
		Random random = new Random();
		int size = 16 + random.nextInt(10);// 产生16-25的随机数
		view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);// 以sp为单位设置文字大小

		// 设置随机文字颜色
		int r = 30 + random.nextInt(210);// 产生30-239的随机颜色, 绕过0-29,
		// 240-255的值,避免颜色过暗或者过亮
		int g = 30 + random.nextInt(210);
		int b = 30 + random.nextInt(210);
		view.setTextColor(Color.rgb(r, g, b));

		return view;
	}

	@Override
	public int getNextGroupOnZoom(int group, boolean isZoomIn) {
		if (!isZoomIn) {
			// 下一组
			if (group < getGroupCount() - 1) {
				return ++group;
			} else {
				return 0;// 如果没有下一页了,就跳到第一组
			}
		} else {
			// 上一组
			if (group > 0) {
				return --group;
			} else {
				return getGroupCount() - 1;// 如果没有上一页了,就跳到最后一组
			}
		}
		
	}
}
