package com.example.database;

public class Model {

    private String name;
    private int g_id;

    public Model(String name, int g_id) {
        this.name = name;
        this.g_id = g_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }
}
