package it.uniba.app.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Classe dedicata ai test dei metodi della classe Pair.
 */
public class PairTest {
    /** Oggetto di tipo Pair creato per effettuare i test
     * sui vari metodi della classe. */
    private Pair<String, Integer> pair;
    /** Oggetto utilizzato come primo parametro dei Pair nei casi di test. */
    private Integer number = 1;
    /** Oggetto utilizzato come secondo parametro dei Pair nei casi di test. */
    private String string = "33";

    /**
     * Metodo per il testing del costruttore Pair
     * della classe Pair senza parametri.
     */
    @Test
    public void testPairEmpty() {
        pair = new Pair<String, Integer>();

        assertTrue(pair.getFirst() == null
            && pair.getSecond() == null);
    }

    /**
     * Metodo per il testing del costruttore Pair
     * della classe Pair con parametri iniziali,
     * per verificare il settaggio del primo oggetto.
     */
    @Test
    public void testPairFirst() {
        pair = new Pair<String, Integer>(string, number);
        assertEquals(string, pair.getFirst());
    }

    /**
     * Metodo per il testing del costruttore Pair
     * della classe Pair con parametri iniziali,
     * per verificare il settaggio del secondo oggetto.
     */
    @Test
    public void testPairSecond() {
        pair = new Pair<String, Integer>(string, number);
        assertEquals(number, pair.getSecond());
    }

    /**
     * Metodo per il testing del metodo getFirst di Pair,
     * che verifica che il campo 1 sia stato passato correttamente.
     */
    @Test
    public void testGetFirst() {
        pair = new Pair<String, Integer>();
        pair.setFirst(string);
        assertEquals(string, pair.getFirst());
    }

    /**
     * Metodo per il testing del metodo getSecond di Pair,
     * che verifica che il campo 2 sia stato passato correttamente.
     */
    @Test
    public void testGetSecond() {
        pair = new Pair<String, Integer>();
        pair.setSecond(number);
        assertEquals(number, pair.getSecond());
    }

    /**
     * Metodo per il testing del metodo setFirst di Pair,
     * che verifica che il campo 1 sia stato settato correttamente.
     */
    @Test
    public void testSetFirst() {
        pair = new Pair<String, Integer>();
        pair.setFirst(string);
        assertEquals(string, pair.getFirst());
    }

    /**
     * Metodo per il testing del metodo setSecond di Pair,
     * che verifica che il campo 2 sia stato settato correttamente.
     */
    @Test
    public void testSetSecond() {
        pair = new Pair<String, Integer>();
        pair.setSecond(number);
        assertEquals(number, pair.getSecond());
    }
}
