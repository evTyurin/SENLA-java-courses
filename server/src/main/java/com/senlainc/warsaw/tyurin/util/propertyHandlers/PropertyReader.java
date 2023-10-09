package com.senlainc.warsaw.tyurin.util.propertyHandlers;

import com.senlainc.warsaw.tyurin.util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader INSTANCE;
    private Properties properties;

    private PropertyReader() {
        properties = new Properties();
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
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Exception occurred during reading from file " + Constants.PATH_TO_PROPERTIES.substring(Constants.PATH_TO_PROPERTIES.lastIndexOf("\\") + 1));
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
