package com.senlainc.warsaw.tyurin.util.propertyHandlers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader INSTANCE;
    private Properties properties;

    private PropertyReader() {
        properties = new Properties();
    }

    public static PropertyReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropertyReader();
        }
        return INSTANCE;
    }

    public String readProperties(String propertyName, String path) {
        try (InputStream input = new FileInputStream(path)) {

            properties.load(input);
            String propertyValue = properties.getProperty(propertyName);
            properties.clear();
            return propertyValue;
        } catch (IOException e) {
            System.out.println("Exception occurred during reading from file " + path.substring(path.lastIndexOf("\\") + 1));
        }

        System.out.println(propertyName + " not found");
        return null;
    }
}
