package it.uniba.app.models;

import java.util.LinkedList;
import java.util.List;

/**
 * <<Entity>>
 * Classe descrive la parola inserita nei tentativi attraverso la parola stessa
 * descritta come stringa e la formattazione di questa in base alla presenza
 * o meno dei suoi caratteri nella parola segreta.
 */
public class Word {
    /** Parola inserita in un tentativo. */
    private String word;
    /**
     * Formattazione della parola in base alla presenza o meno
     * dei caratteri nella parola segreta.
     */
    private List<Integer> format;

    /**
     * Costruttore di classe.
     * Inizializza word e type attraverso i valori passati come parametro.
     *
     * @param newWord   Parola inserita durante un tentativo.
     * @param newFormat Formattazione della parola.
     */
    public Word(final String newWord, final List<Integer> newFormat) {
        this.word = newWord;
        this.format = new LinkedList<Integer>(newFormat);
    }

    /**
     * Restituisce la stringa della parola.
     *
     * @return Valore della parola.
     */
    public String getWord() {
        return word;
    }

    /**
     * Serve per accedere al format della parola.
     *
     * @return Format della parola.
     */
    public List<Integer> getFormat() {
        return new LinkedList<Integer>(format);
    }

}
