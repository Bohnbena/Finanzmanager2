package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Statistik {
    Utils utils = new Utils();
    JSONUtils jsonutils = new JSONUtils();
    Scanner scanner = new Scanner(System.in);
    Listen list = new Listen();

    public void zeigestatisitkan() {
        //wir zeigen am anfang die standard statistik
        //markieren das mit einem int flag
        utils.space(50);
        int flagbreak = 0;
        int anfangstatisitk = 0;
        while (true) {
            if (anfangstatisitk == 0) {
                standartansicht();
                //haben wir jetzt einmal geladen brauchen wir ja jetzt nicht mehr
                anfangstatisitk = 1;
            }
            //abfrage
            utils.space(2);
            int anwser = utils.statistikabfrage();
            utils.space(50);
            switch (anwser) {
                case 1:
                    standartansicht();
                    break;
                case 2:
                    //Eingegangen
                    break;
                case 3:
                    //Offen
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

    public void standartansicht() {
        //1. liste aus json laden
        ArrayList<Transaktionen> einahmeliste = jsonutils.jsontolist("Einahme");
        ArrayList<Transaktionen> ausgabeliste = jsonutils.jsontolist("Ausgabe");
        //Sortiere nach dem höchstem betrag Insertion Sort
        einahmeliste = list.inserationsort(einahmeliste);
        ausgabeliste = list.inserationsort(ausgabeliste);

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
