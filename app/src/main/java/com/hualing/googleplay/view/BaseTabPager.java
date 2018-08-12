package com.hualing.googleplay.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.hualing.googleplay.R;
import com.hualing.googleplay.callback.onPageCallback;
import com.hualing.googleplay.util.UIUtils;

/**
 * 2018/8/12 DuckJing <p>
 * 封装，把各个标签的共同点抽在一起
 */
public abstract class BaseTabPager extends FrameLayout {
	/**
	 * 未加载
	 */
	private static final int STATE_UNDO    = 1;
	/**
	 * 加载中
	 */
	private static final int STATE_LOADING = 2;
	/**
	 * 加载失败
	 */
	private static final int STATE_ERROR   = 3;
	/**
	 * 数据为空
	 */
	private static final int STATE_EMPTY   = 4;
	/**
	 * 加载成功
	 */
	private static final int STATE_OK      = 5;
	private              int mCurrentState = STATE_UNDO;
	private View mLoadingView;
	private View mErrorView;
	private View mEmptyView;
	private View mSuccessView;

	public BaseTabPager(@NonNull Context context) {
		this(context, null);
	}

	public BaseTabPager(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public BaseTabPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	/**
	 * 初始化布局
	 */
	private void initView() {

		if (mLoadingView == null) {
			mLoadingView = UIUtils.inflate(R.layout.page_loading);
			addView(mLoadingView);
		}
		if (mErrorView == null) {

			mErrorView = UIUtils.inflate(R.layout.page_error);
			addView(mErrorView);
		}
		if (mEmptyView == null) {
			mEmptyView = UIUtils.inflate(R.layout.page_empty);
			addView(mEmptyView);
		}
		//更新状态
		showRightView();
	}

	/**
	 * 显示当前状态的View
	 */
	private void showRightView() {
		//三目，高级
		mLoadingView.setVisibility(mCurrentState == STATE_LOADING | mCurrentState == STATE_UNDO ? View.VISIBLE : View.GONE);
		mErrorView.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE : View.GONE);
		mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE : View.GONE);

		if (mCurrentState == STATE_OK && mSuccessView == null) {
			mSuccessView = onCreateSuccessView();
			if (mSuccessView != null) {
				addView(mSuccessView);
			}
		}

		if (mSuccessView != null) {
			mSuccessView.setVisibility(mCurrentState == STATE_OK ? View.VISIBLE : View.GONE);
		}
	}

	/**
	 * 加载数据
	 */
	public void loadData() {

		if (mCurrentState != STATE_LOADING) {
			mCurrentState=STATE_LOADING;
			//加载
			 onLoad(new onPageCallback() {
				@Override
				public void onCall(ResultState state) {

					//回调，加载数据完成，更改布局
					mCurrentState=state.getState();
					showRightView();

				}
			});
		}
	}

	/**
	 * 初始化成功的布局
	 *
	 * @return 加载成功的View
	 */
	public abstract View onCreateSuccessView();

	public abstract void onLoad(onPageCallback callback);

	public enum ResultState {

		stateSuccess(STATE_OK), stateEmpty(STATE_EMPTY), stateError(STATE_ERROR);

		int getState() {
			return state;
		}

		private int state;

		ResultState(int state) {

			this.state = state;
		}

	}
}
