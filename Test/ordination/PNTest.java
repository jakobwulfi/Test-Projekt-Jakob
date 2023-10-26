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
        PN pn1 = new PN(1,LocalDate.parse("2019-02-12"),LocalDate.parse("2019-02-19"));

        pn1.anvendDosis(LocalDate.parse("2019-02-12"));
        pn1.anvendDosis(LocalDate.parse("2019-02-14"));

        assertTrue(pn1.getDatoForDosis().size() == 2);
    }

    public void testDøgnDosis() {
        PN pn1 = new PN(2,LocalDate.parse("2019-02-12"),LocalDate.parse("2019-02-19"));

        pn1.anvendDosis(LocalDate.parse("2019-02-12"));
        pn1.anvendDosis(LocalDate.parse("2019-02-15"));
        pn1.anvendDosis(LocalDate.parse("2019-02-16"));


        assertTrue(pn1.døgnDosis() == 1);


    }

    public void testSamletDosis() {
    }
}