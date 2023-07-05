package com.example.prm392_project.data.DTO.Auth;

public class RegisterDTO {
    private String username;
    private String password;
    private String phone;
    private String credentialCode;

    public RegisterDTO(){
    }

    public RegisterDTO(String username, String password, String phone, String credentialCode) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.credentialCode = credentialCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCredentialCode() {
        return credentialCode;
    }

    public void setCredentialCode(String credentialCode) {
        this.credentialCode = credentialCode;
    }
}
