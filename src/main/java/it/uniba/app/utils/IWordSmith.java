package it.uniba.app.utils;

/**
 * Interfaccia che serve per i metodi che verranno implementati sia nel paroliere (WordSmith) ma anche su UserManager
 */
public interface IWordSmith {
    /** 
     * Metodo che serve per impostare la parola
     * 
     * @param word parola da impostare.
    */
    public void setSecretWord(String word) throws WrongWordException;

}
