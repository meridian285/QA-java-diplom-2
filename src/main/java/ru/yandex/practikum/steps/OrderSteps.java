package ru.yandex.practikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
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
    @Step("Создать заказ с авторизацией")
    public ValidatableResponse createOrderWithAuthorization(User user){
        return given()
                .log().all()
                .spec(getDefaultRequestSpec())
                .body(user)
                .post(ORDERS)
                .then();
    }
}
