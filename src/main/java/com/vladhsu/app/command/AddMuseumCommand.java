package com.vladhsu.app.command;

import com.vladhsu.app.interfaces.Command;
import com.vladhsu.app.interfaces.IPersonFactory;
import com.vladhsu.app.model.*;
import com.vladhsu.app.database.Database;
import com.vladhsu.app.provider.PersonFactoryProvider;

import java.io.BufferedWriter;
import java.io.IOException;

public class AddMuseumCommand implements Command {
    private final String[] args;
    IPersonFactory personFactory;
    BufferedWriter writer;

    public AddMuseumCommand(String[] args, BufferedWriter writer) {
        this.args = args;
        personFactory = PersonFactoryProvider.getInstance();
        this.writer = writer;
    }

    @Override
    public void execute() throws IOException {
        String name = args[1];
        long code = Long.parseLong(args[0]);
        long supervisorCode = Long.parseLong(args[13]);

        String[] latitudeParts = args[17].split(",");
        Integer[] latitude = new Integer[latitudeParts.length];
        String[] longitudeParts = args[18].split(",");
        Integer[] longitude = new Integer[longitudeParts.length];
        Location location = new Location.LocationBuilder(args[2], Integer.parseInt(args[15]))
                .setAddress(args[5])
                .setLocality(args[3])
                .setAdminUnit(args[4])
                .setLatitude(latitude)
                .setLongitude(longitude)
                .build();
        Person manager = null;
        if(!args[12].isEmpty()) {
            String[] managerName = args[12].split(" ");
            manager= personFactory.createPerson(managerName[0], managerName[1], "", "", 0, null, 0, "director");
        }
        Integer foundingYear = null;
        if(!args[9].isEmpty())
            foundingYear = Integer.parseInt(args[9]);
        String phoneNumber = args[7];
        String fax = args[8];
        String email = args[11];
        String url = args[10];
        String profile = args[14];

        Museum museum = new Museum.MuseumBuilder(name, code, supervisorCode, location)
                .setManager(manager)
                .setEmail(email)
                .setFax(fax)
                .setFoundingYear(foundingYear)
                .setPhoneNumber(phoneNumber)
                .setProfile(profile)
                .setUrl(url)
                .build();

        Database.getInstance().addMuseum(museum);
        writer.write(museum.getCode() + ": " + museum.getName() + '\n');
    }
}

