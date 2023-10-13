package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;

import java.lang.reflect.Method;

public class ObjectFactory {
    private final ApplicationContext applicationContext;
    private Injector injector;

    public ObjectFactory(ApplicationContext context) {
        this.applicationContext = context;
        injector = new Injector();
    }

    public <T> T createObject(Class<T> implementation) throws Exception {
        T object = implementation.getDeclaredConstructor().newInstance();
        configureObject(object);
        invokeInit(implementation, object);
        return object;
    }

    private <T> void invokeInit(Class<T> implementation, T object) throws Exception {
        for (Method method : implementation.getMethods()) {
            if (method.isAnnotationPresent(DependencyInitMethod.class)) {
                method.invoke(object);
            }
        }
    }

    private <T> void configureObject(T object) throws Exception {
        injector.injectClasses(object, applicationContext);
        injector.injectConfigProperties(object);
    }
}
