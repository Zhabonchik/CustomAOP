package org.example.customspringaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomAspect {

    @Pointcut("execution(* org.example.customspringaop.KitchenService.takeFoodFromFridge(String)) && args(food)")
    private void takeFoodFromFridgeInKitchen(String food) {
    }

    @Around(value = "takeFoodFromFridgeInKitchen(food)", argNames = "proceedingJoinPoint,food")
    public Object takeFoodFromFridge(ProceedingJoinPoint proceedingJoinPoint, String food) throws Throwable {
        System.out.println("Opened fridge to take " + food);

        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println("Caught Exception in Around " + e.getMessage());
            throw e;
        }

        System.out.println("Closed fridge");
        return result;
    }

    @Before("execution(* org.example.customspringaop.KitchenService.enterKitchen())")
    public void beforeEnteringKitchen() {
        System.out.println("Turned on the lights");
    }

    @After("execution(* org.example.customspringaop.KitchenService.leaveKitchen())")
    public void afterEnteringKitchen() {
        System.out.println("Turned off the lights");
    }

    @AfterThrowing(pointcut = "takeFoodFromFridgeInKitchen(food)", throwing = "exception", argNames = "food,exception")
    public void afterThrowingTakeFoodFromFridgeInKitchen(String food, Exception exception) {
        System.out.println("Aspect: failed to take food from fridge - " + food);
        System.out.println("Aspect: Exception message - " + exception.getMessage());
    }
}
