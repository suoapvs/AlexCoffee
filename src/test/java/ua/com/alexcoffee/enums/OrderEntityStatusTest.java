package ua.com.alexcoffee.enums;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.order.OrderStatus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OrderEntityStatusTest {
    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"OrderStatus\" - START.\n");
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testing class \"OrderStatus\" - FINISH.\n");
    }

    @Test
    public void valueOfTest() {
        System.out.print("-> valueOf() - ");

        OrderStatus orderStatus = OrderStatus.valueOf("NEW");
        assertTrue(orderStatus.equals(OrderStatus.NEW));
        assertFalse(orderStatus.equals(OrderStatus.WORK));

        System.out.println("OK!");
    }

    @Test
    public void valuesTest() {
        System.out.print("-> values() - ");

        OrderStatus[] orderStatus = OrderStatus.values();
        assertNotNull(orderStatus);
        assertTrue(orderStatus.length > 0);

        System.out.println("OK!");
    }
}
