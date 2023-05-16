package models;

import static constants.Constante.VALOARE_STANDARD_CHIRIE;
import static constants.Constante.VALOARE_STANDARD_CUMPARARE;

public class Apartament extends Locuinta {
    protected int etaj;

    public Apartament(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int etaj) {
        super(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere);
        this.etaj = etaj;
    }

    public int getEtaj() {
        return etaj;
    }

    public void setEtaj(int etaj) {
        this.etaj = etaj;
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
        System.out.println("Etaj: " + etaj);
    }

    @Override
    public String toString() {
        return "Apartament{" +
                "etaj=" + etaj +
                "} " + super.toString();
    }

    @Override
    public String toCSVFormat() {
        return super.toCSVFormat() + ", " +
                etaj;
    }
}
