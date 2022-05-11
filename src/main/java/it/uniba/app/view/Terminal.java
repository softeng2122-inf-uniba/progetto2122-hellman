package it.uniba.app.view;

import java.util.*;
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

    public Terminal()
    {
        super();

        // Aggiungere i comandi accettati dal parser
        commands.add(new Pair<String, CommandType>("/help", CommandType.HELP));

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
        }else{
            CommandType type = p.getCommand().getType();
            
            switch(type)
            {
                case HELP:
                    out.println(help());
                    break;

                default:
                    break;
            }
        }

    }

    /**
     * Restituisce la stringa del comando di help
     * 
     * @return comando di help
     */
    private String help()
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
}
