package com.senlainc.warsaw.tyurin.util.jsonHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.senlainc.warsaw.tyurin.annotation.DependencyClass;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@DependencyClass
public class JsonWriter {

    private static JsonWriter INSTANCE;

 //   private JsonWriter() {}

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
        } catch (IOException e) {
            System.out.println("Exception occurred during writing into file " + path.substring(path.lastIndexOf("\\") + 1));
        }
    }
}
