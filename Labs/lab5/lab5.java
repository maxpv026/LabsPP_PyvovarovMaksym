import java.util.*;



interface Command {
    void execute();
    String getCommandName();
}


class CreateCompositionCommand implements Command {
    @Override
    public void execute() {
        // Реалізація створення музичних композицій
        System.out.println("Створення музичних композицій");
    }

    @Override
    public String getCommandName() {
        return "Додати пісню";
    }
}

class CalculateTotalDurationCommand implements Command {
    @Override
    public void execute() {
        // Реалізація рахування загальної тривалості
        System.out.println("Рахування загальної тривалості");
    }

    @Override
    public String getCommandName() {
        return "Рахувати загальну тривалість";
    }
}

class FindByStyleCommand implements Command {
    @Override
    public void execute() {

        System.out.println("Знаходження композиції за стилем");
    }

    @Override
    public String getCommandName() {
        return "Знайти композицію за стилем";
    }
}

class FindByDurationCommand implements Command {
    @Override
    public void execute() {

        System.out.println("Знаходження композиції за тривалістю");
    }

    @Override
    public String getCommandName() {
        return "Знайти композицію за тривалістю";
    }
}


public class lab5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Command> commands = new ArrayList<>();
        commands.add(new CreateCompositionCommand());
        commands.add(new CalculateTotalDurationCommand());
        commands.add(new FindByStyleCommand());
        commands.add(new FindByDurationCommand());

        int commandNumber = 1;
        for (Command command : commands) {
            System.out.println(commandNumber + ". " + command.getCommandName());
            commandNumber++;
        }
        System.out.println(commandNumber + ". Вихід");

        while (true) {

            System.out.print("Введіть номер команди (або 'help' для списку команд): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("help")) {
                for (Command command : commands) {
                    System.out.println(commandNumber + ". " + command.getCommandName());
                    commandNumber++;
                }
                System.out.println(commandNumber + ". Вихід");
            } else if (input.equalsIgnoreCase("exit")) {
                break;
            } else {
                try {
                    int choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= commands.size() + 1) {
                        commands.get(choice - 1).execute();
                    } else {
                        System.out.println("Невірний номер команди. Введіть 'help' для списку доступних команд.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Невідома команда. Введіть 'help' для списку доступних команд.");
                }
            }
        }
    }
}
