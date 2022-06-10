package it.uniba.app.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniba.app.models.Game;

import it.uniba.app.utils.GameException;

public class GameManagerTest {
    private Game game = new Game();

    @Test
    public void testSetSecretWord() {
        String secretWord = "clock";

        try {
            GameManager.setSecretWord(game, secretWord);
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(secretWord, game.getSecretWord());
    }

    @Test
    public void testSecretWordTooLong() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    GameManager.setSecretWord(game, "tooLong");
                });

        assertEquals("La parola inserita contiene un numero"
                + " troppo elevato di caratteri.", exception.getMessage());
    }

    @Test
    public void testSecretWordTooShort() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    GameManager.setSecretWord(game, "test");
                });

        assertEquals("La parola inserita contiene un numero insufficiente di "
                + "caratteri.", exception.getMessage());
    }

    @Test
    public void testSecretWordUnavailableCharacters() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    GameManager.setSecretWord(game, "te$st");
                });

        assertEquals("La parola contiene caratteri non ammessi.",
                exception.getMessage());
    }

    @Test
    public void testSecretWordUnconfigurableGame() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.disableConfigurable();
                    GameManager.setSecretWord(game, "river");
                });

        assertEquals("Il game non è configurabile.", exception.getMessage());
    }

    @Test
    public void testGetSecretWord() {
        String secretWord = "hands";

        game.setSecretWord(secretWord);

        assertEquals(GameManager.getSecretWord(game), secretWord);
    }

    
}
