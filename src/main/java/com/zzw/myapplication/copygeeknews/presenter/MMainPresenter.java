package com.zzw.myapplication.copygeeknews.presenter;

import android.Manifest;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zzw.myapplication.copygeeknews.base.MRxPresenter;
import com.zzw.myapplication.copygeeknews.base.contract.main.MMainContract;
import com.zzw.myapplication.copygeeknews.component.MRxBus;
import com.zzw.myapplication.copygeeknews.modle.bean.VersionBean;
import com.zzw.myapplication.copygeeknews.modle.event.MNightModeEvent;
import com.zzw.myapplication.copygeeknews.modle.http.response.MyHttpResponse;
import com.zzw.myapplication.copygeeknews.modle.prefs.DataManager;
import com.zzw.myapplication.copygeeknews.util.MRxUtil;
import com.zzw.myapplication.copygeeknews.widget.MCommonSubscriber;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by zqy on 2017/12/26.
 */

public class MMainPresenter extends MRxPresenter<MMainContract.View> implements MMainContract.Presenter {
	private DataManager dataManager;

	@Inject
	public MMainPresenter(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	@Override
	public void attachView(MMainContract.View view) {
		registerEvent();

	}

	private void registerEvent() {
		addSubscribe(MRxBus.getDefault().toFlowable(MNightModeEvent.class)
				.compose(MRxUtil.<MNightModeEvent>rxSchedulerHelper())
				.map(new Function<MNightModeEvent, Boolean>() {
					@Override
					public Boolean apply(MNightModeEvent mNightModeEvent) throws Exception {
						return mNightModeEvent.getNightMode();
					}
				})
				.subscribeWith(new MCommonSubscriber<Boolean>(mView, "切换模式失败...") {
					@Override
					public void onNext(Boolean aBoolean) {
						mView.useNightMode(aBoolean);
					}

					@Override
					public void onError(Throwable e) {
						super.onError(e);
						registerEvent();
					}
				}));
	}

	@Override
	public void checkVersion(final String currentVersion) {
		addSubscribe(dataManager.fetchVersionInfo()
				.compose(MRxUtil.<MyHttpResponse<VersionBean>>rxSchedulerHelper())
				.compose(MRxUtil.<VersionBean>handleMyResult())
				.filter(new Predicate<VersionBean>() {
					@Override
					public boolean test(VersionBean versionBean) throws Exception {
						return Integer.valueOf(currentVersion.replace(".", "")) < Integer.valueOf(versionBean.getCode().replace(".", ""));

					}
				})
				.map(new Function<VersionBean, String>() {
					@Override
					public String apply(VersionBean bean) throws Exception {
						StringBuilder content = new StringBuilder("版本号: v");
						content.append(bean.getCode());
						content.append("\r\n");
						content.append("版本大小: ");
						content.append(bean.getSize());
						content.append("\r\n");
						content.append("更新内容:\r\n");
						content.append(bean.getDes().replace("\\r\\n", "\r\n"));
						return content.toString();
					}
				})
				.subscribeWith(new MCommonSubscriber<String>(mView) {
					@Override
					public void onNext(String s) {
						mView.showUpdateDialog(s);
					}
				}));
	}

	@Override
	public void checkPermissions(RxPermissions rxPermissions) {
		addSubscribe(rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
				.subscribe(new Consumer<Boolean>() {
					@Override
					public void accept(Boolean aBoolean) throws Exception {
						if (aBoolean) {
							mView.startDownloadService();
						} else {
							mView.showErrorMsg("下载应用需要文件写入权限哦");
						}
					}
				}));
	}

	@Override
	public void setNightModeState(boolean b) {
		dataManager.setNightModeState(b);
	}

	@Override
	public void setCurrentItem(int index) {
		dataManager.setCurrentItem(index);

	}

	@Override
	public int getCurrentItem() {
		return dataManager.getCurrentItem();
	}

	@Override
	public void setVersionPoint(boolean b) {
		dataManager.setVersionPoint(b);

	}

	@Override
	public boolean getVersionPoint() {
		return dataManager.getVersionPoint();
	}
}
