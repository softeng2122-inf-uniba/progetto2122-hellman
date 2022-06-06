package it.uniba.app.models;

/**
 * <<Entity>>
 * Classe che identifica la figura del giocatore, contiene il game
 * in stato di esecuzione.
 */
public class Player {
    /** Gioco che viene usato durante la partita corrente. */
    private Game currentGame = new Game();

    /**
     * Metodo che permette di far usare e modificare il Game
     * attuale al UserManager.
     *
     * @return game corrente
     */
    public Game getCurrentGame() {
        return new Game(currentGame);
    }

    /**
     * Metodo che permette di settare il Game corrente.
     *
     * @param newCurrentGame Game con il quale settare il Game corrente.
     */
    public void setCurrentGame(final Game newCurrentGame) {
        this.currentGame = new Game(newCurrentGame);
    }

}
