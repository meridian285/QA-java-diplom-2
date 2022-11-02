package ru.yandex.practikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practikum.dataTests.UserData;
import ru.yandex.practikum.dataTests.UserDataForLogin;

import static io.restassured.RestAssured.given;
import static ru.yandex.practikum.dataTests.EndPoints.*;

public class UserSteps extends RestClient{
    @Step("Регистрация пользователя")
    //create
    public ValidatableResponse create(UserData userDataForLogin) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(userDataForLogin)
                .post(CREATE_USER)
                .then();
    }
    @Step("Выполняется вход")
    //create
    public ValidatableResponse login(UserDataForLogin userDataForLogin) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(userDataForLogin)
                .post(LOGIN_USER)
                .then();
    }

    @Step("Удаление пользователя")
    //create
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(accessToken)
                .post(DELETE)
                .then();
    }

//    public  ValidatableResponse checkInfoUser(){
//        return given()
//                .spec(getDefaultRequestSpec())
//                .body()
//                .get(EndPoints.INFO_USER);
//    }

}
