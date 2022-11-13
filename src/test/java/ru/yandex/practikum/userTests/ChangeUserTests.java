package ru.yandex.practikum.userTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.dataTests.User;
import ru.yandex.practikum.gererator.UserDataGenerator;
import ru.yandex.practikum.steps.UserSteps;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;


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
    @DisplayName("Тест - изменение поля email с авторизацией")
    public void checkChangeEmailWithAuthorization() {
        accessToken = userSteps.create(user.getEmail(), user.getPassword(), user.getName())
                .then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken");
        userSteps.checkChangeUserData(user.getEmail(),accessToken)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("Тест - изменение поля password с авторизацией")
    public void checkChangePasswordWithAuthorization() {
                String name = "Jack231423";
        accessToken = userSteps.create(user.getEmail(), user.getPassword(), user.getName())
                .then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken");
        userSteps.checkChangeUserData(name,accessToken)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("user.name", equalTo(name));
    }
    @Test
    @DisplayName("Тест - изменение поля name с авторизацией")
    public void checkChangeNameWithAuthorization() {
        accessToken = userSteps.create(user.getEmail(), user.getPassword(), user.getName())
                .then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken");
        userSteps.checkChangeUserData(user.getName(),accessToken)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("Тест - изменение поля email без авторизации")
    public void checkChangeEmailWithoutAuthorization() {
        userSteps.checkChangeUserDataWithoutAuthorization(user.getEmail())
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message",equalTo("You should be authorised") );
    }
    @Test
    @DisplayName("Тест - изменение поля password без авторизации")
    public void checkChangeUserPasswordWithoutAuthorization() {
        userSteps.checkChangeUserDataWithoutAuthorization(user.getPassword())
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message",equalTo("You should be authorised") );
    }
    @Test
    @DisplayName("Тест - изменение поля password без авторизации")
    public void checkChangeUserNameWithoutAuthorization() {
        userSteps.checkChangeUserDataWithoutAuthorization(user.getName())
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message",equalTo("You should be authorised") );
    }
}
