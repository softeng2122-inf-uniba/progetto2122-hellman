package it.uniba.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    /** Oggetto di tipo Player creato per effettuare i test
     * sui vari metodi della classe. */
    private Player player = new Player();

    /** Oggetto di tipo Game creato per effettuare i test
     * sui vari metodi della classe Player. */
    Game game = new Game();

    /**
     * Metodo per il testing del metodo getCurrentGame di Player,
     * che verifica che il campo secretWord sia stato passato correttamente.
     */
    @Test
    public void testGetCurrentGameSecretWord() {
        player.setCurrentGame(game);
        assertEquals(game.getSecretWord(), player.getCurrentGame().getSecretWord());
    }

    /**
     * Metodo per il testing del metodo getCurrentGame di Player,
     * che verifica che il campo configurable sia stato passato correttamente.
     */
    @Test
    public void testGetCurrentGameConfigurable() {
        player.setCurrentGame(game);
        assertEquals(game.isConfigurable(), player.getCurrentGame().isConfigurable());
    }

    /**
     * Metodo per il testing del metodo getCurrentGame di Player,
     * che verifica che il campo trys sia stato passato correttamente.
     */
    @Test
    public void testGetCurrentGameTrys() {
        player.setCurrentGame(game);
        assertEquals(game.getTrys(), player.getCurrentGame().getTrys());
    }

    /**
     * Metodo per il testing del metodo setCurrentGame di Player,
     * che verifica che il campo secretWord sia stato settato correttamente.
     */
    @Test
    public void testSetCurrentGameSecretWord() {
        player.setCurrentGame(game);
        assertEquals(game.getSecretWord(), player.getCurrentGame().getSecretWord());
    }

    /**
     * Metodo per il testing del metodo setCurrentGame di Player,
     * che verifica che il campo configurable sia stato settato correttamente.
     */
    @Test
    public void testSetCurrentGameConfigurable() {
        player.setCurrentGame(game);
        assertEquals(game.isConfigurable(), player.getCurrentGame().isConfigurable());
    }

    /**
     * Metodo per il testing del metodo setCurrentGame di Player,
     * che verifica che il campo trys sia stato settato correttamente.
     */
    @Test
    public void testSetCurrentGameTrys() {
        player.setCurrentGame(game);
        assertEquals(game.getTrys(), player.getCurrentGame().getTrys());
    }

}