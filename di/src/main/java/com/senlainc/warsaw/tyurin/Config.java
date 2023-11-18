package com.senlainc.warsaw.tyurin;

import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Config {

    private final static Logger logger = Logger.getLogger(Config.class);

    private Reflections scanner;
    private Map<Class, Class> diContainer;

    public Config(String packageToScan) {
        this.diContainer = new HashMap<>();
        this.scanner = new Reflections(packageToScan);
    }

    public <T> Class<? extends T> getImplClass(Class<T> injectableInterface) {
        return diContainer.computeIfAbsent(injectableInterface, aClass -> {
           Set<Class<? extends T>> classes = scanner.getSubTypesOf(injectableInterface);
           if (classes.size() != 1) {
               logger.error(injectableInterface + " has 0 or more than one implementations");
           }
           return classes.iterator().next();
        });
    }

    public Reflections getScanner() {
        return scanner;
    }
}
