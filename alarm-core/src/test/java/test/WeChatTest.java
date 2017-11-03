package test;

import cn.succy.alarm.sender.impl.wechat.util.TokenUtil;
import org.junit.Test;

/**
 * @author Succy
 * @date 2017-10-16 19:57
 **/

public class WeChatTest {
    @Test
    public void testGetToken() {
        String accessToken = TokenUtil.getAccessToken();
        System.out.println(accessToken);
    }
}
