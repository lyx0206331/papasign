package com.papa.signature.viewmodel;

import android.app.Application;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableField;

import cn.jpush.android.api.JPushInterface;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/25 14:12
 */
public class AdViewModel extends BaseViewModel {
    public ObservableField<Boolean> show = new ObservableField<>(false);

    private AppCompatActivity mContext;

    public AdViewModel(@NonNull Application application, AppCompatActivity mContext) {
        super(application);
        this.mContext = mContext;
    }

    /**
     *
     */
    public void  setBooelan() {
        if (show.get()){
            show.set(false);
        }else {
            show.set(true);
        }

    }
}
