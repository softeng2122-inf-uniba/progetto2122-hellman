package it.uniba.app.view;

import java.util.*;
import it.uniba.app.utils.*;
import it.uniba.app.utils.CommandType;
import java.io.PrintStream;

/**
 * Classe front-end che permette di dialogare con l'utente a linea di comando
 */
public class Terminal extends Viewer{

    // Parser utilizzato per il riconoscimento dei comandi accettati dal gioco
    private Parser parser;

    // Lista di comandi accettati dal gioco
    List<Pair<String, CommandType>> commands = new ArrayList<>();

    public Terminal()
    {
        super();

        // Aggiungere i comandi accettati dal parser

        parser = new Parser(commands);
        readInput();
    }

    /**
     * Metodo che invoca l'inserimento di nuovi comandi da tastiera
     */
    protected void readInput()
    {
        while (true){
            nextCommand(parser.readCommand(), System.out);
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
        }

    }
}
