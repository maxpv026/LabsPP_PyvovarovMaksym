package commands;

import composition.MusicalComposition;
import recording.Recording;

import java.util.List;
import java.util.Scanner;

public class FindByStyleCommand implements Command {
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