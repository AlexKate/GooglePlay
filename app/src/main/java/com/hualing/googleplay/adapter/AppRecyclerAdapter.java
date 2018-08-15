package com.hualing.googleplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hualing.googleplay.R;
import com.hualing.googleplay.json.AppJson;
import com.hualing.googleplay.json.HomeJson;
import com.hualing.googleplay.other.ConstantValue;
import com.hualing.googleplay.util.GlideUtil;
import com.hualing.googleplay.util.JingUtil;
import com.hualing.googleplay.util.UIUtils;

import java.util.List;

/**
 * 2018/8/13 DuckJing <p>
 * 软件的适配器
 */
public class AppRecyclerAdapter extends RecyclerView.Adapter<AppRecyclerAdapter.ViewHolder> {
//	private List<HomeJson> mData;


	private List<AppJson> mAppData;

	public AppRecyclerAdapter(List<AppJson> mAppData) {
		this.mAppData = mAppData;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = UIUtils.inflate(R.layout.item_app);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		AppJson info = mAppData.get(position);

		//		holder.tv.setText(mAppData.get(position).name);
		//设置数据
//		Glide.with(JingUtil.getContext()).load(ConstantValue.URL+"image?name="+info.iconUrl).into(holder.icon);
		GlideUtil.show(ConstantValue.URL+"image?name="+info.iconUrl,holder.icon);
		holder.name.setText(info.name);
		holder.size.setText(android.text.format.Formatter.formatFileSize(JingUtil.getContext(), info.size));
		holder.dec.setText(info.des);
		holder.start.setRating(info.stars);




	}

	@Override
	public int getItemCount() {


		return mAppData.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {


		private  ImageView icon;
		private  TextView name;
		private  TextView size;
		private  TextView dec;
		private  RatingBar start;

		public ViewHolder(@NonNull View view) {
			super(view);
			icon = view.findViewById(R.id.image_icon);
			name = view.findViewById(R.id.tv_name);
			size = view.findViewById(R.id.tv_app_size);
			dec = view.findViewById(R.id.tv_dec);
			start = view.findViewById(R.id.rb_start);

		}

	}
}
