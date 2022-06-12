package it.uniba.app.view;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.utils.CommandType;
import it.uniba.app.utils.Pair;

/**
 * Classe dedicata ai test dei metodi della classe Parser.
 */
public class ParserTest {
    /** Oggetto di tipo Parser da utilizzare per effettuare i casi di test. */
    private Parser parser;

    /**
     * Metodo per il set up dell'attributo parser da usare per i casi di test.
     */
    @BeforeEach
    public void setUp() {
        List<Pair<String, CommandType>> commands
            = new ArrayList<Pair<String, CommandType>>();
        commands.add(new Pair<String, CommandType>(
                "/help", CommandType.HELP));
        commands.add(new Pair<String, CommandType>(
                "/gioca", CommandType.START_GAME));
        commands.add(new Pair<String, CommandType>(
                "/nuova", CommandType.NEW));

        parser = new Parser(commands);
    }

    /**
     * Metodo per il testing del metodo getCommands di Commands.
     */
    @Test
    public void testGetCommands() {
        List<Commands> commands = new ArrayList<Commands>();

        commands.add(new Commands(CommandType.HELP, "/help"));
        commands.add(new Commands(CommandType.START_GAME, "/gioca"));
        commands.add(new Commands(CommandType.NEW, "/nuova"));

        int i = 0;

        for (Commands command : commands) {
            assertTrue(command.getName().equals(
                    parser.getCommands().get(i).getName())
                    && command.getType().equals(
                            parser.getCommands().get(i).getType()));
            i++;
        }

    }

    /**
     * Metodo per il testing del metodo parse di Parser
     * nel caso in cui il token delle parole utilizzabili del comando digitato
     * sia vuoto.
     */
    @Test
    public void testParseTokenEmpty() {
        Set<String> ignoreWords = new HashSet<String>();
        ignoreWords.add("/ciao");
        parser.setIgnoreWords(ignoreWords);

        ParserOutput parserOutput = parser.parse("/ciao");

        assertNull(parserOutput);
    }

    /**
     * Metodo per il testing del metodo parse di Parser
     * nel caso in cui il comando non esista e ci siano argomenti.
     */
    @Test
    public void testParseCommandNotExistsWithArguments() {
        ParserOutput parserOutput = parser.parse("/parola nuova");

        assertNull(parserOutput);
    }

    /**
     * Metodo per il testing del metodo parse di Parser
     * nel caso in cui il comando esista e non ci siano argomenti.
     */
    @Test
    public void testParseCommandNoArguments() {
        ParserOutput parserOutput = parser.parse("/gioca");

        ParserOutput parserTest = new ParserOutput(new Commands(
            CommandType.START_GAME, "/gioca"));

        assertTrue(parserOutput.getCommand().getName().equals(
                parserTest.getCommand().getName())
                && parserOutput.getCommand().getType().equals(
                        parserTest.getCommand().getType()));
    }

    /**
     * Metodo per il testing del metodo parse di Parser
     * nel caso in cui il comando esista e ci sia un argomento.
     */
    @Test
    public void testParseCommandWithArgument() {
        ParserOutput parserOutput = parser.parse("/nuova bread");

        ParserOutput parserTest = new ParserOutput(
            new Commands(CommandType.NEW, "bread"));

        assertTrue(parserOutput.getCommand().getName().equals(
                parserTest.getCommand().getName())
                && parserOutput.getCommand().getType().equals(
                        parserTest.getCommand().getType()));
    }

    /**
     * Metodo per il testing del metodo parse di Parser
     * nel caso in cui il comando esista e ci sia più di un argomento.
     */
    @Test
    public void testParseCommandMoreThanOneArgument() {
        ParserOutput parserOutput = parser.parse("/nuova bread nuova");

        assertNull(parserOutput);
    }

    /**
     * Metodo per il testing del metodo parse di Parser
     * nel caso in cui il comando esista e necessiti di un argomento
     * ma che questo non ci sia.
     */
    @Test
    public void testParseOneArgumentCommandWithoutArguments() {
        ParserOutput parserOutput = parser.parse("/nuova");

        assertNull(parserOutput);
    }

    /**
     * Metodo per il testing del metodo parse di Parser
     * nel caso in cui il comando non esista.
     */
    @Test
    public void testParseCommandNotExists() {
        ParserOutput parserOutput = parser.parse("/parola");

        ParserOutput parserTest = new ParserOutput(new Commands(
            CommandType.INPUT_WORD, "/parola"));

        assertTrue(parserOutput.getCommand().getName().equals(
                parserTest.getCommand().getName())
                && parserOutput.getCommand().getType().equals(
                        parserTest.getCommand().getType()));
    }

}
