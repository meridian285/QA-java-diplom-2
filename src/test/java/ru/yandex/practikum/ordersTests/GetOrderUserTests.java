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

import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetOrderUserTests {
    UserSteps userSteps;
    OrderSteps orderSteps;
    String accessToken;
    User user;
    Ingredients ingredients;

    private List<String> ingredientsList;
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
    @DisplayName("Тест - Получение заказов конкретного пользователя: авторизованный пользователь")
    public void checkCreateOrderForUser() {
        ingredients = new Ingredients(IngredientsDataGenerator.getRandomIngredients(ingredientsList));
        orderSteps.createOrderWithAuthorization(ingredients, accessToken)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
        orderSteps.getOrdersUserWithAuthorization(accessToken)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("Тест - Получение заказов конкретного пользователя: не авторизованный пользователь")
    public void checkCreateOrderForUserWithoutAuthorization() {
        ingredients = new Ingredients(IngredientsDataGenerator.getRandomIngredients(ingredientsList));
        orderSteps.createOrderWithAuthorization(ingredients, accessToken)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
        orderSteps.getOrdersUserWithAuthorization()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false));
    }
}
