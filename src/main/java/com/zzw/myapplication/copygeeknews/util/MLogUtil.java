package com.zzw.myapplication.copygeeknews.util;

import com.orhanobut.logger.Logger;

import static com.zzw.myapplication.copygeeknews.app.MAPP.isDebug;

/**
 * Created by codeest on 2016/8/3.
 */
public class MLogUtil {

    private static final String TAG = "com.codeest.geeknews";

    public static void e(String tag,Object o) {
        if(isDebug) {
            Logger.e(tag, o);
        }
    }

    public static void e(Object o) {
        MLogUtil.e(TAG,o);
    }

    public static void w(String tag,Object o) {
        if(isDebug) {
            Logger.w(tag, o);
        }
    }

    public static void w(Object o) {
        MLogUtil.w(TAG,o);
    }

    public static void d(String msg) {
        if(isDebug) {
            Logger.d(msg);
        }
    }

    public static void i(String msg) {
        if(isDebug) {
            Logger.i(msg);
        }
    }
}
