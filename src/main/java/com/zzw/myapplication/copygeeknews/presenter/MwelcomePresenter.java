package com.zzw.myapplication.copygeeknews.presenter;

import com.zzw.myapplication.copygeeknews.base.MRxPresenter;
import com.zzw.myapplication.copygeeknews.base.contract.main.MWelcomeContract;
import com.zzw.myapplication.copygeeknews.modle.bean.WelcomeBean;
import com.zzw.myapplication.copygeeknews.modle.prefs.DataManager;
import com.zzw.myapplication.copygeeknews.util.MRxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by zqy on 2017/10/12.
 */

public class MwelcomePresenter extends MRxPresenter<MWelcomeContract.View> implements MWelcomeContract.Presenter {
	private static final String RES = "1080*1776";
	private static final int COUNT_DOWN_TIME = 2200;//倒计时
	private DataManager mDataManager;

	@Inject  //构造上注解,自己会拿到这个对象
	public MwelcomePresenter(DataManager mDataManager) {
		this.mDataManager = mDataManager;
	}

	@Override
	public void getWelcomeData() {
		addSubscribe(mDataManager.fetchWelcomeInfo(RES)
				/*.compose(new FlowableTransformer<WelcomeBean, Object>() {
					@Override
					public Publisher<Object> apply(@NonNull Flowable<WelcomeBean> upstream) {
						return null;
					}
				})*/
				.compose(MRxUtil.<WelcomeBean>rxSchedulerHelper())//抽取了并设置主线程和子线的切换
				.subscribe(new Consumer<WelcomeBean>() {
					@Override
					public void accept(WelcomeBean welcomeBean) {
						mView.showContent(welcomeBean);
						startCountDown();
					}
					/**
					 * OnErrorNotImplementedException
					 */
				}, new Consumer<Throwable>() {//不懂为什么这里一定要把Throwable写出来
					@Override
					public void accept(Throwable throwable) throws Exception {
						mView.jumpToMain();
					}
				}));
	}

	private void startCountDown() {
		addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
				.compose(MRxUtil.<Long>rxSchedulerHelper())
				.subscribe(new Consumer<Long>() {
					@Override
					public void accept(Long aLong) throws Exception {
						mView.jumpToMain();
					}
				}));
	}
}
