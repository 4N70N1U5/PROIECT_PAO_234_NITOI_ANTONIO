package services;

import exceptions.ExceptieValoareInvalida;
import models.*;

import java.util.ArrayList;
import java.util.TreeMap;

import static validations.ValidareLocuinta.*;

public class ServiceAgentieImobiliara implements IServiceAgentieImobiliara {
    private final ArrayList<AgentieImobiliara> agentiiImobiliare = new ArrayList<>();

    @Override
    public void adaugaAgentie(AgentieImobiliara agentieImobiliara) {
        agentiiImobiliare.add(agentieImobiliara);
    }

    @Override
    public void afiseazaAgentii() {
        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentia " + (i + 1) + ": " + agentiiImobiliare.get(i).getNume());
            System.out.println("Locuinte: ");
            agentiiImobiliare.get(i).afisareLocuinte();
        }
    }

    @Override
    public void afiseazaNumeAgentii() {
        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentia " + (i + 1) + ": " + agentiiImobiliare.get(i).getNume());
        }
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
        agentiiImobiliare.set(i, agentieModificata);
    }

    @Override
    public void stergeAgentie(int i) {
        agentiiImobiliare.remove(i);
    }

    @Override
    public void adaugaLocuinta(int i, Locuinta locuinta) {
        if (!validareValoarePozitiva(locuinta.getDiscount()))
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
        TreeMap<Double, Locuinta> locuinte = agentieImobiliara.getLocuinte();
        locuinte.put(locuinta.calculPretCumparare(0), locuinta);
        agentieImobiliara.setLocuinte(locuinte);
    }

    @Override
    public void afiseazaLocuinteAgentie(int i) {
        agentiiImobiliare.get(i).afisareLocuinte();
    }

    @Override
    public void afiseazaLocuinteAgentieIndexate(int i) {
        agentiiImobiliare.get(i).afisareLocuinteIndexate();
    }

    @Override
    public void modificaLocuinta(int iAgentie, int iLocuinta, Locuinta locuintaModificata) {
        ArrayList<Locuinta> listaLocuinte = new ArrayList<>(agentiiImobiliare.get(iAgentie).getLocuinte().values());
        listaLocuinte.set(iLocuinta, locuintaModificata);
        TreeMap<Double, Locuinta> mapLocuinte = new TreeMap<>();
        for (int i = 0; i < listaLocuinte.size(); i++) {
            mapLocuinte.put(listaLocuinte.get(i).calculPretCumparare(0), listaLocuinte.get(i));
        }
        agentiiImobiliare.get(iAgentie).setLocuinte(mapLocuinte);
    }

    @Override
    public void stergeLocuinta(int iAgentie, int iLocuinta) {
        ArrayList<Locuinta> listaLocuinte = new ArrayList<>(agentiiImobiliare.get(iAgentie).getLocuinte().values());
        listaLocuinte.remove(iLocuinta);
        TreeMap<Double, Locuinta> mapLocuinte = new TreeMap<>();
        for (int i = 0; i < listaLocuinte.size(); i++) {
            mapLocuinte.put(listaLocuinte.get(i).calculPretCumparare(0), listaLocuinte.get(i));
        }
        agentiiImobiliare.get(iAgentie).setLocuinte(mapLocuinte);
    }
}
