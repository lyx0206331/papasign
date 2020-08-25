package com.papa.signature.model;

/**
 * @author PAPA-GuoBa
 * @Desc  把推送来的消息接收的实体类
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/26 14:02
 */
public class MessageBody {
    /**
     * name :
     * phone :
     * card_num : 7573605
     * card_leave : 普通卡[0]
     * date : 2020-03-23
     * des : asd
     * token : 2cd6cda3ee2bff07abd9f56f7803aa54
     * agreement_id : 2cd6cda3ee2bff07abd9f56f7803aa54
     */
    private String name;
    private String phone;
    private String card_num;
    private String card_leave;
    private String date;
    private String des;
    private String token;
    private String agreement_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getCard_leave() {
        return card_leave;
    }

    public void setCard_leave(String card_leave) {
        this.card_leave = card_leave;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAgreement_id() {
        return agreement_id;
    }

    public void setAgreement_id(String agreement_id) {
        this.agreement_id = agreement_id;
    }
}
