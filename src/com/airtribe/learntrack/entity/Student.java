package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

import java.util.Objects;

public class Student extends Person{

    private static final IdGenerator ID_GENERATOR = new IdGenerator("STU");

    private String batch;
    private boolean active;


    public Student(String firstName, String lastName, String email, String batch, boolean active) {
        super(ID_GENERATOR.nextId(), firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    public Student(String firstName, String lastName, String batch, boolean active) {
        super(ID_GENERATOR.nextId(), firstName, lastName);
        this.batch = batch;
        this.active = active;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName(){
        return "Student class";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return active == student.active && Objects.equals(batch, student.batch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batch, active);
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString()+
                ",batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }
}
