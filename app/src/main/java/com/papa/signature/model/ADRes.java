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

    private String return_code;
    private String stadium_name;
    private boolean is_error;
    private List<ADBean> list;

    public String getStadium_name() {
        return stadium_name;
    }

    public void setStadium_name(String stadium_name) {
        this.stadium_name = stadium_name;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public boolean isIs_error() {
        return is_error;
    }

    public void setIs_error(boolean is_error) {
        this.is_error = is_error;
    }

    public List<ADBean> getList() {
        return list;
    }

    public void setList(List<ADBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ADRes{" +
                "return_code='" + return_code + '\'' +
                ", stadium_name='" + stadium_name + '\'' +
                ", is_error=" + is_error +
                ", list=" + list +
                '}';
    }
}
