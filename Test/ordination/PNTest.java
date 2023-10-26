package ordination;

import junit.framework.TestCase;

import java.time.LocalDate;

public class PNTest extends TestCase {


    public void testAnvendDosis() {
        //Testcase I arrange
        //TestCase 1, tilføj dato til array
        PN pn1 = new PN(1,LocalDate.parse("2019-02-12"),LocalDate.parse("2019-02-19"));



        //Act
        pn1.anvendDosis(LocalDate.parse("2019-02-12"));
        //Assertion
        assertTrue(pn1.getDatoForDosis().contains(LocalDate.parse("2019-02-12")));
        //TestCase 2 - Tilføj endnu en dato samme dag
        //act
        pn1.anvendDosis(LocalDate.parse("2019-02-12"));
        //Assert
        assertTrue(pn1.getDatoForDosis().indexOf(LocalDate.parse(("2019-02-12"))) != pn1.getDatoForDosis().lastIndexOf(LocalDate.parse("2019-02-12")));

    }

    public void testAntalGangeAnvendt() {
        //TC1
        PN pn1 = new PN(1,LocalDate.parse("2019-02-12"),LocalDate.parse("2019-02-19"));

        pn1.anvendDosis(LocalDate.parse("2019-02-12"));
        pn1.anvendDosis(LocalDate.parse("2019-02-14"));

        assertTrue(pn1.getDatoForDosis().size() == 2);
    }

    public void testDøgnDosis() {
        //TC2
        PN pn1 = new PN(2,LocalDate.parse("2019-10-25"),LocalDate.parse("2019-10-31"));

        pn1.anvendDosis(LocalDate.parse("2019-10-25"));
        pn1.anvendDosis(LocalDate.parse("2019-10-29"));
        pn1.anvendDosis(LocalDate.parse("2019-10-31"));


        assertEquals(1, pn1.døgnDosis(),0001);

       //TC2
        PN pn2 = new PN(2,LocalDate.parse("2019-10-25"),LocalDate.parse("2019-10-31"));

        pn1.anvendDosis(LocalDate.parse("2019-10-25"));
        pn1.anvendDosis(LocalDate.parse("2019-10-25"));
        pn1.anvendDosis(LocalDate.parse("2019-10-31"));

        assertEquals(0.43,pn2.døgnDosis(),0001);

    }

    public void testSamletDosis() {
        PN pn1 = new PN(2,LocalDate.parse("2019-10-26"),LocalDate.parse("2019-10-31"));

        pn1.anvendDosis(LocalDate.parse("2019-10-26"));
        pn1.anvendDosis(LocalDate.parse("2019-10-29"));
        pn1.anvendDosis(LocalDate.parse("2019-10-31"));

        assertEquals(6.00,pn1.samletDosis(),0001);
    }
}