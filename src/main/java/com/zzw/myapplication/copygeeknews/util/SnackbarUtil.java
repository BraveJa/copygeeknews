package com.zzw.myapplication.copygeeknews.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by codeest on 16/9/3.
 * 底部弹出对话框提示用户,和Toast有区别
 */

public class SnackbarUtil {

	public static void show(View view, String msg) {
		Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
	}

	public static void showShort(View view, String msg) {
		Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
	}
}
