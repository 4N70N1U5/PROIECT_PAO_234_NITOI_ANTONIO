package models;

import static constants.Constants.VALOARE_STANDARD_CHIRIE;
import static constants.Constants.VALOARE_STANDARD_CUMPARARE;

public class Casa extends Locuinta {
    private int numarEtaje;

    public Casa(String numeClient, String prenumeClient, double discount, int suprafataUtila, int numarEtaje) {
        super(numeClient, prenumeClient, discount, suprafataUtila);
        this.numarEtaje = numarEtaje;
    }

    public int getNumarEtaje() {
        return numarEtaje;
    }

    public void setNumarEtaje(int numarEtaje) {
        this.numarEtaje = numarEtaje;
    }

    @Override
    public double calculPretChirie(int aplicareDiscount) {
        return VALOARE_STANDARD_CHIRIE * suprafataUtila * (1 - aplicareDiscount * discount / 100);
    }

    @Override
    public double calculPretCumparare(int aplicareDiscount) {
        return VALOARE_STANDARD_CUMPARARE * suprafataUtila * (1 - aplicareDiscount * discount / 100);
    }

    @Override
    public void afisareLocuinta() {
        super.afisareLocuinta();
        System.out.println("Numar etaje: " + numarEtaje);
    }

    @Override
    public String toString() {
        return "Casa{" +
                "numarEtaje=" + numarEtaje +
                "} " + super.toString();
    }
}
