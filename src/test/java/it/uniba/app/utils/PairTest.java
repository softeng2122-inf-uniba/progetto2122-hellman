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
    Pair<String, Integer> pair = new Pair<String, Integer>();

    /**
     * Metodo per il testing del costruttore Pair
     * della classe Pair senza parametri.
     */
    @Test
    public void testPairEmpty() {
        assertTrue(true);
    }

    /**
     * Metodo per il testing del costruttore Pair
     * della classe Pair con parametri iniziali,
     * per verificare il settaggio del primo oggetto.
     */
    @Test
    public void testPairFirst() {
        Integer number = 100;
        String string = "200";
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
        Integer number = 100;
        String string = "200";
        pair = new Pair<String, Integer>(string, number);
        assertEquals(number, pair.getSecond());
    }

    /**
     * Metodo per il testing del metodo getFirst di Pair,
     * che verifica che il campo 1 sia stato passato correttamente.
     */
    @Test
    public void testGetFirst() {
        String string = "200";
        pair.setFirst(string);
        assertEquals(string, pair.getFirst());
    }
}
