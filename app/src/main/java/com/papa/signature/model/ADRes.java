package com.papa.signature.model;

import java.util.List;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/27 9:42
 */
public class ADRes {


    /**
     * return_code : SUCCESS
     * is_error : false
     * list : [{"banner_id":"1","id":"1","stadium_id":"100004","u_time":"1569546982","c_time":"1569546982","status":"1","name":"cs","screen_id":"2b8be5f94e5d073c","images_src":"/","business_id":"100001"}]
     */

    private String code;
    private String message;
    private List<ADBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ADBean> getData() {
        return data;
    }

    public void setData(List<ADBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ADRes{" +
                "code='" + code + '\'' +
                ", message'" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
