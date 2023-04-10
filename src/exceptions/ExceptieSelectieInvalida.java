package exceptions;

public class ExceptieSelectieInvalida extends RuntimeException {
    public ExceptieSelectieInvalida() {
        super("Optiune invalida! Te rog incearca din nou!");
    }
}
