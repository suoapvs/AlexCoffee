package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.enums.RoleEnum;

import static org.junit.Assert.*;

public class RoleTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"Role\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"Role\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        RoleEnum title = RoleEnum.ADMIN;
        String description = "Description";

        Role role = new Role(title, description);

        String line = "Title: " + title.name() + "\nDescription: " + description;

        assertEquals(role.toString(), line);

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Role role = new Role();
        assertEquals(role, role);

        role = new Role(RoleEnum.ADMIN, "ADMIN");
        assertTrue(role.equals(role));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Role role1 = new Role(RoleEnum.ADMIN, "ADMIN");
        Role role2 = new Role(RoleEnum.ADMIN, "");

        assertTrue(role1.equals(role2));
        assertTrue(role2.equals(role1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Role role1 = new Role(RoleEnum.ADMIN, "ADMIN");
        Role role2 = new Role(RoleEnum.ADMIN, "");
        Role role3 = new Role(RoleEnum.ADMIN, "ADMIN");

        assertTrue(role1.equals(role2));
        assertTrue(role2.equals(role3));
        assertTrue(role1.equals(role3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Role role1 = new Role(RoleEnum.ADMIN, "ADMIN");
        Role role2 = new Role(RoleEnum.ADMIN, "");

        for (int i = 0; i < 10; i++) {
            assertTrue(role1.equals(role2));
        }

        System.out.println("OK!");
    }

    @Test
    public void setAndGetTitleTest() {
        System.out.print("-> setAndGetTitle() - ");

        Role role = new Role();
        role.setTitle(RoleEnum.ADMIN);

        assertNotNull(role.getTitle());
        assertEquals(role.getTitle(), RoleEnum.ADMIN);

        System.out.println("OK!");
    }

    @Test
    public void setDescriptionTest() {
        System.out.print("-> setAndGetDescription() - ");

        Role role = new Role();
        role.setDescription(null);
        assertNotNull(role.getDescription());
        assertTrue(role.getDescription().isEmpty());

        String description = "Description";
        role.setDescription(description);
        assertEquals(role.getDescription(), description);

        System.out.println("OK!");
    }
}
