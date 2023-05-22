package repositories.impl;

import config.DatabaseConfig;
import models.AgentieImobiliara;
import repositories.IRepositoryGeneric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static constants.Constante.*;

public class RepositoryAgentieImobiliara implements IRepositoryGeneric<AgentieImobiliara> {
    private static RepositoryAgentieImobiliara instance;

    private RepositoryAgentieImobiliara() {
    }

    public static RepositoryAgentieImobiliara getInstance() {
        if (instance == null)
            instance = new RepositoryAgentieImobiliara();

        return instance;
    }

    @Override
    public void add(AgentieImobiliara object) {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();

            statement.executeUpdate(getQueryInsertAgentieImobiliara(object));
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la adaugarea Agentiei Imobiliara in baza de date.");
        }
    }

    @Override
    public ArrayList<AgentieImobiliara> getAll() {
        ArrayList<AgentieImobiliara> agentii = new ArrayList<>();

        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_AGENTII_IMOBILIARE);

            while (resultSet.next()) {
                AgentieImobiliara agentieImobiliara = new AgentieImobiliara(resultSet.getInt("idAgentieImobiliara"), resultSet.getString("numeAgentieImobiliara"));
                agentii.add(agentieImobiliara);
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la incarcarea Agentiilor Imobiliare din baza de date.");
        }

        return agentii;
    }

    @Override
    public AgentieImobiliara getById(int id) {
        AgentieImobiliara agentieImobiliara = null;

        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getQueryGetAgentieImobiliaraById(id));
            while (resultSet.next()) {
                agentieImobiliara = new AgentieImobiliara(resultSet.getInt("idAgentieImobiliara"), resultSet.getString("numeAgentieImobiliara"));
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la incarcarea Agentiei Imobiliara din baza de date.");
        }

        return agentieImobiliara;
    }

    @Override
    public void update(AgentieImobiliara object) {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();

            statement.executeUpdate(getQueryUpdateAgentieImobiliara(object));
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la modificarea Agentiei Imobiliara din baza de date.");
        }
    }

    @Override
    public void delete(AgentieImobiliara object) {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();

            statement.executeUpdate(getQueryDeleteAgentieImobiliaraById(object.getId()));
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la stergerea Agentiei Imobiliara din baza de date.");
        }
    }
}
