package cn.succy.alarm.test;

import cn.succy.alarm.Alarm2Mq;
import org.junit.Test;

/**
 * @author Succy
 * @date 2017-11-01 22:04
 **/

public class Alarm2MqTest {
    @Test
    public void testSendAlarm() {
        Alarm2Mq.debug("MQ测试警报", "测试下看看，能不能发送到MQ");
    }
}
