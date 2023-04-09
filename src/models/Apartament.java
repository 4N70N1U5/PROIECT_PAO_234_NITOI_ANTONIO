package models;

public class Apartament extends Locuinta {
    private int etaj;

    public Apartament(String numeClient, Dezvoltator dezvoltator, int suprafataUtila, double discount, int etaj) {
        super(numeClient, dezvoltator, suprafataUtila, discount);
        this.etaj = etaj;
    }

    public int getEtaj() {
        return etaj;
    }

    public void setEtaj(int etaj) {
        this.etaj = etaj;
    }
}
