package ru.yandex.practikum.userTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.dataTests.User;
import ru.yandex.practikum.gererator.UserDataGenerator;
import ru.yandex.practikum.steps.UserSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.yandex.practikum.gererator.UserDataGenerator.*;
import static ru.yandex.practikum.gererator.UserDataGenerator.getUserCreateFaker;


public class ChangeUserTests {
    String accessToken;
    private UserSteps userSteps;
    User user;
    Response response;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
        user = UserDataGenerator.getUserCreateFaker();
    }

    @Test
    @DisplayName("Тест - изменение данных с авторизацией")
    public void checkChangeUserDataWithAuthorization() {

        accessToken = userSteps.create(user.getEmail(), user.getPassword(), user.getName())
                .then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken");
//        userSteps.checkChangeUserEmail(user.getEmail(),accessToken)
//                .assertThat()
//                .statusCode(SC_OK)
//                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("Тест - изменение данных без авторизации")
    public void checkChangeUserDataWithoutAuthorization() {
        userSteps.checkInfoUserWithoutAuthorization(getUserCreateFaker())
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message",equalTo("You should be authorised") );
    }
}
