package ui;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

class MainUITest {

    @Test
    void testMain_AdminLoginAndExit() {
        String input = "admin\n1234\n6\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

    
        try {
            main.main(new String[]{});
        } catch (NoSuchElementException e) {
          
        } finally {
            
            System.setIn(System.in);
        }
    }

    @Test
    void testMain_UserLoginAndExit() {
      
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
    @Test
    void testMain_InvalidMenuChoice() {
       
        String input = "admin\n1234\n9\n6\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            main.main(new String[]{});
        } catch (Exception e) {}
    }
}