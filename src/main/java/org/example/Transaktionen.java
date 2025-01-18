package org.example;

import org.bson.types.ObjectId;

public class Transaktionen {
    //deklarieren alle wichtigen variablen für die transaktionen
    private ObjectId id;
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

    public Transaktionen(ObjectId id, String bezeichnung, int betrag, boolean angekommen) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.betrag = betrag;
        this.angekommen = angekommen;
    }

    // Getters and setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Transaktionen() {}

    public String toString()
    {
        return id + " " + bezeichnung + " " + betrag + "€" + " " + angekommen;
    }

}
