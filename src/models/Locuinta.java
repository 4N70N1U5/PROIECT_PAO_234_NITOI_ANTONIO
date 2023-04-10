package models;

public abstract class Locuinta {
    protected String numeClient;
    protected Dezvoltator dezvoltator;
    protected Arhitect arhitect;
    protected int suprafataUtila;
    protected double discount;

    public Locuinta(String numeClient, Dezvoltator dezvoltator, Arhitect arhitect, int suprafataUtila, double discount) {
        this.numeClient = numeClient;
        this.dezvoltator = dezvoltator;
        this.arhitect = arhitect;
        this.suprafataUtila = suprafataUtila;
        this.discount = discount;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public Dezvoltator getDezvoltator() {
        return dezvoltator;
    }

    public void setDezvoltator(Dezvoltator dezvoltator) {
        this.dezvoltator = dezvoltator;
    }

    public Arhitect getArhitect() {
        return arhitect;
    }

    public void setArhitect(Arhitect arhitect) {
        this.arhitect = arhitect;
    }

    public int getSuprafataUtila() {
        return suprafataUtila;
    }

    public void setSuprafataUtila(int suprafataUtila) {
        this.suprafataUtila = suprafataUtila;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public abstract double calculPretChirie(int aplicareDiscount);

    public abstract double calculPretCumparare(int aplicareDiscount);

    public void afisareLocuinta() {
        System.out.println("Nume client: " + numeClient);
        System.out.println("Nume dezvoltator: " + dezvoltator.getNumeCompanie());
        System.out.println("Nume arhitect: " + arhitect.getNume() + " " + arhitect.getPrenume());
        System.out.println("Discount aplicat: " + discount);
        System.out.println("Suprafata utila: " + suprafataUtila);
    }
}
