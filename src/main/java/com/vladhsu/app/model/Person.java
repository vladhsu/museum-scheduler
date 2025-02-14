package com.vladhsu.app.model;

import com.vladhsu.app.database.Database;
import com.vladhsu.app.interfaces.IEventObserver;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Person implements IEventObserver{
    private String surname;
    private String name;
    private String role;
    private int age;
    private String email;
    private Set<Museum> subscribedMuseums;

    public Person(String surname, String name, String role){
        this.surname = surname;
        this.name = name;
        this.role = role;
        this.subscribedMuseums = new HashSet<>();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public void notify(long code, String name, String message, BufferedWriter writer) throws IOException {
        String result = "To: " + email + " ## Message: " + name + " (" + code + ") " + message + "\n";
        writer.write(result);
    }

    public void subscribeToMuseum(long code){
        for(Museum museum : Database.getInstance().getMuseums()){
            if(museum.getCode() == code){
                subscribedMuseums.add(museum);
                museum.addObserver(this);
            }
        }
    }

    @Override
    public String toString() {
        return "surname=" + surname + ", name=" + name + ", role=" + role + ", age=" + age + ", email=" + email;
    }
}

