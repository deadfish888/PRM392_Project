package com.example.prm392_project.data.DTO.User;

public class UserUpdateDTO {
    public int id;
    public String phoneNumber;
    public UserUpdateDTO(int id, String phoneNumber){
        this.id = id;
        this.phoneNumber = phoneNumber;
    }
}
