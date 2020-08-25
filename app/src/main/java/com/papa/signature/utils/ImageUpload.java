package com.papa.signature.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/26 15:48
 */
public class ImageUpload {
    //1.创建对应的MediaType
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private final OkHttpClient client = new OkHttpClient();

    public void uploadImage(String userName, File file) throws IOException {

        //2.创建RequestBody
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_PNG, file);

        //3.构建MultipartBody
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "testImage.png", fileBody)
                .addFormDataPart("userName", userName)
                .build();

        //4.构建请求
        Request request = new Request.Builder()
                .url("http://spapa.wicp.net:5003/api/img/uploader/dir/agreement")
                .post(requestBody)
                .build();

        //5.发送请求
        Response response = client.newCall(request).execute();
        Log.i("URL",response.body().toString());
    }
}
