package com.senlainc.warsaw.tyurin.util.fileHandlers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private static CsvReader INSTANCE;

    private CsvReader() {}

    public static CsvReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CsvReader();
        }
        return INSTANCE;
    }

    public List<String> readEntities(String path) {
        List<String> rawEntities = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            String entity = null;
            while ((entity = bufferedReader.readLine()) != null) {
                rawEntities.add(entity);
            }
        } catch (IOException e) {
            System.out.println("Exception occurred during reading from file " + path.substring(path.lastIndexOf("\\") + 1));
        }
        return rawEntities;
    }
}
