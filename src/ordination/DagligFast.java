package ordination;

import java.time.LocalDate;
import java.util.Arrays;

public class DagligFast extends  Ordination {

    private final Dosis[] doser = new Dosis[4];




    public DagligFast(LocalDate startDato, LocalDate slutDato, Dosis dosis1, Dosis dosis2, Dosis dosis3, Dosis dosis4) {
        super(startDato, slutDato);
        this.doser[0] = dosis1;
        this.doser[1] = dosis2;
        this.doser[2] = dosis3;
        this.doser[3] = dosis4;
    }

    public Dosis[] getDoser() {
        return doser;
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
