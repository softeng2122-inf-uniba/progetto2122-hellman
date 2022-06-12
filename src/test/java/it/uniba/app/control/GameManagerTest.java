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

import it.uniba.app.models.Game;
import it.uniba.app.models.Word;
import it.uniba.app.utils.GameException;
import it.uniba.app.utils.Helper;
import it.uniba.app.utils.Pair;

/**
 * Classe dedicata ai casi di test della classe GameManager.
 */
public class GameManagerTest {
    /** Oggetto di tipo Game da utilizzare per i casi di test. */
    private Game game = new Game();

    /**
     * Metodo per il testing del metodo setSecretWord di GameManager.
     */
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

    /**
     * Metodo per il testing del metodo setSecretWord di GameManager
     * nel caso in cui la parola segreta dovesse essere troppo lunga.
     */
    @Test
    public void testSecretWordTooLong() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    GameManager.setSecretWord(game, "tooLong");
                });

        assertEquals("La parola inserita contiene un numero"
                + " troppo elevato di caratteri.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo setSecretWord di GameManager
     * nel caso in cui la parola segreta dovesse essere troppo corta.
     */
    @Test
    public void testSecretWordTooShort() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    GameManager.setSecretWord(game, "test");
                });

        assertEquals("La parola inserita contiene un numero insufficiente di "
                + "caratteri.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo setSecretWord di GameManager
     * nel caso in cui la parola segreta dovesse contenere
     * caratteri non ammessi.
     */
    @Test
    public void testSecretWordUnavailableCharacters() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    GameManager.setSecretWord(game, "te$st");
                });

        assertEquals("La parola contiene caratteri non ammessi.",
                exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo setSecretWord di GameManager
     * nel caso in cui il metodo non dovesse essere configurabile.
     */
    @Test
    public void testSecretWordUnconfigurableGame() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.disableConfigurable();
                    GameManager.setSecretWord(game, "river");
                });

        assertEquals("Il game non è configurabile.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo getSecretWord di GameManager.
     */
    @Test
    public void testGetSecretWord() {
        String secretWord = "hands";

        game.setSecretWord(secretWord);

        assertEquals(GameManager.getSecretWord(game), secretWord);
    }

    /**
     * Metodo per il testing dell'assegnazione della secret word
     * nel metodo startGame di GameManager.
     */
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

    /**
     * Metodo per il testing della verifica della non configurabilità del
     * game corrente nel metodo startGame di GameManager.
     */
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

    /**
     * Metodo per il testing del metodo startGame di GameManager
     * nel caso in cui il game configurato sia null.
     */
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

    /**
     * Metodo per il testing del metodo startGame di GameManager
     * nel caso in cui il game configurato non abbia la parola segreta
     * impostata.
     */
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

    /**
     * Metodo per il testing del metodo startGame di GameManager
     * nel caso in cui il game corrente sia già stato configurato.
     */
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

    /**
     * Metodo per il testing del metodo backGame di GameManager.
     */
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

    /**
     * Metodo per il testing del metodo backGame di GameManager
     * nel caso in cui il game corrente sia configurabile.
     */
    @Test
    public void testBackGameConfigurableGame() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.setSecretWord("mouse");
                    GameManager.backGame(game);
                });

        assertEquals("Non c'è un game in corso.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo startGame di GameManager
     * nel caso in cui il game corrente non abbia una parola segreta.
     */
    @Test
    public void testBackGameEmptySecretWord() {

        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.disableConfigurable();
                    GameManager.backGame(game);
                });

        assertEquals("Non c'è un game in corso.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo makeTry di GameManager
     * nel caso in cui la parola del tentativo sia troppo lunga.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di GameManager
     * nel caso in cui la parola del tentativo sia troppo corta.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di GameManager
     * nel caso in cui la parola del tentativo contenga caratteri non ammessi.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di GameManager
     * nel caso in cui il game sia configurabile.
     */
    @Test
    public void testMakeTryGameNotStarted() {
        Throwable exception = assertThrows(
                GameException.class, () -> {
                    game.enableConfigurable();
                    GameManager.makeTry(game, "river");
                });

        assertEquals("Il game non è iniziato.", exception.getMessage());
    }

    /**
     * Metodo per il testing del metodo makeTry di GameManager.
     * Qui viene verificato che, nel caso in cui il game venga vinto,
     * la costante restituita sia quelle che effettivamente ci si aspetta.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di GameManager.
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

    /**
     * Metodo per il testing del metodo makeTry di GameManager.
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

        game.setSecretWord("match");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "match");
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
     * Metodo per il testing del metodo makeTry di GameManager.
     * Qui viene verificato che, nel caso in cui il game venga vinto,
     * il numero di tentativi sia effettivamente compreso nell'intervallo
     * che ci si aspetta.
     */
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

        assertTrue(pair.getSecond().size() > 0
            && pair.getSecond().size() <= Helper.MAX_TRYS);
    }

    /**
     * Metodo per il testing del metodo makeTry di GameManager.
     * Qui viene verificato che, nel caso in cui il game sia ancora in corso,
     * la costante restituita sia quelle che effettivamente ci si aspetta.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di GameManager.
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

    /**
     * Metodo per il testing del metodo makeTry di GameManager.
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

        game.setSecretWord("women");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "woman");
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
     * Metodo per il testing del metodo makeTry di GameManager.
     * Qui viene verificato che, nel caso in cui il game sia in corso,
     * il numero di tentativi sia effettivamente compreso nell'intervallo
     * che ci si aspetta.
     */
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

        assertTrue(pair.getSecond().size() > 0
            && pair.getSecond().size() < Helper.MAX_TRYS);
    }

    /**
     * Metodo per il testing del metodo makeTry di GameManager.
     * Qui viene verificato che, nel caso in cui il game venga perso,
     * la costante restituita sia quelle che effettivamente ci si aspetta.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di GameManager.
     * Qui viene verificato che, nel caso in cui il game venga perso,
     * le parole vengano inserite correttamente nei vari tentativi.
     */
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

    /**
     * Metodo per il testing del metodo makeTry di GameManager.
     * Qui viene verificato che, nel caso in cui il game venga perso,
     * il format delle parole inserite nei vari tentativi
     * sia impostato correttamente.
     */
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
            assertEquals(pair.getSecond().get(i).getFormat(),
                words.getFormat());
            i++;
        }
    }

    /**
     * Metodo per il testing del metodo makeTry di GameManager.
     * Qui viene verificato che, nel caso in cui il game venga perso,
     * il numero di tentativi sia effettivamente compreso nell'intervallo
     * che ci si aspetta.
     */
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

    /**
     * Metodo per il testing della giusta colorazione delle lettere
     * dopo aver effettuato un tentativo con il metodo makeTry
     * di GameManager. In metodo viene verificato il caso in cui
     * sia la lettera sia presente ma si trovi nel posto sbagliato.
     */
    @Test
    public void testMakeTryLetterFoundWrongPosition() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        game.setSecretWord("women");

        game.disableConfigurable();

        try {
            pair = GameManager.makeTry(game, "clock");
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(pair.getSecond().get(0).getFormat().get(2),
            Helper.FORMAT_LETTER_FOUND_WRONG_POSITION);
    }

    /**
     * Metodo per il testing della giusta colorazione delle lettere
     * dopo aver effettuato un tentativo con il metodo makeTry
     * di GameManager. In metodo viene verificato il caso in cui
     * sia presente più volte la stessa lettera nelle parole.
     */
    @Test
    public void testMakeTryRepeatedLetters() {
        Pair<Integer, List<Word>> pair = new Pair<Integer, List<Word>>();

        game.setSecretWord("allen");

        game.disableConfigurable();

        String trying = "lolal";

        try {
            pair = GameManager.makeTry(game, trying);
        } catch (GameException e) {
            e.printStackTrace();
        }

        assertEquals(pair.getSecond().get(0).getFormat().get(trying.length() - 1),
                Helper.FORMAT_LETTER_NOT_FOUND);
    }

    /**
     * Metodo per il testing del metodo isGameStarted di GameManager
     * nel caso in cui il gioco non sia partito.
     */
    @Test
    public void testIsGameStartedGameNotStarted() {
        assertFalse(GameManager.isGameStarted(game));
    }

    /**
     * Metodo per il testing del metodo isGameStarted di GameManager
     * nel caso in cui il gioco sia partito.
     */
    @Test
    public void testIsGameStartedGameStarted() {
        game.setSecretWord("modem");

        assertTrue(GameManager.isGameStarted(game));
    }

}
