package it.uniba.app.view;

import it.uniba.app.utils.CommandType;
import java.util.*;

/**
 * <<noECB>>
 * Classe che gestisce i comandi accettati dal gioco
 */
class Commands {

    // Tipo del comando
    private final CommandType type;

    // Nome del comando
    private final String name;

    // Insieme degli alias associati al comando
    private Set<String> alias;

    // Insieme dei comandi accettati dal gioco
    private static List<String> commands = new ArrayList<>();

    /**
     * Costruttore dei comandi
     * @param type enum del comando
     * @param name nome del comando
     */
    public Commands(CommandType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * Restituisce tutti i comandi del gioco
     * 
     * @return commands
     */
    public List<String> getCommands() {
        return commands;
    }

    /**
     * Restituisce il nome del comando
     * 
     * @return name del comando
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce l'insieme degli alias associati a quel comando
     * 
     * @return alias
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * Imposta gli alias per quel comando
     * 
     * @param alias da impostare
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * Restituisce il tipo di quel comando
     * 
     * @return type
     */
    public CommandType getType() {
        return type;
    }
    
}
