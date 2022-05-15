package it.uniba.app.models;

import java.util.LinkedList;
import java.util.List;

import it.uniba.app.utils.Helper;

/**
 * <<Entity>>
 * ! TODO
 */
public class Game{
    private String secretWord;
    private int maxTry;
    private int numberLetter;
    private boolean configurable;
    private List<Word> trys;

    /**
     * Costruttore di classe.
     * Inizializza maxTry a 6 e numberLetter a 5.
     * secretWord viene inizializzata attraverso il parametro e trys viene instanziata come LinkedList.
     *
     * @param secretWord Parola segreta inizializzata alla creazione dell'oggetto.
     */
    public Game(String secretWord){
        this.maxTry = Helper.MAX_TRYS;
        this.numberLetter = Helper.MAX_LETTERS;
        this.secretWord = secretWord;
        this.configurable = true;
        this.trys = new LinkedList<Word>();
    }

    public Game() {
        this("");
    }

    /**
     * Serve per accedere al valore della parola segreta.
     *
     * @return Valore della parola segreta.
     */
    public String getSecretWord(){
        return this.secretWord;
    }

    /**
     * Serve per modificare il valore della parola segreta.
     *
     * @param secretWord Parola segreta con cui sostituire l'attuale.
     */
    public void setSecretWord(String secretWord){
        this.secretWord = secretWord;
    }

    /**
     * Serve per accedere al valore dei tentativi massimi effettuabili.
     *
     * @return Valore dei tentativi massimi effettuabili.
     */
    public int getMaxTry(){
        return this.maxTry;
    }

    /**
     * Serve per accedere al valore del numero di lettere che formano le parole.
     *
     * @return Valore del numero di lettere che formano le parole.
     */
    public int getNumberLetter(){
        return this.numberLetter;
    }

    /**
     * Serve per controllare che il gioco sia configurabile.
     *
     * @return Valore della variabile booleana che stabilisce se il gioco è configurabile.
     */
    public boolean isConfigurable(){
        return this.configurable;
    }

    /**
     * Serve a disabilitare la possibilità di configurare il gioco.
     */
    public void disableConfigurable(){
        this.configurable = false;
    }

    /**
     * Serve ad abilitare la possibilità di configurare il gioco.
     */
    public void enableConfigurable() {
        this.configurable = true;
    }

    /**
     * Serve per aggiungere un tentativo alla lista dei tentativi effettuati.
     *
     * @param word Parola inserita nell'ultimo tentativo effettuato.
     */
    public void addTry(Word word){
        this.trys.add(word);
    }

    /**
     * Serve per accedere alla lista dei tentativi effettuati.
     * 
     * @return Lista con tutti i tentativi effettuati.
     */
    public List<Word> getTrys(){
        return this.trys;
    }

    /**
     * Serve per accedere alla numero dei tentativi effettuati.
     *
     * @return Valore che stabilisce il numero dei tentativi effettuati.
     */
    public int getNumberTrys(){
        return this.trys.size();
    }

    public void resetGame(){
        this.maxTry = Helper.MAX_TRYS;
        this.numberLetter = Helper.MAX_LETTERS;
        this.secretWord = "";
        this.configurable = true;
        this.trys = new LinkedList<Word>();
    }
}
