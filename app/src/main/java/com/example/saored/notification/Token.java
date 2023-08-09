package com.example.saored.notification;

public class    Token {
    String token;
    public Token() {
        // Constructor không đối số
    }
    public Token(String token) {
        this.token = token;
    }
    public String getToken(){
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
