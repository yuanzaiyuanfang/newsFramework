package com.yzyfdf.ceshi.bean;

import android.support.v4.app.Fragment;

import java.io.Serializable;

/**
 * Created by SJJ .
 * 描述 ${TODO}
 */

public class PagerBean implements Serializable {
    //标题
    public String   title;
    //fragment
    public Fragment fragment;

    //构造

    public PagerBean(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
}
