package test;

import cn.succy.alarm.Alarm;

/**
 * 测试Alarm发送警报，由于junit不支持多线程，因此，直接使用main
 * @author Succy
 * @date 2017-10-15 11:08
 **/

public class AlarmTest {

    public static void main(String[] args) {
        Alarm.debug("发送微信+邮箱测试", "运维一组，suchan可以收到");
    }

}
