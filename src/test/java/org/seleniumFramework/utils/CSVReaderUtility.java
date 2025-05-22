package org.seleniumFramework.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.seleniumFramework.pojo.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

    public static Iterator<User> readCSVFile(String fileName) {

        File file = new File(System.getProperty("user.dir") + "/src/test/resources/testData/" + fileName);
        FileReader fileReader = null;
        CSVReader csvReader;
        String[] line;
        List<User> userList;
        User userData;


        try {
            fileReader = new FileReader(file);
            csvReader = new CSVReader(fileReader);
            csvReader.readNext();//gives first line i.e, header of csv, which store column info, hence going next.

            userList = new ArrayList<>();

            while ((line = csvReader.readNext()) != null) {
                userData = new User(line[0], line[1]);
                userList.add(userData);
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return userList.iterator();

    }

}
