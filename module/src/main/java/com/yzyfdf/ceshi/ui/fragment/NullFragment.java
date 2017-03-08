package com.yzyfdf.ceshi.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.yzyfdf.newsframework.ui.fragment.BaseFragment;

/**
 * Created by SJJ .
 * 描述 ${TODO}
 */

public class NullFragment extends BaseFragment {
    @Override
    protected View createView() {

        TextView textView = new TextView(getContext());
        textView.setText("空白");
        return textView;
    }

    @Override
    public Object requestData() {
        return "";
    }
}
