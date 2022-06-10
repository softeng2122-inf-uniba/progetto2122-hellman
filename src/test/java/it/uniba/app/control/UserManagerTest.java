package it.uniba.app.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import it.uniba.app.utils.GameException;

public class UserManagerTest {
    private UserManager userManager = new UserManager();
    
    @Test
    public void testSetSecretWord() {
        String secretWord = "clock";

        try {
            userManager.setSecretWord(secretWord);
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(secretWord, userManager.getSecretWord());
    }

    @Test
    public void testSecretWordTooLong() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("tooLong");
                });

        assertEquals("La parola inserita contiene un numero"
                + " troppo elevato di caratteri.", exception.getMessage());
    }

    @Test
    public void testSecretWordTooShort() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("test");
                });

        assertEquals("La parola inserita contiene un numero insufficiente di "
                + "caratteri.", exception.getMessage());
    }

    @Test
    public void testSecretWordUnavailableCharacters() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("te$st");
                });

        assertEquals("La parola contiene caratteri non ammessi.",
                exception.getMessage());
    }

    @Test
    public void testGetSecretWord() {
        String secretWord = "hands";

        try {
            userManager.setSecretWord(secretWord);
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(userManager.getSecretWord(), secretWord);
    }
}
