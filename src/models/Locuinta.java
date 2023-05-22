package models;

public abstract class Locuinta {
    protected int id;
    protected String numeClient;
    protected String prenumeClient;
    protected double discount;
    protected Materiale structuraRezistenta;
    protected int suprafataUtila;
    protected int numarCamere;
    protected int idAgentie;

    public Locuinta(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere) {
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.discount = discount;
        this.structuraRezistenta = structuraRezistenta;
        this.suprafataUtila = suprafataUtila;
        this.numarCamere = numarCamere;
    }

    public Locuinta(String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int idAgentie) {
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.discount = discount;
        this.structuraRezistenta = structuraRezistenta;
        this.suprafataUtila = suprafataUtila;
        this.numarCamere = numarCamere;
        this.idAgentie = idAgentie;
    }

    public Locuinta(int id, String numeClient, String prenumeClient, double discount, Materiale structuraRezistenta, int suprafataUtila, int numarCamere, int idAgentie) {
        this.id = id;
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.discount = discount;
        this.structuraRezistenta = structuraRezistenta;
        this.suprafataUtila = suprafataUtila;
        this.numarCamere = numarCamere;
        this.idAgentie = idAgentie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Materiale getStructuraRezistenta() {
        return structuraRezistenta;
    }

    public void setStructuraRezistenta(Materiale structuraRezistenta) {
        this.structuraRezistenta = structuraRezistenta;
    }

    public int getSuprafataUtila() {
        return suprafataUtila;
    }

    public void setSuprafataUtila(int suprafataUtila) {
        this.suprafataUtila = suprafataUtila;
    }

    public int getNumarCamere() {
        return numarCamere;
    }

    public void setNumarCamere(int numarCamere) {
        this.numarCamere = numarCamere;
    }

    public int getIdAgentie() {
        return idAgentie;
    }

    public void setIdAgentie(int idAgentie) {
        this.idAgentie = idAgentie;
    }

    public abstract double calculPretChirie(int aplicareDiscount);

    public abstract double calculPretCumparare(int aplicareDiscount);

    public void afisareLocuinta() {
        System.out.println("Nume client: " + numeClient + " " + prenumeClient);
        System.out.println("Discount aplicat: " + discount);
        System.out.println("Structura de rezistenta din: " + structuraRezistenta);
        System.out.println("Suprafata utila: " + suprafataUtila);
        System.out.println("Numar camere: " + numarCamere);
    }

    @Override
    public String toString() {
        return "Locuinta{" +
                "numeClient='" + numeClient + '\'' +
                ", prenumeClient='" + prenumeClient + '\'' +
                ", discount=" + discount +
                ", structuraRezistenta=" + structuraRezistenta +
                ", suprafataUtila=" + suprafataUtila +
                ", numarCamere=" + numarCamere +
                '}';
    }

    public String toCSVFormat() {
        return numeClient + ", " +
                prenumeClient + ", " +
                discount + ", " +
                structuraRezistenta + ", " +
                suprafataUtila + ", " +
                numarCamere;
    }

    public String toInsertFormat() {
        return "'" + numeClient + "', " +
                "'" + prenumeClient + "', " +
                "'" + discount + "', " +
                "'" + structuraRezistenta + "', " +
                "'" + suprafataUtila + "', " +
                "'" + numarCamere + "'";
    }
}
