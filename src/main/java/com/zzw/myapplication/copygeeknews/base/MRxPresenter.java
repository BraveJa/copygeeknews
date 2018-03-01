package com.zzw.myapplication.copygeeknews.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zqy on 2017/10/12.
 */

public class MRxPresenter<T extends MBaseView> implements MBasePresenter<T> {
	protected T mView;
	protected CompositeDisposable mCompositeDisposable;

	protected void addSubscribe(Disposable disposable) {
		if (mCompositeDisposable == null) {
			mCompositeDisposable = new CompositeDisposable();
		}
		mCompositeDisposable.add(disposable);
	}

	protected void unSubscribe() {
		if (mCompositeDisposable != null) {
			mCompositeDisposable.clear();
		}
	}

	/*protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
		if (mCompositeDisposable == null) {
			mCompositeDisposable = new CompositeDisposable();
		}
		mCompositeDisposable.add(RxBus.getDefault().toDefaultFlowable(eventType, act));
	}*/
	@Override
	public void attachView(T view) {
		this.mView = view;
	}

	@Override
	public void detachView() {
		this.mView = null;
		unSubscribe();
	}
}
