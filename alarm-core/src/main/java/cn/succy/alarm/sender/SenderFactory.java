package cn.succy.alarm.sender;

import cn.hutool.core.lang.Singleton;
import cn.succy.alarm.sender.impl.EmailSenderImpl;
import cn.succy.alarm.sender.impl.SmsSenderImpl;
import cn.succy.alarm.sender.impl.wechat.WeChatSenderImpl;

/**
 * 发送器工厂
 *
 * @author Succy
 * @date 2017-10-15 10:45
 **/

public class SenderFactory {
    public static Sender getSender(String key) {
        if ("email".equals(key)) {
            return Singleton.get(EmailSenderImpl.class);
        }
        if ("sms".equals(key)) {
            return Singleton.get(SmsSenderImpl.class);
        }
        if ("wechat".equals(key)) {
            return Singleton.get(WeChatSenderImpl.class);
        }
        throw new RuntimeException("not support sender type");
    }
}
