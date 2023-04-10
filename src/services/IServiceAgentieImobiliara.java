package services;

import models.AgentieImobiliara;

import java.util.ArrayList;

public interface IServiceAgentieImobiliara {
    void adaugaAgentie(AgentieImobiliara agentieImobiliara);
    void afiseazaAgentii();
    ArrayList<AgentieImobiliara> getAgentii();
    void modificaAgentie(int i, AgentieImobiliara agentieModificata);
    void stergeAgentie(int i);
}
