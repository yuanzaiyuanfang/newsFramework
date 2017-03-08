package com.yzyfdf.newsframework.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yzyfdf.newsframework.R;


public class FootViewLayout extends LinearLayout {
    private LinearLayout mLlFootLayoutLoading;
    private LinearLayout mLlFootLayoutNoMore;
    private LinearLayout mLlFootLayoutNetError;
    private LinearLayout mLlFootLayoutError;

    public FootViewLayout(Context context) {
        this(context, null);
    }

    public FootViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FootViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {
        //把多种状态的底部都加入
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_foot_layout, this, false);
//        View layoutView = View.inflate(context, R.layout.item_foot_layout, null);
        mLlFootLayoutLoading = (LinearLayout) layoutView.findViewById(R.id.ll_foot_layout_loading);
        mLlFootLayoutNoMore = (LinearLayout) layoutView.findViewById(R.id.ll_foot_layout_no_more);
        mLlFootLayoutNetError = (LinearLayout) layoutView.findViewById(R.id.ll_foot_layout_net_error);
        mLlFootLayoutError = (LinearLayout) layoutView.findViewById(R.id.ll_foot_layout_error);
        //添加
        addView(layoutView);
    }

    //定义状态
    public enum FOOTSTAUTS {
        LOADING,
        NOMORE,
        NETERROR,
        ERROR;
    }

    //根据状态去切换view
    public void changeView(FOOTSTAUTS footstauts) {
        //先全部隐藏
        mLlFootLayoutLoading.setVisibility(View.GONE);
        mLlFootLayoutNoMore.setVisibility(View.GONE);

        switch (footstauts) {
            case LOADING:
                //加载中
                mLlFootLayoutLoading.setVisibility(View.VISIBLE);
                break;
            case NOMORE:
                //没有更多
                mLlFootLayoutNoMore.setVisibility(View.VISIBLE);
                break;
        }
    }
}
