package ua.com.alexcoffee.aspect;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ControllerExceptionAspectTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"ControllerExceptionAspect\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"ControllerExceptionAspect\" - FINISH.\n");
    }

    @Test
    public void afterThrowingAdvice() throws Exception {
        System.out.print("-> getDefault() - ");

        ControllerExceptionAspect aspect = new ControllerExceptionAspect();
        aspect.afterThrowingAdvice(new Exception());

        System.out.println("OK!");
    }
}