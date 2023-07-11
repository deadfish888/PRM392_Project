package com.example.prm392_project.data.model;

import java.sql.Timestamp;

public class Chat {
    private int id;
    private Timestamp time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Chat(int id, Timestamp time) {
        this.id = id;
        this.time = time;
    }
}
