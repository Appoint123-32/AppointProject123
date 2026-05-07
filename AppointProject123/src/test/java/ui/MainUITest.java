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
            Main.main(new String[]{});
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
            Main.main(new String[]{});
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
            Main.main(new String[]{});
        } catch (Exception e) {}
    }
    @Test
    void testMain_AdminFullFeatureFlow() {
        String input = "admin\n1234\n" + 
                       "1\n10\nT\n30\n1\n2\n1\n1\nhttp://test.com\n" + 
                       "2\n" + 
                       "4\n10\n" + 
                       "6\n";
        
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            Main.main(new String[]{});
        } catch (Exception e) {
        } finally {
            System.setIn(System.in);
        }
    }
    @Test
    void testMain_UserFullFeatureFlow() {
       
        String input = "admin\n1234\n1\n10\nTest\n30\n1\n2\n1\n1\nlink\n5\n" + 
                       "aseel\n1111\n" + 
                       "1\n" + 
                       "2\n10\n" + 
                       "3\n10\n" + 
                       "5\n";
        
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            Main.main(new String[]{});
        } catch (Exception e) {
        } finally {
            System.setIn(System.in);
        }
    }
    @Test
    void testMain_LoginFailureBranch() {
        
        String input = "admin\nwrongpass\nadmin\n1234\n6\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            Main.main(new String[]{});
        } catch (Exception e) {
        } finally {
            System.setIn(System.in);
        }
    }
    @Test
    void testMain_SubMenuDefaultBranches() {
   
        String script = "admin\n1234\n" + 
                        "1\n111\nT\n30\n1\n9\n9\n9\nDefaultLoc\n" + 
                        "6\n";

        InputStream in = new ByteArrayInputStream(script.getBytes());
        System.setIn(in);

        try {
            Main.main(new String[]{});
        } catch (Exception e) {}
        finally { System.setIn(System.in); }
    }
    
}
