package com.papa.signature.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.papa.signature.AdvertisingActivity;
import com.papa.signature.utils.ExampleUtil;
import com.papa.signature.view.ui.SignaTureActivity;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/8/29 11:53
 */
public class MessageReceiver extends BroadcastReceiver {
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
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
                Intent intent1 = new Intent(context, SignaTureActivity.class);
                context.startActivity(intent1);
            }
        } catch (Exception e) {
        }
    }
}

