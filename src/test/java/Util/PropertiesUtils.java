package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    public static Properties prop = new Properties();
    static {
        try {
            FileInputStream fis = new FileInputStream("src\\test\\resources\\config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
