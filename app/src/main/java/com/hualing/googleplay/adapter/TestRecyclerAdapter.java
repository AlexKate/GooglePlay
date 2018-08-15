package com.hualing.googleplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hualing.googleplay.R;
import com.hualing.googleplay.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/8/13 DuckJing <p>
 */
public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.ViewHolder> {

	public TestRecyclerAdapter(List<String> mData) {
		this.mData = mData;
	}

	private final List<String> mData;


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = View.inflate(parent.getContext(), R.layout.item, null);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

		holder.tv.setText(mData.get(position));
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		private TextView tv;
		public ViewHolder(@NonNull View itemView) {
			super(itemView);

			tv=itemView.findViewById(R.id.tv_item);
		}
	}
}
