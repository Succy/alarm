package cn.succy.alarm.provider;

import cn.succy.alarm.config.ConfigManager;
import cn.succy.alarm.config.ProviderConfig;
import cn.succy.alarm.provider.impl.JdbcContactProviderImpl;
import cn.succy.alarm.provider.impl.JsonContactProviderImpl;
import com.xiaoleilu.hutool.lang.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Succy
 * @date 2017-10-13 21:44
 **/

public class ContactProviderFactory {
    private static final Logger logger = LoggerFactory.getLogger(ContactProviderFactory.class);
    private static ProviderConfig providerConfig = ConfigManager.getConfig(ProviderConfig.class);
    private static final String JSON_TYPE = "json";
    private static final String JDBC_TYPE = "jdbc";

    public static ContactProvider getContactProvider() {
        String type = providerConfig.getType();
        switch (type) {
            case JSON_TYPE:
                logger.debug("contact type is {}", JSON_TYPE);
                return Singleton.get(JsonContactProviderImpl.class);
            case JDBC_TYPE:
                logger.debug("contact type is {}", JDBC_TYPE);
                return Singleton.get(JdbcContactProviderImpl.class);
            default:
                logger.error("Type mismatch, please type json or jdbc");
                throw new RuntimeException("Type mismatch, please type json or jdbc");
        }
    }
}
