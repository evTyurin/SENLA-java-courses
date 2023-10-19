package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;

import java.util.Set;

public class ApplicationStarter {
    public static ApplicationContext run(String packageToScan) {

        Config config = new Config(packageToScan);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        Set<Class<?>> singletonClasses = config.getScanner().getTypesAnnotatedWith(DependencyClass.class);
        singletonClasses.forEach(aClass -> {
            try {
                context.getObject(aClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return context;
    }
}
