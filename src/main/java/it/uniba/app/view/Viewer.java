package it.uniba.app.view;
import it.uniba.app.control.UserManager;

/**
 * <<Boundary>>
 * Classe astratta per gestire le varie interfacce di input
 */
abstract class Viewer {
    
    /** Attributo control che gestisce l'utente(player e paroliere) */
    UserManager usrManager;

    /**
     * Costruttore di viewer, inizializza usrManager
     */
    public Viewer(){
        usrManager = new UserManager();
    }
    
    /** Classe astratta da implementare in ogni classe derivata per avviare l'input */
    abstract protected void readInput();
}
