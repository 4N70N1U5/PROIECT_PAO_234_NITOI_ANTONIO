package exceptions;

public class ExceptieSelectieInvalida extends RuntimeException {
    public ExceptieSelectieInvalida() {
        super("Selectie invalida! Va rog incercati din nou!");
    }
}
