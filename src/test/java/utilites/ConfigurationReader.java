package utilites;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    public ConfigurationReader() {
        properties = new Properties();
        FileInputStream input=null;
        try{
            String filePath = "src/test/resource/TestData/config.properties";
            // load a properties file from class path, inside static method
            input=new FileInputStream(filePath);
            properties.load(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAppUrl() {
        return properties.getProperty("app.url");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getTestString() {
        return properties.getProperty("testString");
    }
    public static String getReportConfigPath() {
        return properties.getProperty("reportConfigPath");
    }


}
