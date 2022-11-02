package ru.yandex.practikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.steps.UserSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
//import static ru.yandex.practikum.gererator.UserDataGenerator.getUserDataForChangDataTest;
import static ru.yandex.practikum.gererator.UserDataGenerator.getUserRequestLogin;

public class ChangeUserDataTests {
    private UserSteps userSteps;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
    }

//    @Test
//    @DisplayName("Тест - логин под существующим пользователем")
//    public void checkChengUserData() {
//        userSteps.login(getUserDataForChangDataTest())
//                .assertThat()
//                .statusCode(SC_OK)
//                .body("success", equalTo(true));
//    }
}
