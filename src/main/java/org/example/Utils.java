package org.example;

import java.util.Scanner;

public class Utils {
    Scanner scanner = new Scanner(System.in);


    public void space(int space)
    {
        for(int i=0;i<space;i++)
        {
            System.out.println(" ");
        }
    }

    public void ascii()
    {
        System.out.println(" ______   __     __   __     ______     __   __     ______     __    __     ______     __   __     ______     ______     ______     ______    ");
        System.out.println("/\\  ___\\ /\\ \\   /\\ \"-.\\ \\   /\\  __ \\   /\\ \"-.\\ \\   /\\___  \\   /\\ \"-./  \\   /\\  __ \\   /\\ \"-.\\ \\   /\\  __ \\   /\\  ___\\   /\\  ___\\   /\\  == \\   ");
        System.out.println("\\ \\  __\\ \\ \\ \\  \\ \\ \\-.  \\  \\ \\  __ \\  \\ \\ \\-.  \\  \\/_/  /__  \\ \\ \\-./\\ \\  \\ \\  __ \\  \\ \\ \\-.  \\  \\ \\  __ \\  \\ \\ \\__ \\  \\ \\  __\\   \\ \\  __<   ");
        System.out.println(" \\ \\_\\    \\ \\_\\  \\ \\_\\\"\\_\\  \\ \\_\\ \\_\\  \\ \\_\\\"\\_\\   /\\_____\\  \\ \\_\\ \\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\\"\\_\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\ ");
        System.out.println("  \\/_/     \\/_/   \\/_/ \\/_/   \\/_/\\/_/   \\/_/ \\/_/   \\/_____/   \\/_/  \\/_/   \\/_/\\/_/   \\/_/ \\/_/   \\/_/\\/_/   \\/_____/   \\/_____/   \\/_/ /_/ ");
    }

    public String abfrage(){
        System.out.println("Willkommen beim Finanzmanager ,Wähle eine Option: ");
        System.out.println("E-Einkommen");
        System.out.println("A-Ausgaben");
        System.out.println("S-Statisitk");
        System.out.println("X-Exit");
        String userinput = scanner.nextLine();
        return userinput.toUpperCase();
    }

    public String abfrageinnen(String quelle)
    {
        System.out.println("Bearbeitungsmodus: " + quelle);
        System.out.println("H-Hinzufügen");
        System.out.println("B-Bearbeiten");
        System.out.println("E-Eingegangen");
        System.out.println("X-Exit");
        String userinput = scanner.nextLine();
        return userinput.toUpperCase();
    }

    public String abfrageinnerebearbeitung()
    {
        space(50);
        System.out.println("Was genau möchten sie bearbeiten");
        System.out.println("N-Name");
        System.out.println("B-Betrag");
        System.out.println("A-Angekommen");
        System.out.println("X-Exit");
        String userinput = scanner.nextLine();
        return userinput.toUpperCase();
    }

    public Transaktionen transaktionenabfrage()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name");
        String name = scanner.nextLine();
        System.out.println("Betrag");
        int betrag = Integer.parseInt(scanner.nextLine());
        return new Transaktionen(name,betrag,false);
    }
}
