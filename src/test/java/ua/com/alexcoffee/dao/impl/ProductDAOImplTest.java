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
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.config.RootConfig;
import ua.com.alexcoffee.config.WebConfig;
import ua.com.alexcoffee.dao.interfaces.ProductDAO;
import ua.com.alexcoffee.model.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class ProductDAOImplTest {

    @Autowired
    private ProductDAO productDAO;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"ProductDAOImpl\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"CategoryDAOImpl\" - FINISH.\n");
    }

    @Test
    public void productDAONotNull() throws Exception {
        System.out.print("-> productDAO Not Null - ");

        assertNotNull(productDAO);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetByUrlAndRemoveByUrlTest() throws Exception {
        System.out.print("-> Add, Get and Remove by URL - ");

        Product product1 = new Product("Title", "URL", null, null, 1.0);
        productDAO.add(product1);
        Product product2 = productDAO.getByUrl(product1.getUrl());

        assertNotNull(product2);
        assertEquals(product1, product2);

        productDAO.removeByUrl(product2.getUrl());
        assertNull(productDAO.getByUrl(product2.getUrl()));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetByUrlAndRemoveByArticleTest() throws Exception {
        System.out.print("-> Add, Get and Remove by Article - ");

        Product product1 = new Product("Title", "URL", null, null, 1.0);
        productDAO.add(product1);
        Product product2 = productDAO.getByArticle(product1.getArticle());

        assertNotNull(product2);
        assertEquals(product1, product2);

        productDAO.removeByArticle(product2.getArticle());
        assertNull(productDAO.getByArticle(product2.getArticle()));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetAndRemoveListTest() throws Exception {
        System.out.print("-> Add, Get and Remove List - ");

        List<Product> products1 = new ArrayList<>();
        products1.add(new Product("t1", "u1", null, null, 1.0));
        products1.add(new Product("t2", "u2", null, null, 1.0));

        productDAO.add(products1);
        List<Product> products2 = productDAO.getAll();

        assertNotNull(products2);
        assertTrue(products2.size() >= products1.size());
        assertTrue(products2.containsAll(products1));

        productDAO.remove(products1);
        products2 = productDAO.getAll();

        assertNotNull(products2);
        assertTrue(products2.size() >= 0);
        assertFalse(products2.containsAll(products1));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        System.out.print("-> Get by Id - ");

        if (productDAO.getAll().size() > 0) {
            assertNotNull(productDAO.get((long) 1));
        }

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndUpdateTest() throws Exception {
        System.out.print("-> Add and Update - ");

        Product product1 = new Product("T", "U", null, null, 1.0);
        productDAO.add(product1);

        String url = "URL";
        product1.initialize("Title", url, "parametrs", "description", null, null, 2.0);
        productDAO.update(product1);

        Product product2 = productDAO.getByUrl(product1.getUrl());
        assertNotNull(product2);
        assertEquals(product1, product2);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void removeAllTest() throws Exception {
        System.out.print("-> Remove All - ");

        List<Product> products = new ArrayList<>();
        products.add(new Product("t1", "u1", null, null, 1.0));
        products.add(new Product("t2", "u2", null, null, 1.0));

        productDAO.add(products);
        assertNotNull(productDAO.getAll());
        assertTrue(productDAO.getAll().size() > 0);

        productDAO.removeAll();
        assertNotNull(productDAO.getAll());
        assertTrue(productDAO.getAll().size() == 0);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getAndRemoveByCategoryIdTest() {
        System.out.print("-> Remove All - ");

        List<Product> products = productDAO.getListByCategoryId(1L);
        assertNotNull(products);
        assertTrue(products.size() > 0);

        productDAO.removeByCategoryId(1L);
        products = productDAO.getListByCategoryId(1L);
        assertNotNull(products);
        assertTrue(products.isEmpty());

        System.out.println("OK!");
    }
}