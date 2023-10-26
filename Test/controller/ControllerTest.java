package controller;

import ordination.DagligFast;
import ordination.Lægemiddel;
import ordination.PN;
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
        //TC1
        Storage storage = new Storage();
        Controller.setStorage(storage);
        Patient patient1 = Controller.opretPatient("121256-0512", "Jane Jensen", 63.4);
        LocalDate startDato1 = LocalDate.parse("2023-07-09");
        LocalDate slutDato1 = LocalDate.parse("2023-07-12");
        Lægemiddel lægemiddel = new Lægemiddel("Paracetamol",1,1.5,2, "ml");

        PN pnOrdination1 = Controller.opretPNOrdination(startDato1,slutDato1,patient1,lægemiddel,5);

        assertSame(patient1.getOrdiantioner().get(0),pnOrdination1);

        //TC2
        Patient patient2 = Controller.opretPatient("121256-0512", "Jane Jensen", 63.4);
        LocalDate startDato2 = LocalDate.parse("2023-07-09");
        LocalDate slutDato2 = LocalDate.parse("2023-07-09");

        PN pnOrdination2 = Controller.opretPNOrdination(startDato2,slutDato2,patient2,lægemiddel,5);

        assertSame(patient2.getOrdiantioner().get(0),pnOrdination2);

        //TC3
        Patient patient3 = Controller.opretPatient("121256-0512", "Jane Jensen", 63.4);
        LocalDate startDato3 = LocalDate.parse("2023-07-09");
        LocalDate slutDato3 = LocalDate.parse("2023-06-09");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Controller.opretPNOrdination(startDato3,slutDato3,patient3,lægemiddel,5));

        assertEquals("Start dato skal være før slut dato ", exception.getMessage());

        //TC4
        Patient patient4 = Controller.opretPatient("121256-0512", "Jane Jensen", 63.4);
        LocalDate startDato4 = LocalDate.parse("2023-07-09");
        LocalDate slutDato4 = LocalDate.parse("2023-12-09");

        PN pnOrdination4 = Controller.opretPNOrdination(startDato4,slutDato4,patient4,lægemiddel,-5);

        assertEquals(patient4.getOrdiantioner().get(0),pnOrdination4);

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