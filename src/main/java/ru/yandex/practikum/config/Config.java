package ru.yandex.practikum.config;

import io.restassured.RestAssured;

public class Config {
    private final static String URL = "https://stellarburgers.nomoreparties.site/";


    public static String getBaseUri() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";

        return URL;
    }
}
