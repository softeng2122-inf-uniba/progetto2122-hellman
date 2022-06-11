package it.uniba.app.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniba.app.models.Word;
import it.uniba.app.utils.GameException;
import it.uniba.app.utils.Helper;
import it.uniba.app.utils.Pair;


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

   
    @Test
    public void testStartGameConfiguratedGameNull() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.startGame();
                });

        assertEquals("Non è ancora stata inserita la parola segreta!",
                exception.getMessage());
    }

    
    @Test
    public void testStartGameConfiguratedGameEmptySecretWord() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.startGame();
                });

        assertEquals("Non è ancora stata inserita la parola segreta!",
                exception.getMessage());
    }

    
    @Test
    public void testBackGame() {
        try {
            userManager.setSecretWord("bread");
            userManager.startGame();

            userManager.backGame();
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertFalse(userManager.isGameStarted());
    }

    
    @Test
    public void testBackGameConfigurableGame() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("mouse");
                    userManager.backGame();
                });

        assertEquals("Non c'è un game in corso.", exception.getMessage());
    }

   
    @Test
    public void testBackGameEmptySecretWord() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.backGame();
                });

        assertEquals("Non c'è un game in corso.", exception.getMessage());
    }

   
    
}
