@Test
    void testMain_LoginThenExit() {
        String simulatedInput = String.join(System.lineSeparator(),
                "admin",
                "1234",
                "6"
        ) + System.lineSeparator();

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream testIn =
                new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();

      
        try (PrintStream ps = new PrintStream(testOut)) {
            System.setIn(testIn);
            System.setOut(ps);

           
            Main.main(new String[]{}); 

            String output = testOut.toString();

            assertTrue(output.contains("Login successful!"));
            assertTrue(output.contains("Exiting system..."));

        } finally {
          
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
