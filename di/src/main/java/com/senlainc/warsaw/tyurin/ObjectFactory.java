package com.senlainc.warsaw.tyurin;

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
        return object;
    }

    private <T> void configureObject(T object) throws Exception {
        injector.injectClasses(object, applicationContext);
        injector.injectConfigProperties(object);
    }
}
