package models;

import static constants.Constante.VALOARE_STANDARD_CHIRIE;
import static constants.Constante.VALOARE_STANDARD_CUMPARARE;

public class ApartamentCuGradina extends Apartament {
    private int suprafataGradina;

    public ApartamentCuGradina(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int etaj, int suprafataGradina) {
        super(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj);
        this.suprafataGradina = suprafataGradina;
    }

    public ApartamentCuGradina(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int idAgentie, int etaj, int suprafataGradina) {
        super(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj);
        this.suprafataGradina = suprafataGradina;
    }

    public ApartamentCuGradina(int id, String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int idAgentie, int etaj, int suprafataGradina) {
        super(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj);
        this.suprafataGradina = suprafataGradina;
    }

    public int getSuprafataGradina() {
        return suprafataGradina;
    }

    public void setSuprafataGradina(int suprafataGradina) {
        this.suprafataGradina = suprafataGradina;
    }

    @Override
    public double calculPretChirie(int aplicareDiscount) {
        return VALOARE_STANDARD_CHIRIE * (suprafataUtila + 0.2 * suprafataGradina) * (1 - aplicareDiscount * discount / 100);
    }

    @Override
    public double calculPretCumparare(int aplicareDiscount) {
        return VALOARE_STANDARD_CUMPARARE * (suprafataUtila + 0.2 * suprafataGradina) * (1 - aplicareDiscount * discount / 100);
    }

    @Override
    public void afisareLocuinta() {
        super.afisareLocuinta();
        System.out.println("Suprafata gradina: " + suprafataGradina);
    }

    @Override
    public String toString() {
        return "ApartamentCuGradina{" +
                "suprafataGradina=" + suprafataGradina +
                "} " + super.toString();
    }

    @Override
    public String toCSVFormat() {
        return super.toCSVFormat() + ", " +
                suprafataGradina;
    }

    @Override
    public String toInsertFormat() {
        return super.toInsertFormat() + ", " +
                "'" + suprafataGradina + "'";
    }
}
