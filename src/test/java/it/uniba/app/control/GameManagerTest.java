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

    @Test
    public void testStartGameSetSecretWord() {
        Game configuratedGame = new Game();

        configuratedGame.setSecretWord("month");

        try {
            GameManager.startGame(game, configuratedGame);
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(GameManager.getSecretWord(game),
                GameManager.getSecretWord(configuratedGame));
    }

    @Test
    public void testStartGameGameNotConfigurable() {
        Game configuratedGame = new Game();

        configuratedGame.setSecretWord("month");

        try {
            GameManager.startGame(game, configuratedGame);
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertTrue(!game.isConfigurable());
    }

    @Test
    public void testStartGameConfiguratedGameNull() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    Game configuratedGame = null;
                    GameManager.startGame(game, configuratedGame);
                });

        assertEquals("Non è ancora stata inserita la parola segreta!",
                exception.getMessage());
    }

    @Test
    public void testStartGameConfiguratedGameEmptySecretWord() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    Game configuratedGame = new Game();
                    GameManager.startGame(game, configuratedGame);
                });

        assertEquals("Non è ancora stata inserita la parola segreta!",
                exception.getMessage());
    }

    @Test
    public void testStartGameCurrentGameHasSecretWord() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    Game configuratedGame = new Game();

                    game.setSecretWord("glass");

                    GameManager.startGame(game, configuratedGame);
                });

        assertEquals("C'è già un game in corso.", exception.getMessage());
    }

    @Test
    public void testBackGame() {
        game.setSecretWord("bread");
        game.disableConfigurable();

        try {
            GameManager.backGame(game);
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(GameManager.getSecretWord(game), "");
    }

    @Test
    public void testBackGameConfigurableGame() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.setSecretWord("mouse");
                    GameManager.backGame(game);
                });

        assertEquals("Non c'è un game in corso.", exception.getMessage());
    }

    @Test
    public void testBackGameEmptySecretWord() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.disableConfigurable();
                    GameManager.backGame(game);
                });

        assertEquals("Non c'è un game in corso.", exception.getMessage());
    }
}
