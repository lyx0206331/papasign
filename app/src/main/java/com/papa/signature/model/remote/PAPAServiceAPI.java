package com.papa.signature.model.remote;

import com.papa.signature.model.ADBean;
import com.papa.signature.model.ADRes;
import com.papa.signature.model.PictureRes;
import com.papa.signature.model.ProtocolRes;
import com.papa.signature.model.ResponseBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * @author PAPA-GuoBa
 * @Desc 用来访问网络 获取数据。或者是上传文件等等。
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/8/30 18:02
 */
public interface PAPAServiceAPI {


    /**
     * 多文件上传
     *
     * @param map   参数
     * @param parts
     * @return
     */
    @POST("api/img/uploader/dir/agreement")
    @Multipart
    Observable<PictureRes> uploadFile(@Header("cookie") String cookie, @QueryMap Map<String, String> map, @Part List<MultipartBody.Part> parts);


    /**
     * 获取协议内容
     *
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("setting/stadiumConfig/getProtocolConf")
    Observable<ProtocolRes> getProtoolConf(@FieldMap Map<String, Object> maps);


    /**
     * 更新协议成功后 返回首页
     *
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("member/agreement/update")
    Observable<ResponseBody> update(@FieldMap Map<String, Object> maps);


    /**
     * 获取广告内容
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("electronicScreen/Banner/listByScreen")
    Observable<ADRes> getAd(@FieldMap Map<String, Object> maps);
}
