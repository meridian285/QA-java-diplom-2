package ru.yandex.practikum.gererator;

import io.qameta.allure.Step;
import ru.yandex.practikum.dataTests.UserDataForLogin;

public class UserDataGeneratorLogin {

    @Step("Данные для теста - логин под существующим пользователем")
    public static UserDataForLogin getUserRequestLogin(){
        UserDataForLogin userDataForLogin = new UserDataForLogin();
        userDataForLogin.setEmail("testik28555456419@gmail.com");
        userDataForLogin.setPassword("12345jhbbjkn61");
        return userDataForLogin;
    }
    @Step("Данные для тестов - логин с неверным email")
    public static UserDataForLogin getUserLoginWithInvalidEmail(){
        UserDataForLogin userDataForLogin = new UserDataForLogin();
        userDataForLogin.setEmail("testik28535456419@gmail.com");
        userDataForLogin.setPassword("12345jhbbjkn61");
        return userDataForLogin;
    }
    @Step("Данные для тестов - логин с неверным password")
    public static UserDataForLogin getUserLoginWithInvalidPassword(){
        UserDataForLogin userDataForLogin = new UserDataForLogin();
        userDataForLogin.setEmail("testik28555456419@gmail.com");
        userDataForLogin.setPassword("12345jhb");
        return userDataForLogin;
    }
}
