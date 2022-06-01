package it.uniba.app.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * <<noECB>>
 * Classe di supporto che ha tutta una serie di costanti che servono per mostrare se le lettere 
 * della parola inserita dall'utente sono in posizione corretta oppure errata, per l'esito 
 * del gioco cioè se l'utente ha perso o vinto, per il numero massimo di lettere e di tentativi
 * e per i colori dello sfondo delle lettere.
 */
public class Helper {
    final public static int FORMAT_LETTER_NEVER_TYPED = 0;
    final public static int FORMAT_LETTER_NOT_FOUND = 1;
    final public static int FORMAT_LETTER_FOUND_WRONG_POSITION = 2;
    final public static int FORMAT_LETTER_FOUND_RIGHT_POSITION = 3;

    final public static int MAX_LETTERS = 5;
    final public static int MAX_TRYS = 6;

    final public static int GAME_WIN = 5;
    final public static int GAME_LOSE = 6;
    final public static int GAME_WAITING = 7;
    
    final public static String ANSI_RESET = "\u001B[0m";
    final public static String ANSI_GREEN = "\u001B[42m";
    final public static String ANSI_YELLOW = "\u001B[43m";
    final public static String ANSI_GREY = "\u001b[47;1m";
    final public static String ANSI_BLACK = "\u001b[30m";
    final public static String ANSI_WHITE = "\u001b[47m";
    
    final public static String ANSI_GREEN_TEXT = "\033[0;32m";
    final public static String ANSI_YELLOW_TEXT = "\033[0;33m";
    final public static String ANSI_CYAN_TEXT = "\033[0;36m";
    final public static String ANSI_RED_TEXT = "\033[0;31m";

    final public static String PATH_WELCOME_STRING = "src/main/java/it/uniba/app/view/welcomeApp.dat";

    /**
     * Costruttore privato non richiamabile
     */
    private Helper() {}
    
    /**
     * Metodo per inserire i valori di un Array di int all'interno di una List di Integer
     * 
     * @param array Arry di int.
     * @param arrayList List di Integer.
     */
    public static void arrayToArrayList(int[] array, List<Integer> arrayList) {
        for(int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }
        return;
    }

    /**
     * Carica da file binario un oggetto di classe T e lo restituisce
     * 
     * @param <T> classe dell'oggetto da caricare da file binario
     * @param relativePath del file
     * @return oggetto binario
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T carica(String relativePath) throws IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(relativePath));
        T obj = (T) in.readObject();
        in.close();

        return obj;
    }
}
