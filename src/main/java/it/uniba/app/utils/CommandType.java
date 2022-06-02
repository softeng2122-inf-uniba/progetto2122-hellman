package it.uniba.app.utils;

/**
 * <<noECB>>
 * Classe enumerativa per la tipologia dei comandi.
 */
public enum CommandType {
  /** Comando di help. */
  HELP,
  /** Comando di uscita dall'app. */
  EXIT_APP,
  /** Comando di consenso. */
  EXIT_YES,
  /** Comando di rifiuto. */
  EXIT_NO,
  /** Comando per impostare la parola segreta. */
  NEW,
  /** Comando per mostrare la parola segreta. */
  SHOW,
  /** Comando per iniziare la partita. */
  START_GAME,
  /** Comando di uscita dalla partita. */
  EXIT_GAME,
  /** Comando di input della parola. */
  INPUT_WORD
}
