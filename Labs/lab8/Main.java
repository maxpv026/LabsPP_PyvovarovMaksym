
import org.apache.log4j.*;

import java.util.*;

interface Command {
    void execute(Recording recording, Scanner scanner);

    String getCommandName();
}

abstract class MusicalComposition {

    private static final Logger logger = Logger.getLogger(MusicalComposition.class);

    private String title;
    private int duration;

    public MusicalComposition(String title, int duration) {
        this.title = title;
        this.duration = duration;
        logger.info("Created MusicalComposition: " + title + ", Duration: " + duration);
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

    private static final Logger logger = Logger.getLogger(Recording.class);

    private List<MusicalComposition> compositions;

    public Recording() {
        compositions = new ArrayList<>();
    }

    public void addComposition(MusicalComposition composition) {
        compositions.add(composition);
        logger.info("Added composition: " + composition.getTitle());
    }

    public int getTotalDuration() {
        int totalDuration = 0;
        for (MusicalComposition composition : compositions) {
            totalDuration += composition.getDuration();
        }
        logger.info("Calculated total duration: " + totalDuration + " seconds");
        return totalDuration;
    }

    public List<MusicalComposition> getCompositionsByStyle(String style) {
        List<MusicalComposition> compositionsByStyle = new ArrayList<>();
        for (MusicalComposition composition : compositions) {
            if (composition.getStyle().equals(style)) {
                compositionsByStyle.add(composition);
            }
        }
        logger.info("Found compositions by style '" + style + "': " + compositionsByStyle);
        return compositionsByStyle;
    }

    public MusicalComposition findCompositionByDurationRange(int minLength, int maxLength) {
        for (MusicalComposition composition : compositions) {
            int duration = composition.getDuration();
            if (duration >= minLength && duration <= maxLength) {
                logger.info("Found composition within duration range: " + composition.getTitle());
                return composition;
            }
        }
        logger.info("No composition found in the specified duration range.");
        return null;
    }
}

class AddSongCommand implements Command {
    private static final Logger logger = Logger.getLogger(AddSongCommand.class);

    @Override
    public void execute(Recording recording, Scanner scanner) {
        try {
            System.out.print("Enter song title: ");
            String title = scanner.nextLine();
            System.out.print("Enter duration (in seconds): ");
            int duration = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter song style: ");
            String style = scanner.nextLine();
            recording.addComposition(new Song(title, duration, style));
            logger.info("Added a new song: " + title);
            System.out.println("Song added successfully!");
        } catch (Exception e) {
            logger.error("Error executing AddSongCommand.", e);

            // Send an email for critical errors
            logger.error("Sending email for critical error.");
        }
    }

    @Override
    public String getCommandName() {
        return "Add a song";
    }
}

class CalculateTotalDurationCommand implements Command {
    private static final Logger logger = Logger.getLogger(CalculateTotalDurationCommand.class);

    @Override
    public void execute(Recording recording, Scanner scanner) {
        try {
            int totalDuration = recording.getTotalDuration();
            logger.info("Calculated total duration command executed.");
            System.out.println("Total duration of compositions: " + totalDuration + " seconds");
        } catch (Exception e) {
            logger.error("Error executing CalculateTotalDurationCommand.", e);
        }
    }

    @Override
    public String getCommandName() {
        return "Calculate total duration";
    }
}

class FindByStyleCommand implements Command {
    private static final Logger logger = Logger.getLogger(FindByStyleCommand.class);

    @Override
    public void execute(Recording recording, Scanner scanner) {
        try {
            System.out.print("Enter the style to search: ");
            String selectedStyle = scanner.nextLine();
            List<MusicalComposition> compositionsByStyle = recording.getCompositionsByStyle(selectedStyle);
            logger.info("Find compositions by style command executed for style: " + selectedStyle);
            System.out.println("Compositions of style '" + selectedStyle + "':");
            for (MusicalComposition composition : compositionsByStyle) {
                System.out.println(composition.getTitle());
            }
        } catch (Exception e) {
            logger.error("Error executing FindByStyleCommand.", e);
        }
    }

    @Override
    public String getCommandName() {
        return "Find compositions by style";
    }
}

class FindByDurationCommand implements Command {
    private static final Logger logger = Logger.getLogger(FindByDurationCommand.class);

    @Override
    public void execute(Recording recording, Scanner scanner) {
        try {
            System.out.print("Enter minimum duration: ");
            int minDuration = scanner.nextInt();
            System.out.print("Enter maximum duration: ");
            int maxDuration = scanner.nextInt();
            scanner.nextLine();
            MusicalComposition foundComposition = recording.findCompositionByDurationRange(minDuration, maxDuration);
            logger.info("Find compositions by duration command executed for duration range: " + minDuration + " - " + maxDuration);
            if (foundComposition != null) {
                System.out.println("Composition found within " + minDuration + " - " + maxDuration + " seconds: " + foundComposition.getTitle());
            } else {
                System.out.println("No composition found in the specified range.");
            }
        } catch (Exception e) {
            logger.error("Error executing FindByDurationCommand.", e);
        }
    }

    @Override
    public String getCommandName() {
        return "Find compositions by duration";
    }
}

class Executor {
    static final Logger logger = Logger.getLogger(Executor.class);
    Map<Integer, Command> commandMap;
    private Scanner scanner;

    public Executor(Recording recording, Scanner scanner) {
        this.scanner = scanner;

        commandMap = new HashMap<>();
        commandMap.put(1, new AddSongCommand());
        commandMap.put(2, new CalculateTotalDurationCommand());
        commandMap.put(3, new FindByStyleCommand());
        commandMap.put(4, new FindByDurationCommand());

        setupLogging();
    }

    private void setupLogging() {
        try {
            // Log configuration
            org.apache.log4j.Logger rootLogger = org.apache.log4j.Logger.getRootLogger();

            // Log to console
            ConsoleAppender consoleAppender = new ConsoleAppender(new PatternLayout("%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
            rootLogger.addAppender(consoleAppender);

            // Log to file
            FileAppender fileAppender = new FileAppender(new PatternLayout("%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"), "music.log", false);
            rootLogger.addAppender(fileAppender);

            // Set log level (you can adjust this as needed)
            rootLogger.setLevel(Level.ALL);

            logger.info("Logging initialized.");
        } catch (Exception e) {
            logger.error("Error setting up logging.", e);
        }
    }
    public void displayCommands() {
        for (Map.Entry<Integer, Command> entry : commandMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getCommandName());
        }
        System.out.println((commandMap.size() + 1) + ". Exit");
    }

    public void executeCommand(int choice, Recording recording) {
        if (commandMap.containsKey(choice)) {
            try {
                commandMap.get(choice).execute(recording, scanner);
            } catch (Exception e) {
                logger.error("Error executing command.", e);
            }
        } else {
            logger.warn("Invalid command entered: " + choice);
            System.out.println("Invalid command. Enter 'help' for available commands.");
        }
    }

    public void closeHandlers() {
        // Log4j doesn't require explicit handler closing
    }
}

public class Main {
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
                executor.logger.warn("Invalid option entered: " + option);
                System.out.println("Invalid option. Please select again.");
            }
        }

        executor.closeHandlers();
    }
}

/*
import org.apache.log4j.*;
import org.apache.log4j.net.SMTPAppender;

import java.util.*;

interface Command {
    void execute(Recording recording, Scanner scanner);

    String getCommandName();
}

abstract class MusicalComposition {

    private static final Logger logger = Logger.getLogger(MusicalComposition.class);

    private String title;
    private int duration;

    public MusicalComposition(String title, int duration) {
        this.title = title;
        this.duration = duration;
        logger.info("Created MusicalComposition: " + title + ", Duration: " + duration);
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

    private static final Logger logger = Logger.getLogger(Recording.class);

    private List<MusicalComposition> compositions;

    public Recording() {
        compositions = new ArrayList<>();
    }

    public void addComposition(MusicalComposition composition) {
        compositions.add(composition);
        logger.info("Added composition: " + composition.getTitle());
    }

    public int getTotalDuration() {
        int totalDuration = 0;
        for (MusicalComposition composition : compositions) {
            totalDuration += composition.getDuration();
        }
        logger.info("Calculated total duration: " + totalDuration + " seconds");
        return totalDuration;
    }

    public List<MusicalComposition> getCompositionsByStyle(String style) {
        List<MusicalComposition> compositionsByStyle = new ArrayList<>();
        for (MusicalComposition composition : compositions) {
            if (composition.getStyle().equals(style)) {
                compositionsByStyle.add(composition);
            }
        }
        logger.info("Found compositions by style '" + style + "': " + compositionsByStyle);
        return compositionsByStyle;
    }

    public MusicalComposition findCompositionByDurationRange(int minLength, int maxLength) {
        for (MusicalComposition composition : compositions) {
            int duration = composition.getDuration();
            if (duration >= minLength && duration <= maxLength) {
                logger.info("Found composition within duration range: " + composition.getTitle());
                return composition;
            }
        }
        logger.info("No composition found in the specified duration range.");
        return null;
    }
}

class AddSongCommand implements Command {
    private static final Logger logger = Logger.getLogger(AddSongCommand.class);

    @Override
    public void execute(Recording recording, Scanner scanner) {
        try {
            System.out.print("Enter song title: ");
            String title = scanner.nextLine();
            System.out.print("Enter duration (in seconds): ");
            int duration = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter song style: ");
            String style = scanner.nextLine();
            recording.addComposition(new Song(title, duration, style));
            logger.info("Added a new song: " + title);
            System.out.println("Song added successfully!");
        } catch (Exception e) {
            logger.error("Error executing AddSongCommand.", e);
        }
    }

    @Override
    public String getCommandName() {
        return "Add a song";
    }
}

class CalculateTotalDurationCommand implements Command {
    private static final Logger logger = Logger.getLogger(CalculateTotalDurationCommand.class);

    @Override
    public void execute(Recording recording, Scanner scanner) {
        try {
            int totalDuration = recording.getTotalDuration();
            logger.info("Calculated total duration command executed.");
            System.out.println("Total duration of compositions: " + totalDuration + " seconds");
        } catch (Exception e) {
            logger.error("Error executing CalculateTotalDurationCommand.", e);
        }
    }

    @Override
    public String getCommandName() {
        return "Calculate total duration";
    }
}

class FindByStyleCommand implements Command {
    private static final Logger logger = Logger.getLogger(FindByStyleCommand.class);

    @Override
    public void execute(Recording recording, Scanner scanner) {
        try {
            System.out.print("Enter the style to search: ");
            String selectedStyle = scanner.nextLine();
            List<MusicalComposition> compositionsByStyle = recording.getCompositionsByStyle(selectedStyle);
            logger.info("Find compositions by style command executed for style: " + selectedStyle);
            System.out.println("Compositions of style '" + selectedStyle + "':");
            for (MusicalComposition composition : compositionsByStyle) {
                System.out.println(composition.getTitle());
            }
        } catch (Exception e) {
            logger.error("Error executing FindByStyleCommand.", e);
        }
    }

    @Override
    public String getCommandName() {
        return "Find compositions by style";
    }
}

class FindByDurationCommand implements Command {
    private static final Logger logger = Logger.getLogger(FindByDurationCommand.class);

    @Override
    public void execute(Recording recording, Scanner scanner) {
        try {
            System.out.print("Enter minimum duration: ");
            int minDuration = scanner.nextInt();
            System.out.print("Enter maximum duration: ");
            int maxDuration = scanner.nextInt();
            scanner.nextLine();
            MusicalComposition foundComposition = recording.findCompositionByDurationRange(minDuration, maxDuration);
            logger.info("Find compositions by duration command executed for duration range: " + minDuration + " - " + maxDuration);
            if (foundComposition != null) {
                System.out.println("Composition found within " + minDuration + " - " + maxDuration + " seconds: " + foundComposition.getTitle());
            } else {
                System.out.println("No composition found in the specified range.");
            }
        } catch (Exception e) {
            logger.error("Error executing FindByDurationCommand.", e);
        }
    }

    @Override
    public String getCommandName() {
        return "Find compositions by duration";
    }
}

class Executor {
    static final Logger logger = Logger.getLogger(Executor.class);
    Map<Integer, Command> commandMap;
    private Scanner scanner;

    public Executor(Recording recording, Scanner scanner) {
        this.scanner = scanner;

        commandMap = new HashMap<>();
        commandMap.put(1, new AddSongCommand());
        commandMap.put(2, new CalculateTotalDurationCommand());
        commandMap.put(3, new FindByStyleCommand());
        commandMap.put(4, new FindByDurationCommand());

        setupLogging();
    }

    private void setupLogging() {
        try {
            // Log configuration
            org.apache.log4j.Logger rootLogger = org.apache.log4j.Logger.getRootLogger();

            // Log to console
            ConsoleAppender consoleAppender = new ConsoleAppender(new PatternLayout("%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
            rootLogger.addAppender(consoleAppender);

            // Log to file
            FileAppender fileAppender = new FileAppender(new PatternLayout("%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"), "music.log", false);
            rootLogger.addAppender(fileAppender);

            // Set log level (you can adjust this as needed)
            rootLogger.setLevel(Level.ALL);

            // SMTP Appender for sending critical errors to email
            SMTPAppender smtpAppender = new SMTPAppender();
            smtpAppender.setTo("maksym.pyvovarov.oi.2022@lpnu.ua");
            smtpAppender.setFrom("maxym940@gmail.com");
            smtpAppender.setSubject("Critical Error in Music App");
            smtpAppender.setLayout(new PatternLayout("%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
            smtpAppender.setSMTPHost("your-smtp-server.com");
            smtpAppender.setSMTPPort(25);  // Adjust the port if needed
            smtpAppender.activateOptions();  // Activate the options

            // Add SMTP Appender
            rootLogger.addAppender(smtpAppender);

            logger.info("Logging initialized.");
        } catch (Exception e) {
            logger.error("Error setting up logging.", e);
        }
    }

    public void displayCommands() {
        for (Map.Entry<Integer, Command> entry : commandMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getCommandName());
        }
        System.out.println((commandMap.size() + 1) + ". Exit");
    }

    public void executeCommand(int choice, Recording recording) {
        if (commandMap.containsKey(choice)) {
            try {
                commandMap.get(choice).execute(recording, scanner);
            } catch (Exception e) {
                logger.error("Error executing command.", e);
            }
        } else {
            logger.warn("Invalid command entered: " + choice);
            System.out.println("Invalid command. Enter 'help' for available commands.");
        }
    }

    public void closeHandlers() {
        // Log4j doesn't require explicit handler closing
    }
}

public class Main {
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
                executor.logger.warn("Invalid option entered: " + option);
                System.out.println("Invalid option. Please select again.");
            }
        }

        executor.closeHandlers();
    }
}

*/
