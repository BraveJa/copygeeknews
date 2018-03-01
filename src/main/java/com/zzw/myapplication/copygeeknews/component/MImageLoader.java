package com.zzw.myapplication.copygeeknews.component;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by zqy on 2017/10/13.
 */

public class MImageLoader {

	public static void load(Context context, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
		//if (!MAPP.getAppComponent().preferencesHelper().getNoImageState()) {
			Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
		//}
	}
}
