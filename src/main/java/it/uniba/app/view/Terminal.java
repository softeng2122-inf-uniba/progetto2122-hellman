package it.uniba.app.view;

import java.util.*;

import it.uniba.app.models.Word;
import it.uniba.app.utils.*;
import java.io.PrintStream;

/**
 * Classe front-end che permette di dialogare con l'utente a linea di comando
 */
public class Terminal extends Viewer{

    // Parser utilizzato per il riconoscimento dei comandi accettati dal gioco
    private Parser parser;

    // Lista di comandi accettati dal gioco
    List<Pair<String, CommandType>> commands = new ArrayList<>();

    /**
     * Costruttore di terminal
     * @param flags passato all'apertura del terminale
     */
    public Terminal(String[] flags)
    {
        super();

        // Aggiungere i comandi accettati dal parser
        commands.add(new Pair<String, CommandType>("/help", CommandType.HELP));
        commands.add(new Pair<String, CommandType>("/esci", CommandType.EXIT_APP));
        commands.add(new Pair<String, CommandType>("si", CommandType.EXIT_APP_YES));
        commands.add(new Pair<String, CommandType>("no", CommandType.EXIT_APP_NO));
    
        parser = new Parser(commands);

        if(flags.length > 0)
        {
            if(flags[0].equals("-h") || flags[0].equals("--help"))
            {
                System.out.println(help());
            }
        }

        readInput();
    }

    /**
     * Metodo che invoca l'inserimento di nuovi comandi da tastiera
     */
    protected void readInput()
    {
        while (true){
            nextCommand(parser.readCommand(true), System.out);
        }
    }

    /**
     * Metodo che gestisce il comando inserito dall'utente
     * 
     * @param p risultato del parser
     * @param out canale di output
     */
    public void nextCommand(ParserOutput p, PrintStream out) {

        if (p == null) {
            out.println("Non ho capito! Prova con un altro comando.");
        }else{
            CommandType type = p.getCommand().getType();
            
            switch(type)
            {
                case HELP:
                    out.println(help());
                    break;

                case EXIT_APP:
                    closeApp(out);
                    break;

                case INPUT_WORD:
                    out.println(makeTry(p.getCommand().getName()));
                    break;

                default:
                    out.println("Non ho capito! Prova con un altro comando.");
                    break;
            }
        }

    }

    /**
     * Restituisce la stringa del comando di help
     * 
     * @return comando di help
     */
    public static String help()
    {
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
    public void closeApp(PrintStream out){
        out.println("Sei sicuro di uscire dall'app? (si/no)");

        ParserOutput po = parser.readCommand(false);
        if(po != null)
        {
            CommandType type = po.getCommand().getType();
            switch(type)
            {
                case EXIT_APP_YES:
                    out.println("Ciao!");
                    System.exit(0);
                    return;

                case EXIT_APP_NO:
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
    public String makeTry(String word){
        String str = "";

        try{
            Pair<Integer, List<Word>> res = usrManager.makeTry(word);

            switch(res.getFirst())
            {
                case Helper.GAME_WIN:
                    str += "Parola segreta indovinata\nNumero tentativi: ";
                    str += printMatrix(res.getSecond());
                    break;

                case Helper.GAME_LOSE:
                    str += "Hai raggiunto il numero massimo di tentativi.\nLa parola segreta è: " + res.getSecond().get(0).getWord();
                    break;

                case Helper.GAME_WAITING:
                    str += "Tentativo errato! ";
                    str += printMatrix(res.getSecond());
                    break;

                default:
                    break;
            }
        }catch(WrongWordException e){
            return e.getMessage();
        }

        return str;
    }

    /**
     * Restituisce la matrice delle parole inserite con i rispettivi colori nelle lettere
     * @param words tentativi effettuati
     * 
     * @return matrice dei tentativi colorata
     */
    private String printMatrix(List<Word> words)
    {
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
    private String getCharColored(char c, int format)
    {
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
