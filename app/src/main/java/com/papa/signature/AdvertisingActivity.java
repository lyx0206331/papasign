package com.papa.signature;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.jaeger.library.StatusBarUtil;
import com.papa.signature.databinding.ActivityAdvertisingBinding;
import com.papa.signature.model.ADBean;
import com.papa.signature.model.ADRes;
import com.papa.signature.model.remote.BaseObserver;
import com.papa.signature.utils.ExampleUtil;
import com.papa.signature.utils.RetrofitUtil002;
import com.papa.signature.view.ui.DeviceInfoActivity;
import com.papa.signature.view.ui.SignaTureActivity;
import com.papa.signature.viewmodel.AdViewModel;
import com.papa.signature.views.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.base.BaseActivity;

public class AdvertisingActivity extends BaseActivity<ActivityAdvertisingBinding, AdViewModel> {
    public static boolean isForeground = false;
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    private Banner banner;
    private TextView text;
    private ImageView imageView;
    private String android_id;
    private List<String> images;
    private List<String> titles;
    private TextView adTitle;
    private TextView apptitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        gainPermission();
        StatusBarUtil.setTransparent(this);
        //权限的配置
        JPushInterface.requestPermission(this);
        initView();
        initPush();
        gotoSignature();
        initAD();
    }

    /**
     * 获取广告内容
     */
    private void initAD() {
        Map<String, Object> map = new HashMap<>();
        map.put("screen_id", android_id);
        RetrofitUtil002.getInstance().getAutoAPIService().getAd(map).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<ADRes>(AdvertisingActivity.this) {
                    @Override
                    protected void onSuccees(ADRes res) throws Exception {
                        Log.i("URL", res.toString());
                        List<ADBean> list = res.getData();
                        if (list != null && list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                images.add(list.get(i).getImages_src());
                                titles.add(list.get(i).getName());
                            }
                        }
                        if (titles.size() > 0) {
                            adTitle.setText(titles.get(0));
                        }
                        apptitle.setText(res.getMessage());
                        intBanner();
                    }

                    @Override
                    protected void onFailure(JSONObject jsonObject, boolean isNetWorkError) throws Exception {

                    }
                });
    }

    private void intBanner() {
        //设置图片集合
        banner.setImages(images);
        banner.start();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //这边该片图片上方广告的名称
                Message message = new Message();
                message.what = 0;
                message.obj = position;
                handler.sendMessage(message);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();

    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    /**
     * 机关推送的初始化
     */
    private void initPush() {
        init();
        registerMessageReceiver();

    }

    /**
     * 组件的初始化
     */
    private void initView() {
        images = new ArrayList<>();
        titles = new ArrayList<>();
        imageView = findViewById(R.id.deviceInfoBtn);
        apptitle = findViewById(R.id.apptitle);
        adTitle = findViewById(R.id.adTitle);
        banner = findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //指示器居中
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);

        /**
         * 查看设备信息
         */
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdvertisingActivity.this, DeviceInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void gotoSignature() {
        android_id = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        Log.i("URL", "ANDROID_ID=" + android_id);
        JPushInterface.setAlias(this, 0, android_id);
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        if (banner != null) {
            banner.startAutoPlay();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        if (banner != null) {
            banner.stopAutoPlay();
        }

    }

    //=================
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }


    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_advertising;
    }

    @Override
    public AdViewModel initViewModel() {
        return new AdViewModel(getApplication(), AdvertisingActivity.this);
    }

    @Override
    public int initVariableId() {
        return BR.adViewModel;
    }

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init() {
        JPushInterface.init(getApplicationContext());
    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    //进行跳转到签字页面
                    Intent intent1 = new Intent(AdvertisingActivity.this, SignaTureActivity.class);
                    intent1.putExtra("message", messge);
                    startActivity(intent1);
                }
            } catch (Exception e) {
            }
        }
    }


    private void gainPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ContextCompat.checkSelfPermission(AdvertisingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AdvertisingActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 4);
            } else {
                Toast.makeText(this, "已开启权限", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //====================handler 更新UI====================
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    int position = (int) msg.obj;
                    adTitle.setText(titles.get(position));
                    break;
            }
        }
    };
}
