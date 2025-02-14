package com.vladhsu.app.model;

import com.vladhsu.app.interfaces.IEventObserver;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Museum {
    private String name;
    private long code;
    private long supervisorCode;
    private Location location;

    private Person manager;
    private Integer foundingYear;
    private String phoneNumber;
    private String fax;
    private String email;
    private String url;
    private String profile;
    private Set<IEventObserver> observers;

    private Museum(MuseumBuilder builder){
        this.name = builder.name;
        this.code = builder.code;
        this.supervisorCode = builder.supervisorCode;
        this.location = builder.location;
        this.manager = builder.manager;
        this.foundingYear = builder.foundingYear;
        this.phoneNumber = builder.phoneNumber;
        this.fax = builder.fax;
        this.email = builder.email;
        this.url = builder.url;
        this.profile = builder.profile;
        this.observers = new HashSet<>();
    }

    public void addObserver(IEventObserver observer){
        observers.add(observer);
    }

    public void notifyObservers(String message, BufferedWriter writer){
        try {
            for (IEventObserver observer : observers) {
                observer.notify(code, name, message, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public long getCode(){
        return code;
    }

    public static class MuseumBuilder {
        private String name;
        private long code;
        private long supervisorCode;
        private Location location;

        private Person manager;
        private Integer foundingYear;
        private String phoneNumber;
        private String fax;
        private String email;
        private String url;
        private String profile;

        public MuseumBuilder(String name, long code, long supervisorCode, Location location) {
            this.name = name;
            this.code = code;
            this.supervisorCode = supervisorCode;
            this.location = location;
        }

        public MuseumBuilder setManager(Person manager){
            this.manager = manager;
            return this;
        }

        public MuseumBuilder setFoundingYear(Integer foundingYear){
            this.foundingYear = foundingYear;
            return this;
        }

        public MuseumBuilder setPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public MuseumBuilder setFax(String fax){
            this.fax = fax;
            return this;
        }

        public MuseumBuilder setEmail(String email){
            this.email = email;
            return this;
        }

        public MuseumBuilder setUrl(String url){
            this.url = url;
            return this;
        }
        public MuseumBuilder setProfile(String profile){
            this.profile = profile;
            return this;
        }

        public Museum build(){
            return new Museum(this);
        }
    }
}

