package com.zzw.myapplication.copygeeknews.di.module;

import android.app.Activity;

import com.zzw.myapplication.copygeeknews.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zqy on 2017/9/28.
 */
@Module
public class MActivityModule {
	private Activity mActivity;

	public MActivityModule(Activity mActivity) {
		this.mActivity = mActivity;
	}

	@Provides
	@ActivityScope
	public Activity providesMActivity() {
		return mActivity;
	}
}
