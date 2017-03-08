package com.yzyfdf.newsframework.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class FinalViewHolder extends RecyclerView.ViewHolder {
    public FinalViewHolder(View itemView) {
        super(itemView);
    }

    private SparseArray<View> mViewSparseArray = new SparseArray<>();

    //根据id找控件
    public View getViewById(int id) {
        View view = mViewSparseArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViewSparseArray.put(id, view);
        }

        //返回view
        return view;
    }

    //    返回特定的view
    public TextView getTextViewById(int id) {
        return (TextView) getViewById(id);
    }

    public void setText(int textViewId, CharSequence str) {
        getTextViewById(textViewId).setText(str);
    }

    public ImageView getImageViewById(int id) {
        return (ImageView) getViewById(id);
    }


}
