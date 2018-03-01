package com.zzw.myapplication.copygeeknews.di.component;

import com.zzw.myapplication.copygeeknews.app.MAPP;
import com.zzw.myapplication.copygeeknews.di.module.MAppModule;
import com.zzw.myapplication.copygeeknews.di.module.MHttpMocule;
import com.zzw.myapplication.copygeeknews.modle.http.MRetrofitHelper;
import com.zzw.myapplication.copygeeknews.modle.prefs.DataManager;
import com.zzw.myapplication.copygeeknews.modle.prefs.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zqy on 2017/9/28.
 * 整个app都会使用到的对象,其他module要用依赖这个接口就可以了
 */
@Singleton
@Component(modules = {MAppModule.class, MHttpMocule.class})
public interface MAppCompnent {
	MAPP getContext();//用于依赖,这里是一个基类,为每个component提供统一的对象,相当于工具类

	DataManager getDataManager(); //数据管理中心

	MRetrofitHelper retrofitHelper();  //提供http的帮助类
	ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
