package it.uniba.app.control;

import it.uniba.app.models.Game;
import it.uniba.app.utils.IWordSmith;
import it.uniba.app.utils.WrongWordException;

/**
 * Classe che sarebbe il paroliere che serve per impostare e visualizzare la parola.
 */
public class WordSmith implements IWordSmith{

    private Game configuratedGame = new Game();

    /**
     * Metodo che demanda al gameManager di impostare la parola.
     *
     * @param word parola da impostare
     *
    */
    public void setSecretWord(String word) throws WrongWordException{

		  GameManager.setSecretWord(configuratedGame,word);

	  }

    /**
     * Metodo che restituisce la parola segreta.
     * @return parola segreta
     */
    public String getSecretWord(){
		  return GameManager.getSecretWord(configuratedGame);
	  }

    public Game getConfiguratedGame() {
        return configuratedGame;
    }
}
