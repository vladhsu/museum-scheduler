package com.vladhsu.app.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Person> members;
    private Professor guide;
    private Integer museumCode;
    private String timetable;

    public Group() {}

    public Group(Professor guide, Integer museumCode, String timetable) {
        this.guide = guide;
        this.museumCode = museumCode;
        this.timetable = timetable;
        members = new ArrayList<>();
    }

    public Professor getGuide() {
        return guide;
    }

    public Integer getMuseumCode() {
        return museumCode;
    }

    public String getTimetable() {
        return timetable;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void addGuide(Professor guide) {
        this.guide = guide;
    }

    public void addMember(Person member) {
        this.members.add(member);
    }

    public void removeMember(Person member) {
        members.removeIf(p -> p.getSurname().equals(member.getSurname())
                && p.getName().equals(member.getName()));
    }

    public void resetGuide() {
        this.guide = null;
    }

    public int findMember(Person member) {
        for(Person p : members) {
            if(p.getSurname().equals(member.getSurname())
                    && p.getName().equals(member.getName())) {
                return 1;
            }
        }
        return -1;
    }
}
