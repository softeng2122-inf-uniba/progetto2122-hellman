package it.uniba.app.utils;

public class Pair<I, O> {
    private I first;
    private O second;

    public Pair(){

    }

    public Pair(I first, O second){
        this.first = first;
        this.second = second;
    }

    public I getFirst(){
        return first;
    }

    public O getSecond(){
        return second;
    }

    public void setFirst(I first){
        this.first = first;
    }

    public void setSecond(O second){
        this.second = second;
    }

    public String toString(){
        return first + " " + second;
    }
}
