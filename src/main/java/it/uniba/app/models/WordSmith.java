package it.uniba.app.models;

/**
 * Classe che sarebbe il paroliere che serve per impostare e visualizzare la parola.
 */
public class WordSmith{

    private Game configuratedGame = new Game();

    /**
     * Metodo che permette di far usare e modificare il Game configurabile al UserManager
     * 
     * @return ritorna il game configurabile
     */
    public Game getConfiguratedGame() {
        return configuratedGame;
    }
}
