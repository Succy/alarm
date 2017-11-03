package cn.succy.alarm.sender.impl;

import cn.succy.alarm.config.AlarmConfig;
import cn.succy.alarm.config.ConfigManager;
import cn.succy.alarm.config.EmailSenderConfig;
import cn.succy.alarm.provider.ContactProvider;
import cn.succy.alarm.provider.ContactProviderFactory;
import cn.succy.alarm.receiver.Receiver;
import cn.succy.alarm.sender.Sender;
import cn.succy.alarm.template.TemplateManager;
import cn.succy.alarm.template.TemplateModel;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 邮件发送器实现类
 *
 * @author Succy
 * @date 2017-10-13 11:36
 **/

public class EmailSenderImpl implements Sender {
    private static final Logger logger = LoggerFactory.getLogger(EmailSenderImpl.class);
    private ContactProvider provider = ContactProviderFactory.getContactProvider();
    private EmailSenderConfig config = ConfigManager.getConfig(EmailSenderConfig.class);
    /**
     * ALARM_SYS_NAME 报警系统的名称
     */
    private static final String ALARM_SYS_NAME = AlarmConfig.me().getName();

    @Override
    public void send(TemplateModel model) {
        HtmlEmail email = new HtmlEmail();
        // 邮件服务器域名
        email.setHostName(config.getHostname());
        // 邮件服务器smtp协议端口
        email.setSmtpPort(config.getPort());
        // 邮箱账户
        email.setAuthentication(config.getUsername(), config.getPassword());
        // 邮件的字符集
        email.setCharset(config.getCharset());
        boolean isUserSSL = config.isUseSSL();
        // 是否开启ssl
        email.setSSLOnConnect(isUserSSL);
        if (isUserSSL) {
            email.setSslSmtpPort(config.getSslPort());
        }

        Receiver receiver = provider.getReceiver();
        Set<String> emailSet = receiver.getEmailSet();
        try {
            email.setFrom(config.getUsername(), ALARM_SYS_NAME);
            for (String to : emailSet) {
                email.addTo(to);
            }
            String template = TemplateManager.getTemplateMsg(model);
            template = template.replaceAll(StrUtil.LF, "<br/>").replaceAll("\\t", "&nbsp;&nbsp;");
            email.setSubject(model.getAlarmName());
            email.setHtmlMsg(template);
            email.send();
            logger.debug("EmailSender has send a email");
        } catch (EmailException e) {
            logger.error("send email failure", e);
            throw new RuntimeException(e);
        }
    }
}
