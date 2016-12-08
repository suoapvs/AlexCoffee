package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SalePositionTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"SalePosition\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"SalePosition\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        Product product = new Product("Title", "url", null, null, 10.0);
        product.setId((long) 1);
        SalePosition salePosition = new SalePosition(product, 5);
        salePosition.setId((long) 2);

        String line = "SalePosition #" + salePosition.getId() + ": "
                + "\n" + product.getTitle() + "\n№ " + product.getId()
                + ", " + product.getPrice() + " UAH"
                + "\nNumber = " + salePosition.getNumber()
                + "\nPrice = " + salePosition.getPrice();

        assertEquals(salePosition.toString(), line);

        System.out.println("OK!");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Product product = new Product("Title", "url", null, null, 10.0);
        product.setId(1L);
        SalePosition salePosition = new SalePosition(product, 5);
        salePosition.setId(2L);

        String line = product.toEquals() + 2L;

        assertEquals(salePosition.toEquals(), line);

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        SalePosition salePosition = new SalePosition();
        assertTrue(salePosition.equals(salePosition));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Product product = new Product();
        SalePosition salePosition1 = new SalePosition(product, 1);
        SalePosition salePosition2 = new SalePosition(product, 1);

        assertTrue(salePosition1.equals(salePosition2));
        assertTrue(salePosition2.equals(salePosition1));

        salePosition2 = new SalePosition(new Product(), 5);

        assertFalse(salePosition1.equals(salePosition2));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Product product = new Product();
        SalePosition salePosition1 = new SalePosition(product, 24);
        SalePosition salePosition2 = new SalePosition(product, 24);
        SalePosition salePosition3 = new SalePosition(product, 24);

        assertTrue(salePosition1.equals(salePosition2));
        assertTrue(salePosition2.equals(salePosition3));
        assertTrue(salePosition1.equals(salePosition3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Product product = new Product();
        SalePosition salePosition1 = new SalePosition(product, 20);
        SalePosition salePosition2 = new SalePosition(product, 20);

        for (int i = 0; i < 10; i++) {
            assertTrue(salePosition1.equals(salePosition2));
        }

        System.out.println("OK!");
    }

    @Test
    public void getPriceTest() {
        Product product = new Product("", "", null, null, 100);
        SalePosition salePosition = new SalePosition(product, 10);

        assertTrue(salePosition.getPrice() == product.getPrice() * 10);
    }

    @Test
    public void numberIncrTest() {
        System.out.print("-> numberIncr() - ");

        SalePosition position = new SalePosition(new Product(), 1);
        position.numberIncr();
        position.numberIncr();

        assertTrue(position.getNumber() == 3);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetProductTest() {
        System.out.print("-> setAndGetProduct() - ");

        Product product = new Product();
        SalePosition position = new SalePosition();
        position.setProduct(product);

        assertNotNull(position.getProduct());
        assertEquals(position.getProduct(), product);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetNumberTest() {
        System.out.print("-> setAndGetNumber() - ");

        SalePosition salePosition = new SalePosition();
        salePosition.setNumber(10);
        assertTrue(salePosition.getNumber() == 10);

        salePosition.setNumber(-10);
        assertTrue(salePosition.getNumber() == 0);

        salePosition.setProduct(null);
        assertTrue(salePosition.getNumber() == 0);

        salePosition.setProduct(new Product());
        assertTrue(salePosition.getNumber() == 1);

        System.out.print("OK!");
    }

    @Test
    public void setAndGetOrderTest() {
        System.out.print("-> setAndGetOrder() - ");

        Order order = new Order();
        SalePosition position = new SalePosition();
        position.setOrder(order);

        assertNotNull(position.getOrder());
        assertEquals(position.getOrder(), order);

        System.out.println("OK!");
    }
}
