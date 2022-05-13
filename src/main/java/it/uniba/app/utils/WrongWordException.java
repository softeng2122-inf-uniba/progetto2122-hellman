package it.uniba.app.utils;

/**
 * Classe eccezione per evitare che venga inserita una parola non idonea o che il game venga configurato
 * in situazioni poco opportune.
 */
public class WrongWordException extends Exception {

    /**
     * Costruttore di classe.
     * Richiama il costruttore della superclasse passandogli il messaggio di errore.
     *
     * @param message Messaggio di errore passato durante il sollevamento dell'eccezione.
     */
    public WrongWordException(String message) {
        super(message);
    }
}
