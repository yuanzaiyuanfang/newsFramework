package com.yzyfdf.ceshi.ui.act;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.yzyfdf.ceshi.R;
import com.yzyfdf.ceshi.bean.MainTabs;
import com.yzyfdf.newsframework.ui.act.NewsApplication;
import com.yzyfdf.newsframework.util.ToastUtil;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    private FrameLayout     mFrameLayout;
    private FragmentTabHost mFragmentTabHost;
    private Toolbar         mToolbar;
    private ActionBar       mSupportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        mFragmentTabHost = (FragmentTabHost) findViewById(R.id.fragmentTabHost);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mSupportActionBar = getSupportActionBar();
        mSupportActionBar.setTitle(MainTabs.NEWS.mTitle);

        init();

        //检查权限
        if (ContextCompat.checkSelfPermission(NewsApplication.mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("申请授权");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10101);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10101:
                if (permissions.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.showToast("授权SD卡读写权限,新闻内容可以缓存在本地,更省流量哦!");
                    return;
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    //初始化
    private void init() {
        //底部跟内容区域进行绑定
        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.frameLayout);

        //初始化底部
        /**
         * tabSpace-->显示的页签内容
         * class--->显示的内容的fragment类
         * args-->传入fragment显示内的参数
         */
        //通过枚举进行循环
        MainTabs[] tabs = MainTabs.values();
        for (int i = 0; i < tabs.length; i++) {

            TabHost.TabSpec tabSpace = mFragmentTabHost.newTabSpec(tabs[i].mTag);//复用
            //设置显示的内容
            View view = View.inflate(this, R.layout.item_tab_indicator, null);
            //查找控件
            TextView title = (TextView) view.findViewById(R.id.tab_title);
            ImageView icon = (ImageView) view.findViewById(R.id.iv_icon);
            //设置数据
            title.setText(tabs[i].mTitle);
            icon.setImageResource(tabs[i].mIcon);

            tabSpace.setIndicator(view);
            //显示的fragment类
            Class<?> clss = tabs[i].mClass;
            mFragmentTabHost.addTab(tabSpace, clss, tabs[i].mBundle);
        }

        //去掉虚线
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);
        mFragmentTabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String tabId) {
        switch (tabId) {
            case "NEWS":
                mSupportActionBar.setTitle("综合");

                break;
            case "TWEENT":
                mSupportActionBar.setTitle("动弹");

                break;
            case "PUBLISHTWTEENT":
                mSupportActionBar.setTitle("弹一弹");

                break;
            case "FIND":
                mSupportActionBar.setTitle("发现");

                break;
            case "ME":
                mSupportActionBar.setTitle("我的");

                break;
            default:
                break;
        }

    }
}
