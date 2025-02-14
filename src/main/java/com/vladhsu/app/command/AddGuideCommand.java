package com.vladhsu.app.command;

import com.vladhsu.app.interfaces.Command;
import com.vladhsu.app.interfaces.IPersonFactory;
import com.vladhsu.app.model.*;
import com.vladhsu.app.exception.*;
import com.vladhsu.app.database.Database;
import com.vladhsu.app.provider.PersonFactoryProvider;

import java.io.BufferedWriter;
import java.io.IOException;

public class AddGuideCommand implements Command {
    private final String[] args;
    IPersonFactory personFactory;
    BufferedWriter writer;

    public AddGuideCommand(String[] args,  BufferedWriter writer) {
        this.args = args;
        this.writer = writer;
        personFactory = PersonFactoryProvider.getInstance();
    }

    @Override
    public void execute() throws IOException {
        String surname = args[0];
        String name = args[1];
        String role = args[2];
        int age = Integer.parseInt(args[3]);
        String email = args[4];
        String school = args[5];
        int relevantYear = Integer.parseInt(args[6]);
        String museumRole = args[7];
        Integer museumCode = Integer.parseInt(args[8]);
        String timetable = args[9];

        Person guide = personFactory.createPerson(surname, name, museumRole, email, age, school, relevantYear, role);

        if(role.equals("student")) {
            String message = museumCode + " ## " + timetable + " ## " + "GuideTypeException: Guide must be a professor. ## " +
                    "(new guide: " + guide.toString() + ")";
            throw new GuideTypeException(message);
        }

        Group group = Database.getInstance().findGroup(museumCode, timetable);
        if(group == null){
            group = new Group((Professor) guide, museumCode, timetable);
            Database.getInstance().addGroup(group);
            String message = museumCode + " ## " + timetable + " ## " +
                    "new guide: " + guide.toString() + "\n";
            writer.write(message);
            guide.subscribeToMuseum(museumCode);
        } else{
            if(group.getGuide() == null) {
                group.addGuide((Professor) guide);
                String message = museumCode + " ## " + timetable + " ## " +
                        "new guide: " + guide.toString() + "\n";
                writer.write(message);
                guide.subscribeToMuseum(museumCode);
            }
            else {
                String errorMessage = museumCode + " ## " + timetable + " ## " + "GuideExistsException: Guide already exists. ## " +
                        "(new guide: " + group.getGuide().toString() + ")";
                throw new GuideExistsException(errorMessage);
            }
        }
    }
}

