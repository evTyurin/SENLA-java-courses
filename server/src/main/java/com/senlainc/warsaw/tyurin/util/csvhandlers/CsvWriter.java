package com.senlainc.warsaw.tyurin.util.csvhandlers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CsvWriter {

    private final static Logger logger = Logger.getLogger(CsvWriter.class);

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
        } catch (IOException exception) {
            logger.error("Exception occurred during writing into file " + path.substring(path.lastIndexOf("\\") + 1), exception);
        }
        return rawEntities;
    }
}
