package it.uniba.app.utils;

/**
 * <<noECB>>
 * Classe parametrizzata che serve per gestire 2 oggetti I ed O.
 */
public class Pair<I, O> {
    // Primo oggetto del Pair
    private I first;
    // Secondo oggetto del Pair
    private O second;

    /** 
     * Costruttore della classe senza argomenti
    */
    public Pair() {}

    /**
     * Costruttore della classe con gli argomenti che sono degli oggetti
     * 
     * @param first è il primo oggetto
     * @param second è il secondo oggetto
     */
    public Pair(I first, O second){
        this.first = first;
        this.second = second;
    }

    /**
     * Metodo che restituisce il primo oggetto
     * 
     * @return primo oggetto
     */
    public I getFirst(){
        return first;
    }

    /**
     * Metodo che restituisce il secondo oggetto
     * 
     * @return secondo oggetto
     */
    public O getSecond(){
        return second;
    }

    /**
     * Metodo che imposta il primo oggetto
     * 
     * @param first primo oggetto che viene impostato
     */
    public void setFirst(I first){
        this.first = first;
    }

    /**
     * Metodo che imposta il secondo oggetto
     * 
     * @param second secondo oggetto che viene impostato
     */
    public void setSecond(O second){
        this.second = second;
    }

    /**
     * Metodo che concatena il primo e secondo oggetto in una stringa
     * 
     * @return first + second in una stringa.
     */
    public String toString(){
        return first + " " + second;
    }
}
