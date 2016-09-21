package logic.processcommand;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by aefrd on 20.09.2016.
 */
public class ConfigurationManager {
    private static Properties config;
    // ����� ��������� ���������� �� ����� config.properties

    private ConfigurationManager() {
    }

    public static boolean initProperty(HttpServletRequest request) {
        try {
            InputStream inputStream = request.getServletContext().getResourceAsStream("/WEB-INF/classes/config.properties");
            config = new Properties();
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String getProperty(String key) {
        return config.getProperty(key);
    }
}