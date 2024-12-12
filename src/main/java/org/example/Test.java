package org.example;

import java.util.ArrayList;

public class Test {
    public void tester(){
        Listen listen = new Listen();
        //Transaktionen transaktionen = new Transaktionen(100,"Arbeit",true);
        //listen.schreibeTransaktionInListe(transaktionen);
        ArrayList<Transaktionen> liste = listen.getList();
        String temp;
        //temp = transaktionen.toString();
        //System.out.println(temp);
    }
}
