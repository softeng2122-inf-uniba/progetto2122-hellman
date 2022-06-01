package it.uniba.app.view;

import it.uniba.app.utils.CommandType;
import it.uniba.app.utils.Pair;
import java.util.*;

/**
 * <<noECB>>
 * La classe Parser permette la lettura dei comandi(del gioco o tentativo di una parola) dell'utente,
 * paroliere o giocatore che sia.
 */
class Parser {
    
    /** Scanner per leggere l'input da tastiera */
    Scanner scanner = new Scanner(System.in);

    /** Lista dei comandi del gioco */
    private final List<Commands> commands = new ArrayList<>();

    /** Insieme di stringhe da ignorare durante il parsing */
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
     * 
     * @return lista dei comandi
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
    private void init(List<Pair<String, CommandType>> commands) throws Exception {
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
    private static List<String> parseString(String string, Set<String> ignoreWords) {
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
     * @param printCommand se true stampa l'inserimento del comando, altrimenti non stampa nulla
     * @param gameStarted se true stampa "Effettua un tentativo (o inserisci un comando):", 
     * altrimenti "Inserisci un comando:"
     * @return risultato di parse.
     */
    public ParserOutput readCommand(boolean printCommand, boolean gameStarted) {
        ParserOutput p = null;

        if(printCommand){
            if(gameStarted){
                System.out.println("Effettua un tentativo (o inserisci un comando):");
            }else{
                System.out.println("Inserisci un comando:");
            }
        }
        
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
     * @param command stringa analizzare
     * @param commands lista dei comandi accettati
     * @return indice nella lista di comandi possibili.
     */
    private ParserOutput parse(String command, List<Commands> commands) {
        List<String> tokens = parseString(command, ignoreWords);
        if (!tokens.isEmpty()) {
            int ic = checkForCommand(tokens.get(0), commands);
            if (ic == -1) {
                if(tokens.size() == 1)
                {
                    Commands inputWord = new Commands(CommandType.INPUT_WORD, tokens.get(0));
                    inputWord.setAlias(new String[] { tokens.get(0).toLowerCase(), tokens.get(0).toUpperCase() });
                    return new ParserOutput(inputWord);
                }

                return null;
            }
            if(commands.get(ic).getType() == CommandType.NEW)
            {
                if (tokens.size() == 2){
                    Commands inputWord = new Commands(CommandType.NEW, tokens.get(1).toLowerCase());
                    return new ParserOutput(inputWord);
                } else
                    return null;
            }
            return new ParserOutput(commands.get(ic));

        } else {
            return null;
        }
    }
}
