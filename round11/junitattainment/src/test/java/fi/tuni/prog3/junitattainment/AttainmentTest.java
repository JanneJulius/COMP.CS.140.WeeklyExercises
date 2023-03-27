package fi.tuni.prog3.junitattainment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AttainmentTest {

    @Test
    void testConstructor() {
        Attainment a = new Attainment("ABC123", "012345678", 3);
        assertEquals("ABC123", a.getCourseCode());
        assertEquals("012345678", a.getStudentNumber());
        assertEquals(3, a.getGrade());
    }

    @Test
    public void testIllegalArgumentException() {
        try {
            new Attainment(null, "01234567", 3);
            org.junit.jupiter.api.Assertions.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Test passed
        }
    
        try {
            new Attainment("CS1234", null, 3);
            org.junit.jupiter.api.Assertions.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Test passed
        }
    
        try {
            new Attainment("CS1234", "01234567", -1);
            org.junit.jupiter.api.Assertions.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Test passed
        }
    
        try {
            new Attainment("CS1234", "01234567", 6);
            org.junit.jupiter.api.Assertions.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Test passed
        }
    }


    @Test
    void testToString() {
        Attainment a = new Attainment("ABC123", "012345678", 3);
        assertEquals("ABC123 012345678 3", a.toString());
    }

    @Test
    void testCompareTo() {
        Attainment a1 = new Attainment("ABC123", "012345678", 3);
        Attainment a2 = new Attainment("DEF456", "012345678", 4);
        Attainment a3 = new Attainment("ABC123", "987654321", 5);
        Attainment a4 = new Attainment("ABC123", "012345678", 5);

        assertTrue(a1.compareTo(a1) == 0);
        assertTrue(a1.compareTo(a2) < 0);
        assertTrue(a1.compareTo(a3) < 0);
        assertTrue(a1.compareTo(a4) == 0);
        assertTrue(a2.compareTo(a1) > 0);
        assertTrue(a3.compareTo(a1) > 0);
        assertTrue(a4.compareTo(a1) == 0);
    }
}
