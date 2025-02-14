package com.vladhsu.app.factory;

import com.vladhsu.app.model.*;
import com.vladhsu.app.interfaces.IPersonFactory;

public class PersonFactory implements IPersonFactory {
    @Override
    public Person createPerson(String surname, String name, String museumRole, String email, int age, String school, int relevantYear, String role){
        Person person = switch (role) {
            case "student" -> {
                Student student = new Student(surname, name, museumRole);
                student.setSchool(school);
                student.setStudyYear(relevantYear);
                yield student;
            }
            case "profesor" -> {
                Professor professor = new Professor(surname, name, museumRole);
                professor.setSchool(school);
                professor.setExperience(relevantYear);
                yield professor;
            }
            default -> new Person(surname, name, museumRole);
        };

        if(!email.isEmpty()){
            person.setEmail(email);
        }else{
            person.setEmail(null);
        }
        if(age > 0){
            person.setAge(age);
        }
        return person;
    }
}

