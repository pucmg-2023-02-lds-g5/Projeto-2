package com.example.app.models.user;

public enum UserRole {
    AGENTE(1,"agente"),
    USER(2,"user");

    private String role;
    private int id;

    UserRole(int id,String role){
        this.role = role;
        this.id = id;
    }

    public String getRole(){
        return role;
    }
}
