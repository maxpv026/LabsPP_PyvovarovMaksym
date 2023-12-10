
/*
import java.util.*;
abstract class MusicalComposition {

    private String title;
    private int duration; // Duration in seconds

    public MusicalComposition(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public abstract String getStyle();
}

class Song extends MusicalComposition {
    private String style;

    public Song(String title, int duration, String style) {
        super(title, duration);
        this.style = style;
    }

    @Override
    public String getStyle() {
        return style;
    }
}

class Symphony extends MusicalComposition {
    private String composer;

    public Symphony(String title, int duration, String composer) {
        super(title, duration);
        this.composer = composer;
    }

    @Override
    public String getStyle() {
        return "Symphony";
    }
}

class Recording {

    private List<MusicalComposition> compositions;

    public Recording() {
        compositions = new ArrayList<>();
    }

    public void addComposition(MusicalComposition composition) {
        compositions.add(composition);
    }

    public int getTotalDuration() {
        int totalDuration = 0;
        for (MusicalComposition composition : compositions) {
            totalDuration += composition.getDuration();
        }
        return totalDuration;
    }

    public List<MusicalComposition> getCompositionsByStyle(String style) {
        List<MusicalComposition> compositionsByStyle = new ArrayList<>();
        for (MusicalComposition composition : compositions) {
            if (composition.getStyle().equals(style)) {
                compositionsByStyle.add(composition);
            }
        }
        return compositionsByStyle;
    }

    public MusicalComposition findCompositionByDurationRange(int minLength, int maxLength) {
        for (MusicalComposition composition : compositions) {
            int duration = composition.getDuration();
            if (duration >= minLength && duration <= maxLength) {
                return composition;
            }
        }
        return null;
    }
}

interface Command {
    void execute(Recording recording, Scanner scanner);
    String getCommandName();
}

class AddSongCommand implements Command {
    @Override
    public void execute(Recording recording, Scanner scanner) {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter duration (in seconds): ");
        int duration = scanner.nextInt();
        scanner.nextLine();
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

class CalculateTotalDurationCommand implements Command {
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

class FindByStyleCommand implements Command {
    @Override
    public void execute(Recording recording, Scanner scanner) {
        System.out.print("Enter the style to search: ");
        String selectedStyle = scanner.nextLine();
        List<MusicalComposition> compositionsByStyle = recording.getCompositionsByStyle(selectedStyle);
        System.out.println("Compositions of style '" + selectedStyle + "':");
        for (MusicalComposition composition : compositionsByStyle) {
            System.out.println(composition.getTitle());
        }
    }

    @Override
    public String getCommandName() {
        return "Find compositions by style";
    }
}

class FindByDurationCommand implements Command {
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

class Executor {
    Map<Integer, Command> commandMap;
    private Scanner scanner;

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
*/

import executor.Executor;
import recording.Recording;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Recording recording = new Recording();
        Scanner scanner = new Scanner(System.in);
        Executor executor = new Executor(recording, scanner);

        while (true) {
            executor.displayCommands();
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == executor.commandMap.size() + 1) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            } else if (option == 99) {
                executor.displayCommands();
            } else if (option >= 1 && option <= executor.commandMap.size()) {
                executor.executeCommand(option, recording);
            } else {
                System.out.println("Invalid option. Please select again.");
            }
        }
    }
}




