package it.uniba.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WordSmithTest{

    /** Oggetto di tipo WordSmith creato per effettuare i test
     * sui vari metodi della classe. */
    private WordSmith wordSmith = new WordSmith();

    /** Oggetto di tipo Game creato per effettuare i test
     * sui vari metodi della classe WordSmith. */
    Game game = new Game();

    /**
     * Metodo per il testing del metodo getCurrentGame di WordSmith,
     * che verifica che il campo secretWord sia stato passato correttamente.
     */
    @Test
    public void testGetConfiguratedGameSecretWord() {
        wordSmith.setConfiguratedGame(game);
        assertEquals(game.getSecretWord(), wordSmith.getConfiguratedGame().getSecretWord());
    }

    /**
     * Metodo per il testing del metodo getConfiguratedGame di WordSmith,
     * che verifica che il campo configurable sia stato passato correttamente.
     */
    @Test
    public void testGetConfiguratedGameConfigurable() {
        wordSmith.setConfiguratedGame(game);
        assertEquals(game.isConfigurable(), wordSmith.getConfiguratedGame().isConfigurable());
    }

    /**w
     * Metodo per il testing del metodo getConfiguratedGame di WordSmith,
     * che verifica che il campo trys sia stato passato correttamente.
     */
    @Test
    public void testGetConfiguratedGameTrys() {
        wordSmith.setConfiguratedGame(game);
        assertEquals(game.getTrys(), wordSmith.getConfiguratedGame().getTrys());
    }

    /**
     * Metodo per il testing del metodo setConfiguratedGame di WordSmith,
     * che verifica che il campo secretWord sia stato settato correttamente.
     */
    @Test
    public void testSetConfiguratedGameSecretWord() {
        wordSmith.setConfiguratedGame(game);
        assertEquals(game.getSecretWord(), wordSmith.getConfiguratedGame().getSecretWord());
    }

    /**
     * Metodo per il testing del metodo setConfiguratedGame di WordSmith,
     * che verifica che il campo configurable sia stato settato correttamente.
     */
    @Test
    public void testSetConfiguratedGameConfigurable() {
        wordSmith.setConfiguratedGame(game);
        assertEquals(game.isConfigurable(), wordSmith.getConfiguratedGame().isConfigurable());
    }

    /**
     * Metodo per il testing del metodo setConfiguratedGame di WordSmith,
     * che verifica che il campo trys sia stato settato correttamente.
     */
    @Test
    public void testSetConfiguratedGameTrys() {
        wordSmith.setConfiguratedGame(game);
        assertEquals(game.getTrys(), wordSmith.getConfiguratedGame().getTrys());
    }

}