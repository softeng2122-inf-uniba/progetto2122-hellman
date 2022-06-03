package it.uniba.app;

import it.uniba.app.view.Terminal;

/**
 * Main class of the application.
 */
public final class App {

    /** Costruttore privato. */
    private App() {
    }

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        new Terminal(args);
    }
}
