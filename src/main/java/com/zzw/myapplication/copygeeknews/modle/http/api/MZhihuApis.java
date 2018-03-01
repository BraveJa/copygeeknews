package com.zzw.myapplication.copygeeknews.modle.http.api;

import com.zzw.myapplication.copygeeknews.modle.bean.WelcomeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zqy on 2017/10/13.
 */

public interface MZhihuApis {
	String HOST = "http://news-at.zhihu.com/api/4/";

	//启动界面图片
	@GET("start-image/{res}")
	Flowable<WelcomeBean> getWelcomeInfo(@Path("res") String res);
}
