package it.uniba.app.models;

/**
 * <<Entity>>
 * Classe che identifica la figura del giocatore, contiene il game in stato di esecuzione.
 */
public class Player{

    private Game currentGame = new Game();

    /**
     * Metodo che permette di far usare e modificare il Game attuale al UserManager
     * 
     * @return ritorna il game configurabile
     */
    public Game getCurrentGame() {
        return currentGame;
    }
    
}
