package it.uniba.app.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.uniba.app.utils.CommandType;

/**
 * <<noECB>>
 * Classe che gestisce i comandi accettati dal gioco.
 */
class Commands {

    /** Tipo del comando. */
    private final CommandType type;

    /** Nome del comando. */
    private final String name;

    /** Insieme degli alias associati al comando. */
    private Set<String> alias;

    /** Insieme dei comandi accettati dal gioco. */
    private static List<String> commands = new ArrayList<>();

    /**
     * Costruttore dei comandi.
     *
     * @param newType enum del comando
     * @param newName nome del comando
     */
    Commands(final CommandType newType, final String newName) {
        this.type = newType;
        this.name = newName;
    }

    /**
     * Restituisce tutti i comandi del gioco.
     *
     * @return commands
     */
    List<String> getCommands() {
        return commands;
    }

    /**
     * Restituisce il nome del comando.
     *
     * @return name del comando
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce l'insieme degli alias associati a quel comando.
     *
     * @return alias
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * Imposta gli alias per quel comando.
     *
     * @param newAlias da impostare
     */
    public void setAlias(final String[] newAlias) {
        this.alias = new HashSet<>(Arrays.asList(newAlias));
    }

    /**
     * Restituisce il tipo di quel comando.
     *
     * @return type
     */
    public CommandType getType() {
        return type;
    }

}
