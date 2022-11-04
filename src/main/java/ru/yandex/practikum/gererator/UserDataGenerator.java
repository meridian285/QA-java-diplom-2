package ru.yandex.practikum.gererator;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import ru.yandex.practikum.dataTests.User;


public class UserDataGenerator {
    public void email(){

    }

    @Step("Случайные данные для регистрации - email, password, name")
    public static User getUserCreateFaker() {
        Faker faker = new Faker();
        var email = faker.internet().emailAddress();
        var password = faker.random().hex(8);
        var name = faker.name().firstName();
        return new User(email, password, name);


    }
    @Step("Случайные данные для регистрации - email, name")
    public static User getUserCreateWithoutPasswordField() {
        Faker faker = new Faker();
        var email = faker.internet().emailAddress();
        var password = "";
        var name = faker.name().firstName();
        return new User(email, password, name);
    }
    @Step("Случайные данные для регистрации - password, name")
    public static User getUserCreateWithoutEmailField() {
        Faker faker = new Faker();
        var email = "";
        var password = faker.random().hex(8);
        var name = faker.name().firstName();
        return new User(email, password, name);
    }

    @Step("Случайные данные для регистрации - email, password")
    public static User getUserCreateWithoutNameField() {
        Faker faker = new Faker();
        var email = faker.internet().emailAddress();
        var password = faker.random().hex(8);
        var name = "";
        return new User(email, password, name);
    }
    @Step("Ввод данных для создания пользователя")
    public static User getUserExistingData(){
        User user = new User();
        user.setEmail("testik28555456419@gmail.com");
        user.setPassword("12345jhbbjkn61");
        user.setName("sdbgjhszfsthtxfhdi1");
        return user;
    }
    @Step("Данные для теста - логин под существующим пользователем")
    public static User getUserRequestLogin(){
        User user = new User();
        user.setEmail("testik28555456419@gmail.com");
        user.setPassword("12345jhbbjkn61");
        return user;
    }
    //@Step("Данные для тестов - логин с неверным email")
    public static User getUserLoginWithInvalidEmail(){
        User user = new User();
        user.setEmail("testik28535456419@gmail.com");
        user.setPassword("12345jhbbjkn61");
        return user;
    }
    @Step("Данные для тестов - логин с неверным password")
    public static User getUserLoginWithInvalidPassword(){
        User user = new User();
        user.setEmail("testik28555456419@gmail.com");
        user.setPassword("12345jhb");
        return user;
    }
    @Step("Созданный пользователь для теста на изменение данных")
    public static User getUserDataForChangDataTest(){
        User user = new User();
        user.setEmail("testik2436@gmail.com");
        user.setPassword("sfhdtdhtdh");
        user.setName("sththdtthd");
        return user;
    }
}
