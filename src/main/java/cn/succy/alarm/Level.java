package cn.succy.alarm;

/**
 * 发送的警报级别的枚举
 */
public enum Level {
    DEBUG("debug"), INFO("info"), WARN("warn"), ERROR("error");
    private String level;

    Level(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
