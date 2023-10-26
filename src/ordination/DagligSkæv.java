package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DagligSkæv extends Ordination {
    private final ArrayList<Dosis> dosis = new ArrayList<>();
    private final LocalTime[] klokkeslet;
    private final double[] antalEnheder;
    public DagligSkæv(LocalDate startDato, LocalDate slutDato, LocalTime[] klokkeslet, double[] antalEnheder) {
        super(startDato, slutDato);
        this.klokkeslet = klokkeslet;
        this.antalEnheder = antalEnheder;
        for (int i = 0; i < this.antalEnheder.length; i++) {
            Dosis d = new Dosis(klokkeslet[i], antalEnheder[i]);
            dosis.add(d);
        }
    }
    @Override
    public double samletDosis() {
        double samletDosis = this.døgnDosis() * this.antalDage();
        return samletDosis;
    }

    public LocalTime[] getKlokkeslet() {
        return klokkeslet;
    }

    public double[] getAntalEnheder() {
        return antalEnheder;
    }
    public ArrayList<Dosis> getDoser() {
        return dosis;
    }
    @Override
    public double døgnDosis() {
        double antal = 0;
        for (int i = 0; i < antalEnheder.length; i++){
            antal += antalEnheder[i];
        }
        return antal;
    }
    @Override
    public String getType() {
        return "Daglig skæv.";
    }
}
