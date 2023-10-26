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

        DagligFast dagligFast1 = Controller.opretDagligFastOrdination(LocalDate.of(2023,9,7),
                LocalDate.of(2023,9,12),patient,lægemiddel,
                0,1,2,3);

        DagligFast dagligFastTest1 = (DagligFast) Controller.getAllPatienter().get(0).getOrdiantioner().get(0);
        //Test 1 oprettes der et objekt af klassen DagligFast
        assertSame(dagligFast1, dagligFastTest1);

        //Test 2 slut dato før start dato giver en illegal argument exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.opretDagligFastOrdination(LocalDate.of(2023,9,7),
                LocalDate.of(2023,9,6),patient,lægemiddel,
                0,1,2,3));
        assertEquals(exception.getMessage(),"Start dato er efter slut dato");


        //Test 3 antal enheder på en af tidspunkterne er under nul og et objekt med forkert parameter oprettes
        DagligFast dagligFast3 = Controller.opretDagligFastOrdination(LocalDate.of(2023,9,7),
                LocalDate.of(2023,9,12),patient,lægemiddel,
                -1,1,2,3);
        DagligFast dagligFastTest3 = (DagligFast) Controller.getAllPatienter().get(0).getOrdiantioner().get(1);
        assertEquals(dagligFast3.getDoser()[0].getAntal(), dagligFastTest3.getDoser()[0].getAntal());

        //Test 4 patient er lig med null og en null point exception opstår
        Exception expception4 = assertThrows(NullPointerException.class,() -> Controller.opretDagligFastOrdination(LocalDate.of(2023,9,7),
                LocalDate.of(2023,9,12),null,lægemiddel,
                0,1,2,3));


        //Test 5 lægemiddel er lig med null
        //Exception exception5 = assertThrows(NullPointerException.class,() -> Controller.opretDagligFastOrdination(LocalDate.of(2023,9,7),
        //      LocalDate.of(2023,9,12),patient,null,
        //    0,1,2,3));



    }

    @Test
    void opretDagligSkævOrdination() {
    }

    @Test
    void anvendOrdinationPN() {
    }

    @Test
    void anbefaletDosisPrDøgn() {
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }
}