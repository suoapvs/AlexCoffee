package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.basket.ShoppingCart;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.product.Product;

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

        final ShoppingCart cart = new ShoppingCart();
        String line = "Shopping Cart: is empty!";
        assertTrue(cart.toString().equals(line));

        final Product product = new Product();
        product.setTitle("Title");
        product.setUrl("URL");
        product.setPrice(10);
        product.setId(5);
        final SalePosition position = new SalePosition();
        position.setProduct(product);
        position.setNumber(10);
        cart.addSalePosition(position);
        line = "Shopping Cart: \n1) " + product.getTitle() + "\n№ " + product.getId() + ", " + position.getPrice() + " UAH"
                +"\nPrice: "+cart.getPrice()+" UAH";
        assertEquals(cart.toString(),line);

        System.out.println("OK!");
    }

    @Test
    public void addSalePositionTest() {
        System.out.print("-> addSalePosition() - ");

        final ShoppingCart shoppingCart = new ShoppingCart();
        final SalePosition position = new SalePosition();
        position.setProduct(new Product());
        position.setNumber(1);
        for (int i = 0; i < 10; i++) {
            shoppingCart.addSalePosition(position);
        }
        assertTrue(shoppingCart.getSalePositions().size() == 1);

        System.out.println("OK!");
    }

    @Test
    public void addSalePositionsTest() {
        System.out.print("-> addSalePositions() - ");

        final ShoppingCart shoppingCart = new ShoppingCart();
        final SalePosition position = new SalePosition();
        position.setProduct(new Product());
        position.setNumber(1);

        final List<SalePosition> positions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            positions.add(position);
        }
        shoppingCart.addSalePositions(positions);
        assertTrue(shoppingCart.getSalePositions().size() == 1);

        System.out.println("OK!");
    }

    @Test
    public void removeSalePositionTest() {
        System.out.print("-> removeSalePosition() - ");

        final Product product = new Product();
        product.setTitle("Title");
        product.setUrl("URL");
        product.setPrice(10);
        product.setId(5);
        final SalePosition position = new SalePosition();
        position.setProduct(product);
        position.setNumber(1);

        ShoppingCart cart = new ShoppingCart();
        cart.addSalePosition(position);
        cart.removeSalePosition(position);

        assertTrue(cart.getSize() == 0);

        System.out.println("OK!");
    }

    @Test
    public void removeSalePositionsTest() {
        System.out.print("-> removeSalePositions() - ");

        final List<SalePosition> positions = getTenSalePositions();
        final ShoppingCart cart = new ShoppingCart();
        cart.addSalePositions(positions);
        cart.removeSalePositions(positions);

        assertTrue(cart.getSize() == 0);

        System.out.println("OK!");
    }

    @Test
    public void clearSalePositionsTest() {
        System.out.print("-> clearSalePositions() - ");

        final List<SalePosition> positions = getTenSalePositions();
        final ShoppingCart cart = new ShoppingCart();
        cart.addSalePositions(positions);
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

        final ShoppingCart shoppingCart = new ShoppingCart();
        final Product product = new Product();
        product.setTitle("Title");
        product.setUrl("URL");
        product.setPrice(10);
        product.setId(5);
        final SalePosition position = new SalePosition();
        position.setProduct(product);
        position.setNumber(1);

        for (int i = 0; i < 10; i++) {
            shoppingCart.addSalePosition(position);
        }

        assertTrue(shoppingCart.getPrice() == position.getPrice());
        assertTrue(shoppingCart.getPrice() == product.getPrice() * 10);

        System.out.println("OK!");
    }

    @Test
    public void getSizeTest() {
        System.out.print("-> getSize() - ");

        List<SalePosition> positions = getTenSalePositions();
        ShoppingCart cart = new ShoppingCart();
        cart.setSalePositions(positions);

        assertTrue(cart.getSize() == 10);

        System.out.println("OK!");
    }
}
