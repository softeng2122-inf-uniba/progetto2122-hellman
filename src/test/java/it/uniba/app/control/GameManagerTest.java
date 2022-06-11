package it.uniba.app.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniba.app.models.Game;
import it.uniba.app.models.Word;
import it.uniba.app.utils.GameException;
import it.uniba.app.utils.Helper;
import it.uniba.app.utils.Pair;

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

    @Test
    public void testMakeTryWordTooLong() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.disableConfigurable();
                    GameManager.makeTry(game, "tooLong");
                });

        assertEquals("La parola inserita contiene un numero"
                + " troppo elevato di caratteri.", exception.getMessage());
    }

    @Test
    public void testMakeTryWordTooShort() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.disableConfigurable();
                    GameManager.makeTry(game, "test");
                });

        assertEquals("La parola inserita contiene un numero insufficiente di "
                + "caratteri.", exception.getMessage());
    }

    @Test
    public void testMakeTryWordUnavailableCharacters() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.disableConfigurable();
                    GameManager.makeTry(game, "te$st");
                });

        assertEquals("La parola contiene caratteri non ammessi.",
                exception.getMessage());
    }

    @Test
    public void testMakeTryGameNotStarted() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.enableConfigurable();
                    GameManager.makeTry(game, "river");
                });

        assertEquals("Il game non è iniziato.", exception.getMessage());
    }

    @Test
    public void testMakeTryGameWinInteger() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        game.setSecretWord("match");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "match");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(pair.getFirst(), Helper.GAME_WIN);
    }

    @Test
    public void testMakeTryGameWinTryFirst() {
        List<Word> trys = new ArrayList<Word>();
        List<Integer> formats = new LinkedList<Integer>();
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        for (int i = 0; i < Helper.MAX_LETTERS; i++) {
            formats.add(Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION);
        }

        trys.add(new Word("match", formats));

        game.setSecretWord("match");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "match");
        } catch (GameException e) {
            e.printStackTrace();
        }

        int i = 0;

        for (Word words : trys) {
            assertEquals(pair.getSecond().get(i).getWord(), words.getWord());
            i++;
        }

    }

    @Test
    public void testMakeTryGameWinTrySecond() {
        List<Word> trys = new ArrayList<Word>();
        List<Integer> formats = new LinkedList<Integer>();
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        for (int i = 0; i < Helper.MAX_LETTERS; i++) {
            formats.add(Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION);
        }

        trys.add(new Word("match", formats));

        game.setSecretWord("match");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "match");
        } catch (GameException e) {
            e.printStackTrace();
        }

        int i = 0;

        for (Word words : trys) {
            assertEquals(pair.getSecond().get(i).getFormat(), words.getFormat());
            i++;
        }

    }

    @Test
    public void testMakeTryGameWinSizeTrys() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        game.setSecretWord("women");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "woman");
            pair = GameManager.makeTry(game, "woman");
            pair = GameManager.makeTry(game, "woman");
            pair = GameManager.makeTry(game, "woman");
            pair = GameManager.makeTry(game, "woman");
            pair = GameManager.makeTry(game, "women");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertTrue(pair.getSecond().size() > 0 && pair.getSecond().size() <= Helper.MAX_TRYS);
    }

    @Test
    public void testMakeTryGameWaitingInteger() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        game.setSecretWord("women");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "woman");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(pair.getFirst(), Helper.GAME_WAITING);
    }

    @Test
    public void testMakeTryGameWaitingTryFirst() {
        List<Word> trys = new ArrayList<Word>();
        List<Integer> formats = new LinkedList<Integer>();
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        formats = Arrays.asList(Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_NOT_FOUND,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION);

        trys.add(new Word("woman", formats));

        game.setSecretWord("women");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "woman");
        } catch (GameException e) {
            e.printStackTrace();
        }

        int i = 0;

        for (Word words : trys) {
            assertEquals(pair.getSecond().get(i).getWord(), words.getWord());
            i++;
        }
    }

    @Test
    public void testMakeTryGameWaitingTrySecond() {
        List<Word> trys = new ArrayList<Word>();
        List<Integer> formats = new LinkedList<Integer>();
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        formats = Arrays.asList(Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_NOT_FOUND,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION);

        trys.add(new Word("woman", formats));

        game.setSecretWord("women");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "woman");
        } catch (GameException e) {
            e.printStackTrace();
        }

        int i = 0;

        for (Word words : trys) {
            assertEquals(pair.getSecond().get(i).getFormat(), words.getFormat());
            i++;
        }
    }

    @Test
    public void testMakeTryGameWaitingSizeTrys() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        game.setSecretWord("women");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "woman");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertTrue(pair.getSecond().size() > 0 && pair.getSecond().size() < Helper.MAX_TRYS);
    }

    @Test
    public void testMakeTryGameLoseInteger() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        game.setSecretWord("women");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "woman");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(pair.getFirst(), Helper.GAME_WAITING);
    }

    @Test
    public void testMakeTryGameLoseTryFirst() {
        List<Word> trys = new ArrayList<Word>();
        List<Integer> formats = new LinkedList<Integer>();
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        game.setSecretWord("women");

        for (int i = 0; i < Helper.MAX_TRYS; i++) {
            for (int j = 0; j < Helper.MAX_LETTERS; j++) {
                formats.add(Helper.FORMAT_LETTER_NOT_FOUND);
            }
            trys.add(new Word("pairs", formats));

            if (i != Helper.MAX_TRYS - 1) {
                game.addTry(new Word("pairs", formats));
            }

            formats.clear();
        }

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "pairs");
        } catch (GameException e) {
            e.printStackTrace();
        }

        trys.add(new Word("women",
                Arrays.asList(Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                        Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                        Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                        Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                        Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION)));

        int i = 0;

        for (Word words : trys) {
            assertEquals(pair.getSecond().get(i).getWord(), words.getWord());
            i++;
        }
    }

    @Test
    public void testMakeTryGameLoseTrySecond() {
        List<Word> trys = new ArrayList<Word>();
        List<Integer> formats = new LinkedList<Integer>();
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        game.setSecretWord("women");

        for (int i = 0; i < Helper.MAX_TRYS; i++) {
            for (int j = 0; j < Helper.MAX_LETTERS; j++) {
                formats.add(Helper.FORMAT_LETTER_NOT_FOUND);
            }
            trys.add(new Word("pairs", formats));

            if (i != Helper.MAX_TRYS - 1) {
                game.addTry(new Word("pairs", formats));
            }

            formats.clear();
        }

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "pairs");
        } catch (GameException e) {
            e.printStackTrace();
        }

        trys.add(new Word("women",
                Arrays.asList(Helper.FORMAT_LETTER_NOT_FOUND,
                        Helper.FORMAT_LETTER_NOT_FOUND,
                        Helper.FORMAT_LETTER_NOT_FOUND,
                        Helper.FORMAT_LETTER_NOT_FOUND,
                        Helper.FORMAT_LETTER_NOT_FOUND)));

        int i = 0;

        for (Word words : trys) {
            assertEquals(pair.getSecond().get(i).getFormat(), words.getFormat());
            i++;
        }
    }

    @Test
    public void testMakeTryGameLoseSizeTrys() {
        List<Integer> formats = new LinkedList<Integer>();
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        game.setSecretWord("women");

        for (int i = 0; i < Helper.MAX_TRYS; i++) {
            for (int j = 0; j < Helper.MAX_LETTERS; j++) {
                formats.add(Helper.FORMAT_LETTER_NOT_FOUND);
            }

            if (i != Helper.MAX_TRYS - 1) {
                game.addTry(new Word("pairs", formats));
            }

            formats.clear();
        }

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "pairs");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertTrue(pair.getSecond().size() == Helper.MAX_TRYS + 1);
    }

}
