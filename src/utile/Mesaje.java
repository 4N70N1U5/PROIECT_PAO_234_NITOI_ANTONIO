package utile;

public class Mesaje {
    public static void Optiuni() {
        System.out.println("Te rog alege o optiune:");
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
        System.out.println("Optiunea 0: Inchide programul.");
        System.out.print("Optiunea aleasa: ");
    }

    public static void InputInvalid() {
        System.out.println("Optiune invalida! Te rog incearca din nou!");
    }

    public static void IndexInvalid() {
        System.out.println("Index invalid! Te rog incearca din nou!");
    }
}
