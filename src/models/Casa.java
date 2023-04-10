package models;

import static constants.Constants.VALOARE_STANDARD_CHIRIE;
import static constants.Constants.VALOARE_STANDARD_CUMPARARE;

public class Casa extends Locuinta {
    private int suprafataCurte;

    public Casa(String numeClient, String prenumeClient, Dezvoltator dezvoltator, Arhitect arhitect, int suprafataUtila, double discount, int suprafataCurte) {
        super(numeClient, prenumeClient, dezvoltator, arhitect, suprafataUtila, discount);
        this.suprafataCurte = suprafataCurte;
    }

    public int getSuprafataCurte() {
        return suprafataCurte;
    }

    public void setSuprafataCurte(int suprafataCurte) {
        this.suprafataCurte = suprafataCurte;
    }

    @Override
    public double calculPretChirie(int aplicareDiscount) {
        return VALOARE_STANDARD_CHIRIE * (suprafataUtila + 0.2 * suprafataCurte) * (1 - aplicareDiscount * discount / 100);
    }

    @Override
    public double calculPretCumparare(int aplicareDiscount) {
        return VALOARE_STANDARD_CUMPARARE * (suprafataUtila + 0.2 * suprafataCurte) * (1 - aplicareDiscount * discount / 100);
    }

    @Override
    public void afisareLocuinta() {
        super.afisareLocuinta();
        System.out.println("Suprafata curte: " + suprafataCurte);
    }

    @Override
    public String toString() {
        return "Casa{" +
                "suprafataCurte=" + suprafataCurte +
                "} " + super.toString();
    }
}
