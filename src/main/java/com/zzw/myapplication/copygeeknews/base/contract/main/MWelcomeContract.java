package com.zzw.myapplication.copygeeknews.base.contract.main;

import com.zzw.myapplication.copygeeknews.base.MBasePresenter;
import com.zzw.myapplication.copygeeknews.base.MBaseView;
import com.zzw.myapplication.copygeeknews.modle.bean.WelcomeBean;

/**
 * Created by zqy on 2017/10/12.
 *
 */

public interface MWelcomeContract {

	interface View extends MBaseView {

		void showContent(WelcomeBean welcomeBean);

		void jumpToMain();

	}

	interface Presenter extends MBasePresenter<View> {

		void getWelcomeData();

	}
}
