package com.papa.signature.model.remote;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/10 15:43
 */
public abstract class BaseObserver<T> implements Observer<T> {
    protected Context mContext;

    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }

    //开始
    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    //获取数据
    @Override
    public void onNext(T tBaseEntity) {
        try {
            onSuccees(tBaseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //失败
    @Override
    public void onError(Throwable e) {
        Log.i("URL", e.getMessage());
        onRequestEnd();
        try {
            HttpException exception = (HttpException) e;
            String string = exception.response().errorBody().string();
            JSONObject jsonObject = new JSONObject(string);
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {

                onFailure(jsonObject, true);  //网络错误
            } else {
                onFailure(jsonObject, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    //结束
    @Override
    public void onComplete() {
        onRequestEnd();//请求结束
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(T t) throws Exception;


    /**
     * 返回失败
     *
     * @param jsonObject
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(JSONObject jsonObject, boolean isNetWorkError) throws Exception;

    protected void onRequestStart() {

    }

    protected void onRequestEnd() {

    }
}
