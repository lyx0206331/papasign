package com.papa.signature.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.papa.signature.R;
import com.papa.signature.model.IpConfigBean;
import com.papa.signature.model.local.IpCofigDao;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/30 9:25
 */
public class IPConfigSetActivity extends AppCompatActivity {

    private IpConfigBean ipConfigBean;
    private EditText imgIpT;
    private EditText baseIpT;
    private Button saveSet;
    private IpCofigDao ipCofigDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sethttps_ip);
        ipCofigDao = IpCofigDao.getInstance();
        ipConfigBean = ipCofigDao.selObject(1);
        initView();
        action();
    }

    private void action() {
        saveSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imgIp = imgIpT.getText().toString().trim();
                String baseIp = baseIpT.getText().toString().trim();
                if (imgIp != null && baseIp != null) {
                    long code = 0;
                    if (ipConfigBean == null) {
                        ipConfigBean = new IpConfigBean();
                        ipConfigBean.setBaseUrl(baseIp);
                        ipConfigBean.setImgUrl(imgIp);
                        code = ipCofigDao.addObject(ipConfigBean);
                    } else {
                        ipConfigBean.setBaseUrl(baseIp);
                        ipConfigBean.setImgUrl(imgIp);
                        code = ipCofigDao.upData(ipConfigBean);
                    }
                    if (code == 1) {
                        Toast.makeText(IPConfigSetActivity.this, "地址设置成功，重新启动app才能生效！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(IPConfigSetActivity.this, "地址设置失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(IPConfigSetActivity.this, "请输入IP地址或者域名", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        imgIpT = findViewById(R.id.imgIp);
        baseIpT = findViewById(R.id.baseIp);
        saveSet = findViewById(R.id.saveSet);
    }


    /**
     * 返回
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }
}
