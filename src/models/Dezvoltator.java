package models;

import java.util.Date;

public class Dezvoltator {
    private String numeCompanie;
    private Date dataInfiintare;

    public Dezvoltator(String numeCompanie, Date dataInfiintare) {
        this.numeCompanie = numeCompanie;
        this.dataInfiintare = dataInfiintare;
    }

    public String getNumeCompanie() {
        return numeCompanie;
    }

    public void setNumeCompanie(String numeCompanie) {
        this.numeCompanie = numeCompanie;
    }

    public Date getDataInfiintare() {
        return dataInfiintare;
    }

    public void setDataInfiintare(Date dataInfiintare) {
        this.dataInfiintare = dataInfiintare;
    }
}
