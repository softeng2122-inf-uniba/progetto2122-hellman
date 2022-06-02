package it.uniba.app.models;

/**
 * <<Entity>>
 * Classe che identifica la figura del paroliere, contiene il game in stato di
 * configurazione.
 */
public class WordSmith {
    /** Gioco che viene configurato prima dell'esecuzione. */
    private Game configuratedGame = new Game();

    /**
     * Metodo che permette di far usare e modificare il Game configurabile al
     * UserManager.
     *
     * @return ritorna il game configurabile
     */
    public Game getConfiguratedGame() {
        return configuratedGame;
    }
}
