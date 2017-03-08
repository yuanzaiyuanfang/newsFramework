package com.yzyfdf.ceshi.ui.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yzyfdf.ceshi.R;
import com.yzyfdf.ceshi.bean.NewHeadBean;
import com.yzyfdf.ceshi.bean.NewItemBean;
import com.yzyfdf.ceshi.util.Urls;
import com.yzyfdf.newsframework.adapter.FinalViewHolder;
import com.yzyfdf.newsframework.interfaces.ItemType;
import com.yzyfdf.newsframework.manager.LoadData;
import com.yzyfdf.newsframework.ui.fragment.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJJ .
 * 描述 ${TODO}
 */

public class NewsFragment extends RecyclerViewFragment {
    private String token = "";
    private NewHeadBean mNewHeadBean;

    @Override
    public int setItemBodyView() {
        return R.layout.item_list_news;
    }

    @Override
    public List<ItemType> requestBodyData(LOADSTATE currentState) {
        //根据当前的状态来请求
        String requestAddress = "";
        if (currentState == LOADSTATE.MORELOAD) {
            //接pagetoken
            requestAddress = Urls.newBodyaddress + token;
        } else {
            //空的
            requestAddress = Urls.newBodyaddress;
        }

        System.out.println("当前的地址:" + requestAddress);
        NewItemBean newItemBean = LoadData.getInstance().getBeanData(requestAddress, NewItemBean.class);
        if (newItemBean == null) {
            return null;
        }
        //token赋值
        token = newItemBean.getResult().getNextPageToken();

        List<ItemType> list = new ArrayList<>();
        list.addAll(newItemBean.getResult().getItems());
        return list;
    }

    @Override
    public void bindBodyView(List<ItemType> showItems, FinalViewHolder holder, int position) {
        NewItemBean.ResultBean.ItemBodyBean itemBodyBean = (NewItemBean.ResultBean.ItemBodyBean) showItems.get(position);

        holder.setText(R.id.tv_title,itemBodyBean.getTitle());
        holder.setText(R.id.tv_description,itemBodyBean.getBody());
        holder.setText(R.id.tv_time,itemBodyBean.getPubDate());
        holder.setText(R.id.tv_comment_count,itemBodyBean.getCommentCount()+"");

    }

    @Override
    public int setItemHeadView() {
        return R.layout.item_list_news_header;
    }

    @Override
    public void requestHeadData() {
        mNewHeadBean = LoadData.getInstance().getBeanData(Urls.newHeadaddress, NewHeadBean.class);
    }

    //在这里要注意
    @Override
    public void bindHeadView(FinalViewHolder holder, int position) {
        final NewHeadBean headBean = mNewHeadBean;
        final ViewPager viewpager = (ViewPager) holder.itemView.findViewById(R.id.viewPager);
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return headBean.getResult().getItems().size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                ImageView imageView = new ImageView(container.getContext());

                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                Glide.with(container.getContext()).load(headBean.getResult().getItems().get(position).getImg()).into(imageView);

                container.addView(imageView);

                return imageView;

            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });


        //设置标题
        final TextView headTitle = (TextView) holder.itemView.findViewById(R.id.tv_news_title);
        headTitle.setText(headBean.getResult().getItems().get(0).getName());


    }


    //显示底部
    @Override
    public boolean isFootShow() {
        return true;
    }

    //下拉刷新
    @Override
    public boolean isLoadRefresh() {
        return true;
    }
}
