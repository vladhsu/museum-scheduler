package com.vladhsu.app.database;

import com.vladhsu.app.model.*;
import java.util.HashSet;
import java.util.Set;

public class Database {
    private static Database database;
    private Set<Museum> museums;
    private Set<Group> groups;

    private Database() {}

    public static Database getInstance() {
        if(database == null) {
            database = new Database();
            database.museums = new HashSet<>();
            database.groups = new HashSet<>();
        }
        return database;
    }

    public Set<Museum> getMuseums() {
        return museums;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void addMuseum(Museum museum) {
        museums.add(museum);
    }

    public void addMuseums(Set<Museum> museums) {
        this.museums.addAll(museums);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addGroups(Set<Group> groups) {
        this.groups.addAll(groups);
    }

    public Group findGroup(Integer museumCode, String timetable){
        for(Group group : groups) {
            if(group.getMuseumCode().equals(museumCode) && group.getTimetable().equals(timetable)){
                return group;
            }
        }
        return null;
    }

    public Museum findMuseum(long code){
        for(Museum museum : museums) {
            if(museum.getCode() == code)
                return museum;
        }
        return null;
    }

    public void cleanup(){
        museums.clear();
        groups.clear();
    }
}

