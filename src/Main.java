import exceptions.ExceptieSelectieInvalida;
import exceptions.ExceptieValoareInvalida;
import models.*;
import services.ServiceAgentieImobiliara;
import services.ServiceAudit;
import services.ServiceCSVAgentieImobiliara;
import services.ServiceCSVLocuinta;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import static utils.Mesaje.*;
import static validations.ValidareLocuinta.validareMaterial;
import static validations.ValidareLocuinta.validareTipLocuinta;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static void clear() {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }

    private static Locuinta citireLocuinta(int tipLocuinta) {
        System.out.print("Nume client: ");
        String nume = scanner.nextLine();

        System.out.print("Prenume client: ");
        String prenume = scanner.nextLine();

        System.out.print("Discount: ");
        double discount = scanner.nextDouble();
        scanner.nextLine();

        afisareOptiuniMateriale();
        int nrMaterial = scanner.nextInt();
        scanner.nextLine();

        if (!validareMaterial(nrMaterial))
            throw new ExceptieValoareInvalida("Nu exista niciun material cu acest numar de ordine!");

        System.out.print("Suprafata utila: ");
        int suprafataUtila = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Numar camere: ");
        int numarCamere = scanner.nextInt();
        scanner.nextLine();

        if (tipLocuinta == 1) {
            System.out.print("Etaj: ");
            int etaj = scanner.nextInt();
            scanner.nextLine();

            return new Apartament(nume, prenume, discount, Materiale.values()[nrMaterial - 1], suprafataUtila, numarCamere, etaj);
        }
        else if (tipLocuinta == 2) {
            System.out.print("Etaj: ");
            int etaj = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Numar etaje: ");
            int nrEtaje = scanner.nextInt();
            scanner.nextLine();

            return new ApartamentDuplex(nume, prenume, discount, Materiale.values()[nrMaterial - 1], suprafataUtila, numarCamere, etaj, nrEtaje);
        }
        else if (tipLocuinta == 3) {
            System.out.print("Etaj: ");
            int etaj = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Suprafata gradina: ");
            int suprafataGradina = scanner.nextInt();
            scanner.nextLine();

            return new ApartamentCuGradina(nume, prenume, discount, Materiale.values()[nrMaterial - 1], suprafataUtila, numarCamere, etaj, suprafataGradina);
        }
        else if (tipLocuinta == 4) {
            System.out.print("Numar etaje: ");
            int nrEtaje = scanner.nextInt();
            scanner.nextLine();

            return new Casa(nume, prenume, discount, Materiale.values()[nrMaterial - 1], suprafataUtila, numarCamere, nrEtaje);
        }
        else if (tipLocuinta == 5) {
            System.out.print("Numar etaje: ");
            int nrEtaje = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Suprafata curte: ");
            int suprafataCurte = scanner.nextInt();
            scanner.nextLine();

            return new CasaCuCurte(nume, prenume, discount, Materiale.values()[nrMaterial - 1], suprafataUtila, numarCamere, nrEtaje, suprafataCurte);
        }
        else if (tipLocuinta == 6) {
            System.out.print("Numar etaje: ");
            int nrEtaje = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Suprafata curte: ");
            int suprafataCurte = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Lungime piscina: ");
            int lungimePiscina = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Latime piscina: ");
            int latimePiscina = scanner.nextInt();
            scanner.nextLine();

            return new CasaCuPiscina(nume, prenume, discount, Materiale.values()[nrMaterial - 1], suprafataUtila, numarCamere, nrEtaje, suprafataCurte, lungimePiscina, latimePiscina);
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        boolean exitProgram = false;
        boolean skipSleep = true;

        ServiceAudit.getInstance().scrieMesaj("Programul a fost pornit.");

        ServiceCSVAgentieImobiliara.getInstance().readFromCSV();
        ServiceCSVLocuinta.getInstance().readFromCSV();

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
                    case 1 -> {
                        System.out.println("Ai ales 1: Adauga o agentie imobiliara.");

                        System.out.print("Introdu numele agentiei (sau \"ANULARE\" pentru a anula): ");
                        String numeAgentie = scanner.nextLine();

                        if (Objects.equals(numeAgentie, "ANULARE")) {
                            skipSleep = true;
                            break;
                        }

                        AgentieImobiliara agentieImobiliara = new AgentieImobiliara(numeAgentie);

                        ServiceAgentieImobiliara.getInstance().adaugaAgentie(agentieImobiliara);

                        System.out.println("Agentia a fost adaugata.");
                    }
                    case 2 -> {
                        System.out.println("Ai ales 2: Afiseaza agentiile imobiliare.");

                        ServiceAgentieImobiliara.getInstance().afiseazaNumeAgentii();

                        System.out.println("Apasa enter pentru a continua.");
                        scanner.nextLine();

                        skipSleep = true;
                    }
                    case 3 -> {
                        System.out.println("Ai ales 3: Modifica o agentie imobiliara.");

                        ServiceAgentieImobiliara.getInstance().afiseazaNumeAgentii();

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
                                    AgentieImobiliara agentieOriginala = ServiceAgentieImobiliara.getInstance().getAgentie(i);

                                    indexValid = true;

                                    System.out.print("Introdu noul nume al agentiei: ");
                                    String numeModificat = scanner.nextLine();

                                    AgentieImobiliara agentieModificata = new AgentieImobiliara(numeModificat, agentieOriginala.getLocuinte());

                                    ServiceAgentieImobiliara.getInstance().modificaAgentie(i, agentieModificata);

                                    System.out.println("Agentia a fost modificata.");
                                } catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            } catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajInputInvalid();
                            }
                        }
                    }
                    case 4 -> {
                        System.out.println("Ai ales 4: Sterge o agentie imobiliara.");

                        ServiceAgentieImobiliara.getInstance().afiseazaNumeAgentii();

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
                                    ServiceAgentieImobiliara.getInstance().stergeAgentie(i);

                                    indexValid = true;

                                    System.out.println("Agentia a fost stearsa.");
                                } catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            } catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajInputInvalid();
                            }
                        }
                    }
                    case 5 -> {
                        System.out.println("Ai ales 5: Adauga o locuinta.");

                        ServiceAgentieImobiliara.getInstance().afiseazaNumeAgentii();

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
                                    ServiceAgentieImobiliara.getInstance().getAgentie(i); // Pentru a primi IndexOutOfBoundsException in cazul in care index-ul citit este invalid.

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
                                            ServiceAgentieImobiliara.getInstance().adaugaLocuinta(i, locuinta);

                                            System.out.println("Locuinta a fost adaugata.");
                                        } else
                                            throw new ExceptieValoareInvalida("Tipul locuintei nu este valid!");
                                    } catch (InputMismatchException exception) {
                                        scanner.nextLine();
                                        afisareMesajInputInvalid();
                                    } catch (ExceptieSelectieInvalida | ExceptieValoareInvalida exceptie) {
                                        System.out.println(exceptie.getMessage());
                                    }
                                } catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            } catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajInputInvalid();
                            }
                        }
                    }
                    case 6 -> {
                        System.out.println("Ai ales 6: Afiseaza locuintele.");

                        ServiceAgentieImobiliara.getInstance().afiseazaNumeAgentii();

                        boolean indexValid = false;

                        while (!indexValid) {
                            System.out.print("Alege agentia pentru care vrei sa afisezi locuintele (sau 0 pentru toate agentiile, -1 pentru a anula): ");
                            try {
                                int i = scanner.nextInt();
                                scanner.nextLine();

                                if (i == -1) {
                                    skipSleep = true;
                                    break;
                                }

                                if (i == 0) {
                                    System.out.println();
                                    ServiceAgentieImobiliara.getInstance().afiseazaLocuinteAgentii();

                                    System.out.println("Apasa enter pentru a continua.");
                                    scanner.nextLine();

                                    skipSleep = true;
                                    break;
                                }

                                i--; // Pentru ca agentiile sunt afisate cu index incepand de la 1, dar in array incep de la 0.

                                try {
                                    System.out.println();
                                    ServiceAgentieImobiliara.getInstance().afiseazaLocuinteAgentie(i);

                                    indexValid = true;

                                    System.out.println("Apasa enter pentru a continua.");
                                    scanner.nextLine();

                                    skipSleep = true;
                                } catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            } catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajInputInvalid();
                            }
                        }
                    }
                    case 7 -> {
                        System.out.println("Ai ales 7: Modifica o locuinta.");

                        ServiceAgentieImobiliara.getInstance().afiseazaNumeAgentii();

                        boolean indexValid = false;

                        while (!indexValid) {
                            System.out.print("Alege agentia pentru care vrei sa modifici o locuinta (sau -1 pentru a anula): ");
                            try {
                                int i = scanner.nextInt();
                                scanner.nextLine();

                                if (i == -1) {
                                    skipSleep = true;
                                    break;
                                }

                                i--; // Pentru ca agentiile sunt afisate cu index incepand de la 1, dar in array incep de la 0.

                                try {
                                    System.out.println();
                                    ServiceAgentieImobiliara.getInstance().afiseazaLocuinteAgentieIndexate(i);

                                    indexValid = true;

//                                    ArrayList<Locuinta> listaLocuinte = new ArrayList<>(ServiceAgentieImobiliara.getInstance().getAgentie(i).getLocuinte().values());

                                    ArrayList<ArrayList<Locuinta>> locuinte = new ArrayList<>(ServiceAgentieImobiliara.getInstance().getAgentie(i).getLocuinte().values());
                                    ArrayList<Locuinta> listaLocuinte = new ArrayList<>();

                                    for (ArrayList<Locuinta> listaInterioara : locuinte) {
                                        listaLocuinte.addAll(listaInterioara);
                                    }

                                    boolean indexLocuintaValid = false;

                                    while (!indexLocuintaValid) {
                                        System.out.print("Alege locuinta pe care vrei sa o modifici: ");
                                        try {
                                            int iLocuinta = scanner.nextInt();
                                            scanner.nextLine();

                                            iLocuinta--;

                                            try {
                                                int tipLocuinta;

                                                if (listaLocuinte.get(iLocuinta).getClass().equals(Apartament.class)) {
                                                    tipLocuinta = 1;
                                                } else if (listaLocuinte.get(iLocuinta).getClass().equals(ApartamentDuplex.class)) {
                                                    tipLocuinta = 2;
                                                } else if (listaLocuinte.get(iLocuinta).getClass().equals(ApartamentCuGradina.class)) {
                                                    tipLocuinta = 3;
                                                } else if (listaLocuinte.get(iLocuinta).getClass().equals(Casa.class)) {
                                                    tipLocuinta = 4;
                                                } else if (listaLocuinte.get(iLocuinta).getClass().equals(CasaCuCurte.class)) {
                                                    tipLocuinta = 5;
                                                } else {
                                                    tipLocuinta = 6;
                                                }

                                                Locuinta locuintaModificata = citireLocuinta(tipLocuinta);

                                                indexLocuintaValid = true;

                                                ServiceAgentieImobiliara.getInstance().modificaLocuinta(i, iLocuinta, locuintaModificata);
                                                System.out.println("Locuinta a fost modificata.");
                                            } catch (IndexOutOfBoundsException exception) {
                                                afisareMesajIndexInvalid();
                                            }
                                        } catch (InputMismatchException exception) {
                                            scanner.nextLine();
                                            afisareMesajInputInvalid();
                                        }
                                    }
                                } catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            } catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajInputInvalid();
                            }
                        }
                    }
                    case 8 -> {
                        System.out.println("Ai ales 8: Sterge o locuinta.");

                        ServiceAgentieImobiliara.getInstance().afiseazaNumeAgentii();

                        boolean indexValid = false;

                        while (!indexValid) {
                            System.out.print("Alege agentia pentru care vrei sa stergi o locuinta (sau -1 pentru a anula): ");
                            try {
                                int i = scanner.nextInt();
                                scanner.nextLine();

                                if (i == -1) {
                                    skipSleep = true;
                                    break;
                                }

                                i--; // Pentru ca agentiile sunt afisate cu index incepand de la 1, dar in array incep de la 0.

                                try {
                                    System.out.println();
                                    ServiceAgentieImobiliara.getInstance().afiseazaLocuinteAgentieIndexate(i);

                                    indexValid = true;

                                    boolean indexLocuintaValid = false;

                                    while (!indexLocuintaValid) {
                                        System.out.print("Alege locuinta pe care vrei sa o stergi: ");
                                        try {
                                            int iLocuinta = scanner.nextInt();
                                            scanner.nextLine();

                                            iLocuinta--;

                                            try {
                                                ServiceAgentieImobiliara.getInstance().stergeLocuinta(i, iLocuinta);

                                                indexLocuintaValid = true;

                                                System.out.println("Locuinta a fost stearsa.");
                                            } catch (IndexOutOfBoundsException exception) {
                                                afisareMesajIndexInvalid();
                                            }
                                        } catch (InputMismatchException exception) {
                                            scanner.nextLine();
                                            afisareMesajInputInvalid();
                                        }
                                    }
                                } catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            } catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajInputInvalid();
                            }
                        }
                    }
                    case 9 -> {
                        System.out.println("Ai ales 9: Calculeaza si afiseaza preturi de cumparare.");

                        ServiceAgentieImobiliara.getInstance().afiseazaNumeAgentii();

                        boolean indexValid = false;

                        while (!indexValid) {
                            System.out.print("Alege agentia pentru care vrei sa afisezi preturile de cumparare (sau 0 pentru toate agentiile, -1 pentru a anula): ");
                            try {
                                int i = scanner.nextInt();
                                scanner.nextLine();

                                if (i == -1) {
                                    skipSleep = true;
                                    break;
                                }

                                int aplicareDiscount;

                                System.out.print("Doresti sa aplici discountul? (0 pentru nu, 1 pentru da): ");

                                try {
                                    aplicareDiscount = scanner.nextInt();
                                    scanner.nextLine();

                                    if (aplicareDiscount != 0 && aplicareDiscount != 1)
                                        throw new ExceptieValoareInvalida("Optiunea pentru aplicarea discountului trebuie sa fie 0 sau 1!");
                                }
                                catch (InputMismatchException exception) {
                                    scanner.nextLine();
                                    afisareMesajOptiuneDiscountInvalida();
                                    break;
                                }
                                catch (ExceptieValoareInvalida exceptie) {
                                    System.out.println(exceptie.getMessage());
                                    break;
                                }

                                if (i == 0) {
                                    System.out.println();
                                    ServiceAgentieImobiliara.getInstance().afiseazaPreturiCumparareAgentii(aplicareDiscount);

                                    System.out.println("Apasa enter pentru a continua.");
                                    scanner.nextLine();

                                    skipSleep = true;
                                    break;
                                }

                                i--; // Pentru ca agentiile sunt afisate cu index incepand de la 1, dar in array incep de la 0.

                                try {
                                    System.out.println();
                                    ServiceAgentieImobiliara.getInstance().afiseazaPreturiCumparareAgentie(i, aplicareDiscount);

                                    indexValid = true;

                                    System.out.println("Apasa enter pentru a continua.");
                                    scanner.nextLine();

                                    skipSleep = true;
                                } catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            } catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajIndexInvalid();
                            }
                        }
                    }
                    case 10 -> {
                        System.out.println("Ai ales 10: Calculeaza si afiseaza chirii.");

                        ServiceAgentieImobiliara.getInstance().afiseazaNumeAgentii();

                        boolean indexValid = false;

                        while (!indexValid) {
                            System.out.print("Alege agentia pentru care vrei sa afisezi chiriile (sau 0 pentru toate agentiile, -1 pentru a anula): ");
                            try {
                                int i = scanner.nextInt();
                                scanner.nextLine();

                                if (i == -1) {
                                    skipSleep = true;
                                    break;
                                }

                                int aplicareDiscount;

                                System.out.print("Doresti sa aplici discountul? (0 pentru nu, 1 pentru da): ");

                                try {
                                    aplicareDiscount = scanner.nextInt();
                                    scanner.nextLine();

                                    if (aplicareDiscount != 0 && aplicareDiscount != 1)
                                        throw new ExceptieValoareInvalida("Optiunea pentru aplicarea discountului trebuie sa fie 0 sau 1!");
                                }
                                catch (InputMismatchException exception) {
                                    scanner.nextLine();
                                    afisareMesajOptiuneDiscountInvalida();
                                    break;
                                }
                                catch (ExceptieValoareInvalida exceptie) {
                                    System.out.println(exceptie.getMessage());
                                    break;
                                }

                                if (i == 0) {
                                    System.out.println();
                                    ServiceAgentieImobiliara.getInstance().afiseazaPreturiChiriiAgentii(aplicareDiscount);

                                    System.out.println("Apasa enter pentru a continua.");
                                    scanner.nextLine();

                                    skipSleep = true;
                                    break;
                                }

                                i--; // Pentru ca agentiile sunt afisate cu index incepand de la 1, dar in array incep de la 0.

                                try {
                                    System.out.println();
                                    ServiceAgentieImobiliara.getInstance().afiseazaPreturiChiriiAgentie(i, aplicareDiscount);

                                    indexValid = true;

                                    System.out.println("Apasa enter pentru a continua.");
                                    scanner.nextLine();

                                    skipSleep = true;
                                } catch (IndexOutOfBoundsException exception) {
                                    afisareMesajIndexInvalid();
                                }
                            } catch (InputMismatchException exception) {
                                scanner.nextLine();
                                afisareMesajIndexInvalid();
                            }
                        }
                    }
                    case 0 -> {
                        System.out.println("Ai ales 0: Inchide programul.");
                        System.out.println("Programul se va inchide.");

                        exitProgram = true;

                        ServiceCSVAgentieImobiliara.getInstance().writeToCSV();
                        ServiceCSVLocuinta.getInstance().writeToCSV();
                        ServiceAudit.getInstance().scrieMesaj("Programul a fost inchis.");
                    }
                    default -> throw new ExceptieSelectieInvalida();
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
