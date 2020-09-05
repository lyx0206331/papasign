package com.papa.signature.utils

import android.content.Context
import android.content.SharedPreferences

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
/**
 * Author:RanQing
 * Create time:20-9-5 下午7:14
 * Description:
 **/
class SpUtil2 {

    public static boolean saveHost(Context context, String apiHost, String imgHost)
    {
        SharedPreferences sp = context . getSharedPreferences ("ipConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp . edit ();
        editor.putString("api_host", apiHost);
        editor.putString("img_host", imgHost);
        return editor.commit();
    }

    fun getApiHost(context: Context) =
        context.getSharedPreferences("ipConfig", Context.MODE_PRIVATE)
            .getString("api_host", "192.168.0.1")

    fun getImgHost(context: Context) =
        context.getSharedPreferences("ipConfig", Context.MODE_PRIVATE)
            .getString("img_host", "192.168.0.1")
}