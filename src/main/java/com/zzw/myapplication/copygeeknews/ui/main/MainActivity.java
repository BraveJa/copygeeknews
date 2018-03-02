package com.zzw.myapplication.copygeeknews.ui.main;

import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zzw.myapplication.copygeeknews.R;
import com.zzw.myapplication.copygeeknews.app.Constants;
import com.zzw.myapplication.copygeeknews.app.MAPP;
import com.zzw.myapplication.copygeeknews.base.MBaseActivity;
import com.zzw.myapplication.copygeeknews.base.contract.main.MMainContract;
import com.zzw.myapplication.copygeeknews.presenter.MMainPresenter;
import com.zzw.myapplication.copygeeknews.ui.fragment.gank.MGankMainFragment;
import com.zzw.myapplication.copygeeknews.ui.fragment.gold.fragment.MGoldMainFragment;
import com.zzw.myapplication.copygeeknews.ui.fragment.vtex.fragment.MVtexMainFragment;
import com.zzw.myapplication.copygeeknews.ui.fragment.wechat.fragment.WechatMainFragment;
import com.zzw.myapplication.copygeeknews.ui.fragment.zhihu.fragment.MZhihuMainFragment;
import com.zzw.myapplication.copygeeknews.ui.main.fragment.MAboutFragment;
import com.zzw.myapplication.copygeeknews.ui.main.fragment.MLikeFragment;
import com.zzw.myapplication.copygeeknews.ui.main.fragment.MSettingFragment;
import com.zzw.myapplication.copygeeknews.util.SystemUtil;

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
	MaterialSearchView mSearchView;
	@BindView(R.id.toolbar_container)
	FrameLayout toolbarContainer;
	@BindView(R.id.fl_main_content)
	FrameLayout flMainContent;


	MZhihuMainFragment mZhihuFragment;
	MGankMainFragment mGankFragment;
	WechatMainFragment mWechatFragment;
	MGoldMainFragment mGoldFragment;
	MVtexMainFragment mVtexFragment;
	MLikeFragment mLikeFragment;
	MSettingFragment mSettingFragment;
	MAboutFragment mAboutFragment;

	MenuItem mLastMenuItem;
	MenuItem mSearchMenuItem;
	ActionBarDrawerToggle mDrawerToggle;
	private int hideFragment = Constants.TYPE_ZHIHU;
	private int showFragment = Constants.TYPE_ZHIHU;

	@Override
	protected void initInject() {
		getActivityCompnent().inject(this);
	}


	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			presenter.setNightModeState(false);
		} else {
			showFragment = presenter.getCurrentItem();
			hideFragment = Constants.TYPE_ZHIHU;
			showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
			mNavigationView.getMenu().findItem(R.id.drawer_zhihu).setChecked(false);
			mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());
			hideFragment = showFragment;
		}
	}

	@Override
	protected void initEventAndDate() {
		setToolBar(mToolbar,"知乎日报");
		mZhihuFragment = new MZhihuMainFragment();
		mGankFragment = new MGankMainFragment();
		mWechatFragment = new WechatMainFragment();
		mGoldFragment = new MGoldMainFragment();
		mVtexFragment = new MVtexMainFragment();
		mLikeFragment = new MLikeFragment();
		mSettingFragment = new MSettingFragment();
		mAboutFragment = new MAboutFragment();
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
		mDrawerToggle.syncState();
		mDrawerLayout.addDrawerListener(mDrawerToggle);
		mLastMenuItem = mNavigationView.getMenu().findItem(R.id.drawer_zhihu);
		loadMultipleRootFragment(R.id.fl_main_content,//要替换的framlayout
				0,//要展示的position
				mZhihuFragment,
				mWechatFragment,
				mGankFragment,
				mGoldFragment,
				mVtexFragment,
				mLikeFragment,
				mSettingFragment,
				mAboutFragment
		);
		mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem menuItem) {
				switch (menuItem.getItemId()) {
					case R.id.drawer_zhihu:
						showFragment = Constants.TYPE_ZHIHU;
						mSearchMenuItem.setVisible(false);
						break;
					case R.id.drawer_gank:
						showFragment = Constants.TYPE_GANK;
						mSearchMenuItem.setVisible(true);
						break;
					case R.id.drawer_wechat:
						showFragment = Constants.TYPE_WECHAT;
						mSearchMenuItem.setVisible(true);
						break;
					case R.id.drawer_gold:
						showFragment = Constants.TYPE_GOLD;
						mSearchMenuItem.setVisible(false);
						break;
					case R.id.drawer_vtex:
						showFragment = Constants.TYPE_VTEX;
						mSearchMenuItem.setVisible(false);
						break;
					case R.id.drawer_setting:
						showFragment = Constants.TYPE_SETTING;
						mSearchMenuItem.setVisible(false);
						break;
					case R.id.drawer_like:
						showFragment = Constants.TYPE_LIKE;
						mSearchMenuItem.setVisible(false);
						break;
					case R.id.drawer_about:
						showFragment = Constants.TYPE_ABOUT;
						mSearchMenuItem.setVisible(false);
						break;
				}
				if (mLastMenuItem != null) {
					mLastMenuItem.setChecked(false);
				}
				mLastMenuItem = menuItem;
				presenter.setCurrentItem(showFragment);
				menuItem.setChecked(true);
				mToolbar.setTitle(menuItem.getTitle());
				mDrawerLayout.closeDrawers();
				showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
				hideFragment = showFragment;
				return true;
			}
		});
		mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				/*if (showFragment == Constants.TYPE_GANK) {
					mGankFragment.doSearch(query);
				} else if (showFragment == Constants.TYPE_WECHAT) {
					RxBus.getDefault().post(new SearchEvent(query, Constants.TYPE_WECHAT));
				}*/
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		});
		if (!presenter.getVersionPoint() && SystemUtil.isWifiConnected()) {
			presenter.setVersionPoint(true);
			try {
				PackageManager pm = getPackageManager();
				PackageInfo pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
				String versionName = pi.versionName;
				presenter.checkVersion(versionName);
			} catch (PackageManager.NameNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		MenuItem item = menu.findItem(R.id.action_search);
		item.setVisible(false);
		mSearchView.setMenuItem(item);
		mSearchMenuItem = item;
		return true;
	}

	@Override
	public void onBackPressedSupport() {
		if (mSearchView.isSearchOpen()) {
			mSearchView.closeSearch();
		} else {
			showExitDialog();
		}
	}

	private SupportFragment getTargetFragment(int item) {
		switch (item) {
			case Constants.TYPE_ZHIHU:
				return mZhihuFragment;
			case Constants.TYPE_GANK:
				return mGankFragment;
			case Constants.TYPE_WECHAT:
				return mWechatFragment;
			case Constants.TYPE_GOLD:
				return mGoldFragment;
			case Constants.TYPE_VTEX:
				return mVtexFragment;
			case Constants.TYPE_LIKE:
				return mLikeFragment;
			case Constants.TYPE_SETTING:
				return mSettingFragment;
			case Constants.TYPE_ABOUT:
				return mAboutFragment;
		}
		return mZhihuFragment;
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

	private void showExitDialog() {
		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("确定退出GeekNews吗");
		builder.setNegativeButton("取消", null);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				MAPP.getInstance().exitApp();
			}
		});
		builder.show();
	}

	@Override
	public void showUpdateDialog(String versionContent) {

	}

	@Override
	public void startDownloadService() {
		presenter.checkPermissions(new RxPermissions(this));
	}
}
