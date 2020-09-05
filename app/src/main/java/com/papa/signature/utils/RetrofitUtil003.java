package com.papa.signature.utils;

import android.util.Log;

import com.papa.signature.model.Config;
import com.papa.signature.model.remote.PAPAServiceAPI;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author PAPA-GuoBa
 * @Desc 访问网络的封装
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/8/30 18:05
 */
public class RetrofitUtil003 {
    private static Retrofit retrofit;
    private static RetrofitUtil003 retrofitUtil;

    private RetrofitUtil003() {
    }

    private RetrofitUtil003(String baseUrl) {
        //第三方的日志拦截器
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OKhttp3  设置拦截器打印日志
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                .addNetworkInterceptor(logInterceptor)
//                .addInterceptor(new RequestInterceptor())
//                .addInterceptor(new HeaderIntercepter())
                .addInterceptor(new LoggingInterceptor())//自定义拦截器
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava2平台
                .client(okHttpClient)//OKhttp3添加到Retrofit
                .build();
    }

    //可指定baseUrl
    public static RetrofitUtil003 getInstance() {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil003.class) {
                if (null == retrofitUtil) {
//                    IpCofigDao ipCofigDao = IpCofigDao.getInstance();
//                    IpConfigBean ipConfigBean = ipCofigDao.selObject(1);
//                    if (ipConfigBean != null) {
//                        retrofitUtil = new RetrofitUtil003(ipConfigBean.getImgUrl());
//                    } else {
                    retrofitUtil = new RetrofitUtil003(Config.BASEURLIMG);
//                    }

                }
            }
        }
        return retrofitUtil;
    }

    //获得Retrofit
    public Retrofit getRetrofit() {
        return retrofit;
    }

    //直接获得AutoAPIService
    public PAPAServiceAPI getAutoAPIService() {
        PAPAServiceAPI retrofitInterface = retrofit.create(PAPAServiceAPI.class);
        return retrofitInterface;
    }

    public static class HeaderIntercepter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request().newBuilder()
                    .addHeader("Access-Control-Allow-Headers", "X-Token,token,authorization,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type")
                    .addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                    .addHeader("Access-Control-Allow-Origin", "*").build();
            return chain.proceed(request);
        }
    }

    //自定义拦截器
    public static class LoggingInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //请求前--打印请求信息
            long t1 = System.nanoTime();
            Log.e("myMessage", String.format("Sending url %s \n %s \n\n%s \n\n %s",
                    request.url(), chain.connection(), request.headers(), request.body()));

            //网络请求
            okhttp3.Response response = chain.proceed(request);

            //网络响应后--打印响应信息
            long t2 = System.nanoTime();
            Log.e("myMessage", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    //日志信息
    public static class HttpLogger implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Log.e("myMessage", message);
        }
    }
}

