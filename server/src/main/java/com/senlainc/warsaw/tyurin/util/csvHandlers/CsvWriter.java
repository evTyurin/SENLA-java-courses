package com.senlainc.warsaw.tyurin.util.csvHandlers;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@DependencyClass
public class CsvWriter {

    private static CsvWriter INSTANCE;

//    private CsvWriter() {}

    public static CsvWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CsvWriter();
        }
        return INSTANCE;
    }

    public List<String> writeEntities(List<String> rawEntities, String header, String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(header);
            rawEntities.forEach(rawEntity -> {
                try {
                    bufferedWriter.newLine();
                    bufferedWriter.write(rawEntity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("Exception occurred during writing into file " + path.substring(path.lastIndexOf("\\") + 1));
        }
        return rawEntities;
    }
}
