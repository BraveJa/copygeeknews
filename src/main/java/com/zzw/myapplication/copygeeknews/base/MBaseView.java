package com.zzw.myapplication.copygeeknews.base;

/**
 * Created by zqy on 2017/9/28.
 */

public interface MBaseView {

	//展示错误消息
	void showErrorMsg(String msg);

	//黑白模式转换
	void useNightMode(boolean isNight);

	//=======  State  =======
	//错误状态
	void stateError();

	//空的状态
	void stateEmpty();

	//加载的状态
	void stateLoading();

	//主要状态
	void stateMain();
}
