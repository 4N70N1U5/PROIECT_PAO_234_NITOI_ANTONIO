package models;

public class Duplex extends Apartament {
    private int numarEtaje;

    public Duplex(String numeClient, String prenumeClient, Dezvoltator dezvoltator, Arhitect arhitect, int suprafataUtila, double discount, int etaj, int numarEtaje) {
        super(numeClient, prenumeClient, dezvoltator, arhitect, suprafataUtila, discount, etaj);
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
