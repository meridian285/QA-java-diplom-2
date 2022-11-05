package ru.yandex.practikum.userTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.dataTests.User;
import ru.yandex.practikum.gererator.UserDataGenerator;
import ru.yandex.practikum.steps.UserSteps;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static ru.yandex.practikum.gererator.UserDataGenerator.*;

public class CreateUserTests {
    private UserSteps userSteps;
    private String accessToken;
    Response response;
    User user;
    @Before
    public void setUp(){

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
    @DisplayName("Тест - создать уникального пользователя")
    public void registrationUserTest(){
        response = userSteps.create(user.getEmail(), user.getPassword(), user.getName());
                assertEquals(200, response.statusCode());
                assertEquals(true,response.path("success"));
        accessToken = response.path("accessToken").toString();

    }
    @Test
    @DisplayName("Тест - создать пользователя, который уже зарегистрирован")
    public void checkCannotCreateExistingUser(){
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        accessToken = userSteps.create(email, password, name)
                .then().assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract().path("accessToken");
        userSteps.create(email, password, name)
                .then().assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false));
    }
    @Test
    @DisplayName("Тест -  создать пользователя и не заполнить поле email")
    public void checkCreateUserWithoutEmailField(){
        response = userSteps.create("", user.getPassword(), user.getName());
        assertEquals(403, response.statusCode());
        assertEquals("Email, password and name are required fields",response.path("message"));
        accessToken = response.path("accessToken");
    }
    @Test
    @DisplayName("Тест -  создать пользователя и не заполнить поле password")
    public void checkCreateUserWithoutPasswordField(){
        response = userSteps.create("", user.getPassword(), user.getName());
        assertEquals(403, response.statusCode());
        assertEquals("Email, password and name are required fields",response.path("message"));
        accessToken = response.path("accessToken");
    }
    @Test
    @DisplayName("Тест -  создать пользователя и не заполнить поле name")
    public void checkCreateUserWithoutNameField(){
        response = userSteps.create("", user.getPassword(), user.getName());
        assertEquals(403, response.statusCode());
        assertEquals("Email, password and name are required fields",response.path("message"));
        accessToken = response.path("accessToken");
    }
}
