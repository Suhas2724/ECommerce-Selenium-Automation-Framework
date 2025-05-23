package org.seleniumFramework.utils;

import com.google.gson.Gson;
import org.seleniumFramework.constants.Environment;
import org.seleniumFramework.pojo.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONUtility {

    public static org.seleniumFramework.pojo.Environment readJSON(Environment env) {

        Gson gson = new Gson();
        File jsonFile = new File(System.getProperty("user.dir") + "/src/test/java/org/seleniumFramework/config/config.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Config config = gson.fromJson(fileReader, Config.class);
        org.seleniumFramework.pojo.Environment environment = config.getEnvironments().get(env.toString().toUpperCase());
        return environment;

    }

}
