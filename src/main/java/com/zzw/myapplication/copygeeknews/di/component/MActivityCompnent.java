package com.zzw.myapplication.copygeeknews.di.component;

import android.app.Activity;

import com.zzw.myapplication.copygeeknews.di.module.MActivityModule;
import com.zzw.myapplication.copygeeknews.di.scope.ActivityScope;
import com.zzw.myapplication.copygeeknews.ui.main.MWelcomeActivity;
import com.zzw.myapplication.copygeeknews.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by zqy on 2017/9/28.
 */
@ActivityScope
@Component(dependencies = MAppCompnent.class, modules = MActivityModule.class)
public interface MActivityCompnent {
	Activity getActivity();

	void inject(MWelcomeActivity welcomeActivity);

	void inject(MainActivity mainActivity);
}
