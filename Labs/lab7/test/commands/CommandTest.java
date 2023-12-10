package commands;

import org.junit.jupiter.api.Test;
import recording.Recording;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    @Test
    void testAddSongCommand() {
        // Set up input for the test
        String input = "Test Song\n200\nRock\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Set up output capturing
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create objects for testing
        Recording recording = new Recording();
        Command addSongCommand = new AddSongCommand();

        // Execute the command
        addSongCommand.execute(recording, new Scanner(System.in));

        // Assert the output
        assertEquals("Enter song title: Enter duration (in seconds): Enter song style: Song added successfully!\n", outputStreamCaptor.toString());
        assertEquals("Test Song", recording.getCompositions().get(0).getTitle());
    }

    @Test
    void testCalculateTotalDurationCommand() {
        // Set up output capturing
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create objects for testing
        Recording recording = new Recording();
        Command calculateTotalDurationCommand = new CalculateTotalDurationCommand();

        // Execute the command
        calculateTotalDurationCommand.execute(recording, new Scanner(System.in));

        // Assert the output
        assertEquals("Total duration of compositions: 0 seconds\n", outputStreamCaptor.toString());
    }

    // Similar tests can be created for FindByStyleCommand and FindByDurationCommand

    @Test
    void testFindByStyleCommand() {
        // Set up input for the test
        String input = "Rock\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Set up output capturing
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create objects for testing
        Recording recording = new Recording();
        Command findByStyleCommand = new FindByStyleCommand();

        // Execute the command
        findByStyleCommand.execute(recording, new Scanner(System.in));

        // Assert the output
        assertEquals("Enter the style to search: Compositions of style 'Rock':\n", outputStreamCaptor.toString());
    }

    @Test
    void testFindByDurationCommand() {
        // Set up input for the test
        String input = "100\n300\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Set up output capturing
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create objects for testing
        Recording recording = new Recording();
        Command findByDurationCommand = new FindByDurationCommand();

        // Execute the command
        findByDurationCommand.execute(recording, new Scanner(System.in));

        // Assert the output
        assertEquals("Enter minimum duration: Enter maximum duration: No composition found in the specified range.\n", outputStreamCaptor.toString());
    }
}
