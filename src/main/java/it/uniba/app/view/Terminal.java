package it.uniba.app.view;

import it.uniba.app.models.Word;
import it.uniba.app.utils.CommandType;
import it.uniba.app.utils.Helper;
import it.uniba.app.utils.Pair;
import it.uniba.app.utils.GameException;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <<Boundary>>
 * Classe front-end che permette di dialogare con l'utente a linea di comando.
 */
public class Terminal extends Viewer {

    /** Parser utilizzato per il riconoscimento dei comandi del gioco. */
    private Parser parser;

    /** Lista di comandi accettati dal gioco. */
    private List<Pair<String, CommandType>> commands = new ArrayList<>();

    /**
     * Costruttore di terminal.
     * @param flags passato all'apertura del terminale
     */
    public Terminal(final String[] flags) {
        super();

        // Aggiungere i comandi accettati dal parser
        commands.add(newCmd("/help", CommandType.HELP));
        commands.add(newCmd("/esci", CommandType.EXIT_APP));
        commands.add(newCmd("/gioca", CommandType.START_GAME));
        commands.add(newCmd("si", CommandType.EXIT_YES));
        commands.add(newCmd("no", CommandType.EXIT_NO));
        commands.add(newCmd("/nuova", CommandType.NEW));
        commands.add(newCmd("/mostra", CommandType.SHOW));
        commands.add(newCmd("/abbandona", CommandType.EXIT_GAME));

        parser = new Parser(commands);

        System.out.println(getWelcome());

        }

        readInput();
    }

    /**
     * Restituisce il nuovo comando creato.
     *
     * @param command stringa del comando
     * @param cType identificativo del comando
     * @return comando creato
     */
    private Pair<String, CommandType> newCmd(final String command,
                                            final CommandType cType) {
        return new Pair<String, CommandType>(command, cType);
    }

    /**
     * Metodo che invoca l'inserimento di nuovi comandi da tastiera.
     */
    void readInput() {
        while (true) {
            boolean isGameStarted = getUserManager().isGameStarted();

            nextCommand(parser.readCommand(true, isGameStarted), System.out);
        }
    }

    /**
     * Metodo che gestisce il comando inserito dall'utente da terminale.
     *
     * @param p   risultato del parser
     * @param out canale di output
     */
    private void nextCommand(final ParserOutput p, final PrintStream out) {

        if (p == null) {
            out.println("Non ho capito! Prova con un altro comando.");
        } else {
            CommandType type = p.getCommand().getType();

            switch (type) {
                case HELP:
                    out.println(help());
                    break;

                case EXIT_APP:
                    closeApp(out);
                    break;

                case INPUT_WORD:
                    out.println(makeTry(p.getCommand().getName()));
                    break;

                case NEW:
                    out.println(setSecretWord(p.getCommand().getName()));
                    break;

                case SHOW:
                    out.println(printSecretWord());
                    break;

                case START_GAME:
                    out.println(startGame());
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
     * Metodo per l'avvio della partita nel caso in cui la
     * parola segreta sia stata impostata.
     * @return messaggio di avvio partita.
     */
    private String startGame() {
        String str = "Avvio partita...\n";

        try {
            getUserManager().startGame();
        } catch (GameException e) {
            str += e.getMessage() + "\n\n";
            return str;
        }

        str += printMatrix(new ArrayList<Word>()) + "\n";
        return str;
    }

    /**
     * Metodo per l'abbandono della partita nel caso in cui si
     * decide di non voler più giocare.
     *
     * @param out Canale di output.
     */
    private void backGame(final PrintStream out) {
        out.println("Sei sicuro di abbandonare il gioco in corso? (si/no)");

        boolean isGameStarted = getUserManager().isGameStarted();
        ParserOutput po = parser.readCommand(false, isGameStarted);

        if (po != null) {
            CommandType type = po.getCommand().getType();
            switch (type) {
                case EXIT_YES:
                    try {
                        getUserManager().backGame();
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
     * Restituisce la stringa del comando di help.
     * @return comando di help
     */
    private String help() {
        String str = "";

        try {
            str = Helper.carica(Helper.PATH_HELP_STRING);
        } catch (Exception e) {
            str = "Help non trovato\n";
        }
        return str;
    }

    /**
     * Gestisce la chiusura dell'app.
     *
     * @param out canale di output
     */
    private void closeApp(final PrintStream out) {
        out.println("Sei sicuro di uscire dall'app? (si/no)");

        boolean isGameStarted = getUserManager().isGameStarted();

        ParserOutput po = parser.readCommand(false, isGameStarted);
        if (po != null) {
            CommandType type = po.getCommand().getType();
            switch (type) {
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
     * Gestisce il tentativo della parola.
     *
     * @param word per effettuare il tentativo
     * @return stringa della risposta del tentativo
     */
    private String makeTry(final String word) {
        String str = "";
        Pair<Integer, List<Word>> res;
        try {
            res = getUserManager().makeTry(word);
        } catch (GameException e) {
            return e.getMessage();
        }

        switch (res.getFirst()) {
            case Helper.GAME_WIN:
                str += printMatrix(res.getSecond());
                str += "Parola segreta indovinata\nNumero tentativi: ";
                str += res.getSecond().size();
                break;

            case Helper.GAME_LOSE:
                int lastIndex = res.getSecond().size() - 1;
                String secretWord = res.getSecond().get(lastIndex).getWord();
                res.getSecond().remove(lastIndex);

                str += printMatrix(res.getSecond());
                str += "Hai raggiunto il numero massimo di tentativi.\n"
                        + "La parola segreta è: " + secretWord;
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
    private String setSecretWord(final String word) {
        String str = "";

        try {
            getUserManager().setSecretWord(word);
            str += "La parola è stata inserita correttamente.";
        } catch (GameException w) {
            str += w.getMessage();
        }

        return str;
    }

    /**
     * Metodo che permette di stampare la parola segreta.
     * @return stringa della parola segreta, se presente
     */
    private String printSecretWord() {
        String str = "";

        if (getUserManager().getSecretWord().compareTo("") != 0) {
            String secretWord = getUserManager().getSecretWord();
            str = "La parola segreta è " + secretWord;
        } else {
            str = "Non è stata inserita alcuna parola segreta.";
        }

        return str;
    }

    /**
     * Restituisce la matrice delle parole inserite con i rispettivi
     * colori nelle lettere.
     *
     * @param words tentativi effettuati
     * @return matrice dei tentativi colorata
     */
    private String printMatrix(final List<Word> words) {
        String str = "\n";

        for (int i = 0; i < Helper.MAX_TRYS; i++) {
            boolean emptyWord = false;
            Word word = null;
            try {
                word = words.get(i);
            } catch (IndexOutOfBoundsException e) {
                emptyWord = true;
            }

            for (int j = 0; j < Helper.MAX_LETTERS; j++) {
                String cell = Helper.ANSI_BLACK + " _ ";

                if (!emptyWord) {
                    char c = word.getWord().charAt(j);
                    cell = getCharColored(c, word.getFormat().get(j));
                }

                str += Helper.ANSI_GREY + cell + Helper.ANSI_RESET;
            }
            str += "\n";
        }

        return str;
    }

    /**
     * Restituisce il carattere colorato del formato passato in input.
     *
     * @param c      carattere da colorare
     * @param format formato del colore
     * @return carattere colorato
     */
    private String getCharColored(final char c, final int format) {
        String str = "";
        switch (format) {
            case Helper.FORMAT_LETTER_NOT_FOUND:
                str += Helper.ANSI_GREY + Helper.ANSI_BLACK + " " + c
                        + " " + Helper.ANSI_RESET;
                break;

            case Helper.FORMAT_LETTER_FOUND_RIGHT_POSITION:
                str += Helper.ANSI_GREEN + Helper.ANSI_BLACK + " " + c
                        + " " + Helper.ANSI_RESET;
                break;

            case Helper.FORMAT_LETTER_FOUND_WRONG_POSITION:
                str += Helper.ANSI_YELLOW + Helper.ANSI_BLACK + " " + c
                        + " " + Helper.ANSI_RESET;
                break;

            default:
                break;
        }

        return str;
    }

    /**
     * Metodo che ritorna una stringa contenente il Welcome dell'app.
     * @return stringa contenente il Welcome dell'app.
     */
    private String getWelcome() {
        String welcomeString;

        try {
            welcomeString = Helper.carica(Helper.PATH_WELCOME_STRING);
        } catch (Exception e) {
            welcomeString = "Benvenuto in Wordle! Digita /help.\n";
        }

        return welcomeString;
    }
}
