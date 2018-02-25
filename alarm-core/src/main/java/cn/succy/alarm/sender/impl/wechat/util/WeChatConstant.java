package cn.succy.alarm.sender.impl.wechat.util;

/**
 * 记录微信相关的一些常量
 *
 * @author Succy
 * @date 2017-10-16 18:49
 **/

public interface WeChatConstant {
    String CORP_ID = "corpid";
    String CORP_SECRET = "corpsecret";
    String ACCESS_TOKEN = "access_token";
    String EXPIRES_IN = "expires_in";
    String AGENT_ID = "agentid";
    String MESSAGE_TYPE = "text";

    String URL_GET_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    String URL_SEND_MSG = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

    String ERR_CODE = "errcode";
    int SUCCESS_CODE = 0;
    int INVALID_TOKEN_CODE = 40014;
}
