package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DagligSkæv extends Ordination {
    private final List<Dosis> dosisList = new ArrayList<>();
    private final LocalTime[] klokkeslet;
    private final double[] antalEnheder;
    public DagligSkæv(LocalDate startDato, LocalDate slutDato, LocalTime[] klokkeslet, double[] antalEnheder) {
        super(startDato, slutDato);
        this.klokkeslet = klokkeslet;
        this.antalEnheder = antalEnheder;
    }
    @Override
    public double samletDosis() {
        double samletDosis = this.døgnDosis() * this.antalDage();
        return samletDosis;
    }


    public List<Dosis> getDoser() {
        return dosisList;
    }

    public LocalTime[] getKlokkeslet() {
        return klokkeslet;
    }

    public double[] getAntalEnheder() {
        return antalEnheder;
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
