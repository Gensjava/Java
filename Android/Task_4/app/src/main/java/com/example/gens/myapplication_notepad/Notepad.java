package com.example.gens.myapplication_notepad;

import java.util.Date;



/**
 * Created by Yanc_sh51 on 19.11.2014.
 */
public class Notepad {
    private String name, description;
    private Date date ;


    public Notepad(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
