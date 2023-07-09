package com.example.prm392_project.data.DTO.Category;

public class CategoryRequestDTO {
    private String name;

    public CategoryRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
