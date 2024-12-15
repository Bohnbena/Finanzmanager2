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

    public int summeliste(ArrayList<Transaktionen> list) {
        int summe = 0;
        for (Transaktionen transaktionen : list) {
            summe += transaktionen.getBetrag();
        }
        return summe;
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

    public ArrayList<Transaktionen> inserationsort(ArrayList<Transaktionen> list) {
        //gehen die ganze liste durch um die transaktionen zu bekommen
        for (int i = 1; i < list.size(); i++) {
            //holen uns position 1
            Transaktionen transaktionen = list.get(i);
            //j i-1 bsp 0 position
            int j = i - 1;
            // solange pos 0 gleich oder größer 0 und j betrag > i betrag
            while (j >= 0 && list.get(j).getBetrag() < transaktionen.getBetrag()) {
                list.set(j + 1, list.get(j));
                j--;
            }
            // Füge das Objekt an der richtigen Position ein
            list.set(j + 1, transaktionen);
        }
        return list;
    }
}

