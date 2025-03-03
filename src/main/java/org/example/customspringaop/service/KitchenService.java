package org.example.customspringaop.service;

import org.example.customspringaop.exceptions.InvalidFoodException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {

    private final Logger serviceLogger = LoggerFactory.getLogger(KitchenService.class);

    public void enterKitchen() {
        serviceLogger.info("Entered the kitchen");
    }

    public void leaveKitchen() {
        serviceLogger.info("Left the kitchen");
    }

    public void takeFoodFromFridge(String food) {
        validateFood(food);
        serviceLogger.info("Took {} from the fridge", food);
    }

    private void validateFood(String food) {
        if (food == null || food.isEmpty()) {
            throw new InvalidFoodException("Invalid food");
        }
    }

}
