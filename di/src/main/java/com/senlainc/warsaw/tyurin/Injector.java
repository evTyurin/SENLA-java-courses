package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.annotation.ConfigProperty;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    private Properties properties;

    public Injector() {
        properties = new Properties();
        readProperties();
    }

    private void readProperties() {
        try (InputStream input = Injector.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Exception occurred during reading from file");
        }
    }

    public void injectConfigProperties(Object object) throws Exception {
        Class<?> implementationClass = object.getClass();
        for (Field field : implementationClass.getDeclaredFields()) {
            ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
            if (annotation != null) {
                Object value = customConverter(field, properties.getProperty(annotation.propertyKey()));
                field.setAccessible(true);
                field.set(object, value);
            }
        }
    }

    public void injectClasses(Object object, ApplicationContext context) throws Exception {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(DependencyComponent.class)) {
                field.setAccessible(true);
                Object objectFromContext = context.getObject(field.getType());
                field.set(object, objectFromContext);
            }
        }
    }

    private Object customConverter(Field field, String variable){
        final String variableType=field.getType().getSimpleName().toLowerCase();
        switch (variableType){
            case "byte":
                return Byte.parseByte(variable);
            case "short":
                return Short.parseShort(variable);
            case  "integer":
                return Integer.parseInt(variable);
            case "long":
                return Long.parseLong(variable);
            case "float":
                return Float.parseFloat(variable);
            case "double":
                return Double.parseDouble(variable);
            case "boolean":
                return Boolean.parseBoolean(variable);
            case "character":
                return variable.charAt(0);
            default:
                return variable;
        }
    }
}
