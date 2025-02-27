package org.example.customspringaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CustomSpringAopApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CustomSpringAopApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        ApplicationContext context = app.run(args);

        KitchenService kitchenService = (KitchenService) context.getBean("kitchenService");

        kitchenService.enterKitchen();
        kitchenService.takeFoodFromFridge("");
        kitchenService.leaveKitchen();
    }

}
