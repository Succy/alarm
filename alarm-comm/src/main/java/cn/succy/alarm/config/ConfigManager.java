package cn.succy.alarm.config;

import com.xiaoleilu.hutool.bean.BeanUtil;
import com.xiaoleilu.hutool.convert.Convert;
import com.xiaoleilu.hutool.lang.Filter;
import com.xiaoleilu.hutool.setting.dialect.Props;
import com.xiaoleilu.hutool.util.CharsetUtil;
import com.xiaoleilu.hutool.util.ClassUtil;
import com.xiaoleilu.hutool.util.ReflectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置管理器
 *
 * @author Succy
 * @date 2017-10-11 19:20
 **/

public class ConfigManager {
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static final String DEFAULT_CONFIG_FILE = "alarm.properties";
    private static final Map<Class<?>, Object> CONFIG_OBJ_MAP = new ConcurrentHashMap<Class<?>, Object>();

    @SuppressWarnings("unchecked")
    public static <T> T getConfig(Class<T> clazz) {
        if (CONFIG_OBJ_MAP.containsKey(clazz)) {
            return (T) CONFIG_OBJ_MAP.get(clazz);
        }
        return (T) addConfig(clazz);
    }

    private static Object addConfig(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(PropertiesConfig.class)) {
            logger.error("the arg class is not a config class");
            throw new RuntimeException("the arg class is not a config class");
        }

        if (!BeanUtil.isBean(clazz)) {
            logger.error("class is not a bean,please add the setter and getter methods");
            throw new RuntimeException("class is not a bean,please add the setter and getter methods");
        }
        PropertiesConfig annotation = clazz.getAnnotation(PropertiesConfig.class);
        String prefix = annotation.prefix();
        String file = annotation.file();
        return get(clazz, file, prefix);
    }

    private static Object get(Class<?> clazz, String profile, String prefix) {
        if (StrUtil.isBlank(profile)) {
            profile = DEFAULT_CONFIG_FILE;
        }
        Props prop = new Props(profile, CharsetUtil.CHARSET_UTF_8);
        Object instance = ReflectUtil.newInstance(clazz);

        // 取出类中所有的setter方法
        List<Method> setMethods = ClassUtil.getPublicMethods(clazz, new Filter<Method>() {
            @Override
            public boolean accept(Method method) {
                return method.getName().startsWith("set") && method.getName().length() > 3 && method.getParameterTypes().length == 1;
            }
        });

        for (Method method : setMethods) {
            String key = StrUtil.getGeneralField(method.getName());
            // 支持同一个前缀下，有多个点构成的配置名，
            // 例如alarm.email.user,在javabean里边，只需使用驼峰即可=>emailUser
            key = StrUtil.toUnderlineCase(key).replaceAll("_", ".");
            if (StrUtil.isNotBlank(prefix)) {
                key = prefix + "." + key;
            }
            Object value = prop.getProperty(key);
            logger.debug("{} => {}", key, value);
            // 对获取到的value进行类型转换
            value = Convert.convert(method.getParameterTypes()[0], value);
            ReflectUtil.invoke(instance, method, value);
        }
        CONFIG_OBJ_MAP.put(clazz, instance);
        return instance;
    }
}
