package ordination;

import gui.StartVindue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;

public class PN extends Ordination{
    private final double antalEnheder;
    private ArrayList<LocalDate> datoForDosis = new ArrayList<>();


    public PN(double antalEnheder, LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
        this.antalEnheder = antalEnheder;

    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    /**
     * Registrer datoen for en anvendt dosis.
     */
    public void anvendDosis(LocalDate dato) {
        if(getStartDato().isAfter(dato) && getSlutDato().isBefore(dato)){
            datoForDosis.add(dato);
        }
    }

    /** Returner antal gange ordinationen er anvendt. */
    public int antalGangeAnvendt() {
        int antalAnvendt = datoForDosis.size();
        return antalAnvendt;
    }

    @Override
    public double døgnDosis() {
        double daysBetween = (double) ChronoUnit.DAYS.between((Temporal) datoForDosis.get(0), (Temporal) datoForDosis.get(datoForDosis.size()));
        double dagligDosis = antalGangeAnvendt() * antalEnheder / daysBetween;
        return dagligDosis;
    }
    @Override
    public double samletDosis() {

        double totalDosis = 0;
        totalDosis = døgnDosis() * antalDage();

        return totalDosis;
    }

    @Override
    public String getType() {
        return "PN";
    }
}
