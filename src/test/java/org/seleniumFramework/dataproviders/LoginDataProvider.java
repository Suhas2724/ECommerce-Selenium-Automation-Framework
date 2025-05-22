package org.seleniumFramework.dataproviders;

import com.google.gson.Gson;
import org.seleniumFramework.pojo.TestData;
import org.seleniumFramework.pojo.User;
import org.seleniumFramework.utils.CSVReaderUtility;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "LoginTestDataProvider")
    public Iterator<User> loginDataProvider() throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/testData/loginData.json");
        FileReader fileReader = new FileReader(file);
        TestData testData = gson.fromJson(fileReader, TestData.class);

        List<User> dataToReturn = new ArrayList<>(testData.getData());

        return dataToReturn.iterator();
    }

    @DataProvider(name = "LoginTestCSVDataProvider")
    public Iterator<User> loginCSVDataProvider(){
        return CSVReaderUtility.readCSVFile("loginData.csv");
    }
}
