package com.yzyfdf.newsframework.manager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpManager {

    private HttpManager() {

    }

    private static HttpManager sHttpManager = new HttpManager();

    public static HttpManager getInstance() {
        return sHttpManager;
    }

    //去网络获取数据
    public String dataGet(String url){
        try {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(3000, TimeUnit.MILLISECONDS)
                    .readTimeout(3000, TimeUnit.MILLISECONDS)
                    .writeTimeout(3000, TimeUnit.MILLISECONDS)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = okHttpClient.newCall(request).execute();

            //获取头
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                String name = headers.name(i);
                String value = headers.get(name);
                System.out.println("HttpManager.dataGet|"+"name:" + name + "---->value" + value);
                //Content-Type---->valuetext/html;charset=UTF-8
                //判断是否网页
            }

            return response.body().string();
        } catch (IOException e) {
            System.out.println("HttpManager.dataGet|"+e.toString());
            return null;
        } catch (NullPointerException e) {
            System.out.println("HttpManager.dataGet|"+e.toString());
            return null;
        }
    }


}
