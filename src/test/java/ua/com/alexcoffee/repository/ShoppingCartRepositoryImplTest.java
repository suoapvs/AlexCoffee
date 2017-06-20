package ua.com.alexcoffee.repository;

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
import ua.com.alexcoffee.repository.ShoppingCartRepository;
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
public class ShoppingCartRepositoryImplTest {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCart shoppingCart;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"ShoppingCartRepositoryImpl\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"ShoppingCartRepositoryImpl\" - FINISH.\n");
    }

    @Test
    public void shoppingCartDAONotNull() throws Exception {
        System.out.print("-> shoppingCartRepository Not Null - ");

        assertNotNull(shoppingCartRepository);

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

        shoppingCartRepository.addSalePosition(position);

        List<SalePosition> positions2 = shoppingCartRepository.getSalePositions();
        assertNotNull(positions2);
        assertEquals(positions1, positions2);

        shoppingCartRepository.removeSalePosition(position);
        assertFalse(shoppingCartRepository.getSalePositions().contains(position));

        System.out.println("OK!");
    }

    @Test
    public void clearSalePositionsTest() throws Exception {
        System.out.print("-> clearSalePositions() - ");

        Product product = new Product("Title", "URL", null, null, 10.0);
        SalePosition position = new SalePosition(product, 1);
        shoppingCartRepository.addSalePosition(position);
        shoppingCartRepository.clearSalePositions();

        assertNotNull(shoppingCartRepository.getSalePositions());
        assertTrue(shoppingCartRepository.getSize() == 0);

        System.out.println("OK!");
    }

    @Test
    public void getTest() throws Exception {
        System.out.print("-> get() - ");

        assertNotNull(shoppingCartRepository.get());

        System.out.println("OK!");
    }

    @Test
    public void getSizeTest() throws Exception {
        System.out.print("-> getSize() - ");

        assertTrue(shoppingCartRepository.getSize() == 0);

        Product product = new Product("Title", "URL", null, null, 10.0);
        SalePosition position = new SalePosition(product, 2);
        shoppingCartRepository.addSalePosition(position);

        assertTrue(shoppingCartRepository.getSize() == 2);

        System.out.println("OK!");
    }

    @Test
    public void getPriceTest() throws Exception {
        System.out.print("-> getPrice() - ");

        assertTrue(shoppingCartRepository.getPrice() == 0);

        Product product = new Product("Title", "URL", null, null, 10.0);
        SalePosition position = new SalePosition(product, 2);
        shoppingCartRepository.addSalePosition(position);

        assertTrue(shoppingCartRepository.getPrice() == position.getPrice());

        System.out.println("OK!");
    }
}
