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
    public static String getQueryInsertAgentieImobiliara(AgentieImobiliara agentieImobiliara) {
        return "INSERT INTO BDProiectPAO.AgentiiImobiliare " +
                "(numeAgentieImobiliara) VALUES " +
                "('" + agentieImobiliara.getNume() + "')";
    }
    public static String getQueryGetAgentieImobiliaraById(int id) {
        return "SELECT * FROM BDProiectPAO.AgentiiImobiliare WHERE idAgentieImobiliara = '" + id + "'";
    }
    public static String getQueryUpdateAgentieImobiliara(AgentieImobiliara agentieImobiliara) {
        return "UPDATE BDProiectPAO.AgentiiImobiliare SET numeAgentieImobiliara = '" + agentieImobiliara.getNume() + "' WHERE (idAgentieImobiliara = '" + agentieImobiliara.getId() + "')";
    }
    public static String getQueryDeleteAgentieImobiliaraById(int id) {
        return "DELETE FROM BDProiectPAO.AgentiiImobiliare WHERE idAgentieImobiliara = '" + id + "'";
    }
    public static final String QUERY_GET_ALL_APARTAMENTE = "SELECT * FROM BDProiectPAO.Apartamente";
    public static final String QUERY_DELETE_ALL_APARTAMENTE = "DELETE FROM BDProiectPAO.Apartamente";
    public static String getQueryInsertApartament(Apartament apartament) {
        return "INSERT INTO BDProiectPAO.Apartamente " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj, idAgentie) VALUES " +
                "(" + apartament.toInsertFormat() + ", '" + apartament.getIdAgentie() + "')";
    }
    public static String getQueryGetApartamentById(int id) {
        return "SELECT * FROM BDProiectPAO.Apartamente WHERE idApartament = '" + id + "'";
    }
    public static String getQueryUpdateApartament(Apartament apartament) {
        return "UPDATE BDProiectPAO.Apartamente SET " +
                "numeClient = '" + apartament.getNumeClient() + "', " +
                "prenumeClient = '" + apartament.getPrenumeClient() + "', " +
                "discount = '" + apartament.getDiscount() + "', " +
                "structuraRezistenta = '" + apartament.getStructuraRezistenta() + "', " +
                "suprafataUtila = '" + apartament.getSuprafataUtila() + "', " +
                "numarCamere = '" + apartament.getNumarCamere() + "', " +
                "etaj = '" + apartament.getEtaj() + "' " +
                "WHERE (idApartament = '" + apartament.getId() +"')";
    }
    public static String getQueryDeleteApartamentById(int id) {
        return "DELETE FROM BDProiectPAO.Apartamente WHERE idApartament = '" + id + "'";
    }
    public static String getQueryGetApartamenteByIdAgentie(int id) {
        return "SELECT * FROM BDProiectPAO.Apartamente WHERE idAgentie = '" + id + "'";
    }
    public static final String QUERY_GET_ALL_APARTAMENTE_DUPLEX = "SELECT * FROM BDProiectPAO.ApartamenteDuplex";
    public static final String QUERY_DELETE_ALL_APARTAMENTE_DUPLEX = "DELETE FROM BDProiectPAO.ApartamenteDuplex";
    public static String getQueryInsertApartamentDuplex(ApartamentDuplex apartamentDuplex) {
        return "INSERT INTO BDProiectPAO.ApartamenteDuplex " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj, numarEtaje, idAgentie) VALUES " +
                "(" + apartamentDuplex.toInsertFormat() + ", '" + apartamentDuplex.getIdAgentie() + "')";
    }
    public static String getQueryGetApartamentDuplexById(int id) {
        return "SELECT * FROM BDProiectPAO.ApartamenteDuplex WHERE idApartamentDuplex = '" + id + "'";
    }
    public static String getQueryUpdateApartamentDuplex(ApartamentDuplex apartamentDuplex) {
        return "UPDATE BDProiectPAO.ApartamenteDuplex SET " +
                "numeClient = '" + apartamentDuplex.getNumeClient() + "', " +
                "prenumeClient = '" + apartamentDuplex.getPrenumeClient() + "', " +
                "discount = '" + apartamentDuplex.getDiscount() + "', " +
                "structuraRezistenta = '" + apartamentDuplex.getStructuraRezistenta() + "', " +
                "suprafataUtila = '" + apartamentDuplex.getSuprafataUtila() + "', " +
                "numarCamere = '" + apartamentDuplex.getNumarCamere() + "', " +
                "etaj = '" + apartamentDuplex.getEtaj() + "', " +
                "numarEtaje = '" + apartamentDuplex.getNumarEtaje() + "' " +
                "WHERE (idApartamentDuplex = '" + apartamentDuplex.getId() +"')";
    }
    public static String getQueryDeleteApartamentDuplexById(int id) {
        return "DELETE FROM BDProiectPAO.ApartamenteDuplex WHERE idApartamentDuplex = '" + id + "'";
    }
    public static String getQueryGetApartamenteDuplexByIdAgentie(int id) {
        return "SELECT * FROM BDProiectPAO.ApartamenteDuplex WHERE idAgentie = '" + id + "'";
    }
    public static final String QUERY_GET_ALL_APARTAMENTE_CU_GRADINA = "SELECT * FROM BDProiectPAO.ApartamenteCuGradina";
    public static final String QUERY_DELETE_ALL_APARTAMENTE_CU_GRADINA = "DELETE FROM BDProiectPAO.ApartamenteCuGradina";
    public static String getQueryInsertApartamentCuGradina(ApartamentCuGradina apartamentCuGradina) {
        return "INSERT INTO BDProiectPAO.ApartamenteCuGradina " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, etaj, suprafataGradina, idAgentie) VALUES " +
                "(" + apartamentCuGradina.toInsertFormat() + ", '" + apartamentCuGradina.getIdAgentie() + "')";
    }
    public static String getQueryGetApartamentCuGradinaById(int id) {
        return "SELECT * FROM BDProiectPAO.ApartamenteCuGradina WHERE idApartamentCuGradina = '" + id + "'";
    }
    public static String getQueryUpdateApartamentCuGradina(ApartamentCuGradina apartamentCuGradina) {
        return "UPDATE BDProiectPAO.ApartamenteCuGradina SET " +
                "numeClient = '" + apartamentCuGradina.getNumeClient() + "', " +
                "prenumeClient = '" + apartamentCuGradina.getPrenumeClient() + "', " +
                "discount = '" + apartamentCuGradina.getDiscount() + "', " +
                "structuraRezistenta = '" + apartamentCuGradina.getStructuraRezistenta() + "', " +
                "suprafataUtila = '" + apartamentCuGradina.getSuprafataUtila() + "', " +
                "numarCamere = '" + apartamentCuGradina.getNumarCamere() + "', " +
                "etaj = '" + apartamentCuGradina.getEtaj() + "', " +
                "suprafataGradina = '" + apartamentCuGradina.getSuprafataGradina() + "' " +
                "WHERE (idApartamentCuGradina = '" + apartamentCuGradina.getId() +"')";
    }
    public static String getQueryDeleteApartamentCuGradinaById(int id) {
        return "DELETE FROM BDProiectPAO.ApartamenteCuGradina WHERE idApartamentCuGradina = '" + id + "'";
    }
    public static String getQueryGetApartamenteCuGradinaByIdAgentie(int id) {
        return "SELECT * FROM BDProiectPAO.ApartamenteCuGradina WHERE idAgentie = '" + id + "'";
    }
    public static final String QUERY_GET_ALL_CASE = "SELECT * FROM BDProiectPAO.Case";
    public static final String QUERY_DELETE_ALL_CASE = "DELETE FROM BDProiectPAO.Case";
    public static String getQueryInsertCasa(Casa casa) {
        return "INSERT INTO BDProiectPAO.Case " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, idAgentie) VALUES " +
                "(" + casa.toInsertFormat() + ", '" + casa.getIdAgentie() + "')";
    }
    public static String getQueryGetCasaById(int id) {
        return "SELECT * FROM BDProiectPAO.Case WHERE idCasa = '" + id + "'";
    }
    public static String getQueryUpdateCasa(Casa casa) {
        return "UPDATE BDProiectPAO.Case SET " +
                "numeClient = '" + casa.getNumeClient() + "', " +
                "prenumeClient = '" + casa.getPrenumeClient() + "', " +
                "discount = '" + casa.getDiscount() + "', " +
                "structuraRezistenta = '" + casa.getStructuraRezistenta() + "', " +
                "suprafataUtila = '" + casa.getSuprafataUtila() + "', " +
                "numarCamere = '" + casa.getNumarCamere() + "', " +
                "numarEtaje = '" + casa.getNumarEtaje() + "' " +
                "WHERE (idCasa = '" + casa.getId() +"')";
    }
    public static String getQueryDeleteCasaById(int id) {
        return "DELETE FROM BDProiectPAO.Case WHERE idCasa = '" + id + "'";
    }
    public static String getQueryGetCaseByIdAgentie(int id) {
        return "SELECT * FROM BDProiectPAO.Case WHERE idAgentie = '" + id + "'";
    }
    public static final String QUERY_GET_ALL_CASE_CU_CURTE = "SELECT * FROM BDProiectPAO.CaseCuCurte";
    public static final String QUERY_DELETE_ALL_CASE_CU_CURTE = "DELETE FROM BDProiectPAO.CaseCuCurte";
    public static String getQueryInsertCasaCuCurte(CasaCuCurte casaCuCurte) {
        return "INSERT INTO BDProiectPAO.CaseCuCurte " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, suprafataCurte, idAgentie) VALUES " +
                "(" + casaCuCurte.toInsertFormat() + ", '" + casaCuCurte.getIdAgentie() + "')";
    }
    public static String getQueryGetCasaCuCurteById(int id) {
        return "SELECT * FROM BDProiectPAO.CaseCuCurte WHERE idCasaCuCurte = '" + id + "'";
    }
    public static String getQueryUpdateCasaCuCurte(CasaCuCurte casaCuCurte) {
        return "UPDATE BDProiectPAO.CaseCuCurte SET " +
                "numeClient = '" + casaCuCurte.getNumeClient() + "', " +
                "prenumeClient = '" + casaCuCurte.getPrenumeClient() + "', " +
                "discount = '" + casaCuCurte.getDiscount() + "', " +
                "structuraRezistenta = '" + casaCuCurte.getStructuraRezistenta() + "', " +
                "suprafataUtila = '" + casaCuCurte.getSuprafataUtila() + "', " +
                "numarCamere = '" + casaCuCurte.getNumarCamere() + "', " +
                "numarEtaje = '" + casaCuCurte.getNumarEtaje() + "', " +
                "suprafataCurte = '" + casaCuCurte.getSuprafataCurte() + "' " +
                "WHERE (idCasaCuCurte = '" + casaCuCurte.getId() +"')";
    }
    public static String getQueryDeleteCasaCuCurteById(int id) {
        return "DELETE FROM BDProiectPAO.CaseCuCurte WHERE idCasaCuCurte = '" + id + "'";
    }
    public static String getQueryGetCaseCuCurteByIdAgentie(int id) {
        return "SELECT * FROM BDProiectPAO.CaseCuCurte WHERE idAgentie = '" + id + "'";
    }
    public static final String QUERY_GET_ALL_CASE_CU_PISCINA = "SELECT * FROM BDProiectPAO.CaseCuPiscina";
    public static final String QUERY_DELETE_ALL_CASE_CU_PISCINA = "DELETE FROM BDProiectPAO.CaseCuPiscina";
    public static String getQueryInsertCasaCuPiscina(CasaCuPiscina casaCuPiscina) {
        return "INSERT INTO BDProiectPAO.CaseCuPiscina " +
                "(numeClient, prenumeClient, discount, structuraRezistenta, suprafataUtila, numarCamere, numarEtaje, suprafataCurte, lungimePiscina, latimePiscina, idAgentie) VALUES " +
                "(" + casaCuPiscina.toInsertFormat() + ", '" + casaCuPiscina.getIdAgentie() + "')";
    }
    public static String getQueryGetCasaCuPiscinaById(int id) {
        return "SELECT * FROM BDProiectPAO.CaseCuPiscina WHERE idCasaCuPiscina = '" + id + "'";
    }
    public static String getQueryUpdateCasaCuPiscina(CasaCuPiscina casaCuPiscina) {
        return "UPDATE BDProiectPAO.CaseCuPiscina SET " +
                "numeClient = '" + casaCuPiscina.getNumeClient() + "', " +
                "prenumeClient = '" + casaCuPiscina.getPrenumeClient() + "', " +
                "discount = '" + casaCuPiscina.getDiscount() + "', " +
                "structuraRezistenta = '" + casaCuPiscina.getStructuraRezistenta() + "', " +
                "suprafataUtila = '" + casaCuPiscina.getSuprafataUtila() + "', " +
                "numarCamere = '" + casaCuPiscina.getNumarCamere() + "', " +
                "numarEtaje = '" + casaCuPiscina.getNumarEtaje() + "', " +
                "suprafataCurte = '" + casaCuPiscina.getSuprafataCurte() + "', " +
                "lungimePiscina = '" + casaCuPiscina.getLungimePiscina() + "', " +
                "latimePiscina = '" + casaCuPiscina.getLatimePiscina() + "' " +
                "WHERE (idCasaCuPiscina = '" + casaCuPiscina.getId() +"')";
    }
    public static String getQueryDeleteCasaCuPiscinaById(int id) {
        return "DELETE FROM BDProiectPAO.CaseCuPiscina WHERE idCasaCuPiscina = '" + id + "'";
    }
    public static String getQueryGetCaseCuPiscinaByIdAgentie(int id) {
        return "SELECT * FROM BDProiectPAO.CaseCuPiscina WHERE idAgentie = '" + id + "'";
    }
}
