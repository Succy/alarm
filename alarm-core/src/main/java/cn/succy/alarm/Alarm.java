package cn.succy.alarm;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.StrUtil;
import cn.succy.alarm.config.AlarmConfig;
import cn.succy.alarm.sender.Sender;
import cn.succy.alarm.sender.SenderFactory;
import cn.succy.alarm.template.TemplateModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * 警报
 *
 * @author Succy
 * @date 2017-10-13 17:16
 **/

public class Alarm {
    private static final Logger logger = LoggerFactory.getLogger(Alarm.class);
    private static AlarmConfig config = AlarmConfig.me();
    private static final List<Sender> SENDER_LIST = new ArrayList<>();
    private static final ExecutorService EXECUTOR_SERVICE;

    static {
        String senderKeyStr = config.getSender();
        // 构造一个线程池,如果没有在配置文件中指定线程数，默认构造10个线程
        int threadPoolSize = config.getThreadPoolSize() == 0 ? 10 : config.getThreadPoolSize();
        logger.debug("threadPoolSize={}", threadPoolSize);
        EXECUTOR_SERVICE = ThreadUtil.newExecutor(threadPoolSize);

        List<String> senderKeys = StrUtil.split(senderKeyStr, StrUtil.C_COMMA);
        for (String key : senderKeys) {
            Sender sender = SenderFactory.getSender(key);
            SENDER_LIST.add(sender);
        }
    }

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
        model.setAppName(config.getAppName());
        model.setContent(content);
        model.setDateTime(DateTime.now().toString());
        model.setLevel(level);
        model.setException(e);

        // 获取当前机器的Ip
        String localhostStr = NetUtil.getLocalhostStr();
        model.setHost(localhostStr);

        String traceStack = (e == null) ? ThreadUtil.getStackTraceElement(6).toString()
                : e.getStackTrace()[0].toString();
        model.setTraceStack(traceStack);
        send(model);
    }

    public static void send(TemplateModel model) {
        EXECUTOR_SERVICE.execute(new SenderTask(model));
    }

    /**
     * 内部类实现一个发送任务，每次发送都会单独通过一个线程发送，线程存放在线程池中
     * 避免每次发送单独创建线程的开销
     */
    private static class SenderTask implements Runnable {
        private TemplateModel model;

        SenderTask(TemplateModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            for (Sender sender : SENDER_LIST) {
                sender.send(model);
            }
        }
    }
}
