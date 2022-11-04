package ru.yandex.practikum.ordersTests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.dataTests.User;
import ru.yandex.practikum.steps.OrderSteps;
import ru.yandex.practikum.steps.RestClient;
import ru.yandex.practikum.steps.UserSteps;

import java.util.List;

import static io.restassured.RestAssured.given;
import static ru.yandex.practikum.dataTests.EndPoints.INFO_USER;
import static ru.yandex.practikum.dataTests.EndPoints.INGREDIENTS;
import static ru.yandex.practikum.gererator.UserDataGenerator.getUserCreateFaker;

public class CreateOrderTests{
    UserSteps userSteps;
    OrderSteps orderSteps;
    String accessToken;
    private List<String> ingredients;

    @Before
    public void setUp(){
        userSteps = new UserSteps();
        accessToken =  userSteps.create(getUserCreateFaker()).extract().path("accessToken");
        orderSteps = new OrderSteps();
        ingredients = orderSteps.getIngredients().extract().path("_id");

    }

    @Test
    @DisplayName("")
    public void checkCreateOrder(){

    }

}
