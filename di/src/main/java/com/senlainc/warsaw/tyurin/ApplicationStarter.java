package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import org.apache.log4j.Logger;

import java.util.Set;

public class ApplicationStarter {

    private final static Logger logger = Logger.getLogger(ApplicationStarter.class);

    public static ApplicationContext run(String packageToScan) {

        Config config = new Config(packageToScan);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        Set<Class<?>> singletonClasses = config.getScanner().getTypesAnnotatedWith(DependencyClass.class);
        singletonClasses.forEach(aClass -> {
            try {
                context.getObject(aClass);
            } catch (Exception exception) {
                logger.error("Can't get object for context", exception);
            }
        });
        return context;
    }
}
