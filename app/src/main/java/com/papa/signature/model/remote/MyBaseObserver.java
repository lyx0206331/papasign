package com.papa.signature.model.remote;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/26 9:27
 *
 */
public class MyBaseObserver implements Observer<ResponseBody> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseBody responseBody) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
