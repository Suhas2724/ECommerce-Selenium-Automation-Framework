package org.seleniumFramework.utils;

import org.seleniumFramework.constants.Environment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

    //read property file

    public static String readProperty(Environment environment, String propertyName) {

        File file = new File(System.getProperty("user.dir") + "/src/test/java/org/seleniumFramework/config/" + environment + ".properties");
        FileReader fileReader = null;
        Properties properties = new Properties();
        try {
            fileReader = new FileReader(file);
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyName.toUpperCase());

    }

}
