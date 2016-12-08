package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ua.com.alexcoffee.tools.MockModel.getTenSalePositions;

public class ShoppingCartTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"ShoppingCart\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"ShoppingCart\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        ShoppingCart cart = new ShoppingCart();
        String line = "Shoping Cart: is empty!";
        assertTrue(cart.toString().equals(line));

        Product product = new Product("Title", "URL", null, null, 10.0);
        product.setId((long) 5);
        SalePosition position = new SalePosition(product, 10);
        cart.addSalePosition(position);
        line = "Shoping Cart: \n1) " + product.getTitle() + "\n№ " + product.getId() + ", " + position.getPrice() + " UAH"
                +"\nPrice: "+cart.getPrice()+" UAH";

        assertEquals(cart.toString(),line);

        System.out.println("OK!");
    }

    @Test
    public void addSalePositionTest() {
        System.out.print("-> addSalePosition() - ");

        ShoppingCart shoppingCart = new ShoppingCart();
        SalePosition salePosition = new SalePosition(new Product(), 1);

        for (int i = 0; i < 10; i++) {
            shoppingCart.addSalePosition(salePosition);
        }

        assertTrue(shoppingCart.getSalePositions().size() == 1);
        assertTrue(shoppingCart.getSalePositions().get(0).getNumber() == 10);

        System.out.println("OK!");
    }

    @Test
    public void addSalePositionsTest() {
        System.out.print("-> addSalePositions() - ");

        ShoppingCart shoppingCart = new ShoppingCart();
        SalePosition salePosition = new SalePosition(new Product(), 1);

        List<SalePosition> salePositions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            salePositions.add(salePosition);
        }

        shoppingCart.addSalePositions(salePositions);

        assertTrue(shoppingCart.getSalePositions().size() == 1);
        assertTrue(shoppingCart.getSalePositions().get(0).getNumber() == 10);

        System.out.println("OK!");
    }

    @Test
    public void removeSalePositionTest() {
        System.out.print("-> removeSalePosition() - ");

        Product product = new Product("Title", "URL", null, null, 10.0);
        SalePosition position = new SalePosition(product, 10);

        ShoppingCart cart = new ShoppingCart();
        cart.addSalePosition(position);
        cart.removeSalePosition(position);

        assertTrue(cart.getSize() == 0);

        System.out.println("OK!");
    }

    @Test
    public void removeSalePositionsTest() {
        System.out.print("-> removeSalePositions() - ");

        List<SalePosition> positions = getTenSalePositions();
        ShoppingCart cart = new ShoppingCart(positions);
        cart.removeSalePositions(positions);

        assertTrue(cart.getSize() == 0);

        System.out.println("OK!");
    }

    @Test
    public void clearSalePositionsTest() {
        System.out.print("-> clearSalePositions() - ");

        ShoppingCart cart = new ShoppingCart(getTenSalePositions());
        cart.clearSalePositions();

        assertTrue(cart.getSize() == 0);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetSalePositionsTest() {
        System.out.print("-> setAndGetSalePositions() - ");

        List<SalePosition> positions = getTenSalePositions();
        ShoppingCart cart = new ShoppingCart();
        cart.setSalePositions(positions);

        assertNotNull(cart.getSalePositions());
        assertEquals(cart.getSalePositions(), positions);

        System.out.println("OK!");
    }

    @Test
    public void getPriceTest() {
        System.out.print("-> getPrice() - ");

        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = new Product("", "", null, null, 100);
        SalePosition salePosition = new SalePosition(product, 1);

        for (int i = 0; i < 10; i++) {
            shoppingCart.addSalePosition(salePosition);
        }

        assertTrue(shoppingCart.getPrice() == salePosition.getPrice());
        assertTrue(shoppingCart.getPrice() == product.getPrice() * 10);

        System.out.println("OK!");
    }

    @Test
    public void getSizeTest() {
        System.out.print("-> getSize() - ");

        ShoppingCart cart = new ShoppingCart(getTenSalePositions());

        assertTrue(cart.getSize() == 10);

        System.out.println("OK!");
    }
}
