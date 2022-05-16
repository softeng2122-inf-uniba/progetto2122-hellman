package it.uniba.app.view;

/**
 * <<noECB>>
 * Classe che gestisce l'output del parser
 */
class ParserOutput {

    /** Comando di cui gestire l'output */
    private Commands command;

    /**
     * Costruttore dell'output del parser
     * 
     * @param command
     */
    public ParserOutput(Commands command) {
        this.command = command;
    }

    /**
     * Restituisce il comando
     * 
     * @return command
     */
     public Commands getCommand() {
        return command;
    }

    /**
     * Imposta il comando
     * 
     * @param command
     */
    public void setCommand(Commands command) {
        this.command = command;
    }

}
