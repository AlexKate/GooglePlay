package com.hualing.googleplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.hualing.googleplay.R;
import com.hualing.googleplay.json.TopicJson;
import com.hualing.googleplay.other.ConstantValue;
import com.hualing.googleplay.util.JingUtil;
import com.hualing.googleplay.util.UIUtils;

import java.util.List;

/**
 * 2018/8/15 DuckJing <p>
 *     专题的Adapter
 */
public class TopicRecyclerAdapter extends RecyclerView.Adapter<TopicRecyclerAdapter.ViewHolder> {

	private List<TopicJson> mData;

	public TopicRecyclerAdapter(List<TopicJson> mData) {

		this.mData = mData;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = UIUtils.inflate(R.layout.item_topic);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

		holder.title.setText(mData.get(position).des);
//		holder.image.setBackgroundDrawable();

		DrawableImageViewTarget target = new DrawableImageViewTarget(holder.image);

		Glide.with(JingUtil.getContext()).load(ConstantValue.URL+"image?name="+mData.get(position).url).into(target);
//		GlideUtil.show(ConstantValue.URL+"image?name="+mData.get(position).url,holder.image);
//		GlideUtil.show(ConstantValue.URL+mData.get(position).url,holder.image);
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder{
		 ImageView image;
		TextView title;

		public ViewHolder(@NonNull View v) {
			super(v);
			title=v.findViewById(R.id.tv_title);
			image=v.findViewById(R.id.image_item);
		}
	}
}
