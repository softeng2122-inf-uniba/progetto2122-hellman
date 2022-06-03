package it.uniba.app.view;

/**
 * <<noECB>>
 * Classe che gestisce l'output del parser.
 */
class ParserOutput {

    /** Comando di cui gestire l'output. */
    private Commands command;

    /**
     * Costruttore dell'output del parser.
     *
     * @param newCommand
     */
    ParserOutput(final Commands newCommand) {
        this.command = newCommand;
    }

    /**
     * Restituisce il comando.
     *
     * @return command
     */
    public Commands getCommand() {
        return command;
    }

    /**
     * Imposta il comando.
     *
     * @param newCommand
     */
    public void setCommand(final Commands newCommand) {
        this.command = newCommand;
    }

}
