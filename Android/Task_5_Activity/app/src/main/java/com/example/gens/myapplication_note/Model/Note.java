package com.example.gens.myapplication_note.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gens on 22.11.2014.
 */
public class Note implements Serializable {
    private String mName, mDescription;//имя, описание заметки
    private Date mDate;// текущая дата новой записи

    public Note(String mName, String mDescription, Date mDate) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mDate = mDate;
    }
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
    public String getmDescription() {
        return mDescription;
    }
    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
    public Date getmDate() {
        return mDate;
    }
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
