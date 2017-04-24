package com.yzyfdf.newsframework.util;

import android.app.Application;
import android.content.Context;
import android.os.Handler;


/**
 * Created by SJJ .
 * 描述 ${TODO}
 */

public class Constant  {
    public static      Context mContext;
    public  static Handler mHandler;

    public static void init(Application application) {
        mContext = application;
        mHandler = new Handler();
    }


}
