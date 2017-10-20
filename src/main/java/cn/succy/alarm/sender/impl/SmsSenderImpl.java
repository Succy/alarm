package cn.succy.alarm.sender.impl;

import cn.succy.alarm.sender.Sender;
import cn.succy.alarm.template.TemplateModel;

/**
 * 短信发送器实现类
 *
 * @author Succy
 * @date 2017-10-13 11:37
 **/

public class SmsSenderImpl implements Sender {
    @Override
    public void send(TemplateModel model) {
        throw new RuntimeException("暂不支持短信发送");
    }
}
