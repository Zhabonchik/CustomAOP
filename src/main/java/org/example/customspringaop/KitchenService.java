package org.example.customspringaop;

import org.example.customspringaop.exceptions.InvalidFoodException;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {

    public void enterKitchen() {
        System.out.println("Entered the kitchen");
    }

    public void leaveKitchen() {
        System.out.println("Leaved the kitchen");
    }

    public void takeFoodFromFridge(String food) {
        validateFood(food);
        System.out.println("Took " + food + " from fridge");
    }

    private void validateFood(String food) {
        if (food == null || food.isEmpty()) {
            throw new InvalidFoodException("Invalid food");
        }
    }

}
