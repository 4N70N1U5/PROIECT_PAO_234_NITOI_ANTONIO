package models;

import java.util.ArrayList;
import java.util.TreeMap;

public class AgentieImobiliara {
    private TreeMap<Double, Locuinta> locuinte;

    public AgentieImobiliara() {
    }

    public AgentieImobiliara(TreeMap<Double, Locuinta> locuinte) {
        this.locuinte = locuinte;
    }

    public TreeMap<Double, Locuinta> getLocuinte() {
        return locuinte;
    }

    public void setLocuinte(TreeMap<Double, Locuinta> locuinte) {
        this.locuinte = locuinte;
    }

    public void afisareLocuinte() {
        for (Double i : locuinte.keySet()) {
            locuinte.get(i).afisareLocuinta();
            System.out.println();
        }
    }

    public void afisareApartamente() {
        for (Double i : locuinte.keySet()) {
            Class<?> clasa = this.locuinte.get(i).getClass();

            if (clasa == Apartament.class || clasa == Duplex.class || clasa == ApartamentCuGradina.class) {
                locuinte.get(i).afisareLocuinta();
                System.out.println();
            }
        }
    }

    public void afisareApartamenteSimple() {
        for (Double i : locuinte.keySet()) {
            Class<?> clasa = this.locuinte.get(i).getClass();

            if (clasa == Apartament.class) {
                locuinte.get(i).afisareLocuinta();
                System.out.println();
            }
        }
    }

    public void afisareApartamenteDuplex() {
        for (Double i : locuinte.keySet()) {
            Class<?> clasa = this.locuinte.get(i).getClass();

            if (clasa == Duplex.class) {
                locuinte.get(i).afisareLocuinta();
                System.out.println();
            }
        }
    }

    public void afisareApartamenteCuGradina() {
        for (Double i : locuinte.keySet()) {
            Class<?> clasa = this.locuinte.get(i).getClass();

            if (clasa == ApartamentCuGradina.class) {
                locuinte.get(i).afisareLocuinta();
                System.out.println();
            }
        }
    }

    public void afisareCase() {
        for (Double i : locuinte.keySet()) {
            Class<?> clasa = this.locuinte.get(i).getClass();

            if (clasa == Casa.class) {
                locuinte.get(i).afisareLocuinta();
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
        return "AgentieImobiliara{" +
                "locuinte=" + locuinte +
                '}';
    }
}
