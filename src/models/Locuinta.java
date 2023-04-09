package models;

public class Locuinta {
    protected String numeClient;
    protected Dezvoltator dezvoltator;
    protected int suprafataUtila;
    protected double discount;

    public Locuinta(String numeClient, Dezvoltator dezvoltator, int suprafataUtila, double discount) {
        this.numeClient = numeClient;
        this.dezvoltator = dezvoltator;
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
}
