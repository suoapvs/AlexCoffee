package ua.com.alexcoffee.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.model.SalePosition;
import ua.com.alexcoffee.model.ShoppingCart;
import ua.com.alexcoffee.service.ShoppingCartService;
import ua.com.alexcoffee.tools.MockService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.alexcoffee.tools.MockModel.getSalePosition;

public class ShoppingCartServiceImplTest {

    private static ShoppingCartService shoppingCartService;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"OrderServiceImpl\" - START.\n");

        shoppingCartService = MockService.getShoppingCartService();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"OrderServiceImpl\" - FINISH.\n");
    }

    @Test(expected = BadRequestException.class)
    public void getShoppingCartTest() throws Exception {
        System.out.print("-> getShoppingCart() - ");

        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
        assertNotNull(shoppingCart);

        shoppingCart = shoppingCartService.getShoppingCart();

        System.out.println("OK!");
    }

    @Test
    public void getSalePositionsTest() throws Exception {
        System.out.print("-> getSalePositions() - ");


        System.out.println("OK!");
    }

    @Test
    public void getPriceTest() throws Exception {
        System.out.print("-> getPrice() - ");

        double price = shoppingCartService.getSize();
        assertNotNull(price);
        assertTrue(price >= 0);

        System.out.println("OK!");
    }

    @Test
    public void getSizeTest() throws Exception {
        System.out.print("-> getSize() - ");

        int size = shoppingCartService.getSize();
        assertNotNull(size);
        assertTrue(size >= 0);

        System.out.println("OK!");
    }

    @Test
    public void noExceptionOfVoidMethodTest() throws Exception {
        System.out.print("-> noExceptionOfVoidMethod() - ");

        SalePosition salePosition = null;
        shoppingCartService.add(salePosition);
        shoppingCartService.remove(salePosition);

        salePosition = getSalePosition();
        shoppingCartService.add(salePosition);
        shoppingCartService.remove(salePosition);

        shoppingCartService.clear();

        System.out.println("OK!");
    }
}
