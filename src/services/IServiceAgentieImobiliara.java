package services;

import models.AgentieImobiliara;
import models.Locuinta;

import java.util.ArrayList;

public interface IServiceAgentieImobiliara {
    void adaugaAgentie(AgentieImobiliara agentieImobiliara);
    void afiseazaNumeAgentii();
    ArrayList<AgentieImobiliara> getAgentii();
    AgentieImobiliara getAgentie(int i);
    void modificaAgentie(int i, AgentieImobiliara agentieModificata);
    void stergeAgentie(int i);
    void adaugaLocuinta(int i, Locuinta locuinta);
    void afiseazaLocuinteAgentii();
    void afiseazaPreturiCumparareAgentii();
    void afiseazaPreturiChiriiAgentii();
    void afiseazaLocuinteAgentie(int i);
    void afiseazaPreturiCumparareAgentie(int i);
    void afiseazaPreturiChiriiAgentie(int i);
    void afiseazaLocuinteAgentieIndexate(int i);
    void modificaLocuinta(int iAgentie, int iLocuinta, Locuinta locuintaModificata);
    void stergeLocuinta(int iAgentie, int iLocuinta);
}
