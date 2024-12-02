package org.example;

import java.net.BindException;
import java.util.Scanner;

public class Transaktionen {
    //deklarieren alle wichtigen variablen für die transaktionen
    private int betrag;
    private String bezeichnung;
    private boolean angekommen = false;

    // Restlichen getter und setter
    public boolean isAngekommen() {
        return angekommen;
    }

    public void setAngekommen(boolean angekommen) {
        this.angekommen = angekommen;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getBetrag() {
        return betrag;
    }

    public void setBetrag(int betrag) {
        this.betrag = betrag;
    }

    public Transaktionen(String bezeichnung,int betrag, boolean angekommen) {
        this.bezeichnung = bezeichnung;
        this.betrag = betrag;
        this.angekommen = angekommen;
    }

    public Transaktionen() {}

    public String toString()
    {
        return  bezeichnung + " " + betrag + "€" + " " + angekommen;
    }

}
