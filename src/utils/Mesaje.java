package utils;

public class Mesaje {
    public static void afisareOptiuni() {
        System.out.println("Te rog alege o optiune (pentru optiunile 5-10 trebuie sa existe agentii imobiliare):");
        System.out.println("Optiunea 1: Adauga o agentie imobiliara.");
        System.out.println("Optiunea 2: Afiseaza agentiile imobiliare.");
        System.out.println("Optiunea 3: Modifica o agentie imobiliara.");
        System.out.println("Optiunea 4: Sterge o agentie imobiliara.");
        System.out.println("Optiunea 5: Adauga o locuinta.");
        System.out.println("Optiunea 6: Afiseaza locuintele.");
        System.out.println("Optiunea 7: Modifica o locuinta.");
        System.out.println("Optiunea 8: Sterge o locuinta.");
        System.out.println("Optiunea 9: Calculeaza si afiseaza preturi de cumparare.");
        System.out.println("Optiunea 10: Calculeaza si afiseaza chirii.");
        System.out.println("Optiunea 11: Salveaza modificarile.");
        System.out.println("Optiunea 0: Inchide programul.");
        System.out.print("Optiunea aleasa: ");
    }

    public static void afisareTipuriLocuinte() {
        System.out.println("Alege un tip de locuinta:");
        System.out.println("1: Apartament simplu");
        System.out.println("2: Apartament duplex");
        System.out.println("3: Apartament cu gradina");
        System.out.println("4: Casa simpla");
        System.out.println("5: Casa cu curte");
        System.out.println("6: Casa cu piscina");
        System.out.print("Optiunea aleasa: ");
    }

    public static void afisareOptiuniMateriale() {
        System.out.println("Optiuni materiale pentru structura de rezistenta:");
        System.out.println("1: Lemn");
        System.out.println("2: Caramida");
        System.out.println("3: Beton");
        System.out.println("4: Beton armat");
        System.out.println("5: Metal");
        System.out.print("Material structura de rezistenta: ");
    }

    public static void afisareMesajInputInvalid() {
        System.out.println("Input invalid! Te rog incearca din nou!");
    }

    public static void afisareMesajIndexInvalid() {
        System.out.println("Index invalid! Te rog incearca din nou!");
    }

    public static void afisareMesajOptiuneInvalida() {
        System.out.println("Optiune invalida! Te rog incearca din nou!");
    }

    public static void afisareMesajOptiuneDiscountInvalida() {
        System.out.println("Optiunea pentru aplicarea discountului trebuie sa fie 0 sau 1!");
    }
}
