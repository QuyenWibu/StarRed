package com.example.saored.models;

public class ModelProfile {
    String fullName, strClass, email, imageUri;

    public ModelProfile(){

    }

    public ModelProfile(String fullName, String strClass, String email, String imageUri) {
        this.fullName = fullName;
        this.strClass = strClass;
        this.email = email;
        this.imageUri = imageUri;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStrClass() {
        return strClass;
    }

    public void setStrClass(String strClass) {
        this.strClass = strClass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
