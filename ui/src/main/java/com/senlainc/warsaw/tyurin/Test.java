package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.menu.MenuController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Test {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.senlainc.warsaw.tyurin");
        MenuController menuController = context.getBean(MenuController.class);
        menuController.run();
    }
}
