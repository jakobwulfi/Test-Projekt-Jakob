package controller;

import ordination.DagligSkæv;
import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import controller.Controller;
import org.junit.jupiter.api.*;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;

class ControllerTest {
    @BeforeEach
    public void setUp() {
        Storage storage = new Storage();
        Controller.setStorage(storage);
    }
    @Test
    void opretPNOrdination() {
    }

    @Test
    void opretDagligFastOrdination() {
    }

    @Test
    void opretDagligSkævOrdination() {
        // Basisdata
        Patient finn = Controller.opretPatient("070985-1153", "Finn Madsen", 83.2);
        Lægemiddel fucidin = Controller.opretLægemiddel(
                "Fucidin", 0.025, 0.025,
                0.025, "Styk");
        double[] antalEnheder = {0.5, 1, 2.5, 3};
        LocalTime[] tidspunkter = {LocalTime.parse("12:00"),LocalTime.parse("12:40"),LocalTime.parse("16:00"),
                LocalTime.parse("18:45")};
        LocalDate startdato = LocalDate.parse("2019-01-23");
        LocalDate slutDato = LocalDate.parse("2019-01-24");

        // TestCase 1 Arrange & Act
        DagligSkæv tc1 = Controller.opretDagligSkævOrdination(startdato,slutDato,finn,fucidin,tidspunkter,antalEnheder);

        // Assert - checker at objektet bliver oprettet med de rigtige parametre
        assertNotNull(tc1);
        assertEquals(finn.getOrdiantioner().get(0), tc1);
        assertEquals(fucidin, tc1.getLægemiddel());
        assertEquals(startdato, tc1.getStartDato());
        assertEquals(slutDato, tc1.getSlutDato());
        assertArrayEquals(antalEnheder, tc1.getAntalEnheder());
        assertArrayEquals(tidspunkter,tc1.getKlokkeslet());
        Patient testPatient = Controller.getAllPatienter().get(0);
        DagligSkæv tc1Storage = (DagligSkæv) testPatient.getOrdiantioner().get(0);
        assertEquals(tc1, tc1Storage);

        // Testcase 2
        LocalDate slutdato2 = startdato;
        DagligSkæv tc2 = Controller.opretDagligSkævOrdination(startdato,slutdato2,finn,fucidin,tidspunkter,antalEnheder);

        assertNotNull(tc2);
        assertEquals(finn.getOrdiantioner().get(1), tc2);
        assertEquals(fucidin, tc2.getLægemiddel());

        // Testcase 3 -> Patient = null, tester for NullPointerException, tester egentlig for en precondition her
        Exception exception = assertThrows(NullPointerException.class,
                () -> Controller.opretDagligSkævOrdination(startdato,slutDato,null,fucidin,tidspunkter,antalEnheder));

        // Testcase 4 -> klokkeslet[].length != antalEnheder.length
        double[] antalEnheder2 = {0.5, 1, 2.5, 3, 7}; // tilføjer et ekstra tal, så arraylængden ikke matcher

        Exception exception2 = assertThrows(IllegalArgumentException.class,
                () -> Controller.opretDagligSkævOrdination(startdato,slutDato,finn,fucidin,tidspunkter,antalEnheder2));
        assertEquals("Antal af klokkeslet og enheder matcher ikke.",exception2.getMessage());

        // Testcase 5 -> startDato > slutdato
        LocalDate startDato2 = LocalDate.parse("2019-01-25");

        Exception exception3 = assertThrows(IllegalArgumentException.class,
                () -> Controller.opretDagligSkævOrdination(startDato2,slutDato,finn,fucidin,tidspunkter,antalEnheder2));
        assertEquals("Startdato er efter slutdato.", exception3.getMessage());
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