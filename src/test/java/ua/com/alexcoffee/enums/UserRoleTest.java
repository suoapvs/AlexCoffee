package ua.com.alexcoffee.enums;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.user.UserRole;

import static org.junit.Assert.*;

public class UserRoleTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"UserRole\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"UserRole\" - FINISH.\n");
    }

    @Test
    public void valueOfTest() {
        System.out.print("-> valueOf() - ");

        UserRole userRole = UserRole.valueOf("ADMIN");
        assertTrue(userRole.equals(UserRole.ADMIN));
        assertFalse(userRole.equals(UserRole.CLIENT));

        System.out.println("OK!");
    }

    @Test
    public void valuesTest() {
        System.out.print("-> values() - ");

        UserRole[] userRoleEnumps = UserRole.values();
        assertNotNull(userRoleEnumps);
        assertTrue(userRoleEnumps.length > 0);

        System.out.println("OK!");
    }
}