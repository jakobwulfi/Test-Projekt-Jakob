package ordination;

import java.time.LocalDate;

public class PN {
    private final double antalEnheder;

    public double getAntalEnheder() {
        return antalEnheder;
    }

    /**
     * Registrer datoen for en anvendt dosis.
     */
    public boolean anvendDosis(LocalDate dato) {

        return false;
    }

    /** Returner antal gange ordinationen er anvendt. */
    public int antalGangeAnvendt() {

        return -1;
    }
}
