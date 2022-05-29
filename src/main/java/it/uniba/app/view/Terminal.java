package it.uniba.app.view;

import java.util.*;

import it.uniba.app.models.Word;
import it.uniba.app.utils.*;
import java.io.PrintStream;

/**
 * <<Boundary>>
 * Classe front-end che permette di dialogare con l'utente a linea di comando
 */
public class Terminal extends Viewer{

    /** Parser utilizzato per il riconoscimento dei comandi accettati dal gioco */
    private Parser parser;

    /** Lista di comandi accettati dal gioco */
    private List<Pair<String, CommandType>> commands = new ArrayList<>();

    /**
     * Costruttore di terminal
     * @param flags passato all'apertura del terminale
     */
    public Terminal(String[] flags){
        super();

        // Aggiungere i comandi accettati dal parser
        commands.add(new Pair<String, CommandType>("/help", CommandType.HELP));
        commands.add(new Pair<String, CommandType>("/esci", CommandType.EXIT_APP));
        commands.add(new Pair<String, CommandType>("/gioca", CommandType.START_GAME));
        commands.add(new Pair<String, CommandType>("si", CommandType.EXIT_YES));
        commands.add(new Pair<String, CommandType>("no", CommandType.EXIT_NO));
        commands.add(new Pair<String, CommandType>("/nuova", CommandType.NUOVA));
        commands.add(new Pair<String, CommandType>("/mostra", CommandType.SHOW));
        commands.add(new Pair<String, CommandType>("/abbandona", CommandType.EXIT_GAME));

        parser = new Parser(commands);

        if(flags.length > 0){
            if(flags[0].equals("-h") || flags[0].equals("--help")){
                System.out.println(help());
            }
        }

        readInput();
    }

    /**
     * Metodo che invoca l'inserimento di nuovi comandi da tastiera
     */
    protected void readInput(){
        while (true){
            nextCommand(parser.readCommand(true,usrManager.isGameStarted()), System.out);
        }
    }

    /**
     * Metodo che gestisce il comando inserito dall'utente da terminale
     *
     * @param p risultato del parser
     * @param out canale di output
     */
    private void nextCommand(ParserOutput p, PrintStream out) {

        if (p == null) {
            out.println("Non ho capito! Prova con un altro comando.");
        }else{
            CommandType type = p.getCommand().getType();

            switch(type){
                case HELP:
                    out.println(help());
                    break;

                case EXIT_APP:
                    closeApp(out);
                    break;

                case INPUT_WORD:
                    out.println(makeTry(p.getCommand().getName()));
                    break;
                    
                case NUOVA:
                    out.println(setSecretWord(p.getCommand().getName()));
                    break;

                case SHOW:
                    printSecretWord();
                    break;

                case START_GAME:
                    startGame(out);
                    break;

                case EXIT_GAME:
                    backGame(out);
                    break;

                default:
                    out.println("Non ho capito! Prova con un altro comando.");
                    break;
            }
        }

    }

    /**
     * Metodo per l'avvio della partita nel caso in cui la parola segreta sia stata
     * impostata.
     * 
     * @param out Canale di output.
     */
    private void startGame(PrintStream out){
        out.println("Avvio partita...");

        try {
            usrManager.startGame();
        } catch (GameException e) {
            out.println(e.getMessage());
            out.println("");
            return;
        }

        out.println(printMatrix(new ArrayList<Word>()));
    }

    /**
     * Metodo per l'abbandono della partita nel caso in cui si decide di non voler più giocare.
     * 
     * @param out Canale di output.
     */
    private void backGame(PrintStream out){
        out.println("Sei sicuro di abbandonare il gioco ancora in corso? (si/no)");

        ParserOutput po = parser.readCommand(false,usrManager.isGameStarted());
        if(po != null){
            CommandType type = po.getCommand().getType();
            switch(type){
                case EXIT_YES:
                    try {
                        usrManager.backGame();
                        out.println("Abbandono partita...");
                    } catch (GameException e) {
                        out.println(e.getMessage());
                        out.println("");
                    }
                    return;

                case EXIT_NO:
                    out.println("Abbandono del gioco annullato.");
                    return;

                default:
                    out.println("Input non valido. Reinserisci l'input.");
                    return;
            }
        }

        out.println("Input non valido. Reinserisci l'input.");
        return;
    }

    /**
     * Restituisce la stringa del comando di help
     *
     * @return comando di help
     */
    public static String help(){
        String str = "";

        str += "==============================================================================================\n";
        str += "Wordle è un videogioco in cui il giocatore deve indovinare una parola di cinque lettere in meno di sei tentativi. \n";
        str += "I comandi del paroliere per interagire con il gioco sono: \n";
        str += "   - imposta parola segreta (comando /nuova)\n";
        str += "   - mostra parola segreta (comando /mostra) \n \n";
        str += "I comandi del giocatore per interagire con il gioco sono:\n";
        str += "   - inizia una nuova partita (comando /gioca)\n";
        str += "   - abbandona la partita corrente (comando /abbandona)\n";
        str += "   - chiudere il gioco (comando /esci)\n";
        str += "   - effettua un tentativo per indovinare la parola segreta (inserendo qualsiasi input dopo /gioca)\n";
        str += "==============================================================================================\n";

        return str;
    }

    /**
     * Gestisce la chiusura dell'app
     *
     * @param out canale di output
     */
    private void closeApp(PrintStream out){
        out.println("Sei sicuro di uscire dall'app? (si/no)");

        ParserOutput po = parser.readCommand(false,usrManager.isGameStarted());
        if(po != null){
            CommandType type = po.getCommand().getType();
            switch(type){
                case EXIT_YES:
                    out.println("Ciao!");
                    System.exit(0);
                    return;

                case EXIT_NO:
                    out.println("Chiusura dell'app annullata.");
                    return;

                default:
                    out.println("Input non valido. Reinserisci l'input.");
                    return;
            }
        }

        out.println("Input non valido. Reinserisci l'input.");
        return;
    }

    /**
     * Gestisce il tentativo della parola
     *  
     * @param word per effettuare il tentativo
     * @return stringa della risposta del tentativo
     */
    private String makeTry(String word){
        String str = "";
        Pair<Integer, List<Word>> res;
        try{
            res = usrManager.makeTry(word);
        }catch(GameException e){
            return e.getMessage();
        }

        switch(res.getFirst())
        {
            case Helper.GAME_WIN:
                str += printMatrix(res.getSecond());
                str += "Parola segreta indovinata\nNumero tentativi: ";
                str += res.getSecond().size();
                break;

            case Helper.GAME_LOSE:
                str += printMatrix(res.getSecond());
                break;

            case Helper.GAME_WAITING:
                str += "Tentativo errato! ";
                str += printMatrix(res.getSecond());
                break;

            default:
                break;
        }

        return str;
    }
    
    /**
     * Metodo che serve a impostare la parola segreta.
     *
     * @param word
     * @return risposta dopo aver impostato la parola
     */
    private String setSecretWord(String word){
        String str= "";

        try{
		    usrManager.setSecretWord(word);
            str += "La parola è stata inserita correttamente.";
        }catch(GameException w){
            System.out.println(w.getMessage());
        }

        return str;
	}

    /**
     * Metodo che permette di stampare la parola segreta.
     */
    private void printSecretWord(){
        if(usrManager.getSecretWord().compareTo("") != 0){
        System.out.println("La parola segreta è " + usrManager.getSecretWord());
        } else {
            System.out.println("Errore, non e' stata inserita alcuna parola segreta.");
        }
    }

    /**
     *
     * Restituisce la matrice delle parole inserite con i rispettivi colori nelle lettere
     * 
     * @param words tentativi effettuati
     * @return matrice dei tentativi colorata
     */
    private String printMatrix(List<Word> words){
        String str = "\n";

        for(int i = 0; i < Helper.MAX_TRYS; i++){
            boolean emptyWord = false;
            Word word = null;
            try{
                word = words.get(i);
            }catch(IndexOutOfBoundsException e){
                emptyWord = true;
            }

            for(int j = 0; j < Helper.MAX_LETTERS; j++){
                str += (emptyWord == true) ? "_" : getCharColored(word.getWord().charAt(j), word.getFormat().get(j));
                str += " ";
            }
            str += "\n";
        }

        return str;
    }

    /**
     * Restituisce il carattere colorato del formato passato in input
     * 
     * @param c carattere da colorare
     * @param format formato del colore
     * @return carattere colorato
     */
    private String getCharColored(char c, int format){
        String str = "";

        switch(format)
        {
            case Helper.FORMAT_LETTER_NOT_FOUND:
                str += Helper.ANSI_GREY + c + Helper.ANSI_RESET;
                break;

            case Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION:
                str += Helper.ANSI_GREEN + c + Helper.ANSI_RESET;
                break;

            case Helper.FORMAT_LETTER_FOUND_WRONG_POSITION:
                str += Helper.ANSI_YELLOW + c + Helper.ANSI_RESET;
                break;

            default:
                break;
        }

        return str;
    }
}
