package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.enums.StatusEnum;

import static org.junit.Assert.*;

public class StatusTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"Status\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"Status\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        StatusEnum title = StatusEnum.NEW;
        String description = "Description";

        Status status = new Status(title, description);
        String line = "Title: " + title.name() + "\nDescription: " + description;

        assertEquals(status.toString(), line);

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Status status = new Status();
        assertEquals(status, status);

        status = new Status(StatusEnum.NEW, "NEW");
        assertTrue(status.equals(status));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Status status1 = new Status(StatusEnum.NEW, "NEW");
        Status status2 = new Status(StatusEnum.NEW, "NEW");

        assertTrue(status1.equals(status2));
        assertTrue(status2.equals(status1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Status status1 = new Status(StatusEnum.NEW, "NEW");
        Status status2 = new Status(StatusEnum.NEW, "NEW");
        Status status3 = new Status(StatusEnum.NEW, "NEW");

        assertTrue(status1.equals(status2));
        assertTrue(status2.equals(status3));
        assertTrue(status1.equals(status3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Status status1 = new Status(StatusEnum.NEW, "NEW");
        Status status2 = new Status(StatusEnum.NEW, "NEW");

        for (int i = 0; i < 10; i++) {
            assertTrue(status1.equals(status2));
        }

        System.out.println("OK!");
    }

    @Test
    public void setAndGetTitleTest() {
        System.out.print("-> setAndGetProducts() - ");

        Status status = new Status();
        status.setTitle(StatusEnum.NEW);

        assertNotNull(status.getTitle());
        assertEquals(status.getTitle(), StatusEnum.NEW);
        assertFalse(status.getTitle().equals(StatusEnum.CLOSED));

        System.out.println("OK!");
    }

    @Test
    public void setAndGetDescriptionTest() {
        System.out.print("-> setAndGetDescription() - ");

        Status status = new Status();
        status.setDescription(null);
        assertNotNull(status.getDescription());
        assertTrue(status.getDescription().isEmpty());

        String description = "Description";
        status.setDescription(description);
        assertEquals(status.getDescription(), description);

        System.out.println("OK!");
    }
}
