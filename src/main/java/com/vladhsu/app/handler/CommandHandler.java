package com.vladhsu.app.handler;

import com.vladhsu.app.interfaces.Command;
import com.vladhsu.app.exception.*;
import com.vladhsu.app.factory.CommandFactory;
import java.io.*;
import java.util.Arrays;

public class CommandHandler {
    public void handleInput(String[] args) {
        if(args.length == 0){
            System.out.println("No command specified");
            return;
        }
        if(args.length == 2){
            processSingleFile(args[0], args[1]);
        } else if(args.length == 4){
            processMultipleFiles(args);
        }
    }

    private void processSingleFile(String pathType, String file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file + ".in"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file + ".out", true))) {
            String line;
            reader.readLine();
            while((line = reader.readLine()) != null){
                processCommand(pathType, line, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processMultipleFiles(String[] args) {
        String pathType = args[0];
        String museumFile = args[1];
        String groupsFile = args[2];
        String eventsFile = args[3];
        processSingleFile(pathType, museumFile);
        processSingleFile(pathType, groupsFile);
        processSingleFile(pathType, eventsFile);
    }

    private void processCommand(String pathType, String line, BufferedWriter writer) throws IOException {
        String[] parts = line.split("\\|");
        String commandType = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        Command command = CommandFactory.createCommand(pathType, commandType, args, writer);
        try {
            command.execute();
        } catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e){
            writer.write("Exception: Data is broken. ## (" + line + ")\n");
        } catch (GuideTypeException | GuideExistsException | GroupNotExistsException
                 | GroupThresholdException | PersonNotExistsException e) {
            writer.write(e.getMessage() + "\n");
        }
    }
}

