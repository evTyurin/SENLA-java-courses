package com.senlainc.warsaw.tyurin;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Config {

    private Reflections scanner;
    private Map<Class, Class> ifc2Impl;

    public Config(String packageToScan) {
        this.ifc2Impl = new HashMap<>();
        this.scanner = new Reflections(packageToScan);
    }

    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        return ifc2Impl.computeIfAbsent(ifc, aClass -> {
           Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
           if (classes.size() != 1) {
               throw new RuntimeException(ifc + " has 0 or more than one implementations. Please update your configuration.");
           }
           return classes.iterator().next();
        });
    }


    public Reflections getScanner() {
        return scanner;
    }
}
