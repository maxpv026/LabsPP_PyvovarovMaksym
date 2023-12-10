package commands;

import recording.Recording;
import java.util.Scanner;

public interface Command {
    void execute(Recording recording, Scanner scanner);
    String getCommandName();
}