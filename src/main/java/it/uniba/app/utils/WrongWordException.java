package it.uniba.app.utils;

/**
 * Classe che gestisce le eccezioni o sulla lunghezza errata della parola impostata dal paroliere
 * o sull'inserimento di numeri nella parola , oppure se si sta impostando una nuova parola ma il
 * gioco non è stato ancora istanziato.
 */
public class WrongWordException extends Exception{
    
    /**
     * 
     * @param message
     */
    public WrongWordException(String message){
        super(message);
    }
}