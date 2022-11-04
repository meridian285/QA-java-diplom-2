package ru.yandex.practikum.steps;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practikum.dataTests.User;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;
import static ru.yandex.practikum.dataTests.EndPoints.*;

public class UserSteps extends RestClient{
    @Step("Регистрация пользователя")
    //create
    public Response create(String email, String password, String name) {
        JSONObject jsonObject = new JSONObject();
        String requestBody;
        requestBody = jsonObject
                .put("email", email)
                .put( "password", password)
                .put("name",name)
                .toString();
        Response response = given()
                .log().all()
                .spec(getDefaultRequestSpec())
                .body(requestBody)
                .post(CREATE_USER);
        return response;
    }
    @Step("Выполняется вход")
    //create
    public Response login(String email, String password) {
        JSONObject jsonObject = new JSONObject();
        String requestBody;
        requestBody = jsonObject
                .put("email", email)
                .put("password", password)
                .toString();
        Response response = given()
                .log().all()
                .spec(getDefaultRequestSpec())
                .body(requestBody)
                .post(LOGIN_USER);
        return response;
    }

    @Step("Удаление пользователя")
    //create
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .log().all()
                .header("authorization", accessToken)
                .spec(getDefaultRequestSpec())
                .delete(DELETE)
                .then();
    }
    @Step("Изменение данных пользователя - Email")
    public  ValidatableResponse checkChangeUserEmail(String email, String accessToken){
        JSONObject requestJsonObjectBody = new JSONObject();
        String requestBody = requestJsonObjectBody
                .put("email", email)
                .toString();
        return given()
                .header("authorization", accessToken)
                .log().all()
                .spec(getDefaultRequestSpec())
                .body(requestBody)
                .patch(INFO_USER)
                .then();
    }
    @Step("Изменение данных пользователя - password")
    public  ValidatableResponse checkChangeUserPassword(User user, String accessToken){
        return given()
                .header("authorization", accessToken)
                .log().all()
                .spec(getDefaultRequestSpec())
                .body(user)
                .patch(INFO_USER)
                .then();
    }

    @Step("Изменение данных пользователя без авторизации")
    public  ValidatableResponse checkInfoUserWithoutAuthorization( User user){
        return given()
                .log().all()
                .spec(getDefaultRequestSpec())
                .body(user)
                .patch(INFO_USER)
                .then();
    }

}
