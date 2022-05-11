package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.uniba.app.view.Terminal;

/**
 * Main test class of the application.
 */
public class AppTest {
    /**
     * Test the help method of the App class.
     */
    @Test
    public void appHasHelp() {
        assertNotNull(
                "app should have a help", Terminal.help());
    }
    
}
