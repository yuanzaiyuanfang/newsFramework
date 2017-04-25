package com.yzyfdf.newsframework.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yzyfdf.newsframework.R;
import com.yzyfdf.newsframework.adapter.FinalAdapter;
import com.yzyfdf.newsframework.adapter.FinalViewHolder;
import com.yzyfdf.newsframework.interfaces.FootType;
import com.yzyfdf.newsframework.interfaces.ItemType;
import com.yzyfdf.newsframework.ui.widget.FootViewLayout;
import com.yzyfdf.newsframework.util.ToastUtil;
import com.yzyfdf.newsframework.util.Util;

import java.util.ArrayList;
import java.util.List;


public abstract class RecyclerViewFragment extends BaseFragment implements FinalAdapter.AdapterListener, FinalAdapter.AdapterItemClickListener {


    //集合
    private List<ItemType> mShowItems = new ArrayList<>();
    private FinalAdapter       mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView       mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    //定义当前的状态
    public enum LOADSTATE {
        NONE,//空闲
        LOADING,//下拉
        MORELOAD//上拉
    }

    //定义当前的状态
    private LOADSTATE mCurrentState = LOADSTATE.NONE;//第一次进来空闲状态

    //传入一个布局
    @Override
    protected View createView() {

        View view = View.inflate(getContext(), R.layout.fragment_base, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //初始化
        init();
        return view;
    }

    //初始化
    private void init() {
        System.out.println("初始化");
        //recyclerView初始化分成两步
        mRecyclerView.setLayoutManager(setRecyclerViewLayoutManager() == null ? mLayoutManager : setRecyclerViewLayoutManager());

        //设置adapter数据
        mAdapter = new FinalAdapter(mShowItems, setItemBodyView(), this);
        mRecyclerView.setAdapter(mAdapter);

        //设置下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //如果当前的状态是空闲的才能下拉
                if (mCurrentState == LOADSTATE.NONE) {
                    //做下拉刷新,做刷新去重新请求数据
                    //一定要先更改状态
                    mCurrentState = LOADSTATE.LOADING;//下拉刷新

                    refreshData();
                } else {
                    ToastUtil.showToast("刷新正忙!!!");
                }


            }
        });

        //设置颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);

        //做一个上拉刷新
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (mCurrentState == LOADSTATE.NONE) {

                    //获取最后一个滚动的位置
                    LinearLayoutManager line = (LinearLayoutManager) recyclerView.getLayoutManager();
                    //获取最后一个
                    int lastVisibleItemPosition = line.findLastVisibleItemPosition();
                    //可见的条目是最后一位
                    //在这里再加一个判断,如果你底部是加载更多的才能上拉加载更多
                    if (lastVisibleItemPosition == mShowItems.size() - 1 && newState == RecyclerView.SCROLL_STATE_IDLE && mShowItems.get(mShowItems.size() - 1) instanceof FootType) {
                        //ToastUtil.showToast("我已经到底了,可以重新加载");
                        //更改当前的状态
                        mCurrentState = LOADSTATE.MORELOAD;

                        //重新加载
                        refreshData();

                        //                        ToastUtil.showToast("上拉加载更多");
                    }
                } else {
                    ToastUtil.showToast("亲,加载中,等会!");
                }

            }
        });

        //显示头部
        mAdapter.setHeadView(setItemHeadView());

        //显示底部
        mAdapter.isShowFoot(isFootShow());

        //控制下拉
        mSwipeRefreshLayout.setEnabled(isLoadRefresh());

        //添加点击事件
        mAdapter.setadapterItemClickListener(this);

    }

    public RecyclerView.LayoutManager setRecyclerViewLayoutManager() {
        return null;
    }


    //是否下拉
    public boolean isLoadRefresh() {
        return false;
    }

    //添加底部
    public boolean isFootShow() {
        return false;
    }

    //添加头部
    public int setItemHeadView() {
        return 0;
    }

    //传入子条目布局
    public abstract int setItemBodyView();

    //请求数据
    @Override
    public Object requestData() {
        //请求数据
        //头部的数据是否是一天有效,那么判断当前的时间

        //只有在上拉加载更多的时候不加载,其他状态都加载
        if (mCurrentState != LOADSTATE.MORELOAD) {
            requestHeadData();
        }

        //请求body
        if (mFootViewLayout != null) {
            Util.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    mFootViewLayout.changeView(FootViewLayout.FOOTSTAUTS.LOADING);
                }
            });
        }
        List<ItemType> data = requestBodyData(mCurrentState);

        //如果集合是空,或者集合的个数为0,你还要判断当前的mShowItems的个数
        //说明以前有数据
        if (mShowItems.size() > 0) {
            if (data == null || data.size() == 0) {
                System.out.println("当前没有数据");
                ToastUtil.showToast("当前没有数据,或者网络异常");
                if (mFootViewLayout != null) {
                    Util.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            mFootViewLayout.changeView(FootViewLayout.FOOTSTAUTS.NOMORE);
                        }
                    });
                }
                //更新状态
                mCurrentState = LOADSTATE.NONE;
                return mShowItems;
            }
        } else {//说明以前没有数据
            if (data == null || data.size() == 0) {
                //更新状态
                mCurrentState = LOADSTATE.NONE;
                return null;
            }

        }

        //下拉刷新要清空以前的数据
        //这里一定要在请求完数据之后
        if (mCurrentState == LOADSTATE.LOADING) {
            mShowItems.clear();//这里一定要用clear
        }

        mShowItems.addAll(data);


        //请求完数据后把刷新重置
        Util.runOnUIThread(new Runnable() {
            @Override
            public void run() {

                //更新列表
                // mAdapter.notifyDataSetChanged();

                mAdapter.updateData();

                mSwipeRefreshLayout.setRefreshing(false);
                //更新状态
                mCurrentState = LOADSTATE.NONE;


            }
        });

        return mShowItems;
    }

    //请求body的数据
    public abstract List<ItemType> requestBodyData(LOADSTATE currentState);

    //请求头部数据
    public void requestHeadData() {

    }

    //监听数据刷新方法
    @Override
    public void bindHeadView(FinalViewHolder holder, int position) {

    }

    @Override
    public abstract void bindBodyView(List<ItemType> showItems, FinalViewHolder holder, int position);

    private FootViewLayout mFootViewLayout;

    @Override
    public void bindFootView(FootViewLayout footViewLayout) {
        this.mFootViewLayout = footViewLayout;
    }

    @Override
    public void onItemClick(FinalViewHolder holder, int position, List<ItemType> showItems) {

    }

    //重新下载刷新方法
    public void reloadData() {
        mSwipeRefreshLayout.setRefreshing(true);

        mCurrentState = LOADSTATE.LOADING;
        //刷新
        refreshData();
    }
}
