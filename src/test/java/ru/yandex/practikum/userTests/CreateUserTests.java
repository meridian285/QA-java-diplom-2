package ru.yandex.practikum.userTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.steps.UserSteps;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static ru.yandex.practikum.gererator.UserDataGenerator.*;

public class CreateUserTests {
    private UserSteps userSteps;
    private String accessToken;



    @Before
    public void setUp(){
        userSteps = new UserSteps();
    }
    @After
    @DisplayName("Удаляем логин пользователя после каждого теста если получен accessToken")
    //удаляем данные после каждого теста
    public void tearDown(){
        if(accessToken != null){
            userSteps.deleteUser(accessToken).assertThat().statusCode(SC_ACCEPTED)
                    .body("success", equalTo(true));
        }
//        assertEquals(403, );
    }

    @Test
    @DisplayName("Тест - создать уникального пользователя")
    public void registrationUserTest(){
        accessToken = userSteps.create(getUserCreateFaker())
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract().path("accessToken");
    }

    @Test
    @DisplayName("Тест - создать пользователя, который уже зарегистрирован")
    public void checkCannotCreateExistingUser(){
        accessToken = userSteps.login(getUserRequestLogin())
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract().path("accessToken");
        userSteps.create(getUserExistingData())
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false));
    }
    @Test
    @DisplayName("Тест -  создать пользователя и не заполнить поле email")
    public void checkCreateUserWithoutEmailField(){
        ValidatableResponse response;
        userSteps.create(getUserCreateWithoutEmailField())
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("message", equalTo("Email, password and name are required fields"));
//        if(accessToken == null){
//
//        }else{
//            userSteps.deleteUser(accessToken).assertThat().statusCode(SC_ACCEPTED)
//                    .body("success", equalTo(true));
//        }
    }
    @Test
    @DisplayName("Тест -  создать пользователя и не заполнить поле password")
    public void checkCreateUserWithoutPasswordField(){
        accessToken = userSteps.create(getUserCreateWithoutPasswordField())
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("message", equalTo("Email, password and name are required fields"))
                .extract().path("accessToken");

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
