package com.vladhsu.app;

import com.vladhsu.app.handler.CommandHandler;
import com.vladhsu.app.database.Database;

public class App {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.handleInput(args);
        Database.getInstance().cleanup();
    }
}
