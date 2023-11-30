package com.senlainc.warsaw.tyurin.util.csvhandlers;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
public class CsvReader {

    private final static Logger logger = Logger.getLogger(CsvReader.class);

    public List<String> readEntities(String path) {
        List<String> rawEntities = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            bufferedReader.readLine();
            String entity = null;
            while ((entity = bufferedReader.readLine()) != null) {
                rawEntities.add(entity);
            }
        } catch (IOException exception) {
            logger.error("Exception occurred during reading from file " + path.substring(path.lastIndexOf("\\") + 1), exception);
        }
        return rawEntities;
    }
}
