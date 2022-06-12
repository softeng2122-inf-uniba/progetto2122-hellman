package it.uniba.app.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.utils.CommandType;

public class ParserOutputTest {
    private ParserOutput parserOutput;

    @BeforeEach
    public void testConstructor() {
        String name = "/help";
        CommandType type = CommandType.HELP;

        Commands command = new Commands(type, name);

        parserOutput = new ParserOutput(command);

        assertEquals(command, parserOutput.getCommand());
    }

    @Test
    public void testGetCommand() {
        String name = "/gioca";
        CommandType type = CommandType.START_GAME;

        Commands command = new Commands(type, name);

        parserOutput.setCommand(command);

        assertEquals(command, parserOutput.getCommand());
    }

    @Test
    public void testSetCommand() {
        String name = "/nuova";
        CommandType type = CommandType.NEW;

        Commands command = new Commands(type, name);

        parserOutput.setCommand(command);

        assertEquals(parserOutput.getCommand(), command);
    }
}
