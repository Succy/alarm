package cn.succy.alarm.template;

import cn.succy.alarm.Level;
import com.xiaoleilu.hutool.util.BeanUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import java.io.StringWriter;
import java.util.Map;

/**
 * 模板模型
 *
 * @author Succy
 * @date 2017-10-12 16:46
 **/

public class TemplateModel {
    /**
     * 警报名称
     */
    private String alarmName;
    /**
     * 警报级别
     */
    private Level level;
    /**
     * 告警的主机
     */
    private String host;
    /**
     * 报警时间
     */
    private String dateTime;
    /**
     * 报警的内容
     */
    private String content;
    /**
     * 报错方法的堆栈，方便开发人员快速定位异常
     */
    private String traceStack;
    /**
     * 异常堆栈
     */
    private String exception;

    public TemplateModel(String alarmName, Level level, String host, String dateTime, String content) {
        this.alarmName = alarmName;
        this.level = level;
        this.host = host;
        this.dateTime = dateTime;
        this.content = content;
    }

    public TemplateModel() {
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTraceStack() {
        return traceStack;
    }

    public void setTraceStack(String traceStack) {
        this.traceStack = traceStack;
    }

    public String getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        if (exception == null) {
            this.exception = "";
            return;
        }

        StringBuilder sb = StrUtil.builder();
        sb.append(exception.toString());
        for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
            sb.append(StrUtil.CRLF);
            sb.append("\tat ").append(stackTraceElement);
        }
        this.exception = sb.toString();
    }

    public Map<String, Object> toMap() {
        return BeanUtil.beanToMap(this);
    }
}
