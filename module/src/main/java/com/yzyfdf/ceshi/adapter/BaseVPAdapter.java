package com.yzyfdf.ceshi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yzyfdf.ceshi.bean.PagerBean;

import java.util.ArrayList;
import java.util.List;


public class BaseVPAdapter extends FragmentStatePagerAdapter {
    //传入集合
    private List<PagerBean> mList = new ArrayList<>();

    public BaseVPAdapter(FragmentManager fm, List<PagerBean> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position).fragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    //初始化标题

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).title;
    }
}
