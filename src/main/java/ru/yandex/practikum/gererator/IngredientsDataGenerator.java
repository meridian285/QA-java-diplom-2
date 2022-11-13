package ru.yandex.practikum.gererator;

import java.util.List;
import java.util.Random;

public class IngredientsDataGenerator {

    public static List<String> getRandomIngredients(List<String> ingredients) {
        if (ingredients != null) {
            return ingredients.subList(0, new Random().nextInt(ingredients.size()));
        } else {
            return null;
        }
    }
}
