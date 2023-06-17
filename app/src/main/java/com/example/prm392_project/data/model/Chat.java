package com.example.prm392_project.data.model;

import java.sql.Timestamp;

public class Chat {
    private int Id;
    private Timestamp time;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Chat(int id, Timestamp time) {
        Id = id;
        this.time = time;
    }
}
