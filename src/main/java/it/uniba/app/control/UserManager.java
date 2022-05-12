package it.uniba.app.control;

import it.uniba.app.models.Game;
import it.uniba.app.utils.IPlayer;
import it.uniba.app.utils.IWordSmith;
import it.uniba.app.utils.WrongWordException;

/**
 * Classe che serve per interfacciare l'utente con il game manager
 */
public class UserManager implements IPlayer, IWordSmith{
    private Player player = new Player();
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
   * @return parola segreta
   */
  public String getSecretWord(){
		return wordSmith.getSecretWord();
	}

  public void startGame() throws WrongWordException{
      GameManager.startGame(currentGame, wordSmith.getConfiguratedGame());
  }
}
