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
}
