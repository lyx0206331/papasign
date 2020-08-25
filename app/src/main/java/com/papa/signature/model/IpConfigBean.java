package com.papa.signature.model;


import com.papa.signature.model.local.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/30 9:26
 */
@Table(database = MyDatabase.class)
public class IpConfigBean extends BaseModel {
    @Column
    @PrimaryKey
    private int ipId;
    @Column
    private String BaseUrl;
    @Column
    private String ImgUrl;

    public int getIpId() {
        return ipId;
    }

    public void setIpId(int ipId) {
        this.ipId = ipId;
    }

    public String getBaseUrl() {
        return BaseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        BaseUrl = baseUrl;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }
}
