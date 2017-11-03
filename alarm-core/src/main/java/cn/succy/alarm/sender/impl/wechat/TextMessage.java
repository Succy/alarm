package cn.succy.alarm.sender.impl.wechat;

import cn.succy.alarm.sender.impl.wechat.util.WeChatConstant;

/**
 * 文本类型的消息对象
 *
 * @author Succy
 * @date 2017-10-17 16:38
 **/

public class TextMessage {
    /**
     * 如果只是设置该字段，那么会在应用可见范围内，发送给指定的用户。
     */
    private String touser;
    /**
     * 一旦设置了该字段，那么会在应用可见范围内，发送给整个部门的人
     * 由于采用了配置可见范围，配置用户id的方式，因此，默认不设置这
     * 个字段。
     */
    private String toparty;
    private String totag;
    private String msgtype;
    private int agentid;
    private int safe;
    private Text text;

    public TextMessage() {
        this.msgtype = WeChatConstant.MESSAGE_TYPE;
        // 如果不对touser进行设置，默认@all，toparty,totag就不需要设置了
        this.touser = "@all";
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getTotag() {
        return totag;
    }

    public void setTotag(String totag) {
        this.totag = totag;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public int getAgentid() {
        return agentid;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public int getSafe() {
        return safe;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    static class Text {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
