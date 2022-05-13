package it.uniba.app.utils;

public class Helper {
  final public static int FORMAT_LETTER_NEVER_TYPED = 0;
  final public static int FORMAT_LETTER_NOT_FOUND = 1;
  final public static int FORMAT_LETTER_FOUND_WRONG_POSITION = 2;
  final public static int FORMAT_LETTER_FOUND_RIGHT_POSITION = 3;
  
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_GREY = "\u001b[0m";

  final public static int GAME_WIN = 5;
  final public static int GAME_LOSE = 6;
  final public static int GAME_WAITING = 7;
}
