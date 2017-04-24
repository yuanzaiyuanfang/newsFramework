package com.yzyfdf.newsframework.manager;

import android.text.TextUtils;

import com.yzyfdf.newsframework.util.GsonUtil;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;


//可以看做一个工具类
public class LoadData {

    private LoadData () {

    }

    private static LoadData sLoadData = new LoadData();


    public static LoadData getInstance() {
        return sLoadData;
    }


    //获取json对象的方法
    public <T> T getBeanData(String url,Class<T> clazz) {

        //1. 去网络获取数据
        String content = HttpManager.getInstance().dataGet(url);

        //2. 判断当前的数据是否为空
        if (TextUtils.isEmpty(content)) {

            //如果是空//去缓存类中去取数据
            System.out.println("获取网络数据失败,尝试读取本地缓存");
            content = CacheManger.getInstance().getCacheData(url);

            //如果任为空,获取数据失败
            if (TextUtils.isEmpty(content)) {
                System.out.println("没有获取任何数据");
                return null;
            }
        } else {

            //不为空//保存数据,刷新缓存数据
            System.out.println("获取网络数据成功,缓存本地");
            CacheManger.getInstance().saveData(content,url);
        }

        //到这一步为止,我们已经得到数据了,解析
        return parseJson(content,clazz);
    }

    public <T> T postBeanData(String url,Class<T> clazz,HashMap<String, String> paramsMap) {

        //1. 去网络获取数据
        String content = HttpManager.getInstance().postData(url,paramsMap);

        //2. 判断当前的数据是否为空
        if (TextUtils.isEmpty(content)) {

            //如果是空//去缓存类中去取数据
            System.out.println("获取网络数据失败,尝试读取本地缓存");
            content = CacheManger.getInstance().getCacheData(url);

            //如果任为空,获取数据失败
            if (TextUtils.isEmpty(content)) {
                System.out.println("没有获取任何数据");
                return null;
            }
        } else {

            //不为空//保存数据,刷新缓存数据
            System.out.println("获取网络数据成功,缓存本地");
//            CacheManger.getInstance().saveData(content,url);
        }

        //到这一步为止,我们已经得到数据了,解析
        content = content.replace("{\"list\":", "");
        content = content.substring(0, content.length() - 1);

        System.out.println(content);
        return parseJson(content,clazz);
    }

    //解析json数据
    private<T> T parseJson(String content,Class<T> clazz) {

        return GsonUtil.parseJsonToBean(content,clazz);
//        return null;
    }

    //获取json对象的方法
    public <T> List<T> getListData(String url, Type type) {

        //1. 去网络获取数据
        String content = HttpManager.getInstance().dataGet(url);
        //2. 判断当前的数据是否为空
        if (TextUtils.isEmpty(content)) {
            //如果是空
            //去缓存类中去取数据
            content = CacheManger.getInstance().getCacheData(url);
            if (TextUtils.isEmpty(content)) {
                System.out.println("没有获取任何数据");
                return null;
            }
//            System.out.println("当前缓存数据:"+content.length());
        } else {
            //不为空
            //保存数据,刷新缓存数据
            CacheManger.getInstance().saveData(content,url);
        }

        return parseJsonList(content,type);

    }

    private <T> List<T> parseJsonList(String content, Type type) {
        return GsonUtil.parseJsonToList(content,type);
    }


}
