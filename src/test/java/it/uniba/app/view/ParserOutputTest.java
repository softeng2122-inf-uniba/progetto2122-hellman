package it.uniba.app.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.utils.CommandType;

/**
 * Classe dedicata ai test dei metodi della classe ParserOutput.
 */
public class ParserOutputTest {
    /** Oggetto da utilizzare per effettuare i casi di test. */
    private ParserOutput parserOutput;

    /**
     * Metodo per testare il comportamento del costruttore e per inizializzare
     * i valori dell'oggetto parserOutput da usare per
     * i successivi casi di test.
     */
    @BeforeEach
    public void testConstructor() {
        String name = "/help";
        CommandType type = CommandType.HELP;

        Commands command = new Commands(type, name);

        parserOutput = new ParserOutput(command);

        assertEquals(command, parserOutput.getCommand());
    }

    /**
     * Metodo utilizzato per il testing del metodo getCommand di ParserOutput.
     */
    @Test
    public void testGetCommand() {
        String name = "/gioca";
        CommandType type = CommandType.START_GAME;

        Commands command = new Commands(type, name);

        parserOutput.setCommand(command);

        assertEquals(command, parserOutput.getCommand());
    }

    /**
     * Metodo utilizzato per il testing del metodo setCommand di ParserOutput.
     */
    @Test
    public void testSetCommand() {
        String name = "/nuova";
        CommandType type = CommandType.NEW;

        Commands command = new Commands(type, name);

        parserOutput.setCommand(command);

        assertEquals(parserOutput.getCommand(), command);
    }
}
