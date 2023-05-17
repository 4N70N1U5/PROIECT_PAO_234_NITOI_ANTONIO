package constants;

import models.*;

public class Constante {
    public static final int VALOARE_STANDARD_CHIRIE = 5;
    public static final int VALOARE_STANDARD_CUMPARARE = 1000;
    public static final String FISIER_AUDIT = "src/csv/Audit.csv";
    public static final String FISIER_AGENTII_IMOBILIARE = "src/csv/AgentiiImobiliare.csv";
    public static final String FISIER_APARTAMENTE = "src/csv/Apartamente.csv";
    public static final String FISIER_APARTAMENTE_DUPLEX = "src/csv/ApartamenteDuplex.csv";
    public static final String FISIER_APARTAMENTE_CU_GRADINA = "src/csv/ApartamenteCuGradina.csv";
    public static final String FISIER_CASE = "src/csv/Case.csv";
    public static final String FISIER_CASE_CU_CURTE = "src/csv/CaseCuCurte.csv";
    public static final String FISIER_CASE_CU_PISCINA = "src/csv/CaseCuPiscina.csv";
    public static final String FISIER_CONEXIUNE_BD = "src/csv/DBInfo.csv";
    public static final String QUERY_GET_ALL_AGENTII_IMOBILIARE = "SELECT * FROM BDProiectPAO.AgentiiImobiliare";
    public static final String QUERY_DELETE_ALL_AGENTII_IMOBILIARE = "DELETE FROM BDProiectPAO.AgentiiImobiliare";
    public static String getQueryInsertAgentiiImobiliare(int i, AgentieImobiliara agentieImobiliara) {
        return "INSERT INTO BDProiectPAO.AgentiiImobiliare " +
                "(idAgentieImobiliara, numeAgentieImobiliara) VALUES " +
                "('" + i + "', '" + agentieImobiliara.getNume() + "')";
    }
    public static final String QUERY_GET_ALL_APARTAMENTE = "SELECT * FROM BDProiectPAO.Apartamente";
    public static final String QUERY_DELETE_ALL_APARTAMENTE = "DELETE FROM BDProiectPAO.Apartamente";
    public static String getQueryInsertApartamente(int i, Apartament apartament) {
        return "INSERT INTO BDProiectPAO.Apartamente " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj, idAgentie) VALUES " +
                "(" + apartament.toInsertFormat() + ", '" + i + "')";
    }
    public static final String QUERY_GET_ALL_APARTAMENTE_DUPLEX = "SELECT * FROM BDProiectPAO.ApartamenteDuplex";
    public static final String QUERY_DELETE_ALL_APARTAMENTE_DUPLEX = "DELETE FROM BDProiectPAO.ApartamenteDuplex";
    public static String getQueryInsertApartamenteDuplex(int i, ApartamentDuplex apartamentDuplex) {
        return "INSERT INTO BDProiectPAO.ApartamenteDuplex " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj, numarEtaje, idAgentie) VALUES " +
                "(" + apartamentDuplex.toInsertFormat() + ", '" + i + "')";
    }
    public static final String QUERY_GET_ALL_APARTAMENTE_CU_GRADINA = "SELECT * FROM BDProiectPAO.ApartamenteCuGradina";
    public static final String QUERY_DELETE_ALL_APARTAMENTE_CU_GRADINA = "DELETE FROM BDProiectPAO.ApartamenteCuGradina";
    public static String getQueryInsertApartamenteCuGradina(int i, ApartamentCuGradina apartamentCuGradina) {
        return "INSERT INTO BDProiectPAO.ApartamenteCuGradina " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj, suprafataGradina, idAgentie) VALUES " +
                "(" + apartamentCuGradina.toInsertFormat() + ", '" + i + "')";
    }
    public static final String QUERY_GET_ALL_CASE = "SELECT * FROM BDProiectPAO.Case";
    public static final String QUERY_DELETE_ALL_CASE = "DELETE FROM BDProiectPAO.Case";
    public static String getQueryInsertCase(int i, Casa casa) {
        return "INSERT INTO BDProiectPAO.Case " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, idAgentie) VALUES " +
                "(" + casa.toInsertFormat() + ", '" + i + "')";
    }
    public static final String QUERY_GET_ALL_CASE_CU_CURTE = "SELECT * FROM BDProiectPAO.CaseCuCurte";
    public static final String QUERY_DELETE_ALL_CASE_CU_CURTE = "DELETE FROM BDProiectPAO.CaseCuCurte";
    public static String getQueryInsertCaseCuCurte(int i, CasaCuCurte casaCuCurte) {
        return "INSERT INTO BDProiectPAO.CaseCuCurte " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, suprafataCurte, idAgentie) VALUES " +
                "(" + casaCuCurte.toInsertFormat() + ", '" + i + "')";
    }
    public static final String QUERY_GET_ALL_CASE_CU_PISCINA = "SELECT * FROM BDProiectPAO.CaseCuPiscina";
    public static final String QUERY_DELETE_ALL_CASE_CU_PISCINA = "DELETE FROM BDProiectPAO.CaseCuPiscina";
    public static String getQueryInsertCaseCuPiscina(int i, CasaCuPiscina casaCuPiscina) {
        return "INSERT INTO BDProiectPAO.CaseCuPiscina " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, suprafataCurte, lungimePiscina, latimePiscina, idAgentie) VALUES " +
                "(" + casaCuPiscina.toInsertFormat() + ", '" + i + "')";
    }
}
