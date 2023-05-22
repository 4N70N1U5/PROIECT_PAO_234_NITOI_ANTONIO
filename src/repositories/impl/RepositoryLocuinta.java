package repositories.impl;

import config.DatabaseConfig;
import models.*;
import repositories.IRepositoryLocuinta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static constants.Constante.*;
import static constants.Constante.getQueryGetApartamenteByIdAgentie;

public class RepositoryLocuinta implements IRepositoryLocuinta<Locuinta> {
    private static RepositoryLocuinta instance;

    private RepositoryLocuinta() {
    }

    public static RepositoryLocuinta getInstance() {
        if (instance == null)
            instance = new RepositoryLocuinta();

        return instance;
    }

    @Override
    public void add(Locuinta object) {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();

            if (object.getClass() == Apartament.class) {
                statement.executeUpdate(getQueryInsertApartament((Apartament) object));
            }
            if (object.getClass() == ApartamentDuplex.class) {
                statement.executeUpdate(getQueryInsertApartamentDuplex((ApartamentDuplex) object));
            }
            if (object.getClass() == ApartamentCuGradina.class) {
                statement.executeUpdate(getQueryInsertApartamentCuGradina((ApartamentCuGradina) object));
            }
            if (object.getClass() == Casa.class) {
                statement.executeUpdate(getQueryInsertCasa((Casa) object));
            }
            if (object.getClass() == CasaCuCurte.class) {
                statement.executeUpdate(getQueryInsertCasaCuCurte((CasaCuCurte) object));
            }
            if (object.getClass() == CasaCuPiscina.class) {
                statement.executeUpdate(getQueryInsertCasaCuPiscina((CasaCuPiscina) object));
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la adaugarea Locuintei in baza de date.");
        }
    }

    @Override
    public ArrayList<Locuinta> getAll() {
        ArrayList<Locuinta> locuinte = new ArrayList<>();

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
                    int idAgentie = resultSet.getInt("idAgentie");

                    switch (query) {
                        case QUERY_GET_ALL_APARTAMENTE -> {
                            int id = resultSet.getInt("idApartament");
                            int etaj = resultSet.getInt("etaj");

                            locuinte.add(new Apartament(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj));
                        }
                        case QUERY_GET_ALL_APARTAMENTE_DUPLEX -> {
                            int id = resultSet.getInt("idApartamentDuplex");
                            int etaj = resultSet.getInt("etaj");
                            int numarEtaje = resultSet.getInt("numarEtaje");

                            locuinte.add(new ApartamentDuplex(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj, numarEtaje));
                        }
                        case QUERY_GET_ALL_APARTAMENTE_CU_GRADINA -> {
                            int id = resultSet.getInt("idApartamentCuGradina");
                            int etaj = resultSet.getInt("etaj");
                            int suprafataGradina = resultSet.getInt("suprafataGradina");

                            locuinte.add(new ApartamentCuGradina(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj, suprafataGradina));
                        }
                        case QUERY_GET_ALL_CASE -> {
                            int id = resultSet.getInt("idCasa");
                            int numarEtaje = resultSet.getInt("numarEtaje");

                            locuinte.add(new Casa(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje));
                        }
                        case QUERY_GET_ALL_CASE_CU_CURTE -> {
                            int id = resultSet.getInt("idCasaCuCurte");
                            int numarEtaje = resultSet.getInt("numarEtaje");
                            int suprafataCurte = resultSet.getInt("suprafataCurte");

                            locuinte.add(new CasaCuCurte(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje, suprafataCurte));
                        }
                        case QUERY_GET_ALL_CASE_CU_PISCINA -> {
                            int id = resultSet.getInt("idCasaCuPiscina");
                            int numarEtaje = resultSet.getInt("numarEtaje");
                            int suprafataCurte = resultSet.getInt("suprafataCurte");
                            int lungimePiscina = resultSet.getInt("lungimePiscina");
                            int latimePiscina = resultSet.getInt("latimePiscina");

                            locuinte.add(new CasaCuPiscina(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje, suprafataCurte, lungimePiscina, latimePiscina));
                        }
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la incarcarea Locuintelor din baza de date.");
        }

        return locuinte;
    }

    @Override
    public Locuinta getById(int id) {
        Locuinta locuinta = null;

        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();
            String[] queries = new String[]{getQueryGetApartamentById(id), getQueryGetApartamentDuplexById(id), getQueryGetApartamentCuGradinaById(id), getQueryGetCasaById(id), getQueryGetCasaCuCurteById(id), getQueryGetCasaCuPiscinaById(id)};

            for (String query : queries) {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String numeClient = resultSet.getString("numeClient");
                    String prenumeClient = resultSet.getString("prenumeClient");
                    double discount = resultSet.getDouble("discount");
                    Materiale structuraRezistenta = Materiale.valueOf(resultSet.getString("structuraRezistenta"));
                    int suprafataUtila = resultSet.getInt("suprafataUtila");
                    int numarCamere = resultSet.getInt("numarCamere");
                    int idAgentie = resultSet.getInt("idAgentie");

                    if (query.equals(queries[0])) {
                        int etaj = resultSet.getInt("etaj");

                        locuinta = new Apartament(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj);
                    } else if (query.equals(queries[1])) {
                        int etaj = resultSet.getInt("etaj");
                        int numarEtaje = resultSet.getInt("numarEtaje");

                        locuinta = new ApartamentDuplex(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj, numarEtaje);
                    } else if (query.equals(queries[2])) {
                        int etaj = resultSet.getInt("etaj");
                        int suprafataGradina = resultSet.getInt("suprafataGradina");

                        locuinta = new ApartamentCuGradina(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj, suprafataGradina);
                    } else if (query.equals(queries[3])) {
                        int numarEtaje = resultSet.getInt("numarEtaje");

                        locuinta = new Casa(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje);
                    } else if (query.equals(queries[4])) {
                        int numarEtaje = resultSet.getInt("numarEtaje");
                        int suprafataCurte = resultSet.getInt("suprafataCurte");

                        locuinta = new CasaCuCurte(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje, suprafataCurte);
                    } else if (query.equals(queries[5])) {
                        int numarEtaje = resultSet.getInt("numarEtaje");
                        int suprafataCurte = resultSet.getInt("suprafataCurte");
                        int lungimePiscina = resultSet.getInt("lungimePiscina");
                        int latimePiscina = resultSet.getInt("latimePiscina");

                        locuinta = new CasaCuPiscina(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje, suprafataCurte, lungimePiscina, latimePiscina);
                    }

                    break;
                }
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la incarcarea Locuintei din baza de date.");
        }

        return locuinta;
    }

    @Override
    public void update(Locuinta object) {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();

            if (object.getClass() == Apartament.class) {
                statement.executeUpdate(getQueryUpdateApartament((Apartament) object));
            }
            if (object.getClass() == ApartamentDuplex.class) {
                statement.executeUpdate(getQueryUpdateApartamentDuplex((ApartamentDuplex) object));
            }
            if (object.getClass() == ApartamentCuGradina.class) {
                statement.executeUpdate(getQueryUpdateApartamentCuGradina((ApartamentCuGradina) object));
            }
            if (object.getClass() == Casa.class) {
                statement.executeUpdate(getQueryUpdateCasa((Casa) object));
            }
            if (object.getClass() == CasaCuCurte.class) {
                statement.executeUpdate(getQueryUpdateCasaCuCurte((CasaCuCurte) object));
            }
            if (object.getClass() == CasaCuPiscina.class) {
                statement.executeUpdate(getQueryUpdateCasaCuPiscina((CasaCuPiscina) object));
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la modificarea Locuintei din baza de date.");
        }
    }

    @Override
    public void delete(Locuinta object) {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();

            if (object.getClass() == Apartament.class) {
                statement.executeUpdate(getQueryDeleteApartamentById(object.getId()));
            }
            if (object.getClass() == ApartamentDuplex.class) {
                statement.executeUpdate(getQueryDeleteApartamentDuplexById(object.getId()));
            }
            if (object.getClass() == ApartamentCuGradina.class) {
                statement.executeUpdate(getQueryDeleteApartamentCuGradinaById(object.getId()));
            }
            if (object.getClass() == Casa.class) {
                statement.executeUpdate(getQueryDeleteCasaById(object.getId()));
            }
            if (object.getClass() == CasaCuCurte.class) {
                statement.executeUpdate(getQueryDeleteCasaCuCurteById(object.getId()));
            }
            if (object.getClass() == CasaCuPiscina.class) {
                statement.executeUpdate(getQueryDeleteCasaCuPiscinaById(object.getId()));
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la stergerea Locuintei din baza de date.");
        }
    }

    @Override
    public ArrayList<Locuinta> getAllByForeignId(int foreignId) {
        ArrayList<Locuinta> locuinte = new ArrayList<>();

        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();
            String[] queries = new String[]{getQueryGetApartamenteByIdAgentie(foreignId), getQueryGetApartamenteDuplexByIdAgentie(foreignId), getQueryGetApartamenteCuGradinaByIdAgentie(foreignId), getQueryGetCaseByIdAgentie(foreignId), getQueryGetCaseCuCurteByIdAgentie(foreignId), getQueryGetCaseCuPiscinaByIdAgentie(foreignId)};

            for (String query : queries) {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String numeClient = resultSet.getString("numeClient");
                    String prenumeClient = resultSet.getString("prenumeClient");
                    double discount = resultSet.getDouble("discount");
                    Materiale structuraRezistenta = Materiale.valueOf(resultSet.getString("structuraRezistenta"));
                    int suprafataUtila = resultSet.getInt("suprafataUtila");
                    int numarCamere = resultSet.getInt("numarCamere");
                    int idAgentie = resultSet.getInt("idAgentie");

                    if (query.equals(queries[0])) {
                        int id = resultSet.getInt("idApartament");
                        int etaj = resultSet.getInt("etaj");

                        locuinte.add(new Apartament(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj));
                    } else if (query.equals(queries[1])) {
                        int id = resultSet.getInt("idApartamentDuplex");
                        int etaj = resultSet.getInt("etaj");
                        int numarEtaje = resultSet.getInt("numarEtaje");

                        locuinte.add(new ApartamentDuplex(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj, numarEtaje));
                    } else if (query.equals(queries[2])) {
                        int id = resultSet.getInt("idApartamentCuGradina");
                        int etaj = resultSet.getInt("etaj");
                        int suprafataGradina = resultSet.getInt("suprafataGradina");

                        locuinte.add(new ApartamentCuGradina(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, etaj, suprafataGradina));
                    } else if (query.equals(queries[3])) {
                        int id = resultSet.getInt("idCasa");
                        int numarEtaje = resultSet.getInt("numarEtaje");

                        locuinte.add(new Casa(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje));
                    } else if (query.equals(queries[4])) {
                        int id = resultSet.getInt("idCasaCuCurte");
                        int numarEtaje = resultSet.getInt("numarEtaje");
                        int suprafataCurte = resultSet.getInt("suprafataCurte");

                        locuinte.add(new CasaCuCurte(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje, suprafataCurte));
                    } else if (query.equals(queries[5])) {
                        int id = resultSet.getInt("idCasaCuPiscina");
                        int numarEtaje = resultSet.getInt("numarEtaje");
                        int suprafataCurte = resultSet.getInt("suprafataCurte");
                        int lungimePiscina = resultSet.getInt("lungimePiscina");
                        int latimePiscina = resultSet.getInt("latimePiscina");

                        locuinte.add(new CasaCuPiscina(id, numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, idAgentie, numarEtaje, suprafataCurte, lungimePiscina, latimePiscina));
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la incarcarea Locuintelor din baza de date.");
        }

        return locuinte;
    }
}
