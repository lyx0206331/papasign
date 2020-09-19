package com.papa.signature.view.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.papa.signature.R;
import com.papa.signature.utils.ExampleUtil;
import com.papa.signature.utils.PackageUtils;
import com.papa.signature.utils.SpUtil;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/25 10:41
 */
public class DeviceInfoActivity extends AppCompatActivity {

    private TextView deviceID;
    private TextView appNameT;
    private TextView versionCodeT;
    private TextView versionNameT;
    private TextView setIpText;
    private TextView tvRegistationID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
        initView();
    }

    /**
     * view的初始化
     */
    private void initView() {
        final String ANDROID_ID = ExampleUtil.getDeviceId(this);
        Log.i("URL", "ANDROID_ID=" + ANDROID_ID);
//        JPushInterface.setAlias(this, 5555, ANDROID_ID);
        deviceID = findViewById(R.id.deviceID);
        tvRegistationID = findViewById(R.id.tvRegistationID);
        appNameT = findViewById(R.id.appName);
        versionCodeT = findViewById(R.id.versionCode);
        versionNameT = findViewById(R.id.versionName);
        deviceID.setText("设备唯一ID：" + ANDROID_ID);
        final String regId = SpUtil.getInstance().getRegistrationID();
        tvRegistationID.setText("RegistationID:" + regId);
        //长按复制用
        final ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        deviceID.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cmb.setPrimaryClip(ClipData.newPlainText(null, ANDROID_ID));//参数一：标签，可为空，参数二：要复制到剪贴板的文本
                if (cmb.hasPrimaryClip()) {
                    cmb.getPrimaryClip().getItemAt(0).getText();
                }
                Toast.makeText(DeviceInfoActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        tvRegistationID.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cmb.setPrimaryClip(ClipData.newPlainText(null, regId));//参数一：标签，可为空，参数二：要复制到剪贴板的文本
                if (cmb.hasPrimaryClip()) {
                    cmb.getPrimaryClip().getItemAt(0).getText();
                }
                Toast.makeText(DeviceInfoActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        String appName = PackageUtils.getAppName(this);
        appNameT.setText("APP名称：" + appName);
        int versionCode = PackageUtils.getVersionCode(this);
        versionCodeT.setText("版本号：" + versionCode);
        String versionName = PackageUtils.getVersionName(this);
        versionNameT.setText("版本名称：" + versionName);
        setIpText = findViewById(R.id.setIP);
        setIpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeviceInfoActivity.this, IPConfigSetActivity.class));
            }
        });
    }

    public void back(View view) {
        finish();
    }
}
