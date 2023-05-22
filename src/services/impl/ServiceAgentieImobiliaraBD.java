package services.impl;

import exceptions.ExceptieValoareInvalida;
import models.*;
import repositories.impl.RepositoryAgentieImobiliara;
import repositories.impl.RepositoryLocuinta;
import services.IServiceAgentieImobiliara;

import java.util.ArrayList;

import static validations.ValidareLocuinta.*;

public class ServiceAgentieImobiliaraBD implements IServiceAgentieImobiliara {
    private static ServiceAgentieImobiliaraBD instance;

    private ServiceAgentieImobiliaraBD() {
    }

    public static ServiceAgentieImobiliaraBD getInstance() {
        if (instance == null)
            instance = new ServiceAgentieImobiliaraBD();

        return instance;
    }

    @Override
    public void adaugaAgentie(AgentieImobiliara agentieImobiliara) {
        RepositoryAgentieImobiliara.getInstance().add(agentieImobiliara);

        ServiceAudit.getInstance().writeToCSV("Agentia " + agentieImobiliara.getNume() + " a fost adaugata in baza de date.");
    }

    @Override
    public void afiseazaNumeAgentii() {
        ArrayList<AgentieImobiliara> agentiiImobiliare = RepositoryAgentieImobiliara.getInstance().getAll();

        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentia " + (i + 1) + ": " + agentiiImobiliare.get(i).getNume());
        }

        ServiceAudit.getInstance().writeToCSV("Agentiile din baza de date au fost afisate.");
    }

    @Override
    public ArrayList<AgentieImobiliara> getAgentii() {
        return RepositoryAgentieImobiliara.getInstance().getAll();
    }

    @Override
    public AgentieImobiliara getAgentie(int i) {
        ArrayList<AgentieImobiliara> agentiiImobiliare = RepositoryAgentieImobiliara.getInstance().getAll();

        int id = agentiiImobiliare.get(i).getId();

        return RepositoryAgentieImobiliara.getInstance().getById(id);
    }

    @Override
    public void modificaAgentie(int i, AgentieImobiliara agentieModificata) {
        AgentieImobiliara agentieOriginala = getAgentie(i);
        int idAgentie = agentieOriginala.getId();
        String numeAgentie = agentieOriginala.getNume();

        agentieModificata.setId(idAgentie);

        RepositoryAgentieImobiliara.getInstance().update(agentieModificata);

        ServiceAudit.getInstance().writeToCSV("Agentia " + numeAgentie + " a fost modificata in baza de date si a devenit " + agentieModificata.getNume());
    }

    @Override
    public void stergeAgentie(int i) {
        String numeAgentie = getAgentie(i).getNume();

        RepositoryAgentieImobiliara.getInstance().delete(getAgentie(i));

        ServiceAudit.getInstance().writeToCSV("Agentia " + numeAgentie + " a fost stearsa din baza de date.");
    }

    @Override
    public void adaugaLocuinta(int i, Locuinta locuinta) {
        if (!validareValoarePozitivaSau0(locuinta.getDiscount()))
            throw new ExceptieValoareInvalida("Discountul trebuie sa aiba valoare pozitiva!");
        if (!validareValoarePozitiva(locuinta.getSuprafataUtila()))
            throw new ExceptieValoareInvalida("Suprafata utila trebuie sa aiba valoare pozitiva!");
        if (!validareValoarePozitiva(locuinta.getNumarCamere()))
            throw new ExceptieValoareInvalida("Numarul de camere trebuie sa fie pozitiv!");

        if (locuinta.getClass() == Apartament.class || locuinta.getClass() == ApartamentDuplex.class) {
            if (!validareEtaj(((Apartament) locuinta).getEtaj()))
                throw new ExceptieValoareInvalida("Etajul treubuie sa fie -1 (subsol) sau mai mare!");
        }

        if (locuinta.getClass() == ApartamentCuGradina.class) {
            if (!validareEtajApartamentGradina(((ApartamentCuGradina) locuinta).getEtaj()))
                throw new ExceptieValoareInvalida("Apartamentele cu gradina trebuie sa fie obligatoriu la parter (etaj 0)!");
            if (!validareValoarePozitiva(((ApartamentCuGradina) locuinta).getSuprafataGradina()))
                throw new ExceptieValoareInvalida("Suprafata gradinii trebuie sa aiba valoare pozitiva!");
        }

        if (locuinta.getClass() == ApartamentDuplex.class) {
            if (!validareNumarEtajeDuplex(((ApartamentDuplex) locuinta).getNumarEtaje()))
                throw new ExceptieValoareInvalida("Apartamentele duplex trebuie sa aiba minim 2 etaje!");
        }

        if (locuinta.getClass() == Casa.class || locuinta.getClass() == CasaCuCurte.class || locuinta.getClass() == CasaCuPiscina.class) {
            if (!validareNumarEtaje(((Casa) locuinta).getNumarEtaje()))
                throw new ExceptieValoareInvalida("Numarul de etaje trebuie sa fie minim 1!");
        }

        if (locuinta.getClass() == CasaCuCurte.class || locuinta.getClass() == CasaCuPiscina.class) {
            if (!validareValoarePozitiva(((CasaCuCurte) locuinta).getSuprafataCurte())) {
                throw new ExceptieValoareInvalida("Suprafata curtii trebuie sa aiba valoare pozitiva!");
            }
        }

        if (locuinta.getClass() == CasaCuPiscina.class) {
            if (!validareValoarePozitiva(((CasaCuPiscina) locuinta).getLungimePiscina()) || !validareValoarePozitiva(((CasaCuPiscina) locuinta).getLatimePiscina()))
                throw new ExceptieValoareInvalida("Dimensiunile piscinei trebuie sa fie pozitive!");
        }

        int idAgentie = getAgentie(i).getId();

        locuinta.setIdAgentie(idAgentie);
        RepositoryLocuinta.getInstance().add(locuinta);

        ServiceAudit.getInstance().writeToCSV("O locuinta a fost adaugata in baza de date in agentia " + getAgentie(i).getNume());
    }

    @Override
    public void afiseazaLocuinteAgentii() {
        ArrayList<AgentieImobiliara> agentiiImobiliare = RepositoryAgentieImobiliara.getInstance().getAll();

        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentia " + (i + 1) + ": " + agentiiImobiliare.get(i).getNume());
            System.out.println("Locuinte: ");

            ArrayList<Locuinta> locuinte = RepositoryLocuinta.getInstance().getAllByForeignId(agentiiImobiliare.get(i).getId());
            for (int j = 0; j < locuinte.size(); j++) {
                System.out.println("Locuinta " + (j + 1) + ":");
                locuinte.get(j).afisareLocuinta();
                System.out.println();
            }
        }

        ServiceAudit.getInstance().writeToCSV("Toate locuintele din baza de date au fost afisate.");
    }

    @Override
    public void afiseazaPreturiCumparareAgentii(int aplicareDiscount) {
        ArrayList<AgentieImobiliara> agentiiImobiliare = RepositoryAgentieImobiliara.getInstance().getAll();

        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentia " + (i + 1) + ": " + agentiiImobiliare.get(i).getNume());
            System.out.println("Locuinte: ");

            ArrayList<Locuinta> locuinte = RepositoryLocuinta.getInstance().getAllByForeignId(agentiiImobiliare.get(i).getId());
            for (int j = 0; j < locuinte.size(); j++) {
                System.out.println("Locuinta " + (j + 1) + ":");
                locuinte.get(j).afisareLocuinta();
                System.out.println("Pret de cumparare: " + locuinte.get(j).calculPretCumparare(aplicareDiscount));
                System.out.println();
            }
        }

        ServiceAudit.getInstance().writeToCSV("Preturile de cumparare ale tuturor locuintelor din baza de date au fost afisate.");
    }

    @Override
    public void afiseazaPreturiChiriiAgentii(int aplicareDiscount) {
        ArrayList<AgentieImobiliara> agentiiImobiliare = RepositoryAgentieImobiliara.getInstance().getAll();

        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentia " + (i + 1) + ": " + agentiiImobiliare.get(i).getNume());
            System.out.println("Locuinte: ");

            ArrayList<Locuinta> locuinte = RepositoryLocuinta.getInstance().getAllByForeignId(agentiiImobiliare.get(i).getId());
            for (int j = 0; j < locuinte.size(); j++) {
                System.out.println("Locuinta " + (j + 1) + ":");
                locuinte.get(j).afisareLocuinta();
                System.out.println("Pret de cumparare: " + locuinte.get(j).calculPretChirie(aplicareDiscount));
                System.out.println();
            }
        }

        ServiceAudit.getInstance().writeToCSV("Preturile chiriilor tuturor locuintelor din baza de date au fost afisate.");
    }

    @Override
    public void afiseazaLocuinteAgentie(int i) {
        ArrayList<AgentieImobiliara> agentiiImobiliare = RepositoryAgentieImobiliara.getInstance().getAll();

        ArrayList<Locuinta> locuinte = RepositoryLocuinta.getInstance().getAllByForeignId(agentiiImobiliare.get(i).getId());
        for (int j = 0; j < locuinte.size(); j++) {
            System.out.println("Locuinta " + (j + 1) + ":");
            locuinte.get(j).afisareLocuinta();
            System.out.println();
        }

        ServiceAudit.getInstance().writeToCSV("Locuintele din baza de date din agentia " + agentiiImobiliare.get(i).getNume() + " au fost afisate.");
    }

    @Override
    public void afiseazaPreturiCumparareAgentie(int i, int aplicareDiscount) {
        ArrayList<AgentieImobiliara> agentiiImobiliare = RepositoryAgentieImobiliara.getInstance().getAll();

        ArrayList<Locuinta> locuinte = RepositoryLocuinta.getInstance().getAllByForeignId(agentiiImobiliare.get(i).getId());
        for (int j = 0; j < locuinte.size(); j++) {
            System.out.println("Locuinta " + (j + 1) + ":");
            locuinte.get(j).afisareLocuinta();
            System.out.println("Pret de cumparare: " + locuinte.get(j).calculPretCumparare(aplicareDiscount));
            System.out.println();
        }

        ServiceAudit.getInstance().writeToCSV("Preturile de cumparare ale tuturor locuintelor din baza de date din agentia " + agentiiImobiliare.get(i).getNume() + " au fost afisate.");
    }

    @Override
    public void afiseazaPreturiChiriiAgentie(int i, int aplicareDiscount) {
        ArrayList<AgentieImobiliara> agentiiImobiliare = RepositoryAgentieImobiliara.getInstance().getAll();

        ArrayList<Locuinta> locuinte = RepositoryLocuinta.getInstance().getAllByForeignId(agentiiImobiliare.get(i).getId());
        for (int j = 0; j < locuinte.size(); j++) {
            System.out.println("Locuinta " + (j + 1) + ":");
            locuinte.get(j).afisareLocuinta();
            System.out.println("Pret de cumparare: " + locuinte.get(j).calculPretChirie(aplicareDiscount));
            System.out.println();
        }

        ServiceAudit.getInstance().writeToCSV("Preturile chiriilor tuturor locuintelor din baza de date din agentia " + agentiiImobiliare.get(i).getNume() + " au fost afisate.");
    }

    @Override
    public ArrayList<Locuinta> getLocuinteAgentie(int iAgentie) {
        int idAgentie = RepositoryAgentieImobiliara.getInstance().getAll().get(iAgentie).getId();

        return RepositoryLocuinta.getInstance().getAllByForeignId(idAgentie);
    }

    @Override
    public void modificaLocuinta(int iAgentie, int iLocuinta, Locuinta locuintaModificata) {
        ArrayList<AgentieImobiliara> agentiiImobiliare = RepositoryAgentieImobiliara.getInstance().getAll();
        int idAgentie = agentiiImobiliare.get(iAgentie).getId();
        int idLocuinta = RepositoryLocuinta.getInstance().getAllByForeignId(idAgentie).get(iLocuinta).getId();

        locuintaModificata.setId(idLocuinta);
        locuintaModificata.setIdAgentie(idAgentie);

        RepositoryLocuinta.getInstance().update(locuintaModificata);

        ServiceAudit.getInstance().writeToCSV("O locuinta din agentia " + agentiiImobiliare.get(iAgentie).getNume() + " a fost modificata in baza de date.");
    }

    @Override
    public void stergeLocuinta(int iAgentie, int iLocuinta) {
        ArrayList<AgentieImobiliara> agentiiImobiliare = RepositoryAgentieImobiliara.getInstance().getAll();
        int idAgentie = agentiiImobiliare.get(iAgentie).getId();
        int idLocuinta = RepositoryLocuinta.getInstance().getAllByForeignId(idAgentie).get(iLocuinta).getId();

        Locuinta locuinta = RepositoryLocuinta.getInstance().getById(idLocuinta);

        RepositoryLocuinta.getInstance().delete(locuinta);

        ServiceAudit.getInstance().writeToCSV("O locuinta din agentia " + agentiiImobiliare.get(iAgentie).getNume() + " a fost stearsa din baza de date.");
    }
}
