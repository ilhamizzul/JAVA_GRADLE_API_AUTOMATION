package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static final String CONFIG_FILE = "config.properties";

    public static String getBearerToken() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE);
        properties.load(fileInputStream);
        return properties.getProperty("BEARER_TOKEN");
    }
}
