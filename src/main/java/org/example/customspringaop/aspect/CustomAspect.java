package org.example.customspringaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomAspect {

    private final Logger aspectLogger = LoggerFactory.getLogger(CustomAspect.class);

    @Pointcut("execution(* org.example.customspringaop.service.KitchenService.takeFoodFromFridge(String)) && args(food)")
    private void takeFoodFromFridgeInKitchen(String food) {
    }

    @Around(value = "takeFoodFromFridgeInKitchen(food)", argNames = "proceedingJoinPoint,food")
    public Object takeFoodFromFridge(ProceedingJoinPoint proceedingJoinPoint, String food) throws Throwable {
        aspectLogger.info("Opened the fridge to take {}", food);

        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            aspectLogger.error("Caught Exception in Around {}", e.getMessage());
            throw e;
        }

        aspectLogger.info("Closed the fridge");
        return result;
    }

    @Before("execution(* org.example.customspringaop.service.KitchenService.enterKitchen())")
    public void beforeEnteringKitchen() {
        aspectLogger.info("Turned on the lights");
    }

    @After("execution(* org.example.customspringaop.service.KitchenService.leaveKitchen())")
    public void afterEnteringKitchen() {
        aspectLogger.info("Turned off the lights");
    }

    @AfterThrowing(pointcut = "takeFoodFromFridgeInKitchen(food)", throwing = "exception", argNames = "food,exception")
    public void afterThrowingTakeFoodFromFridgeInKitchen(String food, Exception exception) {
        aspectLogger.info("Aspect: failed to take food from fridge - {}", food);
        aspectLogger.info("Aspect: Exception message - {}", exception.getMessage());
    }
}
