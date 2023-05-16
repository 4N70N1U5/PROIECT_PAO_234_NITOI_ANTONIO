package services;

import models.AgentieImobiliara;

import java.io.*;
import java.util.ArrayList;

import static constants.Constante.FISIER_AGENTII_IMOBILIARE;

public class ServiceCSVAgentieImobiliara implements IServiceCSV {
    private static ServiceCSVAgentieImobiliara instance;

    private ServiceCSVAgentieImobiliara() {
    }

    public static ServiceCSVAgentieImobiliara getInstance() {
        if (instance == null)
            instance = new ServiceCSVAgentieImobiliara();

        return instance;
    }

    @Override
    public void writeToCSV() {
        try {
            // Creeaza fisierul daca nu exista deja.
            File file = new File(FISIER_AGENTII_IMOBILIARE);
            if (!file.exists() || file.isDirectory()) {
                if (file.createNewFile()) {
                    System.out.println("Fisierul CSV pentru stocarea Agentiilor Imobiliare a fost creat.");
                }
            }

            // Sterg continutul vechi al fisierului.
            new FileWriter(FISIER_AGENTII_IMOBILIARE, false).close();

            // Scrie in fisier
            BufferedWriter writer = new BufferedWriter(new FileWriter(FISIER_AGENTII_IMOBILIARE, true));

            ArrayList<AgentieImobiliara> agentii = ServiceAgentieImobiliara.getInstance().getAgentii();

            for (int i = 0; i < agentii.size(); i++) {
                writer.write(i + ", " + agentii.get(i).getNume() + "\n");
            }

            writer.close();
        }
        catch (IOException e) {
            System.out.println("A aparut o eroare in functionarea sistemului de stocare a Agentiilor Imobiliare in fisier CSV.");
        }
    }

    @Override
    public void readFromCSV() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FISIER_AGENTII_IMOBILIARE));
            String infoAgentie;

            while ((infoAgentie = reader.readLine()) != null) {
                AgentieImobiliara agentie = new AgentieImobiliara(infoAgentie.split(",")[1].trim());
                ServiceAgentieImobiliara.getInstance().adaugaAgentie(agentie);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Fisierul CSV pentru stocarea Agentiilor Imobiliare nu exista. Acesta va fi creat la inchiderea programului.");
        }
        catch (IOException e) {
            System.out.println("A aparut o eroare in functionarea sistemului de stocare a Agentiilor Imobiliare in fisier CSV.");
        }
    }
}
