package org.example;

import java.util.ArrayList;
import java.util.stream.Collectors;

//Klasse für sotrierungsfunktionen von listen
public class Sortierung {

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

    public ArrayList<Transaktionen> nurangekommen(ArrayList<Transaktionen> list) {
        ArrayList<Transaktionen> gefiltert = new ArrayList<>(list.stream()
                .filter(t -> t.isAngekommen())
                .collect(Collectors.toList()));
        return gefiltert;
    }

    public ArrayList<Transaktionen> nichtangekommen(ArrayList<Transaktionen> list) {
        ArrayList<Transaktionen> gefiltert = new ArrayList<>(list.stream()
                .filter(t -> !t.isAngekommen())
                .collect(Collectors.toList()));
        return gefiltert;
    }

}
