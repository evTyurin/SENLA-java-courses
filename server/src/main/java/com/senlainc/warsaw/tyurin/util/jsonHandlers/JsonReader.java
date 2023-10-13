package com.senlainc.warsaw.tyurin.util.jsonHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.senlainc.warsaw.tyurin.annotation.DependencyClass;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@DependencyClass
public class JsonReader {
    private static JsonReader INSTANCE;

//    private JsonReader() {}

    public static JsonReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JsonReader();
        }
        return INSTANCE;
    }

    public <T> List<T> readEntities(Class<T> entityClass, String path) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(new File(path), objectMapper.getTypeFactory().constructCollectionType(List.class, entityClass));
        } catch (IOException e) {
            System.out.println("Exception occurred during reading from file " + path.substring(path.lastIndexOf("\\") + 1));
        }

        return Collections.EMPTY_LIST;
    }
}
