package com.papa.signature.utils;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//   ┃　　　┃   神兽保佑
//   ┃　　　┃   代码无BUG！
//   ┃　　　┗━━━┓
//   ┃　　　　　　　┣┓
//   ┃　　　　　　　┏┛
//   ┗┓┓┏━┳┓┏┛
//     ┃┫┫　┃┫┫
//     ┗┻┛　┗┻┛

import android.content.Context;
import android.content.SharedPreferences;

import com.papa.signature.MyApplication;

/**
 * Author:RanQing
 * Create time:20-9-5 下午7:46
 * Description:
 **/
public class SpUtil {
    private static final SpUtil ourInstance = new SpUtil();

    public static SpUtil getInstance() {
        return ourInstance;
    }

    private SpUtil() {
    }

    public boolean saveHost(String apiHost, String imgHost) {
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences("ipConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("api_host", apiHost);
        editor.putString("img_host", imgHost);
        return editor.commit();
    }

    public String getApiHost() {
        return MyApplication.getInstance().getSharedPreferences("ipConfig", Context.MODE_PRIVATE).getString("api_host", "http://192.168.0.1");
    }

    public String getImgHost() {
        return MyApplication.getInstance().getSharedPreferences("ipConfig", Context.MODE_PRIVATE).getString("img_host", "http://192.168.0.1");
    }
}
