package com.zzw.myapplication.copygeeknews.modle.prefs;

import com.zzw.myapplication.copygeeknews.modle.bean.VersionBean;
import com.zzw.myapplication.copygeeknews.modle.bean.WelcomeBean;
import com.zzw.myapplication.copygeeknews.modle.http.HttpHelper;
import com.zzw.myapplication.copygeeknews.modle.http.response.MyHttpResponse;

import io.reactivex.Flowable;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @desciption:
 */

public class DataManager implements HttpHelper,PreferencesHelper{

    HttpHelper mHttpHelper;
    PreferencesHelper mPreferencesHelper;
    public DataManager(HttpHelper httpHelper,PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mPreferencesHelper = preferencesHelper;

    }
    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mHttpHelper.fetchWelcomeInfo(res);
    }

    @Override
    public Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
        return mHttpHelper.fetchVersionInfo();
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferencesHelper.setNightModeState(state);
    }

    @Override
    public boolean getNoImageState() {
        return false;
    }

    @Override
    public void setNoImageState(boolean state) {

    }

    @Override
    public boolean getAutoCacheState() {
        return false;
    }

    @Override
    public void setAutoCacheState(boolean state) {

    }

    @Override
    public int getCurrentItem() {
        return 0;
    }

    @Override
    public void setCurrentItem(int item) {

    }

    @Override
    public boolean getLikePoint() {
        return false;
    }

    @Override
    public void setLikePoint(boolean isFirst) {

    }

    @Override
    public boolean getVersionPoint() {
        return false;
    }

    @Override
    public void setVersionPoint(boolean isFirst) {

    }

    @Override
    public boolean getManagerPoint() {
        return false;
    }

    @Override
    public void setManagerPoint(boolean isFirst) {

    }
}
