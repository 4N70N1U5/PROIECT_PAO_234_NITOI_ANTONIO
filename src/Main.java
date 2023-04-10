import models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        ApartamentCuGradina locuinta1 = new ApartamentCuGradina("Marian", "Marian", 12, Materiale.BETON_ARMAT, 100, 4, 0, 50);
//        locuinta1.afisareLocuinta();

        Apartament apartament1 = new Apartament("Mihnea", "Mihnea", 15, Materiale.CARAMIDA, 70, 3, 1);
//        apartament1.afisareLocuinta();

        Scanner scanner = new Scanner(System.in);

        TreeMap<Double, Locuinta> listaLocuinte = new TreeMap<>();
        listaLocuinte.put(locuinta1.calculPretCumparare(0), locuinta1);
        listaLocuinte.put(apartament1.calculPretCumparare(0), apartament1);

        AgentieImobiliara ai = new AgentieImobiliara(listaLocuinte);
        ai.afisareLocuinte();
        System.out.println("------------------");
        ai.afisareApartamenteSimple();
    }
}