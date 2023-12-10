package commands;

import composition.MusicalComposition;
import recording.Recording;

import java.util.Scanner;

public class FindByDurationCommand implements Command {
    @Override
    public void execute(Recording recording, Scanner scanner) {
        System.out.print("Enter minimum duration: ");
        int minDuration = scanner.nextInt();
        System.out.print("Enter maximum duration: ");
        int maxDuration = scanner.nextInt();
        scanner.nextLine();
        MusicalComposition foundComposition = recording.findCompositionByDurationRange(minDuration, maxDuration);
        if (foundComposition != null) {
            System.out.println("Composition found within " + minDuration + " - " + maxDuration + " seconds: " + foundComposition.getTitle());
        } else {
            System.out.println("No composition found in the specified range.");
        }
    }

    @Override
    public String getCommandName() {
        return "Find compositions by duration";
    }
}