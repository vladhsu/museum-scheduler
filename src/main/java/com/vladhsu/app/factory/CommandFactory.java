package com.vladhsu.app.factory;

import com.vladhsu.app.interfaces.Command;
import com.vladhsu.app.command.*;
import java.io.BufferedWriter;

public class CommandFactory {
    public static Command createCommand(String pathType, String commandType, String[] args, BufferedWriter writer) {
        return switch(commandType){
            case "ADD MUSEUM" -> new AddMuseumCommand(args, writer);
            case "ADD GUIDE" -> new AddGuideCommand(args, writer);
            case "FIND GUIDE" -> new FindGuideCommand(args, writer);
            case "REMOVE GUIDE" -> new RemoveGuideCommand(args, writer);
            case "ADD MEMBER" -> new AddMemberCommand(args, writer);
            case "FIND MEMBER" -> new FindMemberCommand(args, writer);
            case "REMOVE MEMBER" -> new RemoveMemberCommand(args, writer);
            case "ADD EVENT" -> new AddEventCommand(args, writer);
            default -> null;
        };
    }
}
