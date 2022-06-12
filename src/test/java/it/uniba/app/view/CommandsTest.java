package it.uniba.app.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.utils.CommandType;

public class CommandsTest {
    private Commands commands;
    private String name;
    private CommandType type;

    @BeforeEach
    public void testConstructor() {
        name = "/help";
        type = CommandType.HELP;

        commands = new Commands(type, name);

        assertTrue(commands.getType().equals(type)
            && commands.getName().equals(name));
    }

    @Test
    public void testSetAlias() {
        String[] alias = new String[] {"/h", "/aiuto"};
        commands.setAlias(alias);

        Set<String> aliasSet = new HashSet<String>(Arrays.asList(alias));

        assertEquals(aliasSet, commands.getAlias());
    }

    @Test
    public void testGetAlias() {
        String[] alias = new String[] { "/h", "/aiuto" };
        
        commands.setAlias(alias);

        Set<String> aliasSet = new HashSet<String>(Arrays.asList(alias));

        assertEquals(commands.getAlias(), aliasSet);
    }

    @Test
    public void testGetType() {
        assertEquals(commands.getType(), type);
    }

    @Test
    public void testGetName() {
        assertEquals(commands.getName(), name);
    }
}
