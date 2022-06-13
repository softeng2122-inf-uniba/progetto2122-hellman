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

/**
 * Classe dedicata ai casi di test della classe UserManager.
 */
public class UserManagerTest {
    /** Oggetto di tipo UserManager da utilizzare per i casi di test. */
    private UserManager userManager = new UserManager();

    /**
     * Metodo per il testing del metodo setSecretWord di UserManager.
     */
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

    /**
     * Metodo per il testing del metodo setSecretWord di UserManager
     * nel caso in cui la parola segreta dovesse essere troppo lunga.
     */
    @Test
    public void testSecretWordTooLong() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("tooLong");
                });

        assertEquals("La parola inserita contiene un numero"
                + " troppo elevato di caratteri.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo setSecretWord di UserManager
     * nel caso in cui la parola segreta dovesse essere troppo corta.
     */
    @Test
    public void testSecretWordTooShort() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("test");
                });

        assertEquals("La parola inserita contiene un numero insufficiente di "
                + "caratteri.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo setSecretWord di UserManager
     * nel caso in cui la parola segreta dovesse contenere
     * caratteri non ammessi.
     */
    @Test
    public void testSecretWordUnavailableCharacters() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("te$st");
                });

        assertEquals("La parola contiene caratteri non ammessi.",
                exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo getSecretWord di UserManager.
     */
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

    /**
     * Metodo per il testing del metodo startGame di UserManager
     * nel caso in cui il game corrente sia già stato avviato.
     */
    @Test
    public void testStartGameCurrentGameAlreadyStarted() {
        Throwable exception = assertThrows(
                GameException.class, () -> {

                    userManager.setSecretWord("glass");
                    userManager.startGame();

                    userManager.setSecretWord("glass");
                    userManager.startGame();
                });

        assertEquals("C'è già un game in corso.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo backGame di UserManager.
     */
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

    /**
     * Metodo per il testing del metodo backGame di UserManager
     * nel caso in cui il game corrente sia configurabile.
     */
    @Test
    public void testBackGameConfigurableGame() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("mouse");
                    userManager.backGame();
                });

        assertEquals("Non c'è un game in corso.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo startGame di UserManager
     * nel caso in cui il game corrente non abbia una parola segreta.
     */
    @Test
    public void testBackGameEmptySecretWord() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.backGame();
                });

        assertEquals("Non c'è un game in corso.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo makeTry di UserManager
     * nel caso in cui la parola del tentativo sia troppo lunga.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager
     * nel caso in cui la parola del tentativo sia troppo corta.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager
     * nel caso in cui la parola del tentativo contenga caratteri non ammessi.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager
     * nel caso in cui il game sia configurabile.
     */
    @Test
    public void testMakeTryGameNotStarted() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    userManager.setSecretWord("block");
                    userManager.makeTry("river");
                });

        assertEquals("Il game non è iniziato.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game venga vinto,
     * la costante restituita sia quelle che effettivamente ci si aspetta.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game venga vinto,
     * le parole vengano inserite correttamente nei vari tentativi.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game venga vinto,
     * il format delle parole inserite nei vari tentativi
     * sia impostato correttamente.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game venga vinto,
     * il numero di tentativi sia effettivamente compreso nell'intervallo
     * che ci si aspetta.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game sia ancora in corso,
     * la costante restituita sia quelle che effettivamente ci si aspetta.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game sia in corso,
     * le parole vengano inserite correttamente nei vari tentativi.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game sia in corso,
     * il format delle parole inserite nei vari tentativi
     * sia impostato correttamente.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game sia in corso,
     * il numero di tentativi sia effettivamente compreso nell'intervallo
     * che ci si aspetta.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game venga perso,
     * la costante restituita sia quelle che effettivamente ci si aspetta.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game venga perso,
     * le parole vengano inserite correttamente nei vari tentativi.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game venga perso,
     * il format delle parole inserite nei vari tentativi
     * sia impostato correttamente.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di UserManager.
     * Qui viene verificato che, nel caso in cui il game venga perso,
     * il numero di tentativi sia effettivamente compreso nell'intervallo
     * che ci si aspetta.
     */
    @Test
    public void testMakeTryGameLoseSizeTrys() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

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

    /**
     * Metodo per il testing del metodo isGameStarted di UserManager
     * nel caso in cui il gioco non sia partito.
     */
    @Test
    public void testIsGameStartedGameNotStarted() {
        try {
            userManager.setSecretWord("");
        } catch (GameException e) {
            e.printStackTrace();
        }
        assertFalse(userManager.isGameStarted());
    }

    /**
     * Metodo per il testing del metodo isGameStarted di UserManager
     * nel caso in cui il gioco sia partito.
     */
    @Test
    public void testIsGameStartedGameStarted() {
        try {
            userManager.setSecretWord("modem");
            userManager.startGame();
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertTrue(userManager.isGameStarted());
    }
}
