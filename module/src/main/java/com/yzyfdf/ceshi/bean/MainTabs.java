package com.yzyfdf.ceshi.bean;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yzyfdf.ceshi.R;
import com.yzyfdf.ceshi.ui.fragment.MultipleFragment;
import com.yzyfdf.ceshi.ui.fragment.NullFragment;


public enum MainTabs {

    NEWS("NEWS","综合", R.mipmap.ic_launcher, MultipleFragment.class,null),
    NEWS2("NEWS2","AA", R.mipmap.ic_launcher, NullFragment.class,null),
    NEWS3("NEWS3","BB", R.mipmap.ic_launcher, NullFragment.class,null),
    NEWS4("NEWS4","CC", R.mipmap.ic_launcher, NullFragment.class,null),
    NEWS5("NEWS5","DD", R.mipmap.ic_launcher, NullFragment.class,null);

    //tag
    public String          mTag;
    //标题的文字
    public String          mTitle;
    //图片
    public int             mIcon;
    //class类
    public Class<Fragment> mClass;
    //参数
    public Bundle          mBundle;



    //类型构造
    MainTabs(String tag, String title, int icon, Class clss, Bundle bundle){
        this.mTag = tag;
        this.mTitle = title;
        this.mIcon = icon;
        this.mClass = clss;
        this.mBundle = bundle;
    }

}
