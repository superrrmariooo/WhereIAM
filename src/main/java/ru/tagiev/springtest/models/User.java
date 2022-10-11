package ru.tagiev.springtest.models;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;
    private String location;
    private String moveDate;
    private String movePoint;

    public User() {

    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(int id, String name, String location, String moveDate, String movePoint) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.moveDate = moveDate;
        this.movePoint = movePoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getMoveDate() { return moveDate; }

    public void setMoveDate(String moveDate) { this.moveDate = moveDate; }

    public String getMovePoint() { return movePoint; }

    public void setMovePoint(String movePoint) { this.movePoint = movePoint; }

}



