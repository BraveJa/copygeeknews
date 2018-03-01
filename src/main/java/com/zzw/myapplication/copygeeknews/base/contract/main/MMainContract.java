package com.zzw.myapplication.copygeeknews.base.contract.main;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zzw.myapplication.copygeeknews.base.MBasePresenter;
import com.zzw.myapplication.copygeeknews.base.MBaseView;

/**
 * Created by zqy on 2017/12/26.
 */

public interface MMainContract {
	interface View extends MBaseView{
		//展示更新的dialog
		void showUpdateDialog(String versionContent);

		//开始下载服务
		void startDownloadService();
	}

	interface Presenter extends MBasePresenter<View>{
		//检查版本
		void checkVersion(String currentVersion);

		//检查权限
		void checkPermissions(RxPermissions rxPermissions);

		//黑白模式状态
		void setNightModeState(boolean b);

		//设置现在的itemzhihu  还是weixin 还是干货
		void setCurrentItem(int index);

		//获取现在的item
		int getCurrentItem();

		//设置版本要点
		void setVersionPoint(boolean b);

		//获取版本要点
		boolean getVersionPoint();
	}
}
