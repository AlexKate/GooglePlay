package com.hualing.googleplay.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

/**
 * 2018/7/12 DuckJing <p>
 *     Glide的稍微封装
 */
public class GlideUtil {
	/**
	 * 把一个drawable显示到ImageView上
	 * @param id 要被显示的图片资源路径
	 * @param image ImageView对象
	 */
	public  static void show(String id, ImageView image){

		RequestOptions options = new RequestOptions()
				.override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);

		Glide.with(JingUtil.getContext()).load(id).apply(options).into(image);
	}
}
