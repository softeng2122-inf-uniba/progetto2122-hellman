package it.uniba.app.view;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.utils.CommandType;
import it.uniba.app.utils.Pair;

public class ParserTest {
    private Parser parser;
    

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

    @Test
    public void testGetCommands() {
        List<Commands> commands = new ArrayList<Commands>();

        commands.add(new Commands(CommandType.HELP, "/help"));
        commands.add(new Commands(CommandType.START_GAME, "/gioca"));
        commands.add(new Commands(CommandType.NEW, "/nuova"));

        int i = 0;

        for(Commands command : commands) {
            assertTrue(command.getName().equals(
                parser.getCommands().get(i).getName())
                && command.getType().equals(
                    parser.getCommands().get(i).getType()));
            i++;
        }

    }

    @Test
    public void testParseCommandNotExistsMoreArgument() {
        ParserOutput parserOutput = parser.parse("/parola nuova");

        assertNull(parserOutput);
    }

    @Test
    public void testParseCommandOneArgument() {
        ParserOutput parserOutput = parser.parse("/gioca");

        ParserOutput parserTest = new ParserOutput(new Commands(CommandType.START_GAME, "/gioca"));

        assertTrue(parserOutput.getCommand().getName().equals(
            parserTest.getCommand().getName())
            && parserOutput.getCommand().getType().equals(
                        parserTest.getCommand().getType()));
    }

    @Test
    public void testParseCommandTwoArgument() {
        ParserOutput parserOutput = parser.parse("/nuova bread");

        ParserOutput parserTest = new ParserOutput(new Commands(CommandType.NEW, "bread"));

        assertTrue(parserOutput.getCommand().getName().equals(
                parserTest.getCommand().getName())
                && parserOutput.getCommand().getType().equals(
                        parserTest.getCommand().getType()));
    }

    @Test
    public void testParseCommandMoreThanTwoArguments() {
        ParserOutput parserOutput = parser.parse("/nuova bread nuova");

        assertNull(parserOutput);
    }

    @Test
    public void testParseTwoArgumentCommandWithLessThanTwoArguments() {
        ParserOutput parserOutput = parser.parse("/nuova");

        assertNull(parserOutput);
    }

    @Test
    public void testParseCommandNotExists() {
        ParserOutput parserOutput = parser.parse("/parola");

        ParserOutput parserTest = new ParserOutput(new Commands(CommandType.INPUT_WORD, "/parola"));

        assertTrue(parserOutput.getCommand().getName().equals(
                parserTest.getCommand().getName())
                && parserOutput.getCommand().getType().equals(
                        parserTest.getCommand().getType()));
    }


}
