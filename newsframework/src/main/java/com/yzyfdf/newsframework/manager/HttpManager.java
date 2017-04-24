package com.yzyfdf.newsframework.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HttpManager {

    private static OkHttpClient mOkHttpClient;

    private HttpManager() {

    }

    private static HttpManager sHttpManager = new HttpManager();

    public static HttpManager getInstance() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(3000, TimeUnit.MILLISECONDS)
                    .readTimeout(3000, TimeUnit.MILLISECONDS)
                    .writeTimeout(3000, TimeUnit.MILLISECONDS)
                    .build();
        }
        return sHttpManager;
    }

    //去网络获取数据
    public String dataGet(String url) {
        try {


            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = mOkHttpClient.newCall(request).execute();

            //获取头
            /*Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                String name = headers.name(i);
                String value = headers.get(name);
                System.out.println("HttpManager.dataGet|" + "name:" + name + "---->value" + value);
                //Content-Type---->valuetext/html;charset=UTF-8
                //判断是否网页
            }*/

            return response.body().string();
        } catch (IOException e) {
            System.out.println("HttpManager.dataGet|" + e.toString());
            return null;
        } catch (NullPointerException e) {
            System.out.println("HttpManager.dataGet|" + e.toString());
            return null;
        }
    }


    public String postData(String url, HashMap<String, String> paramsMap) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            //创建一个请求实体对象 RequestBody
            RequestBody body = builder.build();
            //创建一个请求
            final Request request = new Request.Builder().url(url).post(body).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            Response response = call.execute();
            //请求执行成功
            if (response.isSuccessful()) {
                //获取返回数据 可以是String，bytes ,byteStream

                return response.body().string();
//                Log.e(TAG, "response ----->" + response.body().string());
            }

            return null;
        } catch (IOException e) {
            System.out.println(e.toString());

            return null;
        }
    }

}
