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
class GameManager {

    private GameManager() {};

    /**
     * Metodo per l'impostazione della parola segreta di un Game.
     *
     * @param game Game di cui modificare la parola segreta.
     * @param secretWord Parola segreta da impostare.
     * @throws WrongWordException Eccezione sollevata nel caso in cui la parola inserita non sia adeguata
     * o nel caso in cui il gioco sia configurabile.
     */
    static void setSecretWord(Game game, String secretWord) throws WrongWordException {

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
     * Serve ad accedere alla parola segreta di un Game.
     *
     * @param game Game di cui accedere alla parola segreta.
     * @return Parola segreta di game.
     */
    static String getSecretWord(Game game) {
        return game.getSecretWord();
    }

    /**
     * Metodo per l'avvio del gioco instanziando il game attuale con i valori del
     * game precedentemente configurato.
     * Successivamente viene disabilitata la possibilità di configurare il game
     * attuale e viene resettato il game precedente.
     * 
     * @param currentGame      Game attuale da configurare.
     * @param configuratedGame Game configurato i cui valori verranno usati per
     *                         instanziare il game attuale
     * @throws WrongWordException Eccezione che controlla che la parola sia settata
     *                            correttamente.
     */
    static void startGame(Game currentGame, Game configuratedGame) throws WrongWordException{
        if (currentGame.getSecretWord().equals("")){
            if(configuratedGame != null && !configuratedGame.getSecretWord().equals("")){
                currentGame.resetGame();
                currentGame.setSecretWord(configuratedGame.getSecretWord());
                currentGame.disableConfigurable();

            } else {
                throw new WrongWordException("Non è ancora stata inserita la parola segreta!");
            }

        } else {
            throw new WrongWordException("C'è già un game in corso.");
        }
    }

    /**
     * Metodo per l'abbandono del gioco con reset dei dati in esso configurati.
     *  
     * @param currentGame Gioco corrente che viene resettato.
     * @throws WrongWordException Eccezione che controlla il corretto funzionamento del metodo.
     */
    static void backGame(Game currentGame) throws WrongWordException{
        if (currentGame.getSecretWord().equals("") || currentGame.isConfigurable()){
            throw new WrongWordException("Non c'è un game in corso.");
        }else {
            currentGame.resetGame();
        }
    }

    /**
     * Metodo per verificare la presenza o meno di un carattere nella parola
     * così da controllare eventualmente anche se questo è nella posizione corretta o errata.
     * 
     * @param secretword Parola segreta di cui verificare la presenza del carattere.
     * @param character Carattere della parola del tentativo di cui verificare la presenza in word.
     * @param wordCharPosition Posizione della carattere nella parola inserita nel tentativo.
     * @return Costante che stabilisce la presenza o meno del carattere ed eventualmente se la sua posizione è corretta.
     */
    static int matchCharacters(String secretword, char character, int wordCharPosition) {
        int secretwordCharFirstOccurrence = secretword.indexOf(character);
        
        if (secretwordCharFirstOccurrence < 0)
            return Helper.FORMAT_LETTER_NOT_FOUND;
        if (secretword.charAt(wordCharPosition) == character)
            return Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION;
        if (secretword.charAt(wordCharPosition) != character)
            return Helper.FORMAT_LETTER_FOUND_WRONG_POSITION;

        return -1;
    }

    /**
     * Metodo per effettuare un tentativo di inserimento di una parola per
     * indovinare la parola segreta.
     * 
     * @param currentGame Game attuale in cui effettuare il tentativo.
     * @param word Parola da inserire durante il tentativo.
     * @return Costante che stabilisce lo stato del game e la lista aggiornata dei tentativi effettuati.
     * @throws WrongWordException Eccezione sollevata nel caso in cui la parola inserita non sia adeguata
     * o nel caso in cui il gioco sia configurabile.
     */
    static Pair<Integer, List<Word>> makeTry(Game currentGame, String word) throws WrongWordException {
        List<Word> trys;
        if (!currentGame.isConfigurable()) {

            if (currentGame.getNumberLetter() > word.length()) {
                throw new WrongWordException("La parola inserita contiene un numero insufficiente di caratteri.");
            } else if (currentGame.getNumberLetter() < word.length()) {
                throw new WrongWordException("La parola inserita contiene un numero troppo elevato di caratteri.");
            } else if (!Pattern.matches("[a-zA-Z]+", word)) {
                throw new WrongWordException("La parola contiene caratteri non ammessi.");
            } else {
                
                ArrayList<Integer> formats = new ArrayList<Integer>();
                int result = Helper.GAME_WIN;

                for(int i = 0; i < word.length(); i++) {
                    int format = matchCharacters(currentGame.getSecretWord(), word.charAt(i), i);

                    if(format != Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION)
                        result = Helper.GAME_LOSE;

                    formats.add(format);
                }

                currentGame.addTry(new Word(word, formats));

                if(result == Helper.GAME_LOSE && currentGame.getNumberTrys() != currentGame.getMaxTry())
                    result = Helper.GAME_WAITING;

                trys = currentGame.getTrys();

                if(result == Helper.GAME_LOSE || result == Helper.GAME_WIN){
                    if(result == Helper.GAME_LOSE)
                        trys.set(0, new Word(getSecretWord(currentGame), formats));

                    currentGame.resetGame();
                }
                
                return new Pair<Integer, List<Word>>(result, trys);
            }
        } else {
            throw new WrongWordException("Il game non è iniziato.");
        }
    }

    /**
     * Metodo usato per capire se è o meno iniziata una partita.
     * 
     * @param game game su cui effettuare il controllo.
     * @return true se il game è iniziato, false altrimenti.
     */
    static boolean isGameStarted(Game game){
        return !game.getSecretWord().equals("");
    }
}
