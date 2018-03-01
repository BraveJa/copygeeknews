package com.zzw.myapplication.copygeeknews.di.module;

import com.zzw.myapplication.copygeeknews.app.MAPP;
import com.zzw.myapplication.copygeeknews.modle.http.HttpHelper;
import com.zzw.myapplication.copygeeknews.modle.http.MRetrofitHelper;
import com.zzw.myapplication.copygeeknews.modle.prefs.DataManager;
import com.zzw.myapplication.copygeeknews.modle.prefs.ImplPreferencesHelper;
import com.zzw.myapplication.copygeeknews.modle.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zqy on 2017/9/28.
 */
@Module
public class MAppModule {
	private final MAPP mApplication;

	public MAppModule(MAPP mApplication) {
		this.mApplication = mApplication;
	}

	@Singleton //单例
	@Provides
	MAPP providesMapp() {
		return mApplication;
	}

	@Provides
	@Singleton
	HttpHelper provideHttpHelper(MRetrofitHelper retrofitHelper) {
		return retrofitHelper;
	}

	@Provides
	@Singleton
	PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
		return implPreferencesHelper;
	}

	@Provides
	@Singleton
	DataManager provideDataManager(HttpHelper httpHelper,PreferencesHelper preferencesHelper) {
		return new DataManager(httpHelper,preferencesHelper);
	}


}
