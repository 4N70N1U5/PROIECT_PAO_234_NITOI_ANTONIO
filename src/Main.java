import models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Date today = new Date();
        Dezvoltator dezvoltator1 = new Dezvoltator("Dezv SRL", today);

        Arhitect arhitect1 = new Arhitect("Andrei", "Mihai");

        ApartamentCuGradina locuinta1 = new ApartamentCuGradina("Marian", dezvoltator1, arhitect1, 100, 12, 0, 50);
//        locuinta1.afisareLocuinta();

        Apartament apartament1 = new Apartament("Mihnea", dezvoltator1, arhitect1, 70, 15, 1);
//        apartament1.afisareLocuinta();

        Scanner scanner = new Scanner(System.in);

        ArrayList<Locuinta> listaLocuinte = new ArrayList<>();
        listaLocuinte.add(locuinta1);
        listaLocuinte.add(apartament1);

        AgentieImobiliara ai = new AgentieImobiliara(listaLocuinte);
        ai.afisareLocuinte();
        System.out.println("------------------");
        ai.afisareApartamente();
    }
}