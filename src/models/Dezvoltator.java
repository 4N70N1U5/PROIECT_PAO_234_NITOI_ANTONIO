package models;

import java.util.Date;

public class Dezvoltator {
    private String nume;
    private Date dataInfiintare;

    public Dezvoltator(String nume, Date dataInfiintare) {
        this.nume = nume;
        this.dataInfiintare = dataInfiintare;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Date getDataInfiintare() {
        return dataInfiintare;
    }

    public void setDataInfiintare(Date dataInfiintare) {
        this.dataInfiintare = dataInfiintare;
    }
}
