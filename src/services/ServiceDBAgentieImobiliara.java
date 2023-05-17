package services;

import config.DatabaseConfig;
import models.AgentieImobiliara;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static constants.Constante.*;

public class ServiceDBAgentieImobiliara implements IServiceDB {
    private static ServiceDBAgentieImobiliara instance;

    private ServiceDBAgentieImobiliara() {
    }

    public static ServiceDBAgentieImobiliara getInstance() {
        if (instance == null)
            instance = new ServiceDBAgentieImobiliara();

        return instance;
    }

    @Override
    public void deleteFromDB() {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();

            statement.executeUpdate(QUERY_DELETE_ALL_AGENTII_IMOBILIARE);
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la salvarea Agentiilor Imobiliare in baza de date.");
        }
    }

    @Override
    public void saveToDB() {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();

            ArrayList<AgentieImobiliara> agentii = ServiceAgentieImobiliara.getInstance().getAgentii();

            for (int i = 0; i < agentii.size(); i++) {
                statement.executeUpdate(getQueryInsertAgentiiImobiliare(i + 1, agentii.get(i)));
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la salvarea Agentiilor Imobiliare in baza de date.");
        }
    }

    @Override
    public void loadFromDB() {
        try {
            Statement statement = DatabaseConfig.getInstance().getDatabaseConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_AGENTII_IMOBILIARE);

            while (resultSet.next()) {
                AgentieImobiliara agentieImobiliara = new AgentieImobiliara(resultSet.getString("numeAgentieImobiliara"));
                ServiceAgentieImobiliara.getInstance().adaugaAgentie(agentieImobiliara);
            }
        }
        catch (SQLException e) {
            System.out.println("A aparut o problema la incarcarea Agentiilor Imobiliare din baza de date.");
        }
    }
}
