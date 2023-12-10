package executor;

import commands.*;
import recording.Recording;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Executor {

    public Map<Integer, Command> commandMap;
    public Scanner scanner;

    public Executor(Recording recording, Scanner scanner) {
        this.scanner = scanner;

        commandMap = new HashMap<>();
        commandMap.put(1, new AddSongCommand());
        commandMap.put(2, new CalculateTotalDurationCommand());
        commandMap.put(3, new FindByStyleCommand());
        commandMap.put(4, new FindByDurationCommand());
    }

    public void displayCommands() {
        for (Map.Entry<Integer, Command> entry : commandMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getCommandName());
        }
        System.out.println((commandMap.size() + 1) + ". Exit");
    }

    public void executeCommand(int choice, Recording recording) {
        if (commandMap.containsKey(choice)) {
            commandMap.get(choice).execute(recording, scanner);
        } else {
            System.out.println("Invalid command. Enter 'help' for available commands.");
        }
    }
}