package com.senlainc.warsaw.tyurin.util.jsonHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@DependencyClass
public class JsonWriter {

    private final static Logger logger = Logger.getLogger(JsonWriter.class);

    private static JsonWriter INSTANCE;

    public static JsonWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JsonWriter();
        }
        return INSTANCE;
    }

    public <T> void writeEntities(List<T> entities, String path) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new FileWriter(path), entities);
        } catch (IOException exception) {
            logger.error("Exception occurred during writing into file " + path.substring(path.lastIndexOf("\\") + 1), exception);
        }
    }
}
