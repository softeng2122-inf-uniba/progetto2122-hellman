package it.uniba.app.utils;

/**
 * <<noECB>>
 * Classe parametrizzata che serve per gestire 2 oggetti I ed O.
 *
 * @param <I> primo oggetto del Pair.
 * @param <O> Secondo oggetto del Pair.
 */
public class Pair<I, O> {
    /** Primo oggetto del Pair. */
    private I first;
    /** Secondo oggetto del Pair. */
    private O second;

    /**
     * Costruttore della classe senza argomenti.
     */
    public Pair() {
    }

    /**
     * Costruttore della classe con gli argomenti che sono degli oggetti.
     *
     * @param newFirst  è il primo oggetto
     * @param newSecond è il secondo oggetto
     */
    public Pair(final I newFirst, final O newSecond) {
        this.first = newFirst;
        this.second = newSecond;
    }

    /**
     * Metodo che restituisce il primo oggetto.
     *
     * @return primo oggetto
     */
    public I getFirst() {
        return first;
    }

    /**
     * Metodo che restituisce il secondo oggetto.
     *
     * @return secondo oggetto
     */
    public O getSecond() {
        return second;
    }

    /**
     * Metodo che imposta il primo oggetto.
     *
     * @param newFirst primo oggetto che viene impostato
     */
    public void setFirst(final I newFirst) {
        this.first = newFirst;
    }

    /**
     * Metodo che imposta il secondo oggetto.
     *
     * @param newSecond secondo oggetto che viene impostato
     */
    public void setSecond(final O newSecond) {
        this.second = newSecond;
    }

    /**
     * Metodo che concatena il primo e secondo oggetto in una stringa.
     *
     * @return first + second in una stringa.
     */
    public String toString() {
        return first + " " + second;
    }
}
