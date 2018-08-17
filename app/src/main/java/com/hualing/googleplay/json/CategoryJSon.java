package com.hualing.googleplay.json;

import java.util.ArrayList;

/**
 * 2018/8/17 DuckJing <p>
 *  分类的Json数据
 */
public class CategoryJSon {
	public ArrayList<NameInfo> infos;
	public String title;
	public class NameInfo{

		public String name1;
		public String name2;
		public String name3;

		public String url1;
		public String url2;
		public String url3;
	}
}
