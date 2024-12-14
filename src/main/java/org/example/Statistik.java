package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Statistik {
    Utils utils = new Utils();
    JSONUtils jsonutils = new JSONUtils();
    Scanner scanner = new Scanner(System.in);
    Listen list = new Listen();

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
            list.listeanzeigen(einahmeliste);
            //utils.space(2);
            System.out.println("====================================");
            System.out.println("Ausgaben:");
            list.listeanzeigen(ausgabeliste);
            System.out.println("====================================");
            //Allgemeine Statistik
            //Holen uns die summen von denn jeweiligen listen
            int summeeinahme = list.summeliste(einahmeliste);
            int summeausgabe = list.summeliste(ausgabeliste);
            System.out.println("Ausgaben: " + summeausgabe + "€" + " ----------- " + "Einahmen: " + summeeinahme + "€");
            scanner.nextLine();
            //Todo abfrage
        }
    }
}
