package com.senlainc.warsaw.tyurin.util.jsonhandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@DependencyClass
public class JsonReader {

    private final static Logger logger = Logger.getLogger(JsonReader.class);

    public <T> List<T> readEntities(Class<T> entityClass, String path) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(new File(path), objectMapper.getTypeFactory().constructCollectionType(List.class, entityClass));
        } catch (IOException exception) {
            logger.error("Exception occurred during reading from file " + path.substring(path.lastIndexOf("\\") + 1), exception);
        }

        return Collections.EMPTY_LIST;
    }
}
