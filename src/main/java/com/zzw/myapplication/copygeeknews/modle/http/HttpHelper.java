package com.zzw.myapplication.copygeeknews.modle.http;

import com.zzw.myapplication.copygeeknews.modle.bean.VersionBean;
import com.zzw.myapplication.copygeeknews.modle.bean.WelcomeBean;
import com.zzw.myapplication.copygeeknews.modle.http.response.MyHttpResponse;

import io.reactivex.Flowable;

/**
 * zqy
 */

public interface HttpHelper {
	Flowable<WelcomeBean> fetchWelcomeInfo(String res);
	Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo();
}
