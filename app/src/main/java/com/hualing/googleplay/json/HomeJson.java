package com.hualing.googleplay.json;

import java.util.ArrayList;

/**
 * 2018/8/13 DuckJing <p>
 * 首页的数据
 */
public class HomeJson {
	public ArrayList<HomeAppInfo> list;

	public class HomeAppInfo {
		/* "id": 1525490,
            "name": "有缘网",
            "packageName": "com.youyuan.yyhl",
            "iconUrl": "app/com.youyuan.yyhl/icon.jpg",
            "stars": 4,
            "size": 3876203,
            "downloadUrl": "app/com.youyuan.yyhl/com.youyuan.yyhl.apk",
            "des": "产品介绍：有缘是时下最受大众单身男女亲睐的婚恋交友软件。有缘网专注于通过轻松、"
            */
		public String id;
		public String name;
		public String packageName;
		public String iconUrl;
		public float stars;
		public long   size;
		public String downloadUrl;
		public String des;

	}
}
