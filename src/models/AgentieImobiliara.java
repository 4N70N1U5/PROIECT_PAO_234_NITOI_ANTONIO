package models;

import java.util.ArrayList;
import java.util.TreeMap;

public class AgentieImobiliara {
    private String nume;
    private TreeMap<Double, ArrayList<Locuinta>> locuinte = new TreeMap<>();
    // Locuintele sunt ordonate crescator dupa pretul de cumparare.

    public AgentieImobiliara(String nume) {
        this.nume = nume;
    }

    public AgentieImobiliara(String nume, TreeMap<Double, ArrayList<Locuinta>> locuinte) {
        this.nume = nume;
        this.locuinte = locuinte;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public TreeMap<Double, ArrayList<Locuinta>> getLocuinte() {
        return locuinte;
    }

    public void setLocuinte(TreeMap<Double, ArrayList<Locuinta>> locuinte) {
        this.locuinte = locuinte;
    }

    public void afisareLocuinte() {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                locuinta.afisareLocuinta();
                System.out.println();
            }
        }
    }

    public void afisareApartamente() {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                Class<?> clasa = locuinta.getClass();

                if (clasa == Apartament.class || clasa == ApartamentDuplex.class || clasa == ApartamentCuGradina.class) {
                    locuinta.afisareLocuinta();
                    System.out.println();
                }
            }
        }
    }

    public void afisareApartamenteSimple() {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                Class<?> clasa = locuinta.getClass();

                if (clasa == Apartament.class) {
                    locuinta.afisareLocuinta();
                    System.out.println();
                }
            }
        }
    }

    public void afisareApartamenteDuplex() {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                Class<?> clasa = locuinta.getClass();

                if (clasa == ApartamentDuplex.class) {
                    locuinta.afisareLocuinta();
                    System.out.println();
                }
            }
        }
    }

    public void afisareApartamenteCuGradina() {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                Class<?> clasa = locuinta.getClass();

                if (clasa == ApartamentCuGradina.class) {
                    locuinta.afisareLocuinta();
                    System.out.println();
                }
            }
        }
    }

    public void afisareCase() {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                Class<?> clasa = locuinta.getClass();

                if (clasa == Casa.class || clasa == CasaCuCurte.class || clasa == CasaCuPiscina.class) {
                    locuinta.afisareLocuinta();
                    System.out.println();
                }
            }
        }
    }

    public void afisareCaseSimple() {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                Class<?> clasa = locuinta.getClass();

                if (clasa == Casa.class) {
                    locuinta.afisareLocuinta();
                    System.out.println();
                }
            }
        }
    }

    public void afisareCaseCuCurte() {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                Class<?> clasa = locuinta.getClass();

                if (clasa == CasaCuCurte.class) {
                    locuinta.afisareLocuinta();
                    System.out.println();
                }
            }
        }
    }

    public void afisareCaseCuPiscina() {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                Class<?> clasa = locuinta.getClass();

                if (clasa == CasaCuPiscina.class) {
                    locuinta.afisareLocuinta();
                    System.out.println();
                }
            }
        }
    }

    public void afisareLocuintePretCumparare(int aplicareDiscount) {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                locuinta.afisareLocuinta();
                System.out.println("Pret de cumparare: " + locuinta.calculPretCumparare(aplicareDiscount));
                System.out.println();
            }
        }
    }

    public void afisareLocuinteChirii(int aplicareDiscount) {
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                locuinta.afisareLocuinta();
                System.out.println("Chirie: " + locuinta.calculPretChirie(aplicareDiscount));
                System.out.println();
            }
        }
    }

    public void afisareLocuinteIndexate() {
        int index = 1;
        for (Double i : locuinte.keySet()) {
            for (Locuinta locuinta : locuinte.get(i)) {
                System.out.println("Locuinta " + index + ": ");
                index++;
                locuinta.afisareLocuinta();
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
