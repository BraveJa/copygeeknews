package com.zzw.myapplication.copygeeknews.app;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.zzw.myapplication.copygeeknews.BuildConfig;
import com.zzw.myapplication.copygeeknews.di.component.DaggerMAppCompnent;
import com.zzw.myapplication.copygeeknews.di.component.MAppCompnent;
import com.zzw.myapplication.copygeeknews.di.module.MAppModule;
import com.zzw.myapplication.copygeeknews.di.module.MHttpMocule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zqy on 2017/9/26.
 *
 */

public class MAPP extends MultiDexApplication {
	private static MAPP instance;
	public static boolean isDebug = BuildConfig.DEBUG;//日子输出
	private Set<Activity> allActivities;//管理activiy
	public static int SCREEN_WIDTH = -1;//屏幕宽
	public static int SCREEN_HEIGHT = -1;//屏幕高
	public static float DIMEN_RATE = -1.0F;//屏幕比率
	public static int DIMEN_DPI = -1;//屏幕的dpi,单位密度像素
	public static MAppCompnent mAppCompnent;

	public static synchronized MAPP getInstance() {
		return instance;
	}

	static {
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		getScreenSize();
	}

	//64K问题
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	public void addActivity(Activity act) {
		if (allActivities == null) {
			allActivities = new HashSet<>();
		}
		allActivities.add(act);
	}

	public void removeActivity(Activity act) {
		if (allActivities != null) {
			allActivities.remove(act);
		}
	}

	public void exitApp() {
		if (allActivities != null) {
			synchronized (allActivities) {
				for (Activity act : allActivities) {
					act.finish();
				}
			}
		}
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

	//初始化屏幕宽高
	public void getScreenSize() {
		WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		Display display = windowManager.getDefaultDisplay();
		display.getMetrics(dm);
		DIMEN_RATE = dm.density / 1.0F;
		DIMEN_DPI = dm.densityDpi;
		SCREEN_WIDTH = dm.widthPixels;
		SCREEN_HEIGHT = dm.heightPixels;
		if (SCREEN_WIDTH > SCREEN_HEIGHT) {//平板
			int t = SCREEN_HEIGHT;
			SCREEN_HEIGHT = SCREEN_WIDTH;
			SCREEN_WIDTH = t;
		}
	}

	public static MAppCompnent getMappcompnent() {
		if (mAppCompnent == null) {
			mAppCompnent = DaggerMAppCompnent.builder()
					.mAppModule(new MAppModule(instance))
					.mHttpMocule(new MHttpMocule())
					.build();
		}
		return mAppCompnent;
	}
}
