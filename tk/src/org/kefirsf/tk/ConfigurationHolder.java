package org.kefirsf.tk;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
public class ConfigurationHolder {
    private static ConfigurationHolder instance = new ConfigurationHolder();
    private final Properties properties;

    public static ConfigurationHolder getInstance() {
        return instance;
    }

    public String get(String name) {
        return properties.getProperty(name);
    }

    private ConfigurationHolder() {
        properties = new Properties();
        try {
            InputStream stream =
                    getClass().getClassLoader().getResourceAsStream("classpath:configuration.properties");
            try {
                properties.load(stream);
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't load configuration.", e);
        }
    }
}
