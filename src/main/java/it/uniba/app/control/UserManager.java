package it.uniba.app.control;

import it.uniba.app.utils.Pair;
import it.uniba.app.utils.GameException;

import it.uniba.app.models.Game;
import it.uniba.app.models.Player;
import it.uniba.app.models.Word;
import it.uniba.app.models.WordSmith;

import java.util.List;

/**
 * <<Control>>
 * Classe che permette di far interfacciare l'utente con il game manager.
 */
public class UserManager {
    /** Attributo che assume il valore del paroliere. */
    private WordSmith wordSmith = new WordSmith();
    /** Attributo che assume il valore del giocatore. */
    private Player player = new Player();

    /**
     * Metodo che demanda al gameManager di impostare la parola.
     *
     * @param word Nuovo valore della parola segreta.
     */
    public void setSecretWord(final String word) throws GameException {
        Game configuratedGame = wordSmith.getConfiguratedGame();
        GameManager.setSecretWord(configuratedGame, word);
        wordSmith.setConfiguratedGame(configuratedGame);
    }

    /**
     * Metodo che restituisce la parola segreta.
     *
     * @return valore della parola segreta.
     */
    public String getSecretWord() {
        return GameManager.getSecretWord(wordSmith.getConfiguratedGame());
    }

    /**
     * Metodo che serve per uscire da una partita in corso.
     *
     * @throws GameException Eccezione che controlla il corretto funzionamento
     *                       del metodo.
     */
    public void backGame() throws GameException {
        Game currentGame = player.getCurrentGame();
        GameManager.backGame(currentGame);
        player.setCurrentGame(currentGame);
    }

    /**
     * Metodo per avviare la partita attraverso il metodo
     * statico di GameManager.
     *
     * @throws GameException Eccezione che controlla che la parola sia settata
     *                       correttamente.
     */
    public void startGame() throws GameException {
        Game gameStart = player.getCurrentGame();
        Game configuratedGame = wordSmith.getConfiguratedGame();
        GameManager.startGame(gameStart, configuratedGame);
        player.setCurrentGame(gameStart);
        wordSmith.setConfiguratedGame(configuratedGame);
    }

    /**
     * Metodo che richiama il makeTry del GameManager sul game corrente.
     *
     * @param word Parola del tentativo effettuato.
     * @return Pair contenente il risultato del tentativo corrente e
     *         la lista dei tentativi effettuati.
     * @throws GameException Eccezione che controlla che il tentativo
     *                       sia effettuato correttamente.
     */
    public Pair<Integer, List<Word>> makeTry(final String word)
    throws GameException {
        Game currentGame = player.getCurrentGame();
        Pair<Integer, List<Word>> resoult =
        GameManager.makeTry(currentGame, word);
        player.setCurrentGame(currentGame);
        return resoult;
    }

    /**
     * Metodo usato per capire se è o meno iniziata una partita.
     *
     * @return true se il currentGame è iniziato, false altrimenti.
     */
    public boolean isGameStarted() {
        return GameManager.isGameStarted(player.getCurrentGame());
    }
}
