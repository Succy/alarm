package cn.succy.alarm;

import cn.succy.alarm.config.AlarmConfig;
import cn.succy.alarm.template.TemplateModel;
import cn.succy.mq.producer.Producer;
import cn.succy.mq.producer.QueueProducer;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.util.NetUtil;
import com.xiaoleilu.hutool.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 专为Mq设计的报警
 *
 * @author Succy
 * @date 2017-11-01 21:51
 **/

public class Alarm2Mq {
    private static final AlarmConfig CONFIG = AlarmConfig.me();
    private static final Logger logger = LoggerFactory.getLogger(Alarm2Mq.class);

    public static void info(String alarmName, String content) {
        info(alarmName, content, null);
    }

    /**
     * info级别警报
     *
     * @param alarmName 警报名称
     * @param content   警报内容
     */
    public static void info(String alarmName, String content, Throwable e) {
        send(Level.INFO, alarmName, content, e);
    }

    public static void debug(String alarmName, String content) {
        debug(alarmName, content, null);
    }

    /**
     * debug级别警报
     *
     * @param alarmName 警报名称
     * @param content   警报内容
     */
    public static void debug(String alarmName, String content, Throwable e) {
        send(Level.DEBUG, alarmName, content, e);
    }

    public static void warn(String alarmName, String content) {
        warn(alarmName, content, null);
    }

    /**
     * warn级别警报
     *
     * @param alarmName 警报名称
     * @param content   警报内容
     */
    public static void warn(String alarmName, String content, Throwable e) {
        send(Level.WARN, alarmName, content, e);
    }

    public static void error(String alarmName, String content) {
        error(alarmName, content, null);
    }

    /**
     * error级别警报
     *
     * @param alarmName 警报名称
     * @param content   警报内容
     */
    public static void error(String alarmName, String content, Throwable e) {
        send(Level.ERROR, alarmName, content, e);
    }
    private static void send(Level level, String alarmName, String content, Throwable e) {
        TemplateModel model = new TemplateModel();
        model.setAlarmName(alarmName);
        model.setAppName(CONFIG.getAppName());
        model.setContent(content);
        model.setDateTime(DateTime.now().toString());
        model.setLevel(level);
        model.setException(e);
        String traceStack = (e == null) ? ThreadUtil.getStackTraceElement(6).toString()
                : e.getStackTrace()[0].toString();
        model.setTraceStack(traceStack);
        // 获取当前机器的Ip
        String localhostStr = NetUtil.getLocalhostStr();
        model.setHost(localhostStr);

        // 发送到MQ
        Producer producer = new QueueProducer(false, Constant.ALAEM_QUEUE_NAME);
        producer.send(model);
        producer.close();
    }
}
