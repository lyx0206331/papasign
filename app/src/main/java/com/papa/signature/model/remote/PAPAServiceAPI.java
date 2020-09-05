package com.papa.signature.model.remote;

import com.papa.signature.model.ADRes;
import com.papa.signature.model.PictureRes;
import com.papa.signature.model.ProtocolRes;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    @POST("base/images/upload")
    @Multipart
    Observable<PictureRes> uploadFile(@Header("Authorization") String authorization, @QueryMap Map<String, String> map, @Part List<MultipartBody.Part> parts);


    /**
     * 获取协议内容
     *
     * @param authorization
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("cfg/cfgAgreement/detail")
    Observable<ProtocolRes> getProtoolConf(@Header("Authorization") String authorization, @FieldMap Map<String, Object> maps);


    /**
     * 更新协议成功后 返回首页
     *
     * @param authorization
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("member/memberCard/addAgreementImages")
    Observable<ResponseBody> update(@Header("Authorization") String authorization, @FieldMap Map<String, Object> maps);


    /**
     * 获取广告内容
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("device/adBanner/list")
    Observable<ADRes> getAd(@FieldMap Map<String, Object> maps);
}
