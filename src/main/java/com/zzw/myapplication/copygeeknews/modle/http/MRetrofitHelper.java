package com.zzw.myapplication.copygeeknews.modle.http;

import com.zzw.myapplication.copygeeknews.modle.bean.VersionBean;
import com.zzw.myapplication.copygeeknews.modle.bean.WelcomeBean;
import com.zzw.myapplication.copygeeknews.modle.http.api.MZhihuApis;
import com.zzw.myapplication.copygeeknews.modle.http.api.MyApis;
import com.zzw.myapplication.copygeeknews.modle.http.response.MyHttpResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by zqy on 2017/10/12.
 *
 */

public class MRetrofitHelper implements HttpHelper {
	private MZhihuApis mZhihuApis;
	private MyApis mMyApiService;
	@Inject
	public MRetrofitHelper(MZhihuApis mZhihuApis,MyApis myApiService) {
		this.mZhihuApis =mZhihuApis;
		this.mMyApiService = myApiService;
	}

	@Override
	public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
		return mZhihuApis.getWelcomeInfo(res);
	}

	@Override
	public Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
		return mMyApiService.getVersionInfo();
	}
}
