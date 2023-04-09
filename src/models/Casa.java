package models;

public class Casa extends Locuinta {
    private int suprafataCurte;

    public Casa(String numeClient, Dezvoltator dezvoltator, int suprafataUtila, double discount, int suprafataCurte) {
        super(numeClient, dezvoltator, suprafataUtila, discount);
        this.suprafataCurte = suprafataCurte;
    }

    public int getSuprafataCurte() {
        return suprafataCurte;
    }

    public void setSuprafataCurte(int suprafataCurte) {
        this.suprafataCurte = suprafataCurte;
    }
}
