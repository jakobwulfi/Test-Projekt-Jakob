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
        if(getStartDato().minusDays(1).isBefore(dato) && getSlutDato().plusDays(1).isAfter(dato)){
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
        if(datoForDosis.isEmpty()){
            return 0;
        }

        LocalDate start = datoForDosis.get(0);
        LocalDate slut = start;

        for (LocalDate localDate : datoForDosis){
            if (localDate.isBefore(start)){
                start = localDate;
            }
        }

        for(LocalDate localDate : datoForDosis){
            if (localDate.isAfter(slut)){
                slut = localDate;
            }
        }


        double dagligDosis;
        if (start.isEqual(slut)) {
            dagligDosis = antalGangeAnvendt() * antalEnheder;
        } else {
            dagligDosis = antalGangeAnvendt() * antalEnheder / (start.until(slut, ChronoUnit.DAYS) +1);
        }
        return dagligDosis;
    }
    @Override
    public double samletDosis() {
        double totalDosis = 0;
        totalDosis = antalEnheder * antalGangeAnvendt();

        return totalDosis;
    }

    @Override
    public String getType() {
        return "PN";
    }
}
