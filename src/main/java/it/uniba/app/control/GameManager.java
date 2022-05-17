package it.uniba.app.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import it.uniba.app.models.Game;
import it.uniba.app.models.Word;
import it.uniba.app.utils.Helper;
import it.uniba.app.utils.Pair;
import it.uniba.app.utils.GameException;

/**
 * <<Control>>
 * Classe gestisce l'avvio e l'esecuzione del game.
 */
class GameManager {

    /**
     * Metodo costruttore privato non richiamabile.
     */
    private GameManager() {};

    /**
     * Metodo per l'impostazione della parola segreta di un Game.
     *
     * @param game Game di cui modificare la parola segreta.
     * @param secretWord Parola segreta da impostare.
     * @throws GameException Eccezione sollevata nel caso in cui la parola inserita non sia adeguata
     * o nel caso in cui il gioco sia configurabile.
     */
    static void setSecretWord(Game game, String secretWord) throws GameException {

        if (game.isConfigurable()) {
            if (game.getNumberLetter() > secretWord.length()) {
                throw new GameException("La parola inserita contiene un numero insufficiente di caratteri.");
            } else if (game.getNumberLetter() < secretWord.length()) {
                throw new GameException("La parola inserita contiene un numero troppo elevato di caratteri.");
            } else if (!Pattern.matches("[a-zA-Z]+", secretWord)) {
                throw new GameException("La parola contiene caratteri non ammessi.");
            } else {
                game.setSecretWord(secretWord);
            }
        } else {
            throw new GameException("Il game non è configurabile.");
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
     *                         instanziare il game attuale.
     * @throws GameException Eccezione che controlla che la parola sia settata
     *                            correttamente.
     */
    static void startGame(Game currentGame, Game configuratedGame) throws GameException{
        if (currentGame.getSecretWord().equals("")){
            if(configuratedGame != null && !configuratedGame.getSecretWord().equals("")){
                currentGame.resetGame();
                currentGame.setSecretWord(configuratedGame.getSecretWord());
                currentGame.disableConfigurable();

            } else {
                throw new GameException("Non è ancora stata inserita la parola segreta!");
            }

        } else {
            throw new GameException("C'è già un game in corso.");
        }
    }

    /**
     * Metodo per l'abbandono del gioco con reset dei dati in esso configurati.
     *  
     * @param currentGame Gioco corrente che viene resettato.
     * @throws GameException Eccezione che controlla il corretto funzionamento del metodo.
     */
    static void backGame(Game currentGame) throws GameException{
        if (currentGame.getSecretWord().equals("") || currentGame.isConfigurable()){
            throw new GameException("Non c'è un game in corso.");
        }else {
            currentGame.resetGame();
        }
    }

    /**
     * Metodo che conta il numero di occorrenze di ciascuna lettera della parola segreta.
     * 
     * @param lettersQuantity numero di occorrenze per lettera della parola segreta.
     * @param secretword valore della parola segreta.
     */
    private static void countLetters(Map<Character,Integer> lettersQuantity, String secretword) {
        for(int i = 0; i < secretword.length(); i++) {
            char key = secretword.charAt(i);
            try{
                lettersQuantity.put(key, lettersQuantity.get(key)+1);
            }catch(NullPointerException exception){
                lettersQuantity.put(key, 1);
            }
        }
    }

    /**
     * Metodo che verifica quali lettere sono nella giusta posizione e ne ritorna la quantità.
     * 
     * @param secretword valore della parola segreta.
     * @param word valore del tentativo inserito.
     * @param lettersQuantity numero di occorrenze per lettera della parola segreta.
     * @param formats array di formati per i caratteri del tentativo inserito.
     * @return il numero di lettere nella posizione corretta del tentativo inserito.
     */
    private static int matchRightPositionCharacters(String secretword, String word, Map<Character,Integer> lettersQuantity, int[] formats) {
        int rightPositions = 0;
        for(int i=0; i < word.length(); i++) {
            if(secretword.charAt(i) == word.charAt(i)){
                lettersQuantity.put(word.charAt(i), lettersQuantity.get(word.charAt(i))-1);
                formats[i] = Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION;
                rightPositions++;
            }
        }
        return rightPositions;
    }

    /**
     * Metodo che verifica quali lettere sono nella posizione sbagliata o assente.
     * 
     * @param secretword valore della parola segreta.
     * @param word valore del tentativo inserito.
     * @param lettersQuantity numero di occorrenze per lettera della parola segreta.
     * @param formats array di formati per i caratteri del tentativo inserito.
     */
    private static void matchWrongCharacters(String secretword, String word, Map<Character,Integer> lettersQuantity, int[] formats) {
        for(int i=0; i < word.length(); i++) {
            if(formats[i] != Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION){
                try{
                    if(lettersQuantity.get(word.charAt(i))>0){
                        lettersQuantity.put(word.charAt(i), lettersQuantity.get(word.charAt(i))-1);
                        formats[i] = Helper.FORMAT_LETTER_FOUND_WRONG_POSITION;
                    }else{
                        formats[i] = Helper.FORMAT_LETTER_NOT_FOUND;
                    }
                }catch(NullPointerException exception2){
                    formats[i] = Helper.FORMAT_LETTER_NOT_FOUND;
                }
            }
        }
    }

    /**
     * Metodo per effettuare un tentativo di inserimento di una parola per
     * indovinare la parola segreta.
     * 
     * @param currentGame Game attuale in cui effettuare il tentativo.
     * @param word Parola da inserire durante il tentativo.
     * @return Costante che stabilisce lo stato del game e la lista aggiornata dei tentativi effettuati.
     * @throws GameException Eccezione sollevata nel caso in cui la parola inserita non sia adeguata
     * o nel caso in cui il gioco sia configurabile.
     */
    static Pair<Integer, List<Word>> makeTry(Game currentGame, String word) throws GameException {
        List<Word> trys;
        if (!currentGame.isConfigurable()) {

            if (currentGame.getNumberLetter() > word.length()) {
                throw new GameException("La parola inserita contiene un numero insufficiente di caratteri.");
            } else if (currentGame.getNumberLetter() < word.length()) {
                throw new GameException("La parola inserita contiene un numero troppo elevato di caratteri.");
            } else if (!Pattern.matches("[a-zA-Z]+", word)) {
                throw new GameException("La parola contiene caratteri non ammessi.");
            } else {
                
                int[] formatsArray = new int[currentGame.getNumberLetter()];
                List<Integer> formats = new ArrayList<Integer> ();
                int result = Helper.GAME_WIN;

                Map<Character,Integer> lettersQuantity = new HashMap<Character,Integer>(currentGame.getNumberLetter());
                countLetters(lettersQuantity, currentGame.getSecretWord());

                if(matchRightPositionCharacters(currentGame.getSecretWord(), word, lettersQuantity, formatsArray)!=currentGame.getNumberLetter()){
                    matchWrongCharacters(currentGame.getSecretWord(), word, lettersQuantity, formatsArray);
                    result = Helper.GAME_LOSE;
                }

                Helper.arrayToArrayList(formatsArray, formats);

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
            throw new GameException("Il game non è iniziato.");
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
