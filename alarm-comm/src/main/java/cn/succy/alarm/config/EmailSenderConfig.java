package cn.succy.alarm.config;

/**
 * 邮件发送器相关配置类
 *
 * @author Succy
 * @date 2017-10-13 16:20
 **/
@PropertiesConfig(prefix = "alarm.email")
public class EmailSenderConfig {
    private String username;
    private String password;
    private String hostname;
    private int port;
    private String charset;
    private boolean ssl;
    private String sslPort;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isUseSSL() {
        return ssl;
    }

    public void setSsl(boolean useSSL) {
        this.ssl = useSSL;
    }

    public String getSslPort() {
        return sslPort;
    }

    public void setSslPort(String sslPort) {
        this.sslPort = sslPort;
    }
}
