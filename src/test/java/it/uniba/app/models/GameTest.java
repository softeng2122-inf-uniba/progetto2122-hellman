package it.uniba.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import it.uniba.app.utils.Helper;

public class GameTest {
    Game game = new Game();

    @Test
    public void testSetSecretWord() {
        String secretWord = "clock";

        game.setSecretWord(secretWord);

        assertEquals(secretWord, game.getSecretWord());
    }

    @Test
    public void testGetMaxTry() {
        assertTrue(game.getMaxTry() == Helper.MAX_TRYS);
    }

    @Test
    public void testGetNumberLetter() {
        assertTrue(game.getNumberLetter() == Helper.MAX_LETTERS);
    }

    @Test
    public void testIsConfigurable() {
        game.disableConfigurable();
        assertTrue(!game.isConfigurable());
        game.enableConfigurable();
        assertTrue(game.isConfigurable());
    }

    @Test
    public void testEnableConfigurable() {
        game.enableConfigurable();
        assertTrue(game.isConfigurable());
    }

    @Test
    public void testDisableConfigurable() {
        game.disableConfigurable();
        assertTrue(!game.isConfigurable());
    }

    @Test
    public void testAddTry() {
        Word word = new Word("words", new LinkedList<Integer>());
        game.addTry(word);

        assertTrue(game.getTrys().contains(word));
    }

    @Test
    public void testGetTrys() {
        Word word = new Word("words", new LinkedList<Integer>());
        LinkedList<Word> list = new LinkedList<Word>();
        list.add(word);
        game.addTry(word);

        assertEquals(game.getTrys(), list);
    }

    @Test
    public void testGetNumberTrys() {
        int i;

        for (i = 0; i < 5; i++) {
            Word word = new Word("month", new LinkedList<Integer>());
            game.addTry(word);
        }

        assertEquals(game.getNumberTrys(), i);
    }

    @Test
    public void testResetGame() {
        Game game = new Game();

        game.setSecretWord("tests");
        game.disableConfigurable();
        game.addTry(new Word("words", new LinkedList<Integer>()));

        game.resetGame();

        assertEquals(game.getSecretWord(), new Game().getSecretWord());
        assertEquals(game.isConfigurable(), new Game().isConfigurable());
        assertEquals(game.getTrys(), new Game().getTrys());
    }
}
