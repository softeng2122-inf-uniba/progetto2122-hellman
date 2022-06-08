package it.uniba.app.utils;

/**
 * {@literal <<noECB>>}
 * Classe eccezione per evitare che venga inserita una parola non idonea o che
 * il game venga configurato in situazioni poco opportune.
 */
public class GameException extends Exception {

    /**
     * Costruttore di classe.
     * Richiama il costruttore della superclasse passandogli
     * il messaggio di errore.
     *
     * @param message Messaggio di errore dell'eccezione.
     */
    public GameException(final String message) {
        super(message);
    }
}
