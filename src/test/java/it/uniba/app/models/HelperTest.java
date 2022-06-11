package it.uniba.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniba.app.utils.Helper;

/**
 * Classe dedicata ai test dei metodi della classe Helper.
 */
public class HelperTest {

    /**
     * Metodo per il testing del metodo arrayToArrayList di Helper
     * nel caso in cui l'array sia vuoto.
     */
    @Test
    public void testArrayToArrayListEmpty(){
        int[] array = {};
        List<Integer> arrayList = new ArrayList<>();
        Helper.arrayToArrayList(array, arrayList);
        assertEquals(arrayList, new ArrayList<>());
    }

    /**
     * Metodo per il testing del metodo arrayToArrayList di Helper
     * nel caso in cui l'array abbia dei valori.
     */
    @Test
    public void testArrayToArrayListFilled(){
        int[] array = {-2, -1, 0, 1, 2};
        List<Integer> arrayList = new ArrayList<>();
        Helper.arrayToArrayList(array, arrayList);
        for(int i = 0; i < arrayList.size(); i++){
            assertEquals(array[i], arrayList.get(i));
        }
    }
}
