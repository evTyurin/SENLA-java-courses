package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private ObjectFactory objectFactory;
    private Map<Class, Object> applicationObjects = new ConcurrentHashMap<>();
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) throws Exception {
        if (applicationObjects.containsKey(type)) {
            return (T) applicationObjects.get(type);
        }

        Class<? extends T> implementationClass = type;
        if (type.isInterface()) {
            implementationClass = config.getImplClass(type);
        }
        T object = objectFactory.createObject(implementationClass);

        if (implementationClass.isAnnotationPresent(DependencyClass.class)) {
            applicationObjects.put(type, object);
        }
        return object;
    }

    public void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }
}
