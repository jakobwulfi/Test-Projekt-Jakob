package ordination;

import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalTime;

class DagligSkævTest {

    @Test
    void døgnDosis() {
        // Laver en ny dagligSkæv Ordination
        double[] antalEnheder = {3, 2};
        LocalTime[] tidspunkter = {LocalTime.parse("10:00"), LocalTime.parse("16:00")};
        DagligSkæv dskæv1 = new DagligSkæv(LocalDate.parse("2019-01-23"), LocalDate.parse("2019-01-24"),
                tidspunkter, antalEnheder);
        // Anden testcase
        double[] antalEnheder2 = {5,2,3};
        LocalTime[] tidspunkter2 = {LocalTime.parse("12:00"), LocalTime.parse("15:00"), LocalTime.parse("10:00")};
        DagligSkæv dskæv2 = new DagligSkæv(LocalDate.parse("2019-01-23"), LocalDate.parse("2019-01-24"),
                tidspunkter2, antalEnheder2);
        // Assertion
        double result1 = dskæv1.døgnDosis();
        double result2 = dskæv2.døgnDosis();
        assertEquals(5, result1, 0001);
        assertEquals(10, result2, 0001);
    }

    @Test
    void samletDosis() {
        // Testcase 1 Arrange
        double[] antalEnheder = {3};
        LocalTime[] tidspunkter = {LocalTime.parse("10:00")};
        DagligSkæv dskæv1 = new DagligSkæv(LocalDate.parse("2019-01-23"), LocalDate.parse("2019-01-24"),
                tidspunkter, antalEnheder);
        // Act
        double tc1Result = dskæv1.samletDosis();
        // Assert
        assertEquals(6,tc1Result,001);

        // Testcase 2
        double[] antalEnheder2 = {3};
        LocalTime[] tidspunkter2 = {LocalTime.parse("10:00")};
        DagligSkæv dskæv2 = new DagligSkæv(LocalDate.parse("2019-01-20"), LocalDate.parse("2019-01-29"),
                tidspunkter2, antalEnheder2);
        double tc2Result = dskæv2.samletDosis();
        assertEquals(30,tc2Result,001);

        // Testcase 3
        double[] antalEnheder3 = {5,2,3};
        LocalTime[] tidspunkter3 = {LocalTime.parse("12:00"), LocalTime.parse("15:00"), LocalTime.parse("00:00")};
        DagligSkæv dskæv3 = new DagligSkæv(LocalDate.parse("2019-01-23"), LocalDate.parse("2019-01-23"),
                tidspunkter3, antalEnheder3);
        double tc3Result = dskæv3.samletDosis();
        assertEquals(10,tc3Result,001);

        // Testcase 4
        double[] antalEnheder4 = {5,2,3};
        LocalTime[] tidspunkter4 = {LocalTime.parse("12:00"), LocalTime.parse("15:00"), LocalTime.parse("00:00")};
        DagligSkæv dskæv4 = new DagligSkæv(LocalDate.parse("2019-01-20"), LocalDate.parse("2019-01-29"),
                tidspunkter4, antalEnheder4);
        double tc4Result = dskæv4.samletDosis();

        // Assertions





        assertEquals(100,tc4Result,001);
    }
}