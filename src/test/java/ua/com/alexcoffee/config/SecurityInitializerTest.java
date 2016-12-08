package ua.com.alexcoffee.config;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SecurityInitializerTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"SecurityInitializer\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"SecurityInitializer\" - FINISH.\n");
    }

    @Test
    public void ConstructorTest() throws Exception {
        System.out.print("-> SecurityInitializer() - ");
        assertNotNull(new SecurityInitializer());
        System.out.println("OK!");
    }
}
