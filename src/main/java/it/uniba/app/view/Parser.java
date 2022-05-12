package it.uniba.app.view;

import it.uniba.app.utils.*;
import java.util.*;

/*
* La classe contiene il Parser, che permette la lettura dei comandi (del gioco o dell'inserimento di una nuova parola) in input dall'utente,
* giocatore o paroliere che sia.
*/
public class Parser {
    
    // Scanner per leggere l'input da tastiera
    Scanner scanner = new Scanner(System.in);

    // Lista dei comandi del gioco
    private final List<Commands> commands = new ArrayList<>();

    // Insieme di stringhe da ignorare durante il parsing
    private Set<String> ignoreWords; 

    /**
     * Costruttore del parser
     * 
     * @param commands da riconoscere
     */
    public Parser(List<Pair<String, CommandType>> commands) {
        this.ignoreWords = new HashSet<String>();
        try {
            init(commands);
        } catch (Exception exception) {
            System.out.println("Il programma non e' terminato correttamente. Riavviarlo.");
            System.exit(-1);
        }

    }

    /**
     * Restituisce la lista dei comandi accettati dal parser
     * @return commands
     */
    public List<Commands> getCommands() {
        return commands;
    }

    /**
     * Il metodo init inizializza i tipi di comandi che il parser si aspetta.
     * 
     * @param commands
     * @throws Exception
     */
    public void init(List<Pair<String, CommandType>> commands) throws Exception {
        // Commands
        for(Pair<String, CommandType> command: commands){
            Commands newword = new Commands(command.getSecond(), command.getFirst());
            newword.setAlias(new String[] { command.getFirst().toLowerCase(), command.getFirst().toUpperCase() });
            getCommands().add(newword);
        }
    }

    /**
     * Il metodo parseString permette di tokenizzare la stringa letta in input e di
     * gestire l'eventuale presenza di parole da ignorare, ossia parole che andranno 
     * ignorate al momento del parsing.
     * L' attuale implementazione del parser non prevede le parole da ignorare, ma la
     * funzionalità è stata aggiunta per motivi di estendibilità.
     * 
     * @param string da analizzare
     * @param ignoreWords da ignorare
     * @return tokens
     */
    public static List<String> parseString(String string, Set<String> ignoreWords) {
        List<String> tokens = new ArrayList<>();
        String[] split = string.toLowerCase().split("\\s+");
        for (String t : split) {
            if (!ignoreWords.contains(t)) {
                tokens.add(t);
            }
        }
        return tokens;
    }

    /**
     * Il metodo checkForCommand confronta la stringa tokenizzata letta in input
     * con i comandi supportati dal gioco, se la stringa corrisponde ad un comando,
     * restituisce la sua posizione nella lista di comandi memorizzati, che sarà
     * utilizzata da nextCommand di Terminal per interpretare il tipo di comando 
     * inserito e gestirlo di conseguenza.
     * 
     * @param token della stringa tokenizzata
     * @param commands lista di tutti i possibili comandi
     * @return posizione del comando dato in input nella lista dei comandi accettati
     */
    private int checkForCommand(String token, List<Commands> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token)) {
                return i;
            }
        }
        return -1; // posizione invalida altrimenti
    }

    /**
     * Il metodo readCommand permette il confronto del risultato del metodo
     * parse con uno qualsiasi dei comandi supportati, se essa corrisponde ad uno
     * dei comandi, lo restituisce, altrimenti restituisce null
     * 
     * @return risultato di parse.
     */
    public ParserOutput readCommand(boolean print_command) {
        ParserOutput p = null;

        if(print_command)
            System.out.println("Inserisci un comando:");
        
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            p = this.parse(command, getCommands());
            return p;
        }
        return p;
    }


    /**
     * Il metodo parse permette il confronto del risultato di parseString
     * con il risultato di checkForCommand, che restituira' l'indice appropriato
     * nella lista dei comandi.
     *
     * @return indice nella lista di comandi possibili.
     */
    public ParserOutput parse(String command, List<Commands> commands) {
        List<String> tokens = parseString(command, ignoreWords);
        if (!tokens.isEmpty()) {
            int ic = checkForCommand(tokens.get(0), commands);
            if (ic == -1) {
                return null;
            }
            return new ParserOutput(commands.get(ic));

        } else {
            return null;
        }
    }
}
