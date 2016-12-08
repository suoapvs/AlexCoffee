package ua.com.alexcoffee.enums;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleEnumTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"RoleEnum\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"RoleEnum\" - FINISH.\n");
    }

    @Test
    public void valueOfTest() {
        System.out.print("-> valueOf() - ");

        RoleEnum roleEnum = RoleEnum.valueOf("ADMIN");
        assertTrue(roleEnum.equals(RoleEnum.ADMIN));
        assertFalse(roleEnum.equals(RoleEnum.CLIENT));

        System.out.println("OK!");
    }

    @Test
    public void valuesTest() {
        System.out.print("-> values() - ");

        RoleEnum[] roleEnumps = RoleEnum.values();
        assertNotNull(roleEnumps);
        assertTrue(roleEnumps.length > 0);

        System.out.println("OK!");
    }
}