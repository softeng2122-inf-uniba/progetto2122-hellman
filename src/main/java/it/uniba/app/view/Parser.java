package it.uniba.app.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import it.uniba.app.utils.CommandType;
import it.uniba.app.utils.Pair;

/**
 * <<noECB>>
 * La classe Parser permette la lettura dei comandi(del gioco o tentativo di una
 * parola) dell'utente, paroliere o giocatore che sia.
 */
class Parser {

    /** Scanner per leggere l'input da tastiera. */
    private Scanner scanner = new Scanner(System.in);

    /** Lista dei comandi del gioco. */
    private final List<Commands> commands = new ArrayList<>();

    /** Insieme di stringhe da ignorare durante il parsing. */
    private Set<String> ignoreWords;

    /**
     * Costruttore del parser.
     *
     * @param newCommands da riconoscere
     */
    Parser(final List<Pair<String, CommandType>> newCommands) {
        this.ignoreWords = new HashSet<String>();
        try {
            init(newCommands);
        } catch (Exception exception) {
            System.out.println("Il programma non è terminato correttamente.");
            System.exit(-1);
        }

    }

    /**
     * Restituisce la lista dei comandi accettati dal parser.
     *
     * @return lista dei comandi
     */
    List<Commands> getCommands() {
        return commands;
    }

    /**
     * Il metodo init inizializza i tipi di comandi che il parser si aspetta.
     *
     * @param newCommands nuovi comandi accettati dal parser.
     * @throws Exception
     */
    private void init(final List<Pair<String, CommandType>> newCommands)
    throws Exception {
        for (Pair<String, CommandType> command : newCommands) {
            String strCommand = command.getFirst();
            String commandUpper = strCommand.toUpperCase();
            String commandLower = strCommand.toLowerCase();

            Commands newWord = new Commands(command.getSecond(), strCommand);
            newWord.setAlias(new String[] {commandLower, commandUpper});
            getCommands().add(newWord);
        }
    }

    /**
     * Il metodo parseString permette di tokenizzare la stringa letta in
     * input e di gestire l'eventuale presenza di parole da ignorare,
     * ossia parole che andranno ignorate al momento del parsing.
     * L' attuale implementazione del parser non prevede le parole da
     * ignorare, ma la funzionalità è stata aggiunta per motivi
     * di estendibilità.
     *
     * @param string      da analizzare
     * @param ignoreWords da ignorare
     * @return tokens
     */
    private static List<String> parseString(final String string,
                                            final Set<String> ignoreWords) {
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
     * con i comandi supportati dal gioco, se la stringa corrisponde ad un
     * comando, restituisce la sua posizione nella lista di comandi memorizzati,
     * che sarà utilizzata da nextCommand di Terminal per interpretare il tipo
     * di comando inserito e gestirlo di conseguenza.
     *
     * @param token    della stringa tokenizzata
     * @return posizione del comando dato in input nella lista dei comandi
     */
    private int checkForCommand(final String token) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token)) {
                return i;
            }
        }
        return -1; // posizione invalida altrimenti
    }

    /**
     * Il metodo readCommand permette il confronto del risultato del metodo
     * parse con uno qualsiasi dei comandi supportati, se essa corrisponde
     * ad uno dei comandi, lo restituisce, altrimenti restituisce null.
     *
     * @param printCommand se true stampa l'inserimento del comando,
     *                     altrimenti non stampa nulla.
     * @param gameStarted  se true stampa "Effettua un tentativo (o un
     *                     comando):", altrimenti "Inserisci un comando:".
     * @return risultato di parse.
     */
    ParserOutput readCommand(final boolean printCommand,
                                    final boolean gameStarted) {
        ParserOutput p = null;

        if (printCommand) {
            if (gameStarted) {
                System.out.println("Effettua un tentativo (o un comando):");
            } else {
                System.out.println("Inserisci un comando:");
            }
        }

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            p = this.parse(command);
            return p;
        }
        return p;
    }

    /**
     * Il metodo parse permette il confronto del risultato di parseString
     * con il risultato di checkForCommand, che restituira' l'indice appropriato
     * nella lista dei comandi.
     *
     * @param command stringa da analizzare
     * @return indice nella lista di comandi possibili.
     */
    private ParserOutput parse(final String command) {
        List<String> tokens = parseString(command, ignoreWords);
        if (!tokens.isEmpty()) {
            String firstWord = tokens.get(0);
            int ic = checkForCommand(firstWord);
            if (ic == -1) {
                if (tokens.size() == 1) {
                    String firstLower = firstWord.toUpperCase();
                    String firstUpper = firstWord.toUpperCase();
                    CommandType typeInput = CommandType.INPUT_WORD;
                    Commands inputWord = new Commands(typeInput, firstWord);

                    inputWord.setAlias(new String[] {firstLower, firstUpper});
                    return new ParserOutput(inputWord);
                }

                return null;
            }
            if (commands.get(ic).getType() == CommandType.NEW) {
                if (tokens.size() == 2) {
                    String secondWord = tokens.get(1).toLowerCase();
                    Commands input = new Commands(CommandType.NEW, secondWord);
                    return new ParserOutput(input);
                } else {
                    return null;
                }
            }
            return new ParserOutput(commands.get(ic));

        } else {
            return null;
        }
    }
}
