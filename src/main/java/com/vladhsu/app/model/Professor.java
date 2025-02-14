package com.vladhsu.app.model;

public class Professor extends Person {
    int experience;
    String school;

    public Professor(String surname, String name, String role){
        super(surname, name, role);
    }

    public void setExperience(int experience){
        this.experience = experience;
    }

    public void setSchool(String school){
        this.school = school;
    }

    @Override
    public String toString() {
        return super.toString() + ", school=" + school + ", experience=" + experience;
    }
}
