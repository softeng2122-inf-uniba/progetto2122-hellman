package it.uniba.app.view;

import it.uniba.app.control.UserManager;

/**
 * {@literal <<Boundary>>}
 * Classe astratta per gestire le varie interfacce di input.
 */
abstract class Viewer {

    /** Attributo control che gestisce l'utente(player e paroliere). */
    private UserManager usrManager;

    /**
     * Costruttore di viewer, inizializza usrManager.
     */
    Viewer() {
        usrManager = new UserManager();
    }

    /**
     * Classe astratta da implementare in ogni classe derivata
     * per avviare l'input.
     */
    abstract void readInput();

    /**
     * Restituisce l'istanza di usrManager.
     *
     * @return usrManager
     */
    UserManager getUserManager() {
        return usrManager;
    }
}
