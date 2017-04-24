package com.yzyfdf.ceshi.ui.act;

import android.app.Application;

import com.yzyfdf.newsframework.util.Constant;


/**
 * Created by SJJ .
 * 描述 ${TODO}
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化框架
        Constant.init(this);

    }


}
