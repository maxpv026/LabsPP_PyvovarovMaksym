package executor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import recording.Recording;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExecutorTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testDisplayCommands() {
        // Set up output capturing
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create objects for testing
        Recording recording = new Recording();
        Executor executor = new Executor(recording, new Scanner(System.in));

        // Execute the command
        executor.displayCommands();

        // Assert the output
        assertEquals("1. Add a song\n2. Calculate total duration\n3. Find compositions by style\n4. Find compositions by duration\n5. Exit\n", outputStreamCaptor.toString());
    }
    private static final int ADD_SONG_COMMAND = 1;

    void testExecuteValidCommand() {
        // Set up input for the test
        String input = ADD_SONG_COMMAND + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Create objects for testing
        Recording recording = new Recording();
        Executor executor = new Executor(recording, new Scanner(System.in));

        // Execute the command
        executor.executeCommand(ADD_SONG_COMMAND, recording);

        // Assert the output
        assertEquals("Enter song title: Enter duration (in seconds): Enter song style: Song added successfully!\n", outputStreamCaptor.toString());
    }


    @Test
    void testExecuteInvalidCommand() {
        // Create objects for testing
        Recording recording = new Recording();
        Executor executor = new Executor(recording, new Scanner(System.in));

        // Execute the command
        executor.executeCommand(99, recording);

        // Assert the output
        assertEquals("Invalid command. Enter 'help' for available commands.\n", outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
