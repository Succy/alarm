package cn.succy.alarm.test;

import cn.succy.alarm.Level;
import cn.succy.alarm.config.AlarmConfig;
import cn.succy.alarm.template.TemplateManager;
import cn.succy.alarm.template.TemplateModel;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.util.StrUtil;
import org.junit.Test;

/**
 * 模板引擎测试类
 *
 * @author Succy
 * @date 2017-10-12 20:26
 **/

public class TemplateTest {
    @Test
    public void testGenTmplMsg() {
        TemplateModel model = new TemplateModel("用户注册失败", Level.ERROR, "172.168.0.25", DateTime.now().toString(), "你的程序出现bug了，赶快去修复");
        String templateMsg = TemplateManager.getTemplateMsg(model);
        System.out.println(templateMsg.replaceAll(StrUtil.LF, "<br/>"));
    }

    @Test
    public void test() {
        AlarmConfig me = AlarmConfig.me();
        System.out.println(me);
    }
}
