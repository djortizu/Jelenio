package utilities;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class QAConfigReader {

    private static String getConfigValue(String value){
        Properties p = new Properties();
        try{
            FileInputStream file = new FileInputStream("src/main/java/common/jelenio.config");
            p.load(file);
        }
        catch(IOException ex){
            Assert.fail(String.format("Unable to read file. Error: %s", ex.getMessage()));

        }
        return p.getProperty(value);
    }

    public static String gridUrl = getConfigValue("gridurl");
    public static String windowsPath = getConfigValue("linuxpath");
    public static String linuxPath = getConfigValue("windowspath");


}
