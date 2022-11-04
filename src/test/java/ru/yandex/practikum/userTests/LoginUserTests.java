package ru.yandex.practikum.userTests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.steps.UserSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.yandex.practikum.gererator.UserDataGenerator.*;


public class LoginUserTests {
     private UserSteps userSteps;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Тест - логин под существующим пользователем")
    public void checkLoginUser() {
        userSteps.login(getUserRequestLogin())
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("Тест - логин под существующим пользователем")
    public void checkLoginUserWithInvalidEmail() {
        userSteps.login(getUserRequestLogin())
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("Тест - логин с неверным email")
    public void checkLoginWithInvalidEmail() {
        userSteps.login(getUserLoginWithInvalidEmail())
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false));
    }
    @Test
    @DisplayName("Тест - логин с неверным email")
    public void checkLoginWithInvalidPassword() {
        userSteps.login(getUserLoginWithInvalidPassword())
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false));
    }
}
