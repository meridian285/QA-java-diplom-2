package ru.yandex.practikum.dataTests;

public class UserDataForLogin {
    private String email;
    private String password;

    public UserDataForLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDataForLogin() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
