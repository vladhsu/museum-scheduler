package com.vladhsu.app.command;

import com.vladhsu.app.database.Database;
import com.vladhsu.app.interfaces.Command;
import com.vladhsu.app.model.Museum;
import java.io.BufferedWriter;

public class AddEventCommand implements Command {
    private String[] args;
    private BufferedWriter writer;

    public AddEventCommand(String[] args, BufferedWriter writer) {
        this.args = args;
        this.writer = writer;
    }

    @Override
    public void execute(){
        long museumCode = Long.parseLong(args[0]);
        String message = args[1];

        Database db = Database.getInstance();
        Museum museum = db.findMuseum(museumCode);
        museum.notifyObservers(message, writer);
    }

}

