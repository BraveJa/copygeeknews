package com.zzw.myapplication.copygeeknews.ui.main;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zzw.myapplication.copygeeknews.R;
import com.zzw.myapplication.copygeeknews.base.MBaseActivity;
import com.zzw.myapplication.copygeeknews.base.contract.main.MWelcomeContract;
import com.zzw.myapplication.copygeeknews.component.MImageLoader;
import com.zzw.myapplication.copygeeknews.modle.bean.WelcomeBean;
import com.zzw.myapplication.copygeeknews.presenter.MwelcomePresenter;

import butterknife.BindView;

public class MWelcomeActivity extends MBaseActivity<MwelcomePresenter> implements MWelcomeContract.View {

	@BindView(R.id.iv_welcome_bg)
	ImageView ivWelcomeBg;
	@BindView(R.id.tv_welcome_author)
	TextView tvWelcomeAuthor;

	@Override
	protected void initEventAndDate() {
		presenter.getWelcomeData();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_mwelcome;
	}

	@Override
	public void showContent(WelcomeBean welcomeBean) {
		Logger.d("MWelcomeActivity showContent()");
		MImageLoader.load(this, welcomeBean.getImg(), ivWelcomeBg);//加载图片
		ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
		tvWelcomeAuthor.setText(welcomeBean.getText());

	}

	@Override
	public void jumpToMain() {
		Logger.d("MWelcomeActivity jumpToMain()");
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
		//android自带的界面进出动画效果,透明效果
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

	}

	@Override
	protected void initInject() {
		getActivityCompnent().inject(this);
	}

}
