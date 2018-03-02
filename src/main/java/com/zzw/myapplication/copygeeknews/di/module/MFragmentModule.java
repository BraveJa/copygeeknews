package com.zzw.myapplication.copygeeknews.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.zzw.myapplication.copygeeknews.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/3/2.
 */
@Module
public class MFragmentModule {
	private Fragment fragment;

	public MFragmentModule(Fragment fragment) {
		this.fragment = fragment;
	}
	@Provides
	@FragmentScope
	public Activity provideActivity(){
		return fragment.getActivity();
	}
}
