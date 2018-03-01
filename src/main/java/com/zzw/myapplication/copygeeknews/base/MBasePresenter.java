package com.zzw.myapplication.copygeeknews.base;

/**
 * Created by zqy on 2017/9/28.
 *
 */

public interface MBasePresenter<T extends MBaseView> {
	void attachView(T view);

	void detachView();
}
