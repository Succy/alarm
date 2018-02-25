package cn.succy.alarm.sender.impl.wechat.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.succy.alarm.config.ConfigManager;
import cn.succy.alarm.config.WeChatConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信token的工具类
 *
 * @author Succy
 * @date 2017-10-16 19:48
 **/

public class TokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);
    private static WeChatConfig weChatConfig = ConfigManager.getConfig(WeChatConfig.class);
    private static String accessToken;
    private static long expiredate = -1L;


    private TokenUtil() {

    }

    /**
     * 初始化token
     */
    public static void initToken() {
        Map<String, Object> params = new HashMap<>();
        params.put(WeChatConstant.CORP_ID, weChatConfig.getCorpid());
        params.put(WeChatConstant.CORP_SECRET, weChatConfig.getCorpsecret());
        String result = HttpUtil.get(WeChatConstant.URL_GET_TOKEN, params);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        accessToken = jsonObject.getStr(WeChatConstant.ACCESS_TOKEN);
        logger.debug("access_token=", accessToken);
        expiredate = System.currentTimeMillis() + jsonObject.getInt(WeChatConstant.EXPIRES_IN) * 1000;
    }

    private static Boolean isExpired() {
        return System.currentTimeMillis() > expiredate;
    }

    /**
     * 获取token
     * @return 获取到的token
     */
    public static String getAccessToken() {
        if (accessToken == null || isExpired()) {
            initToken();
        }
        return accessToken;
    }

}
