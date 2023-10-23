package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DagligSkæv extends Ordination {
    private final List<Dosis> dosisList = new ArrayList<>();
    protected DagligSkæv(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }
    @Override
    public double samletDosis() {
        double samletDosis = this.døgnDosis() * this.antalDage();
        return samletDosis;
    }


    public List<Dosis> getDoser() {
        return dosisList;
    }

    @Override
    public double døgnDosis() {
        double antal = 0;
        for (Dosis d : dosisList) {
            antal += d.getAntal();
        }
        return antal;
    }
    @Override
    public String getType() {
        return "Daglig skæv.";
    }
    public void addDosis(LocalTime tid, double antal) {
        Dosis d = new Dosis(tid, antal);
        this.dosisList.add(d);
    }
}
