package models;

public abstract class Locuinta {
    protected String numeClient;
    protected String prenumeClient;
    protected double discount;
    protected int suprafataUtila;

    public Locuinta(String numeClient, String prenumeClient, double discount, int suprafataUtila) {
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.discount = discount;
        this.suprafataUtila = suprafataUtila;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getPrenumeClient() {
        return prenumeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        this.prenumeClient = prenumeClient;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getSuprafataUtila() {
        return suprafataUtila;
    }

    public void setSuprafataUtila(int suprafataUtila) {
        this.suprafataUtila = suprafataUtila;
    }

    public abstract double calculPretChirie(int aplicareDiscount);

    public abstract double calculPretCumparare(int aplicareDiscount);

    public void afisareLocuinta() {
        System.out.println("Nume client: " + numeClient + " " + prenumeClient);
        System.out.println("Discount aplicat: " + discount);
        System.out.println("Suprafata utila: " + suprafataUtila);
    }

    @Override
    public String toString() {
        return "Locuinta{" +
                "numeClient='" + numeClient + '\'' +
                ", prenumeClient='" + prenumeClient + '\'' +
                ", discount=" + discount +
                ", suprafataUtila=" + suprafataUtila +
                '}';
    }
}
