package ua.com.alexcoffee.enums;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StatusEnumTest {
    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"StatusEnum\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"StatusEnum\" - FINISH.\n");
    }

    @Test
    public void valueOfTest() {
        System.out.print("-> valueOf() - ");

        StatusEnum statusEnum = StatusEnum.valueOf("NEW");
        assertTrue(statusEnum.equals(StatusEnum.NEW));
        assertFalse(statusEnum.equals(StatusEnum.WORK));

        System.out.println("OK!");
    }

    @Test
    public void valuesTest() {
        System.out.print("-> values() - ");

        StatusEnum[] statusEnum = StatusEnum.values();
        assertNotNull(statusEnum);
        assertTrue(statusEnum.length > 0);

        System.out.println("OK!");
    }
}
