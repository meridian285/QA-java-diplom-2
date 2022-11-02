package ru.yandex.practikum.gererator;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import ru.yandex.practikum.dataTests.UserData;

public class UserDataGenerator {


    @Step("Случайные данные для регистрации - email, password, name")
    public static UserData getUserCreateFaker() {
        Faker faker = new Faker();
        var email = faker.internet().emailAddress();
        var password = faker.random().hex(8);
        var name = faker.name().firstName();
        return new UserData(email, password, name);
    }
    @Step("Случайные данные для регистрации - email, name")
    public static UserData getUserCreateWithoutPasswordField() {
        Faker faker = new Faker();
        var email = faker.internet().emailAddress();
        var password = "";
        var name = faker.name().firstName();
        return new UserData(email, password, name);
    }
    @Step("Случайные данные для регистрации - password, name")
    public static UserData getUserCreateWithoutEmailField() {
        Faker faker = new Faker();
        var email = "";
        var password = faker.random().hex(8);
        var name = faker.name().firstName();
        return new UserData(email, password, name);
    }

    @Step("Случайные данные для регистрации - email, password")
    public static UserData getUserCreateWithoutNameField() {
        Faker faker = new Faker();
        var email = faker.internet().emailAddress();
        var password = faker.random().hex(8);
        var name = "";
        return new UserData(email, password, name);
    }
    @Step("Ввод данных для создания пользователя")
    public static UserData getUserExistingData(){
        UserData userData = new UserData();
        userData.setEmail("testik28555456419@gmail.com");
        userData.setPassword("12345jhbbjkn61");
        userData.setName("sdbgjhszfsthtxfhdi1");
        return userData;
    }

}
