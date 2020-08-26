package com.papa.signature.model;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/26 14:21
 */
public class ProtocolRes {

    private int code;
    private String message;
    private ProtocalDetail data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProtocalDetail getData() {
        return data;
    }

    public void setData(ProtocalDetail data) {
        this.data = data;
    }

    public static class ProtocalDetail {

        /**
         * option_value : <p>**会员卡办理及使用协议</p>

         <p>本协议甲方系指在**运动项目及商品销售的消费客户，乙方系指**公司，根据《中华人名共和国合同法》及其它有关法律、法规的规定，甲乙双方在平等、自愿、公平、诚实、信用的基础上，就**会员卡办理及使用的有关事宜达成协议如下：</p>

         <p>一、服务和费用标准</p>

         <p>1、乙方在**现有条件下，向甲方提供运动项目及商品销售消费服务，详情请参阅入馆须知；</p>

         <p>2、乙方根据**公示费用标准向甲方收取相关费用；</p>

         <p>二、会员卡的办理及使用</p>

         <p>1、甲方办理会员卡时，须向乙方提供真实有效的信息，甲方登记资料发生变更，应及时通知乙方；如甲方提供的客户资料不真实或变更后未通知乙方，使乙方无法将场馆使用等服务信息提供给甲方，乙方不承担由此对甲方造成的不良后果；</p>

         <p>2、甲方可通过现场、微信公众号、电话查询获知会员卡有效期；</p>

         <p>3、会员卡的使用方法、价格调整和具体办理，请参照场馆公示的**会员卡办理及使用须知；</p>

         <p>三、甲方的权利和义务</p>

         <p>1、甲方按双方签订的协议享有乙方提供的运动服务项目及商品零售服务项目的权利并支付相关费用；</p>

         <p>2、甲方未按要求保管会员卡，导致会员卡损毁，乙方电脑终端无法识别的，由乙方自行承担责任；</p>

         <p>3、甲方应妥善保管会员卡，如因甲方保管不善导致会员卡被他人使用消费的行为均视为甲方或甲方授权的行为；</p>

         <p>四、乙方的权利和义务</p>

         <p>1、因甲方保管不善等原因导致会员卡遗失，甲方应及时向乙方提出挂失及办理办卡手续，乙方不承担会员卡挂失生效日之前的甲方任何损失；</p>

         <p>四、其它约定</p>

         <p>1、本协议最终解释权在法律规定的范围内归属**所有。</p>

         <p>嗯</p>

         <p>2.这是个协议666222</p>
         * option_code : wx_opencard_protocol
         * is_error : false
         * return_code : SUCCESS
         */

        private long app_id;
        private String id;
        private String trade_type_id;
        private String protocol;
        private String business_id;
        private int status;
        private int is_protocol;
        private String title;
        private long c_time;
        private long u_time;

        public long getApp_id() {
            return app_id;
        }

        public void setApp_id(long app_id) {
            this.app_id = app_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTrade_type_id() {
            return trade_type_id;
        }

        public void setTrade_type_id(String trade_type_id) {
            this.trade_type_id = trade_type_id;
        }

        public int getStatus() {
            return status;
        }

        public int getIs_protocol() {
            return is_protocol;
        }

        public void setIs_protocol(int is_protocol) {
            this.is_protocol = is_protocol;
        }

        public long getC_time() {
            return c_time;
        }

        public void setC_time(long c_time) {
            this.c_time = c_time;
        }

        public long getU_time() {
            return u_time;
        }

        public void setU_time(long u_time) {
            this.u_time = u_time;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(String business_id) {
            this.business_id = business_id;
        }

        public int isStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "ProtocolRes{" +
                    "app_id=" + app_id +
                    ", id='" + id + '\'' +
                    ", trade_type_id='" + trade_type_id + '\'' +
                    ", protocol='" + protocol + '\'' +
                    ", business_id='" + business_id + '\'' +
                    ", status=" + status +
                    ", is_protocol=" + is_protocol +
                    ", title='" + title + '\'' +
                    ", c_time=" + c_time +
                    ", u_time=" + u_time +
                    '}';
        }

    }
}
