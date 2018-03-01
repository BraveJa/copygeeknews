package com.zzw.myapplication.copygeeknews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zzw.myapplication.copygeeknews.app.MAPP;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by zqy on 2017/9/26.
 * 这是没有MVP的基类,最上层的基类
 */

public abstract class MSimpleActivity extends SupportActivity {
	protected Activity mContext;

	private Unbinder bind;

	//注意这是一个参数的构造
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());//由子类去实现

		bind = ButterKnife.bind(this);
		mContext = this;//上下文
		onViewCreated();
		MAPP.getInstance().addActivity(this);
		initEventAndDate();
	}

	protected void setToolBar(Toolbar toolBar, String title) {
		toolBar.setTitle(title);
		setSupportActionBar(toolBar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		toolBar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressedSupport();//返回按钮
			}
		});
	}

	protected void onViewCreated() {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		MAPP.getInstance().removeActivity(this);
		bind.unbind();//反绑定,防止内存泄露
	}

	protected abstract void initEventAndDate();

	protected abstract int getLayoutId();
}
