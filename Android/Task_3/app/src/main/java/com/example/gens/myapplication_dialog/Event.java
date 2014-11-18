package com.example.gens.myapplication_dialog;

/**
 * Created by Gens on 16.11.2014.
 */
public class Event {
    String name, description, date, time;
    Status status;
    int id;

    public Event(String name, String description, String date, String time, Status status) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Status getStatus() {
        return status;
    }


}

