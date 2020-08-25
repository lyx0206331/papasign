package com.papa.signature.model;

/**
 * @author PAPA-GuoBa
 * @Desc
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/26 14:21
 */
public class ProtocolRes {


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

    private String option_value;
    private String option_code;
    private boolean is_error;
    private String return_code;

    public String getOption_value() {
        return option_value;
    }

    public void setOption_value(String option_value) {
        this.option_value = option_value;
    }

    public String getOption_code() {
        return option_code;
    }

    public void setOption_code(String option_code) {
        this.option_code = option_code;
    }

    public boolean isIs_error() {
        return is_error;
    }

    public void setIs_error(boolean is_error) {
        this.is_error = is_error;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    @Override
    public String toString() {
        return "ProtocolRes{" +
                "option_value='" + option_value + '\'' +
                ", option_code='" + option_code + '\'' +
                ", is_error=" + is_error +
                ", return_code='" + return_code + '\'' +
                '}';
    }
}
