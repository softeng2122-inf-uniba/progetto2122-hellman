package it.uniba.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * <<noECB>>
 * Classe di supporto che ha tutta una serie di costanti che servono per
 * mostrare se le lettere della parola inserita dall'utente sono in posizione
 * corretta oppure errata, per l'esito del gioco cioè se l'utente ha perso o
 * vinto, per il numero massimo di lettere e di tentativi e per i colori dello
 * sfondo delle lettere.
 */
public final class Helper {

    /** Lettera non scritta. */
    public static final int FORMAT_LETTER_NEVER_TYPED = 0;
    /** Lettera non trovata. */
    public static final int FORMAT_LETTER_NOT_FOUND = 1;
    /** Lettera nella posizione errata. */
    public static final int FORMAT_LETTER_FOUND_WRONG_POSITION = 2;
    /** Lettera nella posizione corretta. */
    public static final int FORMAT_LETTER_FOUND_RIGHT_POSITION = 3;

    /** Numero massimo di lettere della parola. */
    public static final int MAX_LETTERS = 5;
    /** Numero massimo di tentativi. */
    public static final int MAX_TRYS = 6;

    /** Identificativo della vittoria. */
    public static final int GAME_WIN = 5;
    /** Identificativo della perdita. */
    public static final int GAME_LOSE = 6;
    /** Identificativo del gioco ancora in corso. */
    public static final int GAME_WAITING = 7;

    /** Colore sfondo reset. */
    public static final String ANSI_RESET = "\u001B[0m";
    /** Colore sfondo verde. */
    public static final String ANSI_GREEN = "\u001B[42m";
    /** Colore sfondo giallo. */
    public static final String ANSI_YELLOW = "\u001B[43m";
    /** Colore sfondo grigio. */
    public static final String ANSI_GREY = "\u001b[47;1m";
    /** Colore sfondo nero. */
    public static final String ANSI_BLACK = "\u001b[30m";
    /** Colore sfondo bianco. */
    public static final String ANSI_WHITE = "\u001b[47m";

    /** Colore testo verde. */
    public static final String ANSI_GREEN_TEXT = "\033[0;32m";
    /** Colore testo giallo. */
    public static final String ANSI_YELLOW_TEXT = "\033[0;33m";
    /** Colore testo ciano. */
    public static final String ANSI_CYAN_TEXT = "\033[0;36m";
    /** Colore testo rosso. */
    public static final String ANSI_RED_TEXT = "\033[0;31m";

    /** Percorso assoluto del progetto. */
    private static final String PATH_ABSOLUTE = new File("").getAbsolutePath();

    /** Percorso dove è salvato il welcome. */
    public static final String PATH_WELCOME_STRING = Helper.PATH_ABSOLUTE
    + "/src/main/java/it/uniba/app/utils/welcomeApp.dat";

    /** Percorso dove è salvato l'help. */
    public static final String PATH_HELP_STRING = Helper.PATH_ABSOLUTE
    + "/src/main/java/it/uniba/app/utils/helpApp.dat";

    /**
     * Costruttore privato non richiamabile.
     */
    private Helper() {
    }

    /**
     * Metodo per inserire i valori di un Array di int all'interno di
     * una List di Integer.
     *
     * @param array     Arry di int.
     * @param arrayList List di Integer.
     */
    public static void arrayToArrayList(final int[] array,
                                        final List<Integer> arrayList) {
        for (int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }
        return;
    }

    /**
     * Carica da file binario un oggetto di classe T e lo restituisce.
     *
     * @param relativePath del file
     * @return oggetto binario
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static String carica(final String relativePath)
    throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(relativePath);
        ObjectInputStream in = new ObjectInputStream(file);
        String obj = (String) in.readObject();
        in.close();

        return obj;
    }
}
