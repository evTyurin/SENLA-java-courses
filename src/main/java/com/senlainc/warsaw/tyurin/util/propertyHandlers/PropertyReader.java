package com.senlainc.warsaw.tyurin.util.propertyHandlers;

import com.senlainc.warsaw.tyurin.util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyReader {

    private static PropertyReader INSTANCE;
    private Properties properties;
    private Map<String, String> propertiesMap;

    private PropertyReader() {
        properties = new Properties();
        propertiesMap = new HashMap();
        readProperties();
    }

    public static PropertyReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropertyReader();
        }
        return INSTANCE;
    }

    private void readProperties() {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream(Constants.PATH_TO_PROPERTIES)) {
            System.out.println("readProperties");
            properties.load(input);
            propertiesMap.putAll(properties.entrySet()
                    .stream()
                    .collect(Collectors.toMap(e -> e.getKey().toString(),
                            e -> e.getValue().toString())));
        } catch (IOException e) {
            System.out.println("Exception occurred during reading from file " + Constants.PATH_TO_PROPERTIES.substring(Constants.PATH_TO_PROPERTIES.lastIndexOf("\\") + 1));
        }
    }

    public String getProperty(String key) {
        System.out.println(propertiesMap.get(key));
        return propertiesMap.get(key);
    }
}
