package com.yzyfdf.newsframework.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzyfdf.newsframework.ui.widget.LoaderPager;


public abstract class BaseFragment extends Fragment {

    private LoaderPager mLoaderPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mLoaderPager == null) {
            mLoaderPager = new LoaderPager(getContext()) {

                @Override
                protected Object getNetData() {
                    return requestData();
                }

                @Override
                protected View createSuccessView() {
                    View view = createView();
                    return view;
                }
            };
        }

        return mLoaderPager;
    }

    //创建view
    protected abstract View createView();

    //请求数据
    public abstract Object requestData();

    //提供一个刷新的方法
    public void refreshData() {
        mLoaderPager.loadData();
    }
}
