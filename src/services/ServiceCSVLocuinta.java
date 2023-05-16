package services;

import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

import static constants.Constante.*;

public class ServiceCSVLocuinta implements IServiceCSV {
    private static ServiceCSVLocuinta instance;

    private ServiceCSVLocuinta() {
    }

    public static ServiceCSVLocuinta getInstance() {
        if (instance == null)
            instance = new ServiceCSVLocuinta();

        return instance;
    }

    private void createFile(String fileName) throws IOException {
        // Creeaza fisierul daca nu exista deja.
        File file = new File(fileName);

        if (!file.exists() || file.isDirectory()) {
            if (file.createNewFile()) {
                System.out.println("Unul din fisierele CSV pentru stocarea Locuintelor a fost creat.");
            }
        }
    }

    @Override
    public void writeToCSV() {
        try {
            // Creeaza fisierele daca nu exista deja.
            createFile(FISIER_APARTAMENTE);
            createFile(FISIER_APARTAMENTE_DUPLEX);
            createFile(FISIER_APARTAMENTE_CU_GRADINA);
            createFile(FISIER_CASE);
            createFile(FISIER_CASE_CU_CURTE);
            createFile(FISIER_CASE_CU_PISCINA);

            // Sterge continutul vechi al fisierelor.
            new FileWriter(FISIER_APARTAMENTE, false).close();
            new FileWriter(FISIER_APARTAMENTE_DUPLEX, false).close();
            new FileWriter(FISIER_APARTAMENTE_CU_GRADINA, false).close();
            new FileWriter(FISIER_CASE, false).close();
            new FileWriter(FISIER_CASE_CU_CURTE, false).close();
            new FileWriter(FISIER_CASE_CU_PISCINA, false).close();

            // Scrie in fisiere
            BufferedWriter writerApartament = new BufferedWriter(new FileWriter(FISIER_APARTAMENTE, true));
            BufferedWriter writerApartamentDuplex = new BufferedWriter(new FileWriter(FISIER_APARTAMENTE_DUPLEX, true));
            BufferedWriter writerApartamentCuGradina = new BufferedWriter(new FileWriter(FISIER_APARTAMENTE_CU_GRADINA, true));
            BufferedWriter writerCasa = new BufferedWriter(new FileWriter(FISIER_CASE, true));
            BufferedWriter writerCasaCuCurte = new BufferedWriter(new FileWriter(FISIER_CASE_CU_CURTE, true));
            BufferedWriter writerCasaCuPiscina = new BufferedWriter(new FileWriter(FISIER_CASE_CU_PISCINA, true));

            ArrayList<AgentieImobiliara> agentii = ServiceAgentieImobiliara.getInstance().getAgentii();

            for (int i = 0; i < agentii.size(); i++) {
                TreeMap<Double, ArrayList<Locuinta>> locuinte = agentii.get(i).getLocuinte();

                for (Double j : locuinte.keySet()) {
                    for (Locuinta locuinta : locuinte.get(j)) {
                        if (locuinta.getClass() == Apartament.class) {
                            writerApartament.write((i + 1) + ", " + locuinta.toCSVFormat() + "\n");
                        }
                        if (locuinta.getClass() == ApartamentDuplex.class) {
                            writerApartamentDuplex.write((i + 1) + ", " + locuinta.toCSVFormat() + "\n");
                        }
                        if (locuinta.getClass() == ApartamentCuGradina.class) {
                            writerApartamentCuGradina.write((i + 1) + ", " + locuinta.toCSVFormat() + "\n");
                        }
                        if (locuinta.getClass() == Casa.class) {
                            writerCasa.write((i + 1) + ", " + locuinta.toCSVFormat() + "\n");
                        }
                        if (locuinta.getClass() == CasaCuCurte.class) {
                            writerCasaCuCurte.write((i + 1) + ", " + locuinta.toCSVFormat() + "\n");
                        }
                        if (locuinta.getClass() == CasaCuPiscina.class) {
                            writerCasaCuPiscina.write((i + 1) + ", " + locuinta.toCSVFormat() + "\n");
                        }
                    }
                }
            }

            writerApartament.close();
            writerApartamentDuplex.close();
            writerApartamentCuGradina.close();
            writerCasa.close();
            writerCasaCuCurte.close();
            writerCasaCuPiscina.close();
        }
        catch (IOException e) {
            System.out.println("A aparut o eroare in functionarea sistemului de stocare a Locuintelor in fisiere CSV.");
        }
    }

    @Override
    public void readFromCSV() {
        try {
            String[] files = new String[]{FISIER_APARTAMENTE, FISIER_APARTAMENTE_DUPLEX, FISIER_APARTAMENTE_CU_GRADINA, FISIER_CASE, FISIER_CASE_CU_CURTE, FISIER_CASE_CU_PISCINA};

            for (String file : files) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String informatieCitita;

                while ((informatieCitita = reader.readLine()) != null) {
                    String[] infoLocuinta = informatieCitita.split(",");

                    String numeClient = infoLocuinta[1].trim();
                    String prenumeClient = infoLocuinta[2].trim();
                    double discount = Double.parseDouble(infoLocuinta[3].trim());
                    Materiale structuraRezistenta = Materiale.valueOf(infoLocuinta[4].trim());
                    int suprafataUtila = Integer.parseInt(infoLocuinta[5].trim());
                    int numarCamere = Integer.parseInt(infoLocuinta[6].trim());

                    switch (file) {
                        case FISIER_APARTAMENTE -> {
                            int etaj = Integer.parseInt(infoLocuinta[7].trim());

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(Integer.parseInt(infoLocuinta[0].trim()) - 1, new Apartament(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj));
                        }
                        case FISIER_APARTAMENTE_DUPLEX -> {
                            int etaj = Integer.parseInt(infoLocuinta[7].trim());
                            int numarEtaje = Integer.parseInt(infoLocuinta[8].trim());

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(Integer.parseInt(infoLocuinta[0].trim()) - 1, new ApartamentDuplex(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj, numarEtaje));
                        }
                        case FISIER_APARTAMENTE_CU_GRADINA -> {
                            int etaj = Integer.parseInt(infoLocuinta[7].trim());
                            int suprafataGradina = Integer.parseInt(infoLocuinta[8].trim());

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(Integer.parseInt(infoLocuinta[0].trim()) - 1, new ApartamentCuGradina(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj, suprafataGradina));
                        }
                        case FISIER_CASE -> {
                            int numarEtaje = Integer.parseInt(infoLocuinta[7].trim());

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(Integer.parseInt(infoLocuinta[0].trim()) - 1, new Casa(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje));
                        }
                        case FISIER_CASE_CU_CURTE -> {
                            int numarEtaje = Integer.parseInt(infoLocuinta[7].trim());
                            int suprafataCurte = Integer.parseInt(infoLocuinta[8].trim());

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(Integer.parseInt(infoLocuinta[0].trim()) - 1, new CasaCuCurte(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, suprafataCurte));
                        }
                        case FISIER_CASE_CU_PISCINA -> {
                            int numarEtaje = Integer.parseInt(infoLocuinta[7].trim());
                            int suprafataCurte = Integer.parseInt(infoLocuinta[8].trim());
                            int lungimePiscina = Integer.parseInt(infoLocuinta[9].trim());
                            int latimePiscina = Integer.parseInt(infoLocuinta[10].trim());

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(Integer.parseInt(infoLocuinta[0].trim()) - 1, new CasaCuPiscina(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, suprafataCurte, lungimePiscina, latimePiscina));
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Unul din fisierele CSV pentru stocarea Locuintelor nu exista. Acesta va fi creat la inchiderea programului.");
        }
        catch (IOException e) {
            System.out.println("A aparut o eroare in functionarea sistemului de stocare a Locuintelor in fisiere CSV.");
        }
    }
}
