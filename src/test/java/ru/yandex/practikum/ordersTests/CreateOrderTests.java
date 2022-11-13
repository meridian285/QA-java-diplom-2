package ru.yandex.practikum.ordersTests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.dataTests.Ingredients;
import ru.yandex.practikum.dataTests.User;
import ru.yandex.practikum.gererator.IngredientsDataGenerator;
import ru.yandex.practikum.gererator.UserDataGenerator;
import ru.yandex.practikum.steps.OrderSteps;
import ru.yandex.practikum.steps.UserSteps;
import java.util.Collections;
import java.util.List;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateOrderTests{
    UserSteps userSteps;
    OrderSteps orderSteps;
    String accessToken;
    User user;
    Ingredients ingredients;

    private  List<String> ingredientsList;
    @Before
    public void setUp(){
        user = UserDataGenerator.getUserCreateFaker();
        userSteps = new UserSteps();
        accessToken =  userSteps.create(user.getEmail(), user.getPassword(), user.getName()).path("accessToken");
        orderSteps = new OrderSteps();
        ingredientsList = orderSteps.getIngredients().extract().path("data._id");
    }
    @After
    @DisplayName("Удаляем логин пользователя после каждого теста если получен accessToken")
    //удаляем данные после каждого теста
    public void tearDown() {
        if (accessToken != null) {
            userSteps.deleteUser(accessToken).assertThat().statusCode(SC_ACCEPTED)
                    .body("success", equalTo(true));
        }
    }
    @Test
    @DisplayName("Тест - Создание заказа: с авторизацией, с ингредиентами")
    public void checkCreateOrderWithAuthorization() {
        ingredients = new Ingredients(IngredientsDataGenerator.getRandomIngredients(ingredientsList));
        orderSteps.createOrderWithAuthorization(ingredients, accessToken)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("Тест - Создание заказа: без авторизации, с ингредиентами")
    public void checkCreateOrderWithoutAuthorization() {
        ingredients = new Ingredients(IngredientsDataGenerator.getRandomIngredients(ingredientsList));
        orderSteps.createOrderWithoutAuthorization(ingredients)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("Тест - Создание заказа: с авторизацией, без ингредиентов")
    public void checkCreateOrderWithoutIngredients() {
        ingredients = new Ingredients(IngredientsDataGenerator.getRandomIngredients(null));
        orderSteps.createOrderWithAuthorization(ingredients, accessToken)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("success", equalTo(false));
    }
    @Test
    @DisplayName("Тест - Создание заказа: без авторизации, без ингредиентов")
    public void checkCreateOrderWithoutIngredientsAndWithoutAuthorization() {
        ingredients = new Ingredients(IngredientsDataGenerator.getRandomIngredients(null));
        orderSteps.createOrderWithoutAuthorization(ingredients)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("success", equalTo(false));
    }
    @Test
    @DisplayName("Тест - Создание заказа: с авторизацией, с неправильным ингредиентом")
    public void checkCreateOrderWithInvalidIngredients() {
        ingredients = new Ingredients(IngredientsDataGenerator.getRandomIngredients(Collections.singletonList("61c0c5asergsregare453")));
        orderSteps.createOrderWithAuthorization(ingredients, accessToken)
                .assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR)
                .body("success", equalTo(false));
    }
}
