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
import ua.com.alexcoffee.model.product.Product;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.basket.ShoppingCart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

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

        final List<SalePosition> positions1 = new ArrayList<>();
        final Product product = new Product();
        product.setTitle("Title");
        product.setUrl("URL");
        product.setPrice(10);
        final SalePosition position = new SalePosition();
        position.setProduct(product);
        position.setNumber(100);
        positions1.add(position);

        shoppingCartRepository.addSalePosition(position);

        Collection<SalePosition> positions2 = shoppingCartRepository.getSalePositions();
        assertNotNull(positions2);
        assertEquals(positions1, positions2);

        shoppingCartRepository.removeSalePosition(position);
        assertFalse(shoppingCartRepository.getSalePositions().contains(position));

        System.out.println("OK!");
    }

    @Test
    public void clearSalePositionsTest() throws Exception {
        System.out.print("-> clearSalePositions() - ");

        final Product product = new Product();
        product.setTitle("Title");
        product.setUrl("URL");
        product.setPrice(10);
        final SalePosition position = new SalePosition();
        position.setProduct(product);
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

        final Product product = new Product();
        product.setTitle("Title");
        product.setUrl("URL");
        product.setPrice(10);
        final SalePosition position = new SalePosition();
        position.setProduct(product);
        position.setNumber(2);
        shoppingCartRepository.addSalePosition(position);

        assertTrue(shoppingCartRepository.getSize() == 2);

        System.out.println("OK!");
    }

    @Test
    public void getPriceTest() throws Exception {
        System.out.print("-> getPrice() - ");

        assertTrue(shoppingCartRepository.getPrice() == 0);

        final Product product = new Product();
        product.setTitle("Title");
        product.setUrl("URL");
        product.setPrice(10);
        final SalePosition position = new SalePosition();
        position.setProduct(product);
        position.setNumber(1);
        shoppingCartRepository.addSalePosition(position);

        assertTrue(shoppingCartRepository.getPrice() == position.getPrice());

        System.out.println("OK!");
    }
}
