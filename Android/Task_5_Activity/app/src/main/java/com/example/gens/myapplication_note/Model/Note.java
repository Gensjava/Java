package com.example.gens.myapplication_note.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gens on 22.11.2014.
 */
public class Note implements Serializable {
    private String mName, mDescription;//имя, описание заметки
    private Date mDate;// текущая дата новой записи

    public Note(String name, String description, Date date) {
        mName = name;
        mDescription = description;
        mDate = date;
    }
    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String description) {
        mDescription = description;
    }
    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
}
