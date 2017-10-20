package cn.succy.alarm.sender;

import cn.succy.alarm.template.TemplateModel;

public interface Sender {
    void send(TemplateModel model);
}
