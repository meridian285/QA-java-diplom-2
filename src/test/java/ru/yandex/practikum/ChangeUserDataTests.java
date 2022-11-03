package ru.yandex.practikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.steps.UserSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.yandex.practikum.gererator.UserDataGenerator.*;


public class ChangeUserDataTests {
    String accessToken;
    private UserSteps userSteps;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
    }

    @Test
    @DisplayName("Тест - изменение данных с авторизацией")
    public void checkChengUserData() {
        accessToken = userSteps.create(getUserCreateFaker())
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken")
                .toString();
        userSteps.checkInfoUser(getUserCreateFaker(),accessToken)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));

    }
}
