package it.uniba.app.view;
import it.uniba.app.control.UserManager;

/**
 * Classe astratta per gestire le varie interfacce di input
 */
public abstract class Viewer {
    
    UserManager usrManager;

    public Viewer()
    {
        usrManager = new UserManager();
    }
    
    // Classe astratta da implementare in ogni classe derivata per avviare l'input
    abstract protected void readInput();
}
