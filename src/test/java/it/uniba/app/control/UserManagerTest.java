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

   
    @Test
    public void testMakeTryWordTooLong() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("block");
                    userManager.startGame();
                    userManager.makeTry("tooLong");
                });

        assertEquals("La parola inserita contiene un numero"
                + " troppo elevato di caratteri.", exception.getMessage());
    }

    @Test
    public void testMakeTryWordTooShort() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("block");
                    userManager.startGame();
                    userManager.makeTry("test");
                });

        assertEquals("La parola inserita contiene un numero insufficiente di "
                + "caratteri.", exception.getMessage());
    }

    @Test
    public void testMakeTryWordUnavailableCharacters() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("block");
                    userManager.startGame();
                    userManager.makeTry("te$st");
                });

        assertEquals("La parola contiene caratteri non ammessi.",
                exception.getMessage());
    }

    @Test
    public void testMakeTryGameNotStarted() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("block");
                    userManager.makeTry("river");
                });

        assertEquals("Il game non è iniziato.", exception.getMessage());
    }

    @Test
    public void testMakeTryGameWinInteger() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        try {
            userManager.setSecretWord("match");
            userManager.startGame();
            pair = userManager.makeTry("match");
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

        try {
            userManager.setSecretWord("match");
            userManager.startGame();
            pair = userManager.makeTry("match");
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

        try {
            userManager.setSecretWord("match");
            userManager.startGame();
            pair = userManager.makeTry("match");
        } catch (GameException e) {
            e.printStackTrace();
        }

        int i = 0;

        for (Word words : trys) {
            assertEquals(pair.getSecond().get(i).getFormat(),
                    words.getFormat());
            i++;
        }

    }

    @Test
    public void testMakeTryGameWinSizeTrys() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        try {
            userManager.setSecretWord("women");
            userManager.startGame();
            pair = userManager.makeTry("woman");
            pair = userManager.makeTry("woman");
            pair = userManager.makeTry("woman");
            pair = userManager.makeTry("woman");
            pair = userManager.makeTry("woman");
            pair = userManager.makeTry("women");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertTrue(pair.getSecond().size() > 0
                && pair.getSecond().size() <= Helper.MAX_TRYS);
    }

    @Test
    public void testMakeTryGameWaitingInteger() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        try {
            userManager.setSecretWord("women");
            userManager.startGame();
            pair = userManager.makeTry("woman");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(pair.getFirst(), Helper.GAME_WAITING);
    }

    @Test
    public void testMakeTryGameWaitingTryFirst() {
        List<Word> trys = new ArrayList<Word>();
        List<Integer> formats;
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        formats = Arrays.asList(Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_NOT_FOUND,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION);

        trys.add(new Word("woman", formats));

        try {
            userManager.setSecretWord("women");
            userManager.startGame();
            pair = userManager.makeTry("woman");
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
        List<Integer> formats;
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        formats = Arrays.asList(Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION,
                Helper.FORMAT_LETTER_NOT_FOUND,
                Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION);

        trys.add(new Word("woman", formats));

        try {
            userManager.setSecretWord("women");
            userManager.startGame();
            pair = userManager.makeTry("woman");
        } catch (GameException e) {
            e.printStackTrace();
        }

        int i = 0;

        for (Word words : trys) {
            assertEquals(pair.getSecond().get(i).getFormat(),
                    words.getFormat());
            i++;
        }
    }

    @Test
    public void testMakeTryGameWaitingSizeTrys() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        try {
            userManager.setSecretWord("women");
            userManager.startGame();
            pair = userManager.makeTry("woman");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertTrue(pair.getSecond().size() > 0
                && pair.getSecond().size() < Helper.MAX_TRYS);
    }

    @Test
    public void testMakeTryGameLoseInteger() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        try {
            userManager.setSecretWord("women");
            userManager.startGame();
            pair = userManager.makeTry("woman");
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

        for (int i = 0; i < Helper.MAX_TRYS; i++) {
            for (int j = 0; j < Helper.MAX_LETTERS; j++) {
                formats.add(Helper.FORMAT_LETTER_NOT_FOUND);
            }
            trys.add(new Word("pairs", formats));

            formats.clear();
        }

        try {
            userManager.setSecretWord("women");
            userManager.startGame();
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
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

        for (int i = 0; i < Helper.MAX_TRYS; i++) {
            for (int j = 0; j < Helper.MAX_LETTERS; j++) {
                formats.add(Helper.FORMAT_LETTER_NOT_FOUND);
            }
            trys.add(new Word("pairs", formats));

            formats.clear();
        }

        try {
            userManager.setSecretWord("women");
            userManager.startGame();
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
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
            assertEquals(pair.getSecond().get(i).getFormat(),
                    words.getFormat());
            i++;
        }
    }

    @Test
    public void testMakeTryGameLoseSizeTrys() {
        List<Integer> formats = new LinkedList<Integer>();
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        for (int i = 0; i < Helper.MAX_TRYS; i++) {
            for (int j = 0; j < Helper.MAX_LETTERS; j++) {
                formats.add(Helper.FORMAT_LETTER_NOT_FOUND);
            }

            formats.clear();
        }

        try {
            userManager.setSecretWord("women");
            userManager.startGame();
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
            pair = userManager.makeTry("pairs");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertTrue(pair.getSecond().size() == Helper.MAX_TRYS + 1);
    }
}
