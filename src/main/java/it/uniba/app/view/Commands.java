package it.uniba.app.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import it.uniba.app.utils.CommandType;

/**
 * {@literal <<noECB>>}
 * Classe che gestisce i comandi accettati dal gioco.
 */
class Commands {

    /** Tipo del comando. */
    private final CommandType type;

    /** Nome del comando. */
    private final String name;

    /** Insieme degli alias associati al comando. */
    private Set<String> alias;

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
     * Restituisce il nome del comando.
     *
     * @return name del comando
     */
    String getName() {
        return name;
    }

    /**
     * Restituisce l'insieme degli alias associati a quel comando.
     *
     * @return alias
     */
    Set<String> getAlias() {
        return alias;
    }

    /**
     * Imposta gli alias per quel comando.
     *
     * @param newAlias da impostare
     */
    void setAlias(final String[] newAlias) {
        this.alias = new HashSet<>(Arrays.asList(newAlias));
    }

    /**
     * Restituisce il tipo di quel comando.
     *
     * @return type
     */
    CommandType getType() {
        return type;
    }

}
