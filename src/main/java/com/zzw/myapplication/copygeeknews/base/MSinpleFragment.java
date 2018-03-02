package com.zzw.myapplication.copygeeknews.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by zqy on 2018/3/2.
 * 无MVP的Fragment基类
 */

public abstract class MSinpleFragment extends SupportFragment {
	protected View mView;
	protected Unbinder mUnBinder;
	protected boolean isInited = false;
	protected Activity mActivity;
	protected Context mContext;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mActivity = (Activity) context;
		mContext = context;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mView = inflater.inflate(getLayoutId(),null);
		return mView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mUnBinder = ButterKnife.bind(this, view);
	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		super.onLazyInitView(savedInstanceState);
		isInited = true;
		initEventAndData();
	}

	protected abstract int getLayoutId();
	protected abstract void initEventAndData();
}
