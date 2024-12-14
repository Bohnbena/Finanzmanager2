package org.example;

public class MainMenu {
    Utils utils = new Utils();
    Listen liste = new Listen();
    Statistik statistik = new Statistik();

    public void start() {
        int flagbreak = 0;
        while (true) {
            utils.space(100);
            utils.ascii();
            utils.space(5);
            String userinput = utils.abfrage();
            switch (userinput) {
                case "E":
                    liste.bearbeitungsmodusliste("Einahme");
                    break;
                case "A":
                    liste.bearbeitungsmodusliste("Ausgabe");
                    break;
                case "S":
                    statistik.zeigestatisitkan();
                    break;
                case "X":
                    flagbreak = 1;
                    break;
            }
            //Programm exit
            if(flagbreak == 1)
            {
                break;
            }
        }

    }
}
