package controller;

import ordination.DagligSkæv;
import ordination.Lægemiddel;
import ordination.Patient;
import ordination.DagligFast;
import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import controller.Controller;
import org.junit.jupiter.api.*;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import storage.Storage;

import java.time.LocalDate;

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
        // Basisdata, initStorage() fra Controller kaldes for at få data
        double startvægt = 25; // kg
        double slutvægt = 80; // kg
        Controller.initStorage();
        Lægemiddel acetylsalicylsyre = Controller.getAllLægemidler().get(0);

        // Testcase 1 Arrange & Act
        double result1 = Controller.antalOrdinationerPrVægtPrLægemiddel(startvægt,slutvægt,acetylsalicylsyre);

        // Assert
        assertEquals(1,result1);

        // Testcase 2
        double startvægt2 = 80; // kg
        double slutvægt2 = 25; // kg

        double result2 = Controller.antalOrdinationerPrVægtPrLægemiddel(startvægt2,slutvægt2,acetylsalicylsyre);

        assertEquals(0, result2);

        // Testcase 3 -> test for om søgning er inklusiv af grænseværdier
        double startvægt3 = 63.4; // kg
        double slutvægt3 = 63.4; // kg vægt for Jane i initStorage()

        double result3 = Controller.antalOrdinationerPrVægtPrLægemiddel(startvægt3,slutvægt3,acetylsalicylsyre);

        assertEquals(1, result3);

        // Testcase 4 -> Tester for et andet lægemiddel, der burde have antal ordinationer > 1
        Lægemiddel parace = Controller.getAllLægemidler().get(1);

        double result4 = Controller.antalOrdinationerPrVægtPrLægemiddel(startvægt,slutvægt,parace);

        assertEquals(2, result4);
    }
}