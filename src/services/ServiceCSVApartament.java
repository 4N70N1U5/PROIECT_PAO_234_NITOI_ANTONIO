package services;

import models.AgentieImobiliara;
import models.Apartament;
import models.Locuinta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import static constants.Constante.FISIER_APARTAMENTE;

public class ServiceCSVApartament implements IServiceCSV {
    private static ServiceCSVApartament instance;

    private ServiceCSVApartament() {
    }

    public static ServiceCSVApartament getInstance() {
        if (instance == null)
            instance = new ServiceCSVApartament();

        return instance;
    }

    @Override
    public void writeToCSV() {
        try {
            // Creeaza fisierul daca nu exista deja.
            File file = new File(FISIER_APARTAMENTE);
            if (!file.exists() || file.isDirectory()) {
                if (file.createNewFile()) {
                    System.out.println("Fisierul CSV pentru stocarea Apartamentelor a fost creat.");
                }
            }

            // Sterg continutul vechi al fisierului.
            new FileWriter(FISIER_APARTAMENTE, false).close();

            // Scrie in fisier
            BufferedWriter writer = new BufferedWriter(new FileWriter(FISIER_APARTAMENTE, true));

            ArrayList<AgentieImobiliara> agentii = ServiceAgentieImobiliara.getInstance().getAgentii();

            for (int i = 0; i < agentii.size(); i++) {
                TreeMap<Double, Locuinta> locuinte = agentii.get(i).getLocuinte();

                for (Double j : locuinte.keySet()) {
                    if (locuinte.get(j).getClass() == Apartament.class) {
                        writer.write(i + ", " + locuinte.get(j).toCSVFormat() + "\n");
                    }
                }
            }

            writer.close();
        }
        catch (IOException e) {
            System.out.println("A aparut o eroare in functionarea sistemului de stocare a Apartamentelor in fisier CSV.");
        }
    }

    @Override
    public void readFromCSV() {

    }
}
