package models;

import java.util.ArrayList;

public class AgentieImobiliara {
    private ArrayList<Locuinta> locuinte;

    public AgentieImobiliara() {
    }

    public AgentieImobiliara(ArrayList<Locuinta> locuinte) {
        this.locuinte = locuinte;
    }

    public void afisareLocuinte() {
        for (int i = 0; i < this.locuinte.size(); i++) {
            locuinte.get(i).afisareLocuinta();
            System.out.println();
        }
    }

    public void afisareApartamente() {
        for (int i = 0; i < this.locuinte.size(); i++) {
            Class<?> clasa = this.locuinte.get(i).getClass();

            if (clasa == Apartament.class || clasa == Duplex.class || clasa == ApartamentCuGradina.class) {
                locuinte.get(i).afisareLocuinta();
                System.out.println();
            }
        }
    }

    public void afisareApartamenteSimple() {
        for (int i = 0; i < this.locuinte.size(); i++) {
            Class<?> clasa = this.locuinte.get(i).getClass();

            if (clasa == Apartament.class) {
                locuinte.get(i).afisareLocuinta();
                System.out.println();
            }
        }
    }

    public void afisareApartamenteDuplex() {
        for (int i = 0; i < this.locuinte.size(); i++) {
            Class<?> clasa = this.locuinte.get(i).getClass();

            if (clasa == Duplex.class) {
                locuinte.get(i).afisareLocuinta();
                System.out.println();
            }
        }
    }

    public void afisareApartamenteCuGradina() {
        for (int i = 0; i < this.locuinte.size(); i++) {
            Class<?> clasa = this.locuinte.get(i).getClass();

            if (clasa == ApartamentCuGradina.class) {
                locuinte.get(i).afisareLocuinta();
                System.out.println();
            }
        }
    }

    public void afisareCase() {
        for (int i = 0; i < this.locuinte.size(); i++) {
            Class<?> clasa = this.locuinte.get(i).getClass();

            if (clasa == Casa.class) {
                locuinte.get(i).afisareLocuinta();
                System.out.println();
            }
        }
    }
}
