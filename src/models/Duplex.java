package models;

public class Duplex extends Apartament {
    private int numarEtaje;

    public Duplex(String numeClient, String prenumeClient, double discount, int suprafataUtila, int etaj, int numarEtaje) {
        super(numeClient, prenumeClient, discount, suprafataUtila, etaj);
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
        return "Duplex{" +
                "numarEtaje=" + numarEtaje +
                "} " + super.toString();
    }
}
