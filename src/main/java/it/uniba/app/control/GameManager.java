package it.uniba.app.control;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import it.uniba.app.models.Game;
import it.uniba.app.models.Word;
import it.uniba.app.utils.Helper;
import it.uniba.app.utils.Pair;
import it.uniba.app.utils.WrongWordException;

/**
 * <<Control>>
 * Classe gestisce l'avvio e l'esecuzione del game.
 */
public class GameManager {

    private GameManager() {};

    /**
     * Metodo per l'impostazione della parola segreta di un Game.
     * 
     * @param game Game di cui modificare la parola segreta.
     * @param secretWord Parola segreta da impostare.
     * @throws WrongWordException Eccezione sollevata nel caso in cui la parola inserita non sia adeguata
     * o nel caso in cui il gioco sia configurabile.
     */
    public static void setSecretWord(Game game, String secretWord) throws WrongWordException {

        if (game.isConfigurable()) {
            if (game.getNumberLetter() > secretWord.length()) {
                throw new WrongWordException("La parola inserita contiene un numero insufficiente di caratteri.");
            } else if (game.getNumberLetter() < secretWord.length()) {
                throw new WrongWordException("La parola inserita contiene un numero troppo elevato di caratteri.");
            } else if (!Pattern.matches("[a-zA-Z]+", secretWord)) {
                throw new WrongWordException("La parola contiene caratteri non ammessi.");
            } else {
                game.setSecretWord(secretWord);
            }
        } else {
            throw new WrongWordException("Il game non è configurabile.");
        }
    }

    /**
     * 
     */
    }

    
}
