package services;

import models.AgentieImobiliara;

import java.util.ArrayList;

public class ServiceAgentieImobiliara implements IServiceAgentieImobiliara {
    private final ArrayList<AgentieImobiliara> agentiiImobiliare = new ArrayList<>();

    @Override
    public void adaugaAgentie(AgentieImobiliara agentieImobiliara) {
        agentiiImobiliare.add(agentieImobiliara);
    }

    @Override
    public void afiseazaAgentii() {
        for (int i = 0; i < agentiiImobiliare.size(); i++) {
            System.out.println("Agentie " + (i + 1) + ":");
            System.out.println("Nume: " + agentiiImobiliare.get(i).getNume());
            System.out.println("Locuinte: ");
            agentiiImobiliare.get(i).afisareLocuinte();
            System.out.println();
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
}
