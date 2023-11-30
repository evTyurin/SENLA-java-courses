package com.senlainc.warsaw.tyurin.util.jsonhandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class JsonWriter {

    private final static Logger logger = Logger.getLogger(JsonWriter.class);

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
