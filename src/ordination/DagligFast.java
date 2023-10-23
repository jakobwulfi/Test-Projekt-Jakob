package ordination;

import java.time.LocalDate;
import java.util.Arrays;

public class DagligFast extends  Ordination {

    private final Dosis[] doser;




    protected DagligFast(LocalDate startDato, LocalDate slutDato, Dosis[] doser) {
        super(startDato, slutDato);
        this.doser = doser;
    }

    @Override
    public double døgnDosis() {
        double døDosis = 0;
        for (Dosis dos : doser){
            døDosis += dos.getAntal();
        }
        return døDosis;
    }

    @Override
    public double samletDosis() {
        return døgnDosis() * antalDage();
    }
    @Override
    public String getType() {
        return "Daglig Fast";
    }
}
