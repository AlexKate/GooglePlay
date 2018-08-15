package com.hualing.googleplay.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 2018/8/15 DuckJing <p>
 *     按照比例，包裹整个布局
 */
public class WarpLayout extends FrameLayout {
	public WarpLayout(@NonNull Context context) {
		this(context,null);
	}

	public WarpLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public WarpLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);


	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		double ratio = 2.43;
		int width=MeasureSpec.getSize(widthMeasureSpec);

		if (MeasureSpec.getMode(widthMeasureSpec)==MeasureSpec.EXACTLY&&MeasureSpec.getMode(heightMeasureSpec)!=MeasureSpec.EXACTLY){

			int imageWidth=width-getPaddingLeft()-getPaddingRight();
			int imageHeight= (int) (imageWidth/ratio+0.5f);
			int height=imageHeight+getPaddingTop()+getPaddingBottom();
			heightMeasureSpec=MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);

		}
		/*
		* 1.获取比例
		* 2.控件宽已经订好
		* 3.算出控件高
		* */
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}











