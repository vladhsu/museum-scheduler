package com.vladhsu.app.interfaces;

import com.vladhsu.app.model.Person;

public interface IPersonFactory {
    Person createPerson(String surname, String name, String role, String email, int age, String school, int relevantYear, String museumRole);
}

