package com.example.gens.myapplication_sms_binder_2;

/**
 * Created by Gens on 29.12.2014.
 */
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gens on 27.12.2014.
 */
public class Note implements Serializable {
    private String mName, mDescription;//имя, описание заметки
    private Date mDate;// текущая дата новой записи
    private String mDateFormat;// текущая дата в формате yyyy-MM-dd HH:mm:ss

    public Note(String name, String description, Date date, String DateFormat) {
        mName = name;
        mDescription = description;
        mDate = date;
        mDateFormat = DateFormat;
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

    public String getDateFormat() {
        return mDateFormat;
    }

    public void setDateFormat(String date) {
        mDateFormat = date;
    }
}

