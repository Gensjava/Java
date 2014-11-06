package com.example.gens.myapplication_animal;

/**
 * Created by Gens on 05.11.2014.
 */
public abstract class Animal {
    private int mId;

    private String mName;

    public Animal(final int id, final String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
