package com.zzw.myapplication.copygeeknews.di.component;

import android.app.Activity;

import com.zzw.myapplication.copygeeknews.di.module.MFragmentModule;
import com.zzw.myapplication.copygeeknews.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by Administrator on 2018/3/2.
 */
@FragmentScope
@Component(dependencies = MAppCompnent.class,modules = MFragmentModule.class)
public interface MFragmentComponent {
	Activity getActivity();
	//void inject(DailyFragment dailyFragment);

	//void inject(ThemeFragment themeFragment);

	//void inject(MSectionFragment sectionFragment);

	//void inject(MHotFragment hotFragment);

	//void inject(MCommentFragment longCommentFragment);

	//void inject(MTechFragment techFragment);

	//void inject(MGirlFragment girlFragment);

	//void inject(MLikeFragment likeFragment);

	//void inject(WechatMainFragment wechatMainFragment);

	//void inject(MSettingFragment settingFragment);

	//void inject(MGoldMainFragment goldMainFragment);

	//void inject(MGoldPagerFragment goldPagerFragment);

	//void inject(MVtexPagerFragment vtexPagerFragment);
}
