package com.papa.signature.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.papa.signature.model.ResponseBean;
import com.papa.signature.utils.RetrofitUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.base.BaseViewModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/8/30 18:10
 */
public class SignaTureViewModel extends BaseViewModel {


    public SignaTureViewModel(@NonNull Application application) {
        super(application);
    }
}
