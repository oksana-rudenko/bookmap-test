package org.bookmap.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {

    public String readDataFromFile(String pathFile) {
        File file = new File(pathFile);
        StringBuilder dataFileReader = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value !=null) {
                dataFileReader.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't get data from file by path: " + pathFile, e);
        }
        return dataFileReader.toString();
    }

    public void writeDataToFile(String path, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file by path:  " + path, e);
        }
    }
}
