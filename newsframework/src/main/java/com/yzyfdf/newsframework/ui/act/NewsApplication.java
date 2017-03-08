package com.yzyfdf.newsframework.ui.act;

import android.app.Application;
import android.content.Context;
import android.os.Handler;


/**
 * Created by SJJ .
 * 描述 ${TODO}
 */

public class NewsApplication extends Application {
    public static      Context mContext;
    public  static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler();

    }


}
