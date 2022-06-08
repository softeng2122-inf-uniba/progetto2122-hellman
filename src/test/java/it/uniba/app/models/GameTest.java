package it.uniba.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import it.uniba.app.utils.Helper;

/**
 * Classe dedicata ai test dei metodi della classe Game.
 */
public class GameTest {
    /** Oggetto di tipo Game creato per effettuare i test
     * sui vari metodi della classe. */
    private Game game = new Game();

    /**
     * Metodo per il testing del metodo testSetSecretWord() di Game.
     */
    @Test
    public void testSetSecretWord() {
        String secretWord = "clock";

        game.setSecretWord(secretWord);

        assertEquals(secretWord, game.getSecretWord());
    }

    /**
     * Metodo per il testing del metodo getMaxTry() di Game.
     */
    @Test
    public void testGetMaxTry() {
        assertEquals(game.getMaxTry(), Helper.MAX_TRYS);
    }

    /**
     * Metodo per il testing del metodo getNumberLetter() di Game.
     */
    @Test
    public void testGetNumberLetter() {
        assertEquals(game.getNumberLetter(), Helper.MAX_LETTERS);
    }

    /**
     * Metodo per il testing del metodo isConfigurable() di Game.
     */
    @Test
    public void testIsConfigurable() {
        game.disableConfigurable();
        assertTrue(!game.isConfigurable());
        game.enableConfigurable();
        assertTrue(game.isConfigurable());
    }

    /**
     * Metodo per il testing del metodo enableConfigurable() di Game.
     */
    @Test
    public void testEnableConfigurable() {
        game.enableConfigurable();
        assertTrue(game.isConfigurable());
    }

    /**
     * Metodo per il testing del metodo disableConfigurable() di Game.
     */
    @Test
    public void testDisableConfigurable() {
        game.disableConfigurable();
        assertTrue(!game.isConfigurable());
    }

    /**
     * Metodo per il testing del metodo getAddTry() di Game.
     */
    @Test
    public void testAddTry() {
        Word word = new Word("words", new LinkedList<Integer>());
        game.addTry(word);

        assertTrue(game.getTrys().contains(word));
    }

    /**
     * Metodo per il testing del metodo getTrys() di Game.
     */
    @Test
    public void testGetTrys() {
        Word word = new Word("words", new LinkedList<Integer>());
        LinkedList<Word> list = new LinkedList<Word>();
        list.add(word);
        game.addTry(word);

        assertEquals(game.getTrys(), list);
    }

    /**
     * Metodo per il testing del metodo getNumberTrys() di Game.
     */
    @Test
    public void testGetNumberTrys() {
        int i;
        int maxTrys = game.getMaxTry();

        for (i = 0; i < maxTrys; i++) {
            Word word = new Word("month", new LinkedList<Integer>());
            game.addTry(word);
        }

        assertEquals(game.getNumberTrys(), i);
    }

    /**
     * Metodo per il testing del metodo resetGame() di Game.
     */
    @Test
    public void testResetGame() {

        game.setSecretWord("tests");
        game.disableConfigurable();
        game.addTry(new Word("words", new LinkedList<Integer>()));

        game.resetGame();

        assertEquals(game.getSecretWord(), new Game().getSecretWord());
        assertEquals(game.isConfigurable(), new Game().isConfigurable());
        assertEquals(game.getTrys(), new Game().getTrys());
    }
}
