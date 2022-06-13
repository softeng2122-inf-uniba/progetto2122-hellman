package it.uniba.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Classe dedicata ai test dei metodi della classe Game.
 */
public class WordTest {
    /** Oggetto di tipo Word creato per effettuare i test
     * sui vari metodi della classe. */
    private Word wordObject;

    /** Oggetto di tipo String creato per effettuare i test
     * sui vari metodi della classe Word. */
    private String word = "villa";

    /** Oggetto di tipo List<Integer> creato per effettuare i test
     * sui vari metodi della classe Word. */
    private List<Integer> format = Arrays.asList(1, 1, 1, 1, 1);

    /**
     * Metodo per il testing del costruttore di Word.
     */
    @Test
    public void testWordWord() {
        wordObject = new Word(word, format);
        assertEquals(word, wordObject.getWord());
    }

    /**
     * Metodo per il testing del costruttore di Word.
     */
    @Test
    public void testWordFormat() {
        wordObject = new Word(word, format);
        assertEquals(format, wordObject.getFormat());
    }

    /**
     * Metodo per il testing del metodo getWord di Word.
     */
    @Test
    public void testGetWord() {
        wordObject = new Word(word, format);
        assertEquals(word, wordObject.getWord());
    }

    /**
     * Metodo per il testing del metodo getFormat di Word.
     */
    @Test
    public void testGetFormat() {
        wordObject = new Word(word, format);
        assertEquals(format, wordObject.getFormat());
    }
}
