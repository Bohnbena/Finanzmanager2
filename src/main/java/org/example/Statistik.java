package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Statistik {
    Utils utils = new Utils();
    JSONUtils jsonutils = new JSONUtils();
    Scanner scanner = new Scanner(System.in);
    Listen list = new Listen();
    Sortierung sortieren = new Sortierung();

    public void zeigestatisitkan() {
        //wir zeigen am anfang die standard statistik
        //markieren das mit einem int flag
        utils.space(50);
        int flagbreak = 0;
        int anfangstatisitk = 0;
        while (true) {
            if (anfangstatisitk == 0) {
                standartansicht(1);
                //haben wir jetzt einmal geladen brauchen wir ja jetzt nicht mehr
                anfangstatisitk = 1;
            }
            //abfrage
            utils.space(2);
            int anwser = utils.statistikabfrage();
            utils.space(50);
            switch (anwser) {
                case 1, 2, 3:
                    standartansicht(anwser);
                    break;
                case 4:
                    //Exit
                    flagbreak = 1;
                    break;
            }
            if (flagbreak == 1) {
                break;
            }
        }
    }

    public void standartansicht(int option) {
        //1. liste aus json laden
        ArrayList<Transaktionen> einahmeliste = jsonutils.jsontolist("Einahme");
        ArrayList<Transaktionen> ausgabeliste = jsonutils.jsontolist("Ausgabe");
        //Sortiere nach dem höchstem betrag Insertion Sort
        einahmeliste = sortieren.inserationsort(einahmeliste);
        ausgabeliste = sortieren.inserationsort(ausgabeliste);

        //Sortierung welche liste zeigen wir denn an?
        if (option != 1) {
            switch (option) {
                case 2:
                    einahmeliste = sortieren.nurangekommen(einahmeliste);
                    ausgabeliste = sortieren.nurangekommen(ausgabeliste);
                    break;
                case 3:
                    einahmeliste = sortieren.nichtangekommen(einahmeliste);
                    ausgabeliste = sortieren.nichtangekommen(ausgabeliste);
                    break;
            }
        }

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
    }
}
