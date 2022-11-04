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
import static org.junit.Assert.assertEquals;
import static ru.yandex.practikum.gererator.UserDataGenerator.*;


public class LoginUserTests {
    private UserSteps userSteps;
    private String accessToken;
    Response response;
    User user;
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
        } else {
            assertEquals("Неверный код ответа", 403, response.statusCode());

        }
    }

    @Test
    @DisplayName("Тест - логин под существующим пользователем")
    public void checkLoginUser() {
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        accessToken = userSteps.create(email, password, name)
                .then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract().path("accessToken");
        userSteps.login(email, password)
                .then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("Тест - логин с неверным email")
    public void checkLoginWithInvalidEmail() {
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        accessToken = userSteps.create(email, password, name)
                .then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract().path("accessToken");
        userSteps.login("aer7655gvsr@gmail.com", password)
                .then().assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false));
    }
    @Test
    @DisplayName("Тест - логин с неверным email")
    public void checkLoginWithInvalidPassword() {
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        accessToken = userSteps.create(email, password, name)
                .then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract().path("accessToken");
        userSteps.login(email, "sigfvisurufbhs47365t")
                .then().assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false));
    }
}
