package ru.yandex.practikum.gererator;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import ru.yandex.practikum.dataTests.User;


public class UserDataGenerator {

    @Step("Случайные данные для регистрации - email, password, name")
    public static User getUserCreateFaker() {
        Faker faker = new Faker();
        var email = faker.internet().emailAddress();
        var password = faker.random().hex(8);
        var name = faker.name().firstName();
        return new User(email, password, name);
    }
}
