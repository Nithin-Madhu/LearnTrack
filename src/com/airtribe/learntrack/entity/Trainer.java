package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

public class Trainer extends Person{

    private static final IdGenerator ID_GENERATOR = new IdGenerator("TRA");

    public Trainer(long id,String firstName,  String lastName, String email) {
        super( ID_GENERATOR.nextId(),firstName, lastName, email);
    }


    @Override
    public String getDisplayName(){
        return "Trainer class";
    }
}
