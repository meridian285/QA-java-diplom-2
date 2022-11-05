package ru.yandex.practikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import ru.yandex.practikum.dataTests.Ingredients;
import ru.yandex.practikum.dataTests.User;

import static io.restassured.RestAssured.given;
import static ru.yandex.practikum.dataTests.EndPoints.*;

public class OrderSteps extends RestClient {

    @Step("Изменение данных пользователя")
    public ValidatableResponse checkInfoUser(User user, String accessToken){
        return given()
                .header("authorization", accessToken)
                .log().all()
                .spec(getDefaultRequestSpec())
                .body(user)
                .patch(INFO_USER)
                .then();
    }
    @Step("Получить список всех ингредиентов")
    public ValidatableResponse getIngredients(){
        return given()
                .spec(getDefaultRequestSpec())
                .get(INGREDIENTS)
                .then();
    }
    @Step("Создание заказа с авторизацией")
    public ValidatableResponse createOrderWithAuthorization(Ingredients ingredients, String accessToken){
        return given()
                .log().all()
                .header("authorization", accessToken)
                .spec(getDefaultRequestSpec())
                .body(ingredients)
                .post(ORDERS)
                .then();
    }
    @Step("Создание заказа без авторизации")
    public ValidatableResponse createOrderWithoutAuthorization(Ingredients ingredients){
        return given()
                .log().all()
                .spec(getDefaultRequestSpec())
                .body(ingredients)
                .post(ORDERS)
                .then();
    }

}
