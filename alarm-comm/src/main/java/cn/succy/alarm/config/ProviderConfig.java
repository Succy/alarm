package cn.succy.alarm.config;

/**
 * @author Succy
 * @date 2017-10-13 18:03
 **/
@PropertiesConfig(prefix = "alarm.provider")
public class ProviderConfig {
    private String type;
    private String jsonfilePath;
    private String jdbcUsername;
    private String jdbcPassword;
    private String jdbcUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJsonfilePath() {
        return jsonfilePath;
    }

    public void setJsonfilePath(String jsonfilePath) {
        this.jsonfilePath = jsonfilePath;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }
}
