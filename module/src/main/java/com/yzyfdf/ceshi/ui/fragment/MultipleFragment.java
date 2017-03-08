package com.yzyfdf.ceshi.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yzyfdf.newsframework.ui.fragment.BaseFragment;
import com.yzyfdf.newsframework.util.Util;
import com.yzyfdf.ceshi.R;
import com.yzyfdf.ceshi.adapter.BaseVPAdapter;
import com.yzyfdf.ceshi.bean.PagerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJJ .
 * 描述 ${TODO}
 */

public class MultipleFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<PagerBean> mList = new ArrayList<>();


    @Override
    protected View createView() {
        //得到参数
        // Bundle arguments = getArguments();
        View view = View.inflate(getContext(), R.layout.fragment_first, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        init();
        return view;
    }

    private void init() {
        String[] titles = Util.getStringArray(R.array.news_titles);

        mList.add(new PagerBean(titles[0], new NewsFragment()));
        mList.add(new PagerBean(titles[1], new NullFragment()));
        mList.add(new PagerBean(titles[2], new NullFragment()));
        mList.add(new PagerBean(titles[3], new NullFragment()));

        //viewpager初始化
        mViewPager.setAdapter(new BaseVPAdapter(getChildFragmentManager(), mList));

        //页签绑定viewpager
        mTabLayout.setupWithViewPager(mViewPager);

        //设置tablayout的模式
        // mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);滚动
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//均分
        //如果超过4个以上的用滚动

        //设置颜色
        int blue = Util.getColor(R.color.colorPrimary);

        mTabLayout.setSelectedTabIndicatorColor(blue);

        //设置字体颜色
        mTabLayout.setTabTextColors(Color.GRAY, blue);
    }

    @Override
    public Object requestData() {
        return "";
    }
}
