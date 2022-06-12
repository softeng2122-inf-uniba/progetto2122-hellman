package it.uniba.app.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.utils.CommandType;

/**
 * Classe dedicata ai test dei metodi della classe Commands.
 */
public class CommandsTest {
    /** Attributo da utilizzare per effettuare i casi di test. */
    private Commands commands;
    /**
     * Serve per impostare il nome del comando durante la creazione
     *  dell'oggetto e durante il test del costruttore.
     */
    private String name;
    /**
     * Serve per impostare il tipo del comando durante la creazione
     *  dell'oggetto e durante il test del costruttore.
     */
    private CommandType type;

    /**
     * Metodo per testare il comportamento del costruttore e per inizializzare
     * i valori dell'oggetto commands da usare per i successivi casi di test.
     */
    @BeforeEach
    public void testConstructor() {
        name = "/help";
        type = CommandType.HELP;

        commands = new Commands(type, name);

        assertTrue(commands.getType().equals(type)
            && commands.getName().equals(name));
    }

    /**
     * Metodo per il testing del metodo setAlias di Commands.
     */
    @Test
    public void testSetAlias() {
        String[] alias = new String[] {"/h", "/aiuto"};
        commands.setAlias(alias);

        Set<String> aliasSet = new HashSet<String>(Arrays.asList(alias));

        assertEquals(aliasSet, commands.getAlias());
    }

    /**
     * Metodo per il testing del metodo getAlias di Commands.
     */
    @Test
    public void testGetAlias() {
        String[] alias = new String[] {"/h", "/aiuto"};

        commands.setAlias(alias);

        Set<String> aliasSet = new HashSet<String>(Arrays.asList(alias));

        assertEquals(commands.getAlias(), aliasSet);
    }

    /**
     * Metodo per il testing del metodo getType di Commands.
     */
    @Test
    public void testGetType() {
        assertEquals(commands.getType(), type);
    }

    /**
     * Metodo per il testing del metodo getName di Commands.
     */
    @Test
    public void testGetName() {
        assertEquals(commands.getName(), name);
    }
}
