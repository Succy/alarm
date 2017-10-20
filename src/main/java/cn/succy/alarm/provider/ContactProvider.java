package cn.succy.alarm.provider;

import cn.succy.alarm.receiver.Receiver;

/**
 * 定义一个联系人提供者的接口，因为本系统不仅仅支持配置文件定义发送者
 * 也支持将联系人存放在数据库中，因此，定义一个接口，方便拓展。
 */
public interface ContactProvider {
    Receiver getReceiver();
}
