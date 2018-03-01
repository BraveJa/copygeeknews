package com.zzw.myapplication.copygeeknews.widget;

import android.text.TextUtils;

import com.zzw.myapplication.copygeeknews.base.MBaseView;
import com.zzw.myapplication.copygeeknews.modle.http.exception.ApiException;
import com.zzw.myapplication.copygeeknews.util.MLogUtil;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by zqy on 2018/2/28.
 */

public class MCommonSubscriber<T> extends ResourceSubscriber<T> {
	private MBaseView mView;
	private String mErrorMsg;
	private boolean isShowErrorState = true;

	public MCommonSubscriber(MBaseView mView) {
		this.mView = mView;
	}

	public MCommonSubscriber(MBaseView mView, String mErrorMsg) {
		this.mView = mView;
		this.mErrorMsg = mErrorMsg;
	}

	public MCommonSubscriber(MBaseView mView, String mErrorMsg, boolean isShowErrorState) {
		this.mView = mView;
		this.mErrorMsg = mErrorMsg;
		this.isShowErrorState = isShowErrorState;
	}

	@Override
	public void onNext(T t) {

	}

	@Override
	public void onError(Throwable e) {
		if (mView == null) return;
		if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
			mView.showErrorMsg(mErrorMsg);
		} else if (e instanceof ApiException) {
			mView.showErrorMsg(e.toString());
		} else if (e instanceof HttpException) {
			mView.showErrorMsg("数据加载失败ヽ(≧Д≦)ノ");
		} else {
			mView.showErrorMsg("未知错误ヽ(≧Д≦)ノ");
			MLogUtil.d(e.toString());
		}
		if (isShowErrorState) {
			mView.stateError();
		}

	}

	@Override
	public void onComplete() {

	}
}
