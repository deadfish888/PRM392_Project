package com.example.prm392_project.data.model;

public class UserInfo {
    private int id;
    private String username;
    private String phoneNumber;
    private String credentialCode;

    public UserInfo() {
    }

    public UserInfo(int id, String username, String phoneNumber, String credentialCode) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.credentialCode = credentialCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCredentialCode() {
        return credentialCode;
    }

    public void setCredentialCode(String credentialCode) {
        this.credentialCode = credentialCode;
    }
}
