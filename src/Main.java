import exceptions.ExceptieSelectieInvalida;
import exceptions.ExceptieValoareInvalida;
import models.*;
import services.ServiceAgentieImobiliara;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import static utile.Mesaje.*;
import static validations.ValidareLocuinta.validareMaterial;
import static validations.ValidareLocuinta.validareTipLocuinta;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final ServiceAgentieImobiliara service = new ServiceAgentieImobiliara();

    public static void clear() {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }

    public static Locuinta citireLocuinta(int tipLocuinta) {
        System.out.print("Nume client: ");
        String nume = scanner.nextLine();

        System.out.print("Prenume client: ");
        String prenume = scanner.nextLine();

        System.out.println("Discount: ");
        double discount = scanner.nextDouble();
        scanner.nextLine();

        afisareOptiuniMateriale();
        int nrMaterial = scanner.nextInt();
        scanner.nextLine();

        if (!validareMaterial(nrMaterial))
            throw new ExceptieValoareInvalida("Nu exista niciun material cu acest numar de ordine!");

        System.out.println("Suprafata utila: ");
        int suprafataUtila = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Numar camere: ");
        int numarCamere = scanner.nextInt();
        scanner.nextLine();

        if (tipLocuinta == 1) {
            System.out.println("Etaj: ");
            int etaj = scanner.nextInt();
            scanner.nextLine();

            switch (nrMaterial) {
                case 1 -> {
                    return new Apartament(nume, prenume, discount, Materiale.LEMN, suprafataUtila, numarCamere, etaj);
                }
                case 2 -> {
                    return new Apartament(nume, prenume, discount, Materiale.CARAMIDA, suprafataUtila, numarCamere, etaj);
                }
                case 3 -> {
                    return new Apartament(nume, prenume, discount, Materiale.BETON, suprafataUtila, numarCamere, etaj);
                }
                case 4 -> {
                    return new Apartament(nume, prenume, discount, Materiale.BETON_ARMAT, suprafataUtila, numarCamere, etaj);
                }
                case 5 -> {
                    return new Apartament(nume, prenume, discount, Materiale.METAL, suprafataUtila, numarCamere, etaj);
                }
            }
        }
        else if (tipLocuinta == 2) {
            System.out.println("Etaj: ");
            int etaj = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Numar etaje: ");
            int nrEtaje = scanner.nextInt();
            scanner.nextLine();

            switch (nrMaterial) {
                case 1 -> {
                    return new ApartamentDuplex(nume, prenume, discount, Materiale.LEMN, suprafataUtila, numarCamere, etaj, nrEtaje);
                }
                case 2 -> {
                    return new ApartamentDuplex(nume, prenume, discount, Materiale.CARAMIDA, suprafataUtila, numarCamere, etaj, nrEtaje);
                }
                case 3 -> {
                    return new ApartamentDuplex(nume, prenume, discount, Materiale.BETON, suprafataUtila, numarCamere, etaj, nrEtaje);
                }
                case 4 -> {
                    return new ApartamentDuplex(nume, prenume, discount, Materiale.BETON_ARMAT, suprafataUtila, numarCamere, etaj, nrEtaje);
                }
                case 5 -> {
                    return new ApartamentDuplex(nume, prenume, discount, Materiale.METAL, suprafataUtila, numarCamere, etaj, nrEtaje);
                }
            }
        }
        else if (tipLocuinta == 3) {
            System.out.println("Etaj: ");
            int etaj = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Suprafata gradina: ");
            int suprafataGradina = scanner.nextInt();
            scanner.nextLine();

            switch (nrMaterial) {
                case 1 -> {
                    return new ApartamentCuGradina(nume, prenume, discount, Materiale.LEMN, suprafataUtila, numarCamere, etaj, suprafataGradina);
                }
                case 2 -> {
                    return new ApartamentCuGradina(nume, prenume, discount, Materiale.CARAMIDA, suprafataUtila, numarCamere, etaj, suprafataGradina);
                }
                case 3 -> {
                    return new ApartamentCuGradina(nume, prenume, discount, Materiale.BETON, suprafataUtila, numarCamere, etaj, suprafataGradina);
                }
                case 4 -> {
                    return new ApartamentCuGradina(nume, prenume, discount, Materiale.BETON_ARMAT, suprafataUtila, numarCamere, etaj, suprafataGradina);
                }
                case 5 -> {
                    return new ApartamentCuGradina(nume, prenume, discount, Materiale.METAL, suprafataUtila, numarCamere, etaj, suprafataGradina);
                }
            }
        }
        else if (tipLocuinta == 4) {
            System.out.println("Numar etaje: ");
            int nrEtaje = scanner.nextInt();
            scanner.nextLine();

            switch (nrMaterial) {
                case 1 -> {
                    return new Casa(nume, prenume, discount, Materiale.LEMN, suprafataUtila, numarCamere, nrEtaje);
                }
                case 2 -> {
                    return new Casa(nume, prenume, discount, Materiale.CARAMIDA, suprafataUtila, numarCamere, nrEtaje);
                }
                case 3 -> {
                    return new Casa(nume, prenume, discount, Materiale.BETON, suprafataUtila, numarCamere, nrEtaje);
                }
                case 4 -> {
                    return new Casa(nume, prenume, discount, Materiale.BETON_ARMAT, suprafataUtila, numarCamere, nrEtaje);
                }
                case 5 -> {
                    return new Casa(nume, prenume, discount, Materiale.METAL, suprafataUtila, numarCamere, nrEtaje);
                }
            }
        }
        else if (tipLocuinta == 5) {
            System.out.println("Numar etaje: ");
            int nrEtaje = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Suprafata curte: ");
            int suprafataCurte = scanner.nextInt();
            scanner.nextLine();

            switch (nrMaterial) {
                case 1 -> {
                    return new CasaCuCurte(nume, prenume, discount, Materiale.LEMN, suprafataUtila, numarCamere, nrEtaje, suprafataCurte);
                }
                case 2 -> {
                    return new CasaCuCurte(nume, prenume, discount, Materiale.CARAMIDA, suprafataUtila, numarCamere, nrEtaje, suprafataCurte);
                }
                case 3 -> {
                    return new CasaCuCurte(nume, prenume, discount, Materiale.BETON, suprafataUtila, numarCamere, nrEtaje, suprafataCurte);
                }
                case 4 -> {
                    return new CasaCuCurte(nume, prenume, discount, Materiale.BETON_ARMAT, suprafataUtila, numarCamere, nrEtaje, suprafataCurte);
                }
                case 5 -> {
                    return new CasaCuCurte(nume, prenume, discount, Materiale.METAL, suprafataUtila, numarCamere, nrEtaje, suprafataCurte);
                }
            }
        }
        else if (tipLocuinta == 6) {
            System.out.println("Numar etaje: ");
            int nrEtaje = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Suprafata curte: ");
            int suprafataCurte = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Lungime piscina: ");
            int lungimePiscina = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Latime piscina: ");
            int latimePiscina = scanner.nextInt();
            scanner.nextLine();

            switch (nrMaterial) {
                case 1 -> {
                    return new CasaCuPiscina(nume, prenume, discount, Materiale.LEMN, suprafataUtila, numarCamere, nrEtaje, suprafataCurte, lungimePiscina, latimePiscina);
                }
                case 2 -> {
                    return new CasaCuPiscina(nume, prenume, discount, Materiale.CARAMIDA, suprafataUtila, numarCamere, nrEtaje, suprafataCurte, lungimePiscina, latimePiscina);
                }
                case 3 -> {
                    return new CasaCuPiscina(nume, prenume, discount, Materiale.BETON, suprafataUtila, numarCamere, nrEtaje, suprafataCurte, lungimePiscina, latimePiscina);
                }
                case 4 -> {
                    return new CasaCuPiscina(nume, prenume, discount, Materiale.BETON_ARMAT, suprafataUtila, numarCamere, nrEtaje, suprafataCurte, lungimePiscina, latimePiscina);
                }
                case 5 -> {
                    return new CasaCuPiscina(nume, prenume, discount, Materiale.METAL, suprafataUtila, numarCamere, nrEtaje, suprafataCurte, lungimePiscina, latimePiscina);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        boolean exitProgram = false;
        boolean skipSleep = false;

        while (!exitProgram) {
            if (!skipSleep)
                Thread.sleep(2048);
            else
                skipSleep = false;

            clear();
            afisareOptiuni();

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                clear();

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
                        break;
                    }

                    case 2:
                        System.out.println("Ai ales 2: Afiseaza agentiile imobiliare.");

                        service.afiseazaAgentii();

                        System.out.println("Apasa enter pentru a continua.");
                        scanner.nextLine();

                        skipSleep = true;
                        break;

                    case 3: {
                        System.out.println("Ai ales 3: Modifica o agentie imobiliara.");

                        service.afiseazaAgentii();

                        boolean indexValid = false;

                        while (!indexValid) {
                            System.out.print("Alege agentia pe care vrei sa o modifici (sau -1 pentru a anula): ");
                            try {
                                int i = scanner.nextInt();
                                scanner.nextLine();

                                if (i == -1) {
                                    skipSleep = true;
                                    break;
                                }

                                i--; // Pentru ca agentiile sunt afisate cu index incepand de la 1, dar in array incep de la 0.

                                try {
                                    AgentieImobiliara agentieOriginala = service.getAgentie(i);

                                    indexValid = true;

                                    System.out.print("Introdu noul nume al agentiei: ");
                                    String numeModificat = scanner.nextLine();

                                    AgentieImobiliara agentieModificata = new AgentieImobiliara(numeModificat, agentieOriginala.getLocuinte());

                                    service.modificaAgentie(i, agentieModificata);

                                    System.out.println("Agentia a fost modificata.");
                                }
                                catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            }
                            catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajInputInvalid();
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
                            try {
                                int i = scanner.nextInt();
                                scanner.nextLine();

                                if (i == -1) {
                                    skipSleep = true;
                                    break;
                                }

                                i--; // Pentru ca agentiile sunt afisate cu index incepand de la 1, dar in array incep de la 0.

                                try {
                                    service.stergeAgentie(i);

                                    indexValid = true;

                                    System.out.println("Agentia a fost stearsa.");
                                }
                                catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            }
                            catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajInputInvalid();
                            }
                        }
                        break;
                    }

                    case 5: {
                        System.out.println("Ai ales 5: Adauga o locuinta.");

                        service.afiseazaNumeAgentii();

                        boolean indexValid = false;

                        while (!indexValid) {
                            System.out.print("Alege agentia in care vrei sa adaugi o locuinta (sau -1 pentru a anula): ");
                            try {
                                int i = scanner.nextInt();
                                scanner.nextLine();

                                if (i == -1) {
                                    skipSleep = true;
                                    break;
                                }

                                i--; // Pentru ca agentiile sunt afisate cu index incepand de la 1, dar in array incep de la 0.

                                try {
                                    service.getAgentie(i); // Pentru a primi IndexOutOfBoundsException in cazul in care index-ul citit este invalid.

                                    indexValid = true;
                                    clear();

                                    afisareTipuriLocuinte();
                                    try {
                                        int tipLocuinta = scanner.nextInt();
                                        scanner.nextLine();

                                        if (!validareTipLocuinta(tipLocuinta))
                                            throw new ExceptieSelectieInvalida();

                                        Locuinta locuinta = citireLocuinta(tipLocuinta);

                                        if (locuinta != null) {
                                            service.adaugaLocuinta(i, locuinta);

                                            System.out.println("Locuinta a fost adaugata.");
                                        }
                                        else
                                            throw new ExceptieValoareInvalida("Tipul locuintei nu este valid!");
                                    }
                                    catch (InputMismatchException exception) {
                                        scanner.nextLine();
                                        afisareMesajInputInvalid();
                                    }
                                    catch (ExceptieSelectieInvalida | ExceptieValoareInvalida exceptie) {
                                        System.out.println(exceptie.getMessage());
                                    }
                                }
                                catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            }
                            catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajInputInvalid();
                            }
                        }
                        break;
                    }

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
                scanner.nextLine();
                afisareMesajOptiuneInvalida();
            }
            catch (ExceptieSelectieInvalida exceptie) {
                System.out.println(exceptie.getMessage());
            }
        }
    }
}
