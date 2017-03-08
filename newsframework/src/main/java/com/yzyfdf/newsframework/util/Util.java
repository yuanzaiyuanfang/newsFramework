package com.yzyfdf.newsframework.util;

import android.content.res.Resources;

import com.yzyfdf.newsframework.ui.act.NewsApplication;


public class Util {
    //这个是在主线程去更新ui,在没有上下文的环境,
    public static void runOnUIThread(Runnable runnable) {
        NewsApplication.mHandler.post(runnable);
    }

    //得到资源管理的类
    public static Resources getResources() {
        return NewsApplication.mContext.getResources();
    }

    //在屏幕适配时候使用,让代码中使用dip属性
    public static int getDimens(int resId) {

        return getResources().getDimensionPixelSize(resId);
    }

    //得到颜色
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    //得到字符串数组信息
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }
}
