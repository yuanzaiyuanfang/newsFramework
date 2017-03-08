package com.yzyfdf.newsframework.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzyfdf.newsframework.interfaces.BodyType;
import com.yzyfdf.newsframework.interfaces.FootBean;
import com.yzyfdf.newsframework.interfaces.FootType;
import com.yzyfdf.newsframework.interfaces.HeadBean;
import com.yzyfdf.newsframework.interfaces.HeadType;
import com.yzyfdf.newsframework.interfaces.ItemType;
import com.yzyfdf.newsframework.ui.widget.FootViewLayout;

import java.util.ArrayList;
import java.util.List;


public class FinalAdapter extends RecyclerView.Adapter<FinalViewHolder> {

    private List<ItemType> mShowItems = new ArrayList<>();


    private AdapterListener mAdapterListener;
    private int mBodyLayout = 0;
    private FootViewLayout mFootViewLayout;

    public FinalAdapter(List<ItemType> showItems, int bodyLayout, AdapterListener adapterListener) {
        mShowItems = showItems;
        mBodyLayout = bodyLayout;
        this.mAdapterListener = adapterListener;
    }

    //返回一个viewholder
    @Override
    public FinalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据不同的条目类型展示不同的view
        View view = null;
        switch (viewType) {
            case HEADTYPE:
                //因为这里用构造就会造成必传,所有使用钩子方法
                //但是这里有一个小的隐"换"
                view = LayoutInflater.from(parent.getContext()).inflate(mHeadView, parent, false);

                break;
            case BODYTYPE:
                if (mBodyLayout <= 0) {
                    throw new RuntimeException("哥们传个item布局吧");
                }
                view = LayoutInflater.from(parent.getContext()).inflate(mBodyLayout, parent, false);
                break;
            case FOOTTYPE:
                mFootViewLayout = new FootViewLayout(parent.getContext());
                //                footViewLayout.changeView(FootViewLayout.FOOTSTAUTS.NOMORE);
                view = mFootViewLayout;
                break;

            default:
                break;

        }
        return new FinalViewHolder(view);
    }

    //更新数据
    @Override
    public void onBindViewHolder(final FinalViewHolder holder, final int position) {

        //设置view的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(holder,position,mShowItems);
            }
        });

        //得到条目类型
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case HEADTYPE:

                mAdapterListener.bindHeadView(holder,position);
                break;
            case BODYTYPE:
                mAdapterListener.bindBodyView(mShowItems,holder,position);

                break;
            case FOOTTYPE:
                mAdapterListener.bindFootView(mFootViewLayout);

                break;

            default:
                break;
        }
    }

    //展示的条目
    @Override
    public int getItemCount() {
        return mShowItems.size();
    }

    //多条目
    public static final int HEADTYPE = 0;//头部
    public static final int BODYTYPE = 1;//内容
    public static final int FOOTTYPE = 2;//底部

    //实现多条目的
    @Override
    public int getItemViewType(int position) {
        if (mShowItems.get(position) instanceof HeadType) {
            return HEADTYPE;
        }

        if (mShowItems.get(position) instanceof BodyType) {
            return BODYTYPE;
        }
        if (mShowItems.get(position) instanceof FootType) {
            return FOOTTYPE;
        }

        //这里不太可能
        return BODYTYPE;

    }

    //定义头部布局
    private int mHeadView = 0;

    //传入头部布局
    public void setHeadView(int headView) {
        mHeadView = headView;
    }


    private boolean isFootShow = false;//这里代表不显示底部

    //是否需要底部
    public void isShowFoot(boolean isShow) {
        isFootShow = isShow;
    }

    //接口回调
    public interface AdapterListener {
        void bindHeadView(FinalViewHolder holder, final int position);

        void bindBodyView(List<ItemType> showItems, FinalViewHolder holder, final int position);

        void bindFootView(FootViewLayout mFootViewLayout);
    }

    //根据布局自动添加头部或者底部
    public void updateData() {
        //根据头部布局自动刷新数据
        //不管你以前有没有,都删除
        for (int i = 0; i < mShowItems.size(); i++) {
            if (mShowItems.get(i) instanceof HeadType) {
                mShowItems.remove(i);
            }
        }
        if (mHeadView > 0) {
            //说明要添加头部
            mShowItems.add(0,new HeadBean());
        }

        //底部
        for (int i = 0; i < mShowItems.size(); i++) {
            if (mShowItems.get(i) instanceof FootType) {
                mShowItems.remove(i);
            }
        }

        //根据你是否需要底部来添加数据
        if (isFootShow) {
            //true说明要底部
            mShowItems.add(new FootBean());
        }

        //刷新
        notifyDataSetChanged();


    }


    //添加点击事件
    public interface AdapterItemClickListener {
        void onItemClick(FinalViewHolder holder, int position, List<ItemType> showItems);
    }

    //这个监听我想通过方法,为什么???
    //在这里有时候我们只要静静的展示一下
    private AdapterItemClickListener mListener;

    public void setadapterItemClickListener(AdapterItemClickListener listener) {
        this.mListener = listener;
    }

}
