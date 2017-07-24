package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.category.Category;
import ua.com.alexcoffee.tools.MockModel;

import java.util.Collections;

import static org.junit.Assert.*;

public class ModelTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"Model\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"Model\" - FINISH.\n");
    }

    @Test
    public void equalsTest() {
        System.out.print("-> equals() - ");

        Category category = Category.getBuilder().build();;

        assertFalse(category.equals(null));
        assertEquals(category, category);

        System.out.println("OK!");
    }

    @Test
    public void getUnmodifiableListTest() {
        System.out.print("-> getUnmodifiableList() - ");

        Category category = Category.getBuilder().build();;
        assertEquals(category.getUnmodifiableList(null), Collections.EMPTY_LIST);
        assertTrue(category.getUnmodifiableList(MockModel.getTenProducts()).size() == 10);

        System.out.println("OK!");
    }
}
