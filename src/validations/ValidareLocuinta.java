package validations;

public class ValidareLocuinta {
    public static boolean validareTipLocuinta(int tipLocuinta) {
        return 1 <= tipLocuinta && tipLocuinta <= 6;
    }

    public static boolean validareValoarePozitiva(int x) {
        return x > 0;
    }

    public static boolean validareValoarePozitiva(double x) {
        return x > 0;
    }

    public static boolean validareEtaj(int x) {
        return x >= -1;
    }

    public static boolean validareEtajApartamentGradina (int x) {
        return x == 0;
    }

    public static boolean validareNumarEtaje(int x) {
        return x >= 1;
    }

    public static boolean validareNumarEtajeDuplex(int x) {
        return x >= 2;
    }

    public static boolean validareMaterial(int x) {
        return 1 <= x && x <= 5;
    }
}
