import exceptions.ExceptieSelectieInvalida;
import models.AgentieImobiliara;
import services.ServiceAgentieImobiliara;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import static utile.Mesaje.*;

public class Main {
    public static void Clear() {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }

    public static void main(String[] args) throws InterruptedException {
        boolean exitProgram = false;

        ServiceAgentieImobiliara service = new ServiceAgentieImobiliara();
        Scanner scanner = new Scanner(System.in);

        while (!exitProgram) {
            Clear();
            Optiuni();

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                Clear();

                switch (input) {
                    case 1: {
                        System.out.println("Ai ales 1: Adauga o agentie imobiliara.");

                        System.out.print("Introdu numele agentiei (sau \"ANULARE\" pentru a anula): ");
                        String numeAgentie = scanner.nextLine();

                        if (Objects.equals(numeAgentie, "ANULARE"))
                            break;

                        AgentieImobiliara agentieImobiliara = new AgentieImobiliara(numeAgentie);

                        service.adaugaAgentie(agentieImobiliara);

                        System.out.println("Agentia a fost adaugata.");
                        Thread.sleep(2048);

                        break;
                    }

                    case 2:
                        System.out.println("Ai ales 2: Afiseaza agentiile imobiliare.");

                        service.afiseazaAgentii();

                        System.out.println("Apasa enter pentru a continua.");
                        scanner.nextLine();

                        break;

                    case 3: {
                        System.out.println("Ai ales 3: Modifica o agentie imobiliara.");

                        service.afiseazaAgentii();

                        boolean indexValid = false;

                        while (!indexValid) {
                            System.out.print("Alege agentia pe care vrei sa o modifici (sau -1 pentru a anula): ");
                            int i = scanner.nextInt();
                            scanner.nextLine();

                            if (i == -1)
                                break;

                            i--; // Pentru ca agentiile sunt afisate cu index incepand de la 1, dar in array incep de la 0.

                            try {
                                AgentieImobiliara agentieOriginala = service.getAgentie(i);

                                indexValid = true;

                                System.out.print("Introdu noul nume al agentiei: ");
                                String numeModificat = scanner.nextLine();

                                AgentieImobiliara agentieModificata = new AgentieImobiliara(numeModificat, agentieOriginala.getLocuinte());

                                service.modificaAgentie(i, agentieModificata);

                                System.out.println("Agentia a fost modificata.");
                                Thread.sleep(2048);
                            }
                            catch (IndexOutOfBoundsException exception) {
                                IndexInvalid();
                            }
                        }
                        break;
                    }

                    case 4: {
                        System.out.println("Ai ales 4: Sterge o agentie imobiliara.");

                        service.afiseazaAgentii();

                        boolean indexValid = false;

                        while (!indexValid) {
                            System.out.print("Alege agentia pe care vrei sa o stergi (sau -1 pentru a anula): ");
                            int i = scanner.nextInt();
                            scanner.nextLine();

                            if (i == -1)
                                break;

                            i--; // Pentru ca agentiile sunt afisate cu index incepand de la 1, dar in array incep de la 0.

                            try {
                                service.stergeAgentie(i);

                                indexValid = true;

                                System.out.println("Agentia a fost stearsa.");
                                Thread.sleep(2048);
                            }
                            catch (IndexOutOfBoundsException exception) {
                                IndexInvalid();
                            }
                        }
                        break;
                    }

                    case 5:
                        break;

                    case 6:
                        break;

                    case 7:
                        break;

                    case 8:
                        break;

                    case 9:
                        break;

                    case 10:
                        break;

                    case 0:
                        System.out.println("Ai ales 0: Inchide programul.");
                        System.out.println("Programul se va inchide.");

                        exitProgram = true;

                        break;

                    default:
                        throw new ExceptieSelectieInvalida();
                }
            }
            catch (InputMismatchException exception) {
                InputInvalid();
            }
            catch (ExceptieSelectieInvalida exceptie) {
                System.out.println(exceptie.getMessage());
            }
        }
    }
}