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
    void afiseazaPreturiCumparareAgentii(int aplicareDiscount);
    void afiseazaPreturiChiriiAgentii(int aplicareDiscount);
    void afiseazaLocuinteAgentie(int i);
    void afiseazaPreturiCumparareAgentie(int i, int aplicareDiscount);
    void afiseazaPreturiChiriiAgentie(int i, int aplicareDiscount);
    void afiseazaLocuinteAgentieIndexate(int i);
    void modificaLocuinta(int iAgentie, int iLocuinta, Locuinta locuintaModificata);
    void stergeLocuinta(int iAgentie, int iLocuinta);
}
