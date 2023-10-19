package com.senlainc.warsaw.tyurin.util.csvHandlers;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
public class CsvReader {
    private static CsvReader INSTANCE;

//    private CsvReader() {}

    public static CsvReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CsvReader();
        }
        return INSTANCE;
    }

    public List<String> readEntities(String path) {
        List<String> rawEntities = new ArrayList<>();
        System.out.println(path);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            System.out.println("1");
            bufferedReader.readLine();
            System.out.println("2");
            String entity = null;
            while ((entity = bufferedReader.readLine()) != null) {
                System.out.println("4454");
                rawEntities.add(entity);
            }
        } catch (IOException e) {
            System.out.println("Exception occurred during reading from file " + path.substring(path.lastIndexOf("\\") + 1));
        }
        return rawEntities;
    }
}
