package models;

import static constants.Constante.VALOARE_STANDARD_CHIRIE;
import static constants.Constante.VALOARE_STANDARD_CUMPARARE;

public class Casa extends Locuinta {
    protected int numarEtaje;

    public Casa(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int numarEtaje) {
        super(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere);
        this.numarEtaje = numarEtaje;
    }

    public Casa(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int idAgentie, int numarEtaje) {
        super(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie);
        this.numarEtaje = numarEtaje;
    }

    public Casa(int id, String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int idAgentie, int numarEtaje) {
        super(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie);
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

    @Override
    public String toCSVFormat() {
        return super.toCSVFormat() + ", " +
                numarEtaje;
    }

    @Override
    public String toInsertFormat() {
        return super.toInsertFormat() + ", " +
                "'" + numarEtaje + "'";
    }
}
