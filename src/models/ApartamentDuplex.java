package models;

public class ApartamentDuplex extends Apartament {
    private int numarEtaje;

    public ApartamentDuplex(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int etaj, int numarEtaje) {
        super(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj);
        this.numarEtaje = numarEtaje;
    }

    public ApartamentDuplex(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int idAgentie, int etaj, int numarEtaje) {
        super(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj);
        this.numarEtaje = numarEtaje;
    }

    public ApartamentDuplex(int id, String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int idAgentie, int etaj, int numarEtaje) {
        super(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj);
        this.numarEtaje = numarEtaje;
    }

    public int getNumarEtaje() {
        return numarEtaje;
    }

    public void setNumarEtaje(int numarEtaje) {
        this.numarEtaje = numarEtaje;
    }

    @Override
    public void afisareLocuinta() {
        super.afisareLocuinta();
        System.out.println("Numar etaje: " + numarEtaje);
    }

    @Override
    public String toString() {
        return "ApartamentDuplex{" +
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
