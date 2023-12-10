package commands;

import recording.Recording;
import java.util.Scanner;

public class CalculateTotalDurationCommand implements Command {
    @Override
    public void execute(Recording recording, Scanner scanner) {
        int totalDuration = recording.getTotalDuration();
        System.out.println("Total duration of compositions: " + totalDuration + " seconds");
    }

    @Override
    public String getCommandName() {
        return "Calculate total duration";
    }
}