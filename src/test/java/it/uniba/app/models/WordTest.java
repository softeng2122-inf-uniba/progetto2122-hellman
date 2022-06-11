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
    private Word word_object;

    /** Oggetto di tipo String creato per effettuare i test
     * sui vari metodi della classe Word. */
    private String word = "villa";

    /** Oggetto di tipo List<Integer> creato per effettuare i test
     * sui vari metodi della classe Word. */
    private List<Integer> format = Arrays.asList(1,1,1,1,1);
    
    /**
     * Metodo per il testing del costruttore di Word.
     */
    @Test
    public void testWordWord() {
        word_object = new Word(word, format);
        assertEquals(word, word_object.getWord());
    }

    /**
     * Metodo per il testing del costruttore di Word.
     */
    @Test
    public void testWordFormat() {
        word_object = new Word(word, format);
        assertEquals(format, word_object.getFormat());
    }

    /**
     * Metodo per il testing del metodo getWord di Word.
     */
    @Test
    public void testGetWord() {
        word_object = new Word(word, format);
        assertEquals(word, word_object.getWord());
    }

    /**
     * Metodo per il testing del metodo getFormat di Word.
     */
    @Test
    public void testGetFormat() {
        word_object = new Word(word, format);
        assertEquals(format, word_object.getFormat());
    }
}
