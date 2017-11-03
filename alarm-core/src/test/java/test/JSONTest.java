package test;

import cn.succy.alarm.sender.impl.wechat.TextMessage;
import cn.succy.alarm.sender.impl.wechat.util.TokenUtil;
import com.xiaoleilu.hutool.collection.CollUtil;
import com.xiaoleilu.hutool.json.JSON;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Succy
 * @date 2017-10-17 16:50
 **/

public class JSONTest {
    @Test
    public void testToJson() {
        JSON json = JSONUtil.parse(new TextMessage());
        System.out.println(json.toStringPretty());
    }

    @Test
    public void test() {
        Set<String> set = new HashSet<>();
        set.add("succy");
        set.add("lili");
        set.add("shahg");

        String join = CollUtil.join(set, "|");
        System.out.println(join);
    }

    @Test
    public void testToken() {
        String accessToken = TokenUtil.getAccessToken();
        System.out.println(accessToken);
    }
}
