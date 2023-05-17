package services;

import config.DatabaseConfig;
import models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

import static constants.Constante.*;

public class ServiceDBLocuinta implements IServiceDB {
    private static ServiceDBLocuinta instance;

    private ServiceDBLocuinta() {
    }

    public static ServiceDBLocuinta getInstance() {
        if (instance == null)
            instance = new ServiceDBLocuinta();

        return instance;
    }

    @Override
    public void deleteFromDB() {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();

            statement.executeUpdate(QUERY_DELETE_ALL_APARTAMENTE);
            statement.executeUpdate(QUERY_DELETE_ALL_APARTAMENTE_DUPLEX);
            statement.executeUpdate(QUERY_DELETE_ALL_APARTAMENTE_CU_GRADINA);
            statement.executeUpdate(QUERY_DELETE_ALL_CASE);
            statement.executeUpdate(QUERY_DELETE_ALL_CASE_CU_CURTE);
            statement.executeUpdate(QUERY_DELETE_ALL_CASE_CU_PISCINA);
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la salvarea Locuintelor in baza de date.");
        }
    }

    @Override
    public void saveToDB() {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();

            ArrayList<AgentieImobiliara> agentii = ServiceAgentieImobiliara.getInstance().getAgentii();

            for (int i = 0; i < agentii.size(); i++) {
                TreeMap<Double, ArrayList<Locuinta>> locuinte = agentii.get(i).getLocuinte();

                for (Double j : locuinte.keySet()) {
                    for (Locuinta locuinta : locuinte.get(j)) {
                        if (locuinta.getClass() == Apartament.class) {
                            statement.executeUpdate(getQueryInsertApartamente(i + 1, (Apartament) locuinta));
                        }
                        if (locuinta.getClass() == ApartamentDuplex.class) {
                            statement.executeUpdate(getQueryInsertApartamenteDuplex(i + 1, (ApartamentDuplex) locuinta));
                        }
                        if (locuinta.getClass() == ApartamentCuGradina.class) {
                            statement.executeUpdate(getQueryInsertApartamenteCuGradina(i + 1, (ApartamentCuGradina) locuinta));
                        }
                        if (locuinta.getClass() == Casa.class) {
                            statement.executeUpdate(getQueryInsertCase(i + 1, (Casa) locuinta));
                        }
                        if (locuinta.getClass() == CasaCuCurte.class) {
                            statement.executeUpdate(getQueryInsertCaseCuCurte(i + 1, (CasaCuCurte) locuinta));
                        }
                        if (locuinta.getClass() == CasaCuPiscina.class) {
                            statement.executeUpdate(getQueryInsertCaseCuPiscina(i + 1, (CasaCuPiscina) locuinta));
                        }
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la salvarea Locuintelor in baza de date.");
        }
    }

    @Override
    public void loadFromDB() {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();
            String[] queries = new String[]{QUERY_GET_ALL_APARTAMENTE, QUERY_GET_ALL_APARTAMENTE_DUPLEX, QUERY_GET_ALL_APARTAMENTE_CU_GRADINA, QUERY_GET_ALL_CASE, QUERY_GET_ALL_CASE_CU_CURTE, QUERY_GET_ALL_CASE_CU_PISCINA};

            for (String query : queries) {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String numeClient = resultSet.getString("numeClient");
                    String prenumeClient = resultSet.getString("prenumeClient");
                    double discount = resultSet.getDouble("discount");
                    Materiale structuraRezistenta = Materiale.valueOf(resultSet.getString("structuraRezistenta"));
                    int suprafataUtila = resultSet.getInt("suprafataUtila");
                    int numarCamere = resultSet.getInt("numarCamere");

                    switch (query) {
                        case QUERY_GET_ALL_APARTAMENTE -> {
                            int etaj = resultSet.getInt("etaj");

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(resultSet.getInt("idAgentie") - 1, new Apartament(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj));
                        }
                        case QUERY_GET_ALL_APARTAMENTE_DUPLEX -> {
                            int etaj = resultSet.getInt("etaj");
                            int numarEtaje = resultSet.getInt("numarEtaje");

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(resultSet.getInt("idAgentie") - 1, new ApartamentDuplex(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj, numarEtaje));
                        }
                        case QUERY_GET_ALL_APARTAMENTE_CU_GRADINA -> {
                            int etaj = resultSet.getInt("etaj");
                            int suprafataGradina = resultSet.getInt("suprafataGradina");

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(resultSet.getInt("idAgentie") - 1, new ApartamentCuGradina(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj, suprafataGradina));
                        }
                        case QUERY_GET_ALL_CASE -> {
                            int numarEtaje = resultSet.getInt("numarEtaje");

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(resultSet.getInt("idAgentie") - 1, new Casa(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje));
                        }
                        case QUERY_GET_ALL_CASE_CU_CURTE -> {
                            int numarEtaje = resultSet.getInt("numarEtaje");
                            int suprafataCurte = resultSet.getInt("suprafataCurte");

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(resultSet.getInt("idAgentie") - 1, new CasaCuCurte(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, suprafataCurte));
                        }
                        case QUERY_GET_ALL_CASE_CU_PISCINA -> {
                            int numarEtaje = resultSet.getInt("numarEtaje");
                            int suprafataCurte = resultSet.getInt("suprafataCurte");
                            int lungimePiscina = resultSet.getInt("lungimePiscina");
                            int latimePiscina = resultSet.getInt("latimePiscina");

                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(resultSet.getInt("idAgentie") - 1, new CasaCuPiscina(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, suprafataCurte, lungimePiscina, latimePiscina));
                        }
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la incarcarea Locuintelor din baza de date.");
        }
    }
}
