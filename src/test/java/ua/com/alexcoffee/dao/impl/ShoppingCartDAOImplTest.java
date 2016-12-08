package ua.com.alexcoffee.dao.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.alexcoffee.config.RootConfig;
import ua.com.alexcoffee.config.WebConfig;
import ua.com.alexcoffee.dao.ShoppingCartDAO;
import ua.com.alexcoffee.model.Product;
import ua.com.alexcoffee.model.SalePosition;
import ua.com.alexcoffee.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class ShoppingCartDAOImplTest {

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    @Autowired
    private ShoppingCart shoppingCart;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"ShoppingCartDAOImpl\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"ShoppingCartDAOImpl\" - FINISH.\n");
    }

    @Test
    public void shoppingCartDAONotNull() throws Exception {
        System.out.print("-> shoppingCartDAO Not Null - ");

        assertNotNull(shoppingCartDAO);

        System.out.println("OK!");
    }

    @Test
    public void shoppingCartNotNull() throws Exception {
        System.out.print("-> shoppingCart Not Null - ");

        assertNotNull(shoppingCart);

        System.out.println("OK!");
    }

    @Test
    public void addAndGetAndRemoveSalePositions() throws Exception {
        System.out.print("-> Add and Get and Remove - ");

        List<SalePosition> positions1 = new ArrayList<>();
        Product product = new Product("Title", "URL", null, null, 10.0);
        SalePosition position = new SalePosition(product, 1);
        positions1.add(position);

        shoppingCartDAO.addSalePosition(position);

        List<SalePosition> positions2 = shoppingCartDAO.getSalePositions();
        assertNotNull(positions2);
        assertEquals(positions1, positions2);

        shoppingCartDAO.removeSalePosition(position);
        assertFalse(shoppingCartDAO.getSalePositions().contains(position));

        System.out.println("OK!");
    }

    @Test
    public void clearSalePositionsTest() throws Exception {
        System.out.print("-> clearSalePositions() - ");

        Product product = new Product("Title", "URL", null, null, 10.0);
        SalePosition position = new SalePosition(product, 1);
        shoppingCartDAO.addSalePosition(position);
        shoppingCartDAO.clearSalePositions();

        assertNotNull(shoppingCartDAO.getSalePositions());
        assertTrue(shoppingCartDAO.getSize() == 0);

        System.out.println("OK!");
    }

    @Test
    public void getTest() throws Exception {
        System.out.print("-> get() - ");

        assertNotNull(shoppingCartDAO.get());

        System.out.println("OK!");
    }

    @Test
    public void getSizeTest() throws Exception {
        System.out.print("-> getSize() - ");

        assertTrue(shoppingCartDAO.getSize() == 0);

        Product product = new Product("Title", "URL", null, null, 10.0);
        SalePosition position = new SalePosition(product, 2);
        shoppingCartDAO.addSalePosition(position);

        assertTrue(shoppingCartDAO.getSize() == 2);

        System.out.println("OK!");
    }

    @Test
    public void getPriceTest() throws Exception {
        System.out.print("-> getPrice() - ");

        assertTrue(shoppingCartDAO.getPrice() == 0);

        Product product = new Product("Title", "URL", null, null, 10.0);
        SalePosition position = new SalePosition(product, 2);
        shoppingCartDAO.addSalePosition(position);

        assertTrue(shoppingCartDAO.getPrice() == position.getPrice());

        System.out.println("OK!");
    }
}
