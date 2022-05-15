package it.uniba.app.control;

import it.uniba.app.models.Game;
import it.uniba.app.utils.IPlayer;
import it.uniba.app.utils.WrongWordException;
import java.util.List;
import it.uniba.app.models.Word;
import it.uniba.app.utils.Pair;

/**
 * Classe che serve per interfacciare l'utente con il game manager
 */
public class UserManager implements IPlayer{
    //private Player player = new Player();
    private WordSmith wordSmith = new WordSmith();
    private Game currentGame = new Game();

    /**
     * Metodo che demanda al paroliere l'impostazione della parola segreta.
     *
     * @param word parola da impostare.
     */
    public void setSecretWord(String word) throws WrongWordException{
        wordSmith.setSecretWord(word);
    }

    /**
     * Metodo che restituisce la parola segreta.
     * 
     * @return parola segreta
     */
    public String getSecretWord(){
        return wordSmith.getSecretWord();
    }

    /**
     *  Metodo che serve per uscire da una partita in corso.
     * 
     * @throws WrongWordException Eccezione che controlla il corretto funzionamento del metodo
     */
    public void backGame() throws WrongWordException{
        GameManager.backGame(currentGame);
    }

    /**
     * Metodo per avviare la partita attraverso il metodo statico di GameManager.
     * 
     * @throws WrongWordException Eccezione che controlla che la parola sia settata correttamente.
     */
    public void startGame() throws WrongWordException{
        GameManager.startGame(currentGame, wordSmith.getConfiguratedGame());
    }

    /**
     * Metodo che richiama il makeTry del GameManager sul game corrente.
     * 
     * @param word Parola del tentativo effettuato.
     * @return Pair contenente risultato del tentativo corrente e la lista dei tentativi effettuati.
     * @throws WrongWordException Eccezione che controlla che il tentativo sia effettuato correttamente.
     */
    public Pair<Integer, List<Word>> makeTry(String word) throws WrongWordException{
        return GameManager.makeTry(currentGame,word);
	}
    
    /**
     * Metodo usato per capire se è o meno iniziata una partita.
     * 
     * @return true se il currentGame è iniziato, false altrimenti.
     */
    public boolean isGameStarted(){
        return GameManager.isGameStarted(currentGame);
    }
}
