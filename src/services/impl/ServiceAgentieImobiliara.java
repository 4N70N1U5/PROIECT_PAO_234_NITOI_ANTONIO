package services.impl;

import exceptions.ExceptieValoareInvalida;
import models.*;
import services.IServiceAgentieImobiliara;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static validations.ValidareLocuinta.*;

public class ServiceAgentieImobiliara implements IServiceAgentieImobiliara {
    private static ServiceAgentieImobiliara instance;
    private final ArrayList<AgentieImobiliara> agentiiImobiliare = new ArrayList<>();

    private ServiceAgentieImobiliara() {
    }

    public static ServiceAgentieImobiliara getInstance() {
        if (instance == null)
            instance = new ServiceAgentieImobiliara();

        return instance;
    }

    @Override
    public void adaugaAgentie(AgentieImobiliara agentieImobiliara) {
        agentiiImobiliare.add(agentieImobiliara);

        ServiceAudit.getInstance().writeToCSV("Agentia " + agentieImobiliara.getNume() + " a fost adaugata.");
    }

    @Override
    public void afiseazaNumeAgentii() {
        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentia " + (i + 1) + ": " + agentiiImobiliare.get(i).getNume());
        }

        ServiceAudit.getInstance().writeToCSV("Agentiile au fost afisate.");
    }

    @Override
    public ArrayList<AgentieImobiliara> getAgentii() {
        return agentiiImobiliare;
    }

    public AgentieImobiliara getAgentie(int i) {
        return agentiiImobiliare.get(i);
    }

    @Override
    public void modificaAgentie(int i, AgentieImobiliara agentieModificata) {
        AgentieImobiliara agentieOriginala = agentiiImobiliare.get(i);

        agentiiImobiliare.set(i, agentieModificata);

        ServiceAudit.getInstance().writeToCSV("Agentia " + agentieOriginala.getNume() + " a fost modificata si a devenit " + agentieModificata.getNume());
    }

    @Override
    public void stergeAgentie(int i) {
        String numeAgentie = getAgentie(i).getNume();

        agentiiImobiliare.remove(i);

        ServiceAudit.getInstance().writeToCSV("Agentia " + numeAgentie + " a fost stearsa.");
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

        AgentieImobiliara agentieImobiliara = agentiiImobiliare.get(i);
        TreeMap<Double, ArrayList<Locuinta>> locuinte = agentieImobiliara.getLocuinte();

        if (locuinte.containsKey(locuinta.calculPretCumparare(0))) {
            locuinte.get(locuinta.calculPretCumparare(0)).add(locuinta);
        }
        else {
            locuinte.put(locuinta.calculPretCumparare(0), new ArrayList<>(List.of(locuinta)));
        }

        agentieImobiliara.setLocuinte(locuinte);

        ServiceAudit.getInstance().writeToCSV("O locuinta a fost adaugata in agentia " + agentieImobiliara.getNume());
    }

    @Override
    public void afiseazaLocuinteAgentii() {
        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentia " + (i + 1) + ": " + agentiiImobiliare.get(i).getNume());
            System.out.println("Locuinte: ");
            agentiiImobiliare.get(i).afisareLocuinte();
        }

        ServiceAudit.getInstance().writeToCSV("Toate locuintele au fost afisate.");
    }

    @Override
    public void afiseazaPreturiCumparareAgentii(int aplicareDiscount) {
        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentia " + (i + 1) + ": " + agentiiImobiliare.get(i).getNume());
            System.out.println("Locuinte: ");
            agentiiImobiliare.get(i).afisareLocuintePretCumparare(aplicareDiscount);
        }

        ServiceAudit.getInstance().writeToCSV("Preturile de cumparare ale tuturor locuintelor au fost afisate.");
    }

    @Override
    public void afiseazaPreturiChiriiAgentii(int aplicareDiscount) {
        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentia " + (i + 1) + ": " + agentiiImobiliare.get(i).getNume());
            System.out.println("Locuinte: ");
            agentiiImobiliare.get(i).afisareLocuinteChirii(aplicareDiscount);
        }

        ServiceAudit.getInstance().writeToCSV("Preturile chiriilor tuturor locuintelor au fost afisate.");
    }

    @Override
    public void afiseazaLocuinteAgentie(int i) {
        agentiiImobiliare.get(i).afisareLocuinte();

        ServiceAudit.getInstance().writeToCSV("Locuintele din agentia " + agentiiImobiliare.get(i).getNume() + " au fost afisate.");
    }

    @Override
    public void afiseazaPreturiCumparareAgentie(int i, int aplicareDiscount) {
        agentiiImobiliare.get(i).afisareLocuintePretCumparare(aplicareDiscount);

        ServiceAudit.getInstance().writeToCSV("Preturile de cumparare ale tuturor locuintelor din agentia " + agentiiImobiliare.get(i).getNume() + " au fost afisate.");
    }

    @Override
    public void afiseazaPreturiChiriiAgentie(int i, int aplicareDiscount) {
        agentiiImobiliare.get(i).afisareLocuinteChirii(aplicareDiscount);

        ServiceAudit.getInstance().writeToCSV("Preturile chiriilor tuturor locuintelor din agentia " + agentiiImobiliare.get(i).getNume() + " au fost afisate.");
    }

    @Override
    public ArrayList<Locuinta> getLocuinteAgentie(int iAgentie) {
        ArrayList<ArrayList<Locuinta>> locuinte = new ArrayList<>(getAgentie(iAgentie).getLocuinte().values());
        ArrayList<Locuinta> listaLocuinte = new ArrayList<>();

        for (ArrayList<Locuinta> listaInterioara : locuinte) {
            listaLocuinte.addAll(listaInterioara);
        }

        return listaLocuinte;
    }

    @Override
    public void modificaLocuinta(int iAgentie, int iLocuinta, Locuinta locuintaModificata) {
        ArrayList<ArrayList<Locuinta>> locuinte = new ArrayList<>(agentiiImobiliare.get(iAgentie).getLocuinte().values());
        ArrayList<Locuinta> listaLocuinte = new ArrayList<>();

        for (ArrayList<Locuinta> listaInterioara : locuinte) {
            listaLocuinte.addAll(listaInterioara);
        }

        listaLocuinte.set(iLocuinta, locuintaModificata);

        TreeMap<Double, ArrayList<Locuinta>> mapLocuinte = new TreeMap<>();

        for (Locuinta locuinta : listaLocuinte) {
            if (mapLocuinte.containsKey(locuinta.calculPretCumparare(0))) {
                mapLocuinte.get(locuinta.calculPretCumparare(0)).add(locuinta);
            }
            else {
                mapLocuinte.put(locuinta.calculPretCumparare(0), new ArrayList<>(List.of(locuinta)));
            }
        }

        agentiiImobiliare.get(iAgentie).setLocuinte(mapLocuinte);

        ServiceAudit.getInstance().writeToCSV("O locuinta din agentia " + agentiiImobiliare.get(iAgentie).getNume() + " a fost modificata.");
    }

    @Override
    public void stergeLocuinta(int iAgentie, int iLocuinta) {
        ArrayList<ArrayList<Locuinta>> locuinte = new ArrayList<>(agentiiImobiliare.get(iAgentie).getLocuinte().values());
        ArrayList<Locuinta> listaLocuinte = new ArrayList<>();

        for (ArrayList<Locuinta> listaInterioara : locuinte) {
            listaLocuinte.addAll(listaInterioara);
        }

        listaLocuinte.remove(iLocuinta);

        TreeMap<Double, ArrayList<Locuinta>> mapLocuinte = new TreeMap<>();

        for (Locuinta locuinta : listaLocuinte) {
            if (mapLocuinte.containsKey(locuinta.calculPretCumparare(0))) {
                mapLocuinte.get(locuinta.calculPretCumparare(0)).add(locuinta);
            }
            else {
                mapLocuinte.put(locuinta.calculPretCumparare(0), new ArrayList<>(List.of(locuinta)));
            }
        }

        agentiiImobiliare.get(iAgentie).setLocuinte(mapLocuinte);

        ServiceAudit.getInstance().writeToCSV("O locuinta din agentia " + agentiiImobiliare.get(iAgentie).getNume() + " a fost stearsa.");
    }
}
