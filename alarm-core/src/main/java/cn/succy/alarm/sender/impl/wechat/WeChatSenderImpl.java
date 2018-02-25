package cn.succy.alarm.sender.impl.wechat;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.succy.alarm.config.ConfigManager;
import cn.succy.alarm.config.WeChatConfig;
import cn.succy.alarm.provider.ContactProvider;
import cn.succy.alarm.provider.ContactProviderFactory;
import cn.succy.alarm.receiver.Receiver;
import cn.succy.alarm.sender.Sender;
import cn.succy.alarm.sender.impl.wechat.util.TokenUtil;
import cn.succy.alarm.sender.impl.wechat.util.WeChatConstant;
import cn.succy.alarm.template.TemplateManager;
import cn.succy.alarm.template.TemplateModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 微信发送器实现类
 *
 * @author Succy
 * @date 2017-10-13 11:37
 **/

public class WeChatSenderImpl implements Sender {
    private static final Logger logger = LoggerFactory.getLogger(WeChatSenderImpl.class);
    private WeChatConfig config = ConfigManager.getConfig(WeChatConfig.class);
    private ContactProvider provider = ContactProviderFactory.getContactProvider();

    @Override
    public void send(TemplateModel model) {
        Receiver receiver = provider.getReceiver();
        Set<String> weChatSet = receiver.getWeChatSet();
        String touser = CollUtil.join(weChatSet, "|");

        TextMessage message = new TextMessage();
        message.setAgentid(config.getAgentid());
        message.setTouser(touser);
        // 设置整个部门发送
        if (StrUtil.isNotBlank(config.getPartyid())) {
            message.setToparty(config.getPartyid());
        }
        TextMessage.Text text = new TextMessage.Text();
        text.setContent(TemplateManager.getTemplateMsg(model));
        message.setText(text);

        JSON msgJson = JSONUtil.parse(message);
        String response = HttpRequest.post(WeChatConstant.URL_SEND_MSG + "?access_token=" + TokenUtil.getAccessToken())
                .body(msgJson)
                .execute()
                .body();
        JSONObject respJson = JSONUtil.parseObj(response);
        // 如果不成功，则重试
        int errcode = respJson.getInt(WeChatConstant.ERR_CODE);
        if (errcode != WeChatConstant.SUCCESS_CODE) {
            // 如果是token失效，则刷新token
            if (errcode == WeChatConstant.INVALID_TOKEN_CODE) {
                TokenUtil.initToken();
            }
            // 如果再一次重试不成功，直接抛出运行时异常
            retry(msgJson);
        } else {
            logger.debug("send wechat message success; resp={}", respJson);
        }

    }

    /**
     * 重试机制，如果遇到token失效，则刷新token，重试一次。或者其他异常，也重试一次
     * 如果重试一次之后，还是不成功，直接抛异常了。
     * @param request 请求体json
     */
    private void retry(JSON request) {
        String response = HttpRequest.post(WeChatConstant.URL_SEND_MSG)
                .body(request)
                .execute()
                .body();
        JSONObject respJson = JSONUtil.parseObj(response);

        if (respJson.getInt(WeChatConstant.ERR_CODE) == WeChatConstant.SUCCESS_CODE) {
            logger.debug("send wechat message success; resp=>{}", respJson);
        } else {
            logger.error("send wechat message failure; resp={}", respJson);
            throw new RuntimeException("send wechat message failure");
        }
    }
}
