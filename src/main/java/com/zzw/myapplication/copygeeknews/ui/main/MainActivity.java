package com.zzw.myapplication.copygeeknews.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.zzw.myapplication.copygeeknews.R;
import com.zzw.myapplication.copygeeknews.app.Constants;
import com.zzw.myapplication.copygeeknews.base.MBaseActivity;
import com.zzw.myapplication.copygeeknews.base.contract.main.MMainContract;
import com.zzw.myapplication.copygeeknews.presenter.MMainPresenter;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends MBaseActivity<MMainPresenter> implements MMainContract.View {

	@BindView(R.id.drawer)
	DrawerLayout mDrawerLayout;
	@BindView(R.id.toolbar)
	Toolbar mToolbar;
	@BindView(R.id.navigation)
	NavigationView mNavigationView;
	@BindView(R.id.view_search)
	MaterialSearchView viewSearch;
	@BindView(R.id.toolbar_container)
	FrameLayout toolbarContainer;
	@BindView(R.id.fl_main_content)
	FrameLayout flMainContent;

	private int hideFragment = Constants.TYPE_ZHIHU;
	private int showFragment = Constants.TYPE_ZHIHU;

	@Override
	protected void initEventAndDate() {

	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initInject() {
		getActivityCompnent().inject(this);
	}

	@Override
	public void showUpdateDialog(String versionContent) {

	}

	@Override
	public void startDownloadService() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			presenter.setNightModeState(false);
		} else {
			showFragment = presenter.getCurrentItem();
			hideFragment = Constants.TYPE_ZHIHU;
			//showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
			//mNavigationView.getMenu().findItem(R.id.drawer_zhihu).setChecked(false);
			mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());
			hideFragment = showFragment;
		}
	}

	private int getCurrentItem(int item) {
		switch (item) {
			case Constants.TYPE_ZHIHU:
				return R.id.drawer_zhihu;
			case Constants.TYPE_GANK:
				return R.id.drawer_gank;
			case Constants.TYPE_WECHAT:
				return R.id.drawer_wechat;
			case Constants.TYPE_GOLD:
				return R.id.drawer_gold;
			case Constants.TYPE_VTEX:
				return R.id.drawer_vtex;
			case Constants.TYPE_LIKE:
				return R.id.drawer_like;
			case Constants.TYPE_SETTING:
				return R.id.drawer_setting;
			case Constants.TYPE_ABOUT:
				return R.id.drawer_about;
		}
		return R.id.drawer_zhihu;
	}

	private SupportFragment getTargetFragment(int item) {
		switch (item) {
			case Constants.TYPE_ZHIHU:
				return null;
			case Constants.TYPE_GANK:
				return null;
			case Constants.TYPE_WECHAT:
				return null;
			case Constants.TYPE_GOLD:
				return null;
			case Constants.TYPE_VTEX:
				return null;
			case Constants.TYPE_LIKE:
				return null;
			case Constants.TYPE_SETTING:
				return null;
			case Constants.TYPE_ABOUT:
				return null;
		}
		return null;
	}
}
