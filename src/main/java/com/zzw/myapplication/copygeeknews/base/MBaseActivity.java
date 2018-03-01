package com.zzw.myapplication.copygeeknews.base;

import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;

import com.zzw.myapplication.copygeeknews.app.MAPP;
import com.zzw.myapplication.copygeeknews.di.component.DaggerMActivityCompnent;
import com.zzw.myapplication.copygeeknews.di.component.MActivityCompnent;
import com.zzw.myapplication.copygeeknews.di.module.MActivityModule;
import com.zzw.myapplication.copygeeknews.util.MSnackbarUtil;

import javax.inject.Inject;

/**
 * Created by zqy on 2017/9/28.
 */

public abstract class MBaseActivity<T extends MBasePresenter> extends MSimpleActivity implements MBaseView {
	@Inject
	protected T presenter;

	protected MActivityCompnent getActivityCompnent() {
		return DaggerMActivityCompnent.builder()
				.mAppCompnent(((MAPP)getApplication()).getMappcompnent())
				.mActivityModule(getActivityModule())
				.build();
	}

	protected MActivityModule getActivityModule() {
		return new MActivityModule(this);
	}

	@Override
	protected void onViewCreated() {
		super.onViewCreated();
		initInject();
		if (presenter != null) {
			presenter.attachView(this);
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (presenter != null) {
			presenter.detachView();
		}
	}

	@Override
	public void showErrorMsg(String msg) {
		MSnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
	}

	@Override
	public void useNightMode(boolean isNight) {
		if (isNight) {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
		} else {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
		}
		recreate();//重新创建
	}

	/**
	 * 初始化注入
	 */
	protected abstract void initInject();

	@Override
	public void stateError() {

	}

	@Override
	public void stateEmpty() {

	}

	@Override
	public void stateLoading() {

	}

	@Override
	public void stateMain() {

	}
}
