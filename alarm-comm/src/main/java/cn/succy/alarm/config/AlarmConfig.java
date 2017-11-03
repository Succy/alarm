package cn.succy.alarm.config;

/**
 * 整个报警项目的配置
 *
 * @author Succy
 * @date 2017-10-12 16:40
 **/

@PropertiesConfig(prefix = "alarm")
public final class AlarmConfig {
    private static AlarmConfig config = ConfigManager.getConfig(AlarmConfig.class);
    public static AlarmConfig me() {
        return config;
    }

    private String name;
    private String template;
    private Integer threadPoolSize;
    private String sender;
    private String appName;

    public AlarmConfig() {
        // 默认报警系统名称
        this.name = "报警系统";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "AlarmConfig{" +
                "name='" + name + '\'' +
                ", template='" + template + '\'' +
                ", threadPoolSize=" + threadPoolSize +
                ", sender='" + sender + '\'' +
                '}';
    }
}
