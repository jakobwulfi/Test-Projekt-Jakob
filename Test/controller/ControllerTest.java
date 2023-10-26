package controller;

import ordination.DagligFast;
import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import controller.Controller;
import org.junit.jupiter.api.*;
import storage.Storage;

import java.time.LocalDate;

class ControllerTest {

    @Test
    void opretPNOrdination() {
    }

    @Test
    void opretDagligFastOrdination() {
        Storage storage = new Storage();
        Controller.setStorage(storage);
        Patient patient =  Controller.opretPatient("121256-0512", "Jane Jensen", 63.4);
        Lægemiddel lægemiddel = Controller.opretLægemiddel("Paracetamol", 1, 1.5,
                2, "Ml");
        LocalDate start = LocalDate.of(2023,9,7);
        LocalDate slut = LocalDate.of(2023,9,12);
        double morgenAntal = 0;
        double middagAntal = 1;
        double aftenAntal = 2;
        double natAntal = 3;

        DagligFast dagligFast1 = Controller.opretDagligFastOrdination(start, slut,patient,lægemiddel,
                morgenAntal,middagAntal,aftenAntal,natAntal);

        //Test 1 oprettes der et objekt af klassen DagligFast
        assertNotNull(dagligFast1);
        assertSame(lægemiddel,dagligFast1.getLægemiddel());
        assertSame(start,dagligFast1.getStartDato());
        assertSame(slut,dagligFast1.getSlutDato());
        assertEquals(morgenAntal,dagligFast1.getDoser()[0].getAntal());
        assertEquals(middagAntal,dagligFast1.getDoser()[1].getAntal());
        assertEquals(aftenAntal,dagligFast1.getDoser()[2].getAntal());
        assertEquals(natAntal,dagligFast1.getDoser()[3].getAntal());
        Patient testPatient = Controller.getAllPatienter().get(0);
        DagligFast testDagligfast = (DagligFast) Controller.getAllPatienter().get(0).getOrdiantioner().get(0);
        assertSame(dagligFast1,testDagligfast);
        assertSame(patient,testPatient);


        //Test 2 slut dato før start dato giver en illegal argument exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.opretDagligFastOrdination(LocalDate.of(2023,9,7),
                LocalDate.of(2023,9,6),patient,lægemiddel,
                0,1,2,3));
        assertEquals(exception.getMessage(),"Start dato er efter slut dato");


        //Test 3 antal enheder på en af tidspunkterne er under nul og et objekt med forkert parameter oprettes
        double altMorgen = -1;
        DagligFast dagligFast3 = Controller.opretDagligFastOrdination(start,
                slut,patient,lægemiddel,
                altMorgen,middagAntal,aftenAntal,natAntal);
        DagligFast dagligFastTest3 = (DagligFast) Controller.getAllPatienter().get(0).getOrdiantioner().get(1);
        assertEquals(dagligFast3.getDoser()[0].getAntal(), dagligFastTest3.getDoser()[0].getAntal());
        assertNotNull(dagligFast3);
        assertSame(lægemiddel,dagligFast3.getLægemiddel());
        assertSame(start,dagligFast3.getStartDato());
        assertSame(slut,dagligFast3.getSlutDato());
        assertEquals(altMorgen,dagligFast3.getDoser()[0].getAntal());
        assertEquals(middagAntal,dagligFast3.getDoser()[1].getAntal());
        assertEquals(aftenAntal,dagligFast3.getDoser()[2].getAntal());
        assertEquals(natAntal,dagligFast3.getDoser()[3].getAntal());
        Patient testPatient3 = Controller.getAllPatienter().get(0);
        DagligFast testDagligfast3 = (DagligFast) Controller.getAllPatienter().get(0).getOrdiantioner().get(1);
        assertSame(dagligFast3,testDagligfast3);
        assertSame(patient,testPatient3);



    }

    @Test
    void opretDagligSkævOrdination() {
    }

    @Test
    void anvendOrdinationPN() {
    }

    @Test
    void anbefaletDosisPrDøgn() {
        Storage storage = new Storage();
        Controller.setStorage(storage);

        //Test 1 mellem størrelse
        Patient mid = new Patient("121256-0512","Jane Jensen", 63.4);
        Lægemiddel paracetamol = new Lægemiddel("Paracetamol",1,1.5,
                2,"Ml");
        double anbefaletMid = Controller.anbefaletDosisPrDøgn(mid,paracetamol);
        assertEquals(anbefaletMid,95.1,0001);

        //Test 2 lav vægt
        Patient low = new Patient("123456-7890","Sebald",24);
        double anbefaletLav = Controller.anbefaletDosisPrDøgn(low,paracetamol);
        assertEquals(anbefaletLav,24,0001);

        //Test 3 høj vægt
        Patient høj = new Patient("098765-4321","Fede Dorit", 121);
        double anbefaletHøj = Controller.anbefaletDosisPrDøgn(høj,paracetamol);
        assertEquals(anbefaletHøj,242);





    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }
}