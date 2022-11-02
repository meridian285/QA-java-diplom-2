package ru.yandex.practikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.steps.UserSteps;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.yandex.practikum.gererator.UserDataGenerator.*;
//import static ru.yandex.practikum.gererator.UserDataGeneratorLogin.getUserRequestLogin;

public class CreateUserTests {
    private UserSteps userSteps;

    @Before
    public void setUp(){
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Тест - создать уникального пользователя")
    public void registrationUserTest(){
        userSteps.create(getUserCreateFaker())
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken");
    }
    @Test
    @DisplayName("Тест - создать пользователя, который уже зарегистрирован")
    public void checkCannotCreateExistingUser(){
        userSteps.login(getUserRequestLogin())
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
        userSteps.create(getUserExistingData())
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false));
    }
    @Test
    @DisplayName("Тест -  создать пользователя и не заполнить поле email")
    public void checkCreateUserWithoutEmailField(){
        userSteps.create(getUserCreateWithoutEmailField())
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("message", equalTo("Email, password and name are required fields"));
    }
    @Test
    @DisplayName("Тест -  создать пользователя и не заполнить поле password")
    public void checkCreateUserWithoutPasswordField(){
        userSteps.create(getUserCreateWithoutPasswordField())
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("message", equalTo("Email, password and name are required fields"));
    }
    @Test
    @DisplayName("Тест -  создать пользователя и не заполнить поле name")
    public void checkCreateUserWithoutNameField(){
        userSteps.create(getUserCreateWithoutNameField())
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
