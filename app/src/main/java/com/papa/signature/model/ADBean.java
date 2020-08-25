package com.papa.signature.model;

/**
 * @author PAPA-GuoBa
 * @Desc  广告的实体类
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/27 9:40
 */
public class ADBean {


    /**
     * banner_id : 1
     * id : 1
     * stadium_id : 100004
     * u_time : 1569546982
     * c_time : 1569546982
     * status : 1
     * name : cs
     * screen_id : 2b8be5f94e5d073c
     * images_src : /
     * business_id : 100001
     */

    private String banner_id;
    private String id;
    private String stadium_id;
    private String u_time;
    private String c_time;
    private String status;
    private String name;
    private String screen_id;
    private String images_src;
    private String business_id;

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStadium_id() {
        return stadium_id;
    }

    public void setStadium_id(String stadium_id) {
        this.stadium_id = stadium_id;
    }

    public String getU_time() {
        return u_time;
    }

    public void setU_time(String u_time) {
        this.u_time = u_time;
    }

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen_id() {
        return screen_id;
    }

    public void setScreen_id(String screen_id) {
        this.screen_id = screen_id;
    }

    public String getImages_src() {
        return images_src;
    }

    public void setImages_src(String images_src) {
        this.images_src = images_src;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    @Override
    public String toString() {
        return "ADBean{" +
                "banner_id='" + banner_id + '\'' +
                ", id='" + id + '\'' +
                ", stadium_id='" + stadium_id + '\'' +
                ", u_time='" + u_time + '\'' +
                ", c_time='" + c_time + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", screen_id='" + screen_id + '\'' +
                ", images_src='" + images_src + '\'' +
                ", business_id='" + business_id + '\'' +
                '}';
    }
}
