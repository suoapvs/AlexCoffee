package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.order.Order;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.product.Product;

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

        final Product product = new Product();
        product.setTitle("Title");
        product.setUrl("url");
        product.setPrice(10.0);
        product.setId(1);
        final SalePosition position = new SalePosition();
        position.setId(1);
        position.setProduct(product);
        position.setNumber(2);

        final String line = "SalePosition #" + position.getId() + ":"
                + "\n" + product.getTitle() + "\n№ " + product.getId()
                + ", " + product.getPrice() + " UAH"
                + "\nNumber = " + position.getNumber()
                + "\nPrice = " + position.getPrice();

        assertEquals(position.toString(), line);

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        SalePosition position = new SalePosition();
        assertTrue(position.equals(position));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        final Product product = new Product();
        final SalePosition position1 = new SalePosition();
        position1.setId(1);
        position1.setProduct(product);
        position1.setNumber(2);
        SalePosition position2 = new SalePosition();
        position2.setId(2);
        position2.setProduct(product);
        position2.setNumber(2);

        assertTrue(position1.equals(position2));
        assertTrue(position2.equals(position1));

        position2 = new SalePosition();
        position2.setId(2);
        position2.setProduct(new Product());
        position2.setNumber(5);

        assertFalse(position1.equals(position2));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        final Product product = new Product();
        final SalePosition position1 = new SalePosition();
        position1.setId(1);
        position1.setProduct(product);
        position1.setNumber(2);
        final SalePosition position2 = new SalePosition();
        position2.setId(2);
        position2.setProduct(product);
        position2.setNumber(2);
        final SalePosition position3 = new SalePosition();
        position3.setId(3);
        position3.setProduct(product);
        position3.setNumber(2);

        assertTrue(position1.equals(position2));
        assertTrue(position2.equals(position3));
        assertTrue(position1.equals(position3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        final Product product = new Product();
        final SalePosition position1 = new SalePosition();
        position1.setId(1);
        position1.setProduct(product);
        position1.setNumber(2);
        final SalePosition position2 = new SalePosition();
        position2.setId(2);
        position2.setProduct(product);
        position2.setNumber(2);

        for (int i = 0; i < 10; i++) {
            assertTrue(position1.equals(position2));
        }

        System.out.println("OK!");
    }

    @Test
    public void getPriceTest() {
        final Product product = new Product();
        product.setPrice(100);
        SalePosition position = new SalePosition();
        position.setProduct(product);
        position.setNumber(10);
        assertTrue(position.getPrice() == product.getPrice() * 10);
    }

    @Test
    public void numberIncrTest() {
        System.out.print("-> numberIncr() - ");
        SalePosition position = new SalePosition();
        position.setProduct(new Product());
        position.setNumber(1);
        position.numberIncrement();
        position.numberIncrement();
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

        SalePosition position = new SalePosition();
        position.setNumber(10);
        assertTrue(position.getNumber() == 10);

        position.setNumber(-10);
        assertTrue(position.getNumber() == 0);

        position.setProduct(null);
        assertTrue(position.getNumber() == 0);

        position.setProduct(new Product());
        assertTrue(position.getNumber() == 1);

        System.out.print("OK!");
    }

    @Test
    public void setAndGetOrderTest() {
        System.out.print("-> setAndGetOrder() - ");

        Order orderEntity = Order.getBuilder().build();
        SalePosition position = new SalePosition();
        position.setOrder(orderEntity);

        assertNotNull(position.getOrder());
        assertEquals(position.getOrder(), orderEntity);

        System.out.println("OK!");
    }
}
