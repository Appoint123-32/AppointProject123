package ui;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

class MainUITest {

    @Test
    void testMain_AdminLoginAndExit() {
        // Simulate user typing: "admin" (username), "1234" (pass), "6" (Exit option)
        String input = "admin\n1234\n6\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Run the main method - it will read our 'input' string instead of waiting for a keyboard
        try {
            main.main(new String[]{});
        } catch (NoSuchElementException e) {
            // This might happen if the scanner looks for more input than provided
        } finally {
            // Reset System.in to the keyboard for other tests
            System.setIn(System.in);
        }
    }

    @Test
    void testMain_UserLoginAndExit() {
        // Simulate user typing: "aseel" (username), "1111" (pass), "5" (Exit option)
        String input = "aseel\n1111\n5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            main.main(new String[]{});
        } catch (NoSuchElementException e) {
        } finally {
            System.setIn(System.in);
        }
    }
}