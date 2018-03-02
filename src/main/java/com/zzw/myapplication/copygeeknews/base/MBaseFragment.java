package com.zzw.myapplication.copygeeknews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zzw.myapplication.copygeeknews.app.MAPP;
import com.zzw.myapplication.copygeeknews.di.component.DaggerMFragmentComponent;
import com.zzw.myapplication.copygeeknews.di.component.MFragmentComponent;
import com.zzw.myapplication.copygeeknews.di.module.MFragmentModule;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/2.
 */

public abstract class MBaseFragment<T extends MBasePresenter> extends MSinpleFragment implements MBaseView {
	@Inject
	protected T mPresenter;

	protected MFragmentComponent getFragmentComponent() {
		return DaggerMFragmentComponent.builder()
				.mAppCompnent(MAPP.getMappcompnent()).build();
	}

	protected MFragmentModule getFragmentModule() {
		return new MFragmentModule(this);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		initInject();
	//	mPresenter.attachView(this);
		super.onViewCreated(view, savedInstanceState);
	}
	@Override
	public void onDestroyView() {
		if (mPresenter != null) mPresenter.detachView();
		super.onDestroyView();
	}
	@Override
	public void showErrorMsg(String msg) {

	}

	@Override
	public void useNightMode(boolean isNight) {

	}

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

	protected abstract void initInject();
}
