package com.yzyfdf.newsframework.util;

import android.widget.Toast;

import com.yzyfdf.newsframework.ui.act.NewsApplication;

public class ToastUtil {
	private static Toast toast;
	/**
	 * 强大的吐司，能够连续弹的吐司
	 * @param text
	 */
	public static void showToast(final String text){
		Util.runOnUIThread(new Runnable() {
			@Override
			public void run() {
				if(toast==null){
					toast = Toast.makeText(NewsApplication.mContext, text,Toast.LENGTH_SHORT);
				}else {
					toast.setText(text);//如果不为空，则直接改变当前toast的文本
				}
				toast.show();
			}
		});

	}
}
