package ordination;

import static org.junit.jupiter.api.Assertions.*;
import controller.Controller;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalTime;


class DagligFastTest {



    @Test
    void døgnDosis() {
        Patient patient = new Patient("121256-0512", "Jane Jensen", 63.4);
        Dosis dosis1 =new Dosis(LocalTime.of(8,0),2);
        Dosis dosis2 =new Dosis(LocalTime.of(12,0),0);
        Dosis dosisAlt2 =new Dosis(LocalTime.of(12,0),1);
        Dosis dosis3 =new Dosis(LocalTime.of(18,0),3);
        Dosis dosis4 =new Dosis(LocalTime.of(0,0),0);
        Dosis dosisAlt4 =new Dosis(LocalTime.of(0,0),1);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2019,2,12),
                LocalDate.of(2019,2,19),dosis1,dosis2,dosis3,dosis4);
        DagligFast dagligFastAlt = new DagligFast(LocalDate.of(2019,2,12),
                LocalDate.of(2019,2,19),dosis1,dosisAlt2,dosis3,dosisAlt4);
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15,
                0.16, "Styk");
        patient.addOrdination(dagligFastAlt);
        patient.addOrdination(dagligFast);

        double test1 = dagligFast.døgnDosis();
        assertEquals(5,test1,0001);

        double test2 = dagligFastAlt.døgnDosis();
        assertEquals(7,test2,0001);
    }


    @Test
    void samletDosis() {
        Patient patient = new Patient("121256-0512", "Jane Jensen", 63.4);
        Dosis dosis1 =new Dosis(LocalTime.of(8,0),2);
        Dosis dosis2 =new Dosis(LocalTime.of(12,0),0);
        Dosis dosisAlt2 =new Dosis(LocalTime.of(12,0),1);
        Dosis dosis3 =new Dosis(LocalTime.of(18,0),3);
        Dosis dosis4 =new Dosis(LocalTime.of(0,0),0);
        Dosis dosisAlt4 =new Dosis(LocalTime.of(0,0),1);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2019,2,12),
                LocalDate.of(2019,2,19),dosis1,dosis2,dosis3,dosis4);
        DagligFast dagligFastAlt = new DagligFast(LocalDate.of(2019,2,12),
                LocalDate.of(2019,2,19),dosis1,dosisAlt2,dosis3,dosisAlt4);
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15,
                0.16, "Styk");
        patient.addOrdination(dagligFastAlt);
        patient.addOrdination(dagligFast);

        double test1 = dagligFast.samletDosis();
        assertEquals(40,test1,0001);

        double test2 = dagligFastAlt.samletDosis();
        assertEquals(56,test2,0001);
    }
}