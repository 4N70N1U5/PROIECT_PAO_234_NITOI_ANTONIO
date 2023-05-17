package models;

import static constants.Constante.VALOARE_STANDARD_CHIRIE;
import static constants.Constante.VALOARE_STANDARD_CUMPARARE;

public class CasaCuPiscina extends CasaCuCurte {
    private int lungimePiscina;
    private int latimePiscina;

    public CasaCuPiscina(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int numarEtaje, int suprafataCurte, int lungimePiscina, int latimePiscina) {
        super(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, suprafataCurte);
        this.lungimePiscina = lungimePiscina;
        this.latimePiscina = latimePiscina;
    }

    public int getLungimePiscina() {
        return lungimePiscina;
    }

    public void setLungimePiscina(int lungimePiscina) {
        this.lungimePiscina = lungimePiscina;
    }

    public int getLatimePiscina() {
        return latimePiscina;
    }

    public void setLatimePiscina(int latimePiscina) {
        this.latimePiscina = latimePiscina;
    }

    @Override
    public double calculPretChirie(int aplicareDiscount) {
        return VALOARE_STANDARD_CHIRIE * (suprafataUtila + 0.2 * suprafataCurte + 0.1 * (lungimePiscina * latimePiscina)) * (1 - aplicareDiscount * discount / 100);
    }

    @Override
    public double calculPretCumparare(int aplicareDiscount) {
        return VALOARE_STANDARD_CUMPARARE * (suprafataUtila + 0.2 * suprafataCurte  + 0.1 * (lungimePiscina * latimePiscina)) * (1 - aplicareDiscount * discount / 100);
    }

    @Override
    public void afisareLocuinta() {
        super.afisareLocuinta();
        System.out.println("Dimensiune piscina: " + lungimePiscina + " x " + latimePiscina);
    }

    @Override
    public String toString() {
        return "CasaCuPiscina{" +
                "lungimePiscina=" + lungimePiscina +
                ", latimePiscina=" + latimePiscina +
                "} " + super.toString();
    }

    @Override
    public String toCSVFormat() {
        return super.toCSVFormat() + ", " +
                lungimePiscina + ", " +
                latimePiscina;
    }

    @Override
    public String toInsertFormat() {
        return super.toInsertFormat() + ", " +
                "'" + lungimePiscina + "', " +
                "'" + latimePiscina + "'";
    }
}
