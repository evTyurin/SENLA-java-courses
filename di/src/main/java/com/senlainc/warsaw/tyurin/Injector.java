package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.annotation.ConfigProperty;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;

import javax.lang.model.type.PrimitiveType;
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
        if (boolean.class.equals(field.getType())) {
            return Boolean.parseBoolean(variable);
        } else if (byte.class.equals(field.getType())) {
            return Byte.parseByte(variable);
        } else if (short.class.equals(field.getType())) {
            return Short.parseShort(variable);
        } else if (int.class.equals(field.getType())) {
            return Integer.parseInt(variable);
        } else if (long.class.equals(field.getType())) {
            return Long.parseLong(variable);
        } else if (float.class.equals(field.getType())) {
            return Float.parseFloat(variable);
        } else if (double.class.equals(field.getType())) {
            return Float.parseFloat(variable);
        }
        return variable;
    }
}
