package it.uniba.app.utils;

/** 
 * Classe di supporto che ha tutta una serie di costanti che servono per mostrare se le lettere della parola inserita
 * dall'utente sono in posizione corretta oppure errata oppure per l'esito del gioco cioè se l'utente ha perso o vinto.
*/
public class Helper {
    final public static int FORMAT_LETTER_NEVER_TYPED = 0;
    final public static int FORMAT_LETTER_NOT_FOUND = 1;
    final public static int FORMAT_LETTER_FOUND_WRONG_POSITION = 2;
    final public static int FORMAT_LETTER_FOUND_RIGHT_POSITION = 3;

    final public static int GAME_WIN = 5;
    final public static int GAME_LOSE = 6;
    final public static int GAME_WAITING = 7;

    private Helper(){}
}
