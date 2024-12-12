package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Listen {
    Utils utils = new Utils();
    JSONUtils jsonutils = new JSONUtils();
    Scanner scanner = new Scanner(System.in);

    //Deklarieren eine liste für die transaktionen
    private ArrayList<Transaktionen> List = new ArrayList<>();

    public ArrayList<Transaktionen> getList() {
        return List;
    }

    public void setList(ArrayList<Transaktionen> list) {
        List = list;
    }

    public void schreibeTransaktionInListe(Transaktionen transaktionen) {
        List.add(transaktionen);
    }

    public void listeanzeigen(ArrayList<Transaktionen> transaktionens) {
        int counter = 0;
        for (Transaktionen transaktionen : transaktionens) {
            String counterstring = Integer.toString(counter);
            System.out.println(counterstring + ". " + transaktionen);
            counter++;
        }
    }


    public void zeigestatisitkan() {
        //wie möchten wir die listen anzeigen untereinander oder nebeneinander
        //villeicht in zukunft eine einstellungs option?
        //TODO Einstellungen Option , Listen Anzeigen nebeneinander oder untereinander
        //erstmal untereinander

        while (true) {
            utils.space(50);
            //1. liste aus json laden
            ArrayList<Transaktionen> einahmeliste = jsonutils.jsontolist("Einahme");
            ArrayList<Transaktionen> ausgabeliste = jsonutils.jsontolist("Ausgabe");
            //2. zeige die listen an
            System.out.println("Einahmen:");
            listeanzeigen(einahmeliste);
            //utils.space(2);
            System.out.println("====================================");
            System.out.println("Ausgaben:");
            listeanzeigen(ausgabeliste);
            System.out.println("====================================");

            scanner.nextLine();
            //Todo abfrage

        }
    }

    public void bearbeitelisteposition() {
        //Wir fragen welche positon bearbeitet werdensoll
        System.out.println("Welche Position möchten sie bearbeiten?");
        //Weisen einen scanner zu
        String positionstring = scanner.nextLine();

        System.out.println("Möchtest du denn Eintrag komplett ändern oder nur bestimme einstellungen? K/E");
        System.out.println("K-Komplett / E-Einzelne Elemente");

        String userinput = scanner.nextLine();
        userinput.toUpperCase();
        //Formatieren in integer
        int position = Integer.parseInt(positionstring);

        if (userinput.equals("K")) {
            //machen die selbe abfrage wie beim hinzufügen und erstellen ein neues trankasktions object
            Transaktionen transaktionen = utils.transaktionenabfrage();
            //Setze neue daten in
            List.set(position, transaktionen);
        } else if (userinput.equals("E")) {
            //laden von der liste ein object um es weiter zu bearbeiten
            Transaktionen transaktionen = List.get(position);
            //bearbeiten jetzt die einzelenen einstellungen
            bearbeiteeinzelneelemtevonposition(transaktionen);
        }
    }

    public void bearbeiteeinzelneelemtevonposition(Transaktionen transaktionen) {
        String antwort = utils.abfrageinnerebearbeitung();
        antwort.toUpperCase();
        utils.space(5);
        switch (antwort) {
            case "N":
                System.out.println("Name?");
                String bezeichnung = scanner.nextLine();
                transaktionen.setBezeichnung(bezeichnung);
                break;
            case "B":
                System.out.println("Betrag?");
                String betragstring = scanner.nextLine();
                int betrag = Integer.parseInt(betragstring);
                transaktionen.setBetrag(betrag);
                break;
            case "A":
                System.out.println("True Oder False");
                String bool = scanner.nextLine();
                if (bool.equals("True")) {
                    transaktionen.setAngekommen(true);
                } else {
                    transaktionen.setAngekommen(false);
                }
                break;
            case "X":
                break;
        }
    }

    public void transaktioneingegangen() {
        System.out.println("Welche Position ist Eingegangen?");
        String userinput = scanner.nextLine();
        Transaktionen transaktionen = List.get(Integer.parseInt(userinput));
        if (transaktionen.isAngekommen()) {
            transaktionen.setAngekommen(false);
        } else {
            transaktionen.setAngekommen(true);
        }
    }

    public void bearbeitungsmodusliste(String quelle) {
        int flagbreak = 0;
        List = jsonutils.jsontolist(quelle);
        while (true) {
            //Quellen kann es nur zwei geben einahme / ausgabe
            // wir wandeln die liste in json
            utils.space(50);
            listeanzeigen(List);
            utils.space(5);

            //hollen uns die liste
            String userinput = utils.abfrageinnen(quelle);
            switch (userinput) {
                case "H":
                    //Transaktion abfrage
                    Transaktionen transaktionen = utils.transaktionenabfrage();
                    //Schreibe Transkaktion in liste
                    schreibeTransaktionInListe(transaktionen);
                    break;
                case "B":
                    //Bearbeite eine position
                    bearbeitelisteposition();
                    break;
                case "E":
                    transaktioneingegangen();
                    break;
                case "X":
                    flagbreak = 1;
                    break;
            }
            jsonutils.listtojson(List, quelle);
            if (flagbreak == 1) {
                break;
            }
        }
    }
}

