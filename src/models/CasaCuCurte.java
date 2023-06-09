package models;

import static constants.Constante.VALOARE_STANDARD_CHIRIE;
import static constants.Constante.VALOARE_STANDARD_CUMPARARE;

public class CasaCuCurte extends Casa {
    protected int suprafataCurte;

    public CasaCuCurte(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int numarEtaje, int suprafataCurte) {
        super(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje);
        this.suprafataCurte = suprafataCurte;
    }

    public CasaCuCurte(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int idAgentie, int numarEtaje, int suprafataCurte) {
        super(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje);
        this.suprafataCurte = suprafataCurte;
    }

    public CasaCuCurte(int id, String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int idAgentie, int numarEtaje, int suprafataCurte) {
        super(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje);
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
        return "CasaCuCurte{" +
                "suprafataCurte=" + suprafataCurte +
                "} " + super.toString();
    }

    @Override
    public String toCSVFormat() {
        return super.toCSVFormat() + ", " +
                suprafataCurte;
    }

    @Override
    public String toInsertFormat() {
        return super.toInsertFormat() + ", " +
                "'" + suprafataCurte + "'";
    }
}
