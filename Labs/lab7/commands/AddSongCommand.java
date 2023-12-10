package commands;

import composition.Song;
import recording.Recording;

import java.util.Scanner;

//interface Command {
//    void execute(Recording recording, Scanner scanner);
//    String getCommandName();
//}

public class AddSongCommand implements Command {
    @Override
    public void execute(Recording recording, Scanner scanner) {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();

        int duration;
        while (true) {
            System.out.print("Enter duration (in seconds): ");

            if (scanner.hasNextInt()) {
                duration = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                break;
            } else {
                System.out.println("Invalid input for duration. Please enter a valid integer.");
                scanner.nextLine(); // consume the invalid input
            }
        }

        System.out.print("Enter song style: ");
        String style = scanner.nextLine();
        recording.addComposition(new Song(title, duration, style));
        System.out.println("Song added successfully!");
    }

    @Override
    public String getCommandName() {
        return "Add a song";
    }
}
