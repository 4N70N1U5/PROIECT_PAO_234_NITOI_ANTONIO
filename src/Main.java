import models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Date today = new Date();
        Dezvoltator dezvoltator1 = new Dezvoltator("Dezv SRL", today);

        Arhitect arhitect1 = new Arhitect("Andrei", "Mihai");

        ApartamentCuGradina locuinta1 = new ApartamentCuGradina("Marian", "Marian", dezvoltator1, arhitect1, 100, 12, 0, 50);
//        locuinta1.afisareLocuinta();

        Apartament apartament1 = new Apartament("Mihnea", "Mihnea", dezvoltator1, arhitect1, 70, 15, 1);
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