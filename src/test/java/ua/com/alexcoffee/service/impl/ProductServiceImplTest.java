package ua.com.alexcoffee.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.product.Product;
import ua.com.alexcoffee.service.interfaces.ProductService;
import ua.com.alexcoffee.tools.MockService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.alexcoffee.tools.MockModel.*;

public class ProductServiceImplTest {

    private static ProductService productService;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"ProductServiceImpl\" - START.\n");

        productService = MockService.getProductService();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"ProductServiceImpl\" - FINISH.\n");
    }

    @Test
    public void getByIdTest() throws Exception {
        System.out.print("-> getById() - ");

        Product product = productService.get(ID);
        assertNotNull(product);

        System.out.println("OK!");
    }

    @Test(expected = NullPointerException.class)
    public void getByUnknownIdTest() throws Exception {
        System.out.println("-> getByUnknownId() - OK!");
        productService.get(UNKNOWN_ID);
    }

    @Test
    public void getByUrlTest() throws Exception {
        System.out.print("-> getByUrl() - ");

        Product product = productService.getByUrl(URL);
        assertNotNull(product);

        System.out.println("OK!");
    }

    @Test(expected = NullPointerException.class)
    public void getByNullUrlTest() throws Exception {
        System.out.println("-> getByNullUrl() - OK!");

        String url = null;
        Product product = productService.getByUrl(url);
    }

    @Test(expected = NullPointerException.class)
    public void getByEmptyUrlTest() throws Exception {
        System.out.println("-> getByEmptyUrl() - OK!");

        String url = "";
        Product product = productService.getByUrl(url);
    }

    @Test(expected = NullPointerException.class)
    public void getByUnknownURLTest() throws Exception {
        System.out.println("-> getByUnknownURL() - OK!");

        Product product = productService.getByUrl(ANY_STRING);
    }

    @Test
    public void getByArticleTest() throws Exception {
        System.out.print("-> getByArticle() - ");

        Product product = productService.getByArticle(ARTICLE);
        assertNotNull(product);

        System.out.println("OK!");
    }

    @Test(expected = NullPointerException.class)
    public void getByUnknownArticleTest() throws Exception {
        System.out.println("-> getByUnknownArticle() - OK!");

        Product product = productService.getByArticle(UNKNOWN_ARTICLE);
    }

    @Test
    public void getByCategoryUrlTest() throws Exception {
        System.out.print("-> getByCategoryUrl() - ");

        Collection<Product> products = productService.getByCategoryUrl(URL);
        assertNotNull(products);
        assertTrue(products.size() >= 0);

        System.out.println("OK!");
    }

    @Test(expected = NullPointerException.class)
    public void getByNullCategoryUrlTest() throws Exception {
        System.out.println("-> getByCategoryNullUrl() - OK!");

        String url = null;
        productService.getByCategoryUrl(url);
    }

    @Test(expected = NullPointerException.class)
    public void getByEmptyCategoryUrlTest() throws Exception {
        System.out.println("-> getByEmptyCategoryUrl() - OK!");
        String url = "";
        productService.getByCategoryUrl(url);
    }

    @Test
    public void getByCategoryIdTest() throws Exception {
        System.out.print("-> getByCategoryId() - ");

        Collection<Product> products = productService.getByCategoryId(ID);
        assertNotNull(products);
        assertTrue(products.size() >= 0);

        System.out.println("OK!");
    }

    @Test
    public void getByUnknownCategoryIdTest() throws Exception {
        System.out.println("-> getByUnknownCategoryId() - OK!");

        Collection<Product> products = productService.getByCategoryId(UNKNOWN_ID);
        assertNotNull(products);
        assertTrue(products.isEmpty());
    }

    @Test
    public void getRandomByCategoryIdTest() throws Exception {
        System.out.println("-> getRandomByCategoryId() - ");

        Collection<Product> products1 = productService.getRandomByCategoryId(SIZE, ID, ID);
        assertNotNull(products1);
        assertTrue(products1.size() >= 0);

        Collection<Product> products2 = productService.getRandomByCategoryId(SIZE, ID);
        assertNotNull(products2);
        assertTrue(products2.size() >= 0);

        System.out.println("OK!");
    }

    @Test
    public void getRandomByUnknownCategoryIdTest() throws Exception {
        System.out.println("-> getRandomByUnknownCategoryId() - ");

        Collection<Product> products = productService.getRandomByCategoryId(SIZE, UNKNOWN_ID, ID);
        assertNotNull(products);
        assertTrue(products.size() >= 0);

        System.out.println("OK!");
    }

    @Test
    public void getRandomTest() throws Exception {
        System.out.println("-> getRandomByUnknownCategoryId() - ");

        Collection<Product> products = productService.getRandom(SIZE);
        assertNotNull(products);
        assertTrue(products.size() >= 0);

        System.out.println("OK!");
    }

    @Test(expected = NullPointerException.class)
    public void removeByNullUrl() throws Exception {
        System.out.print("-> removeByNullUrl() - ");

        productService.removeByUrl(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeByNullCategoryUrl() throws Exception {
        System.out.print("-> removeByNullCategoryUrl() - ");

        productService.removeByCategoryUrl(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeByEmptyCategoryUrl() throws Exception {
        System.out.print("-> removeByEmptyCategoryUrl() - ");

        productService.removeByCategoryUrl("");
    }

    @Test
    public void getAllTest() throws Exception {
        System.out.println("-> getAll() - ");

        Collection<Product> products = productService.getAll();
        assertNotNull(products);
        assertTrue(products.size() >= 0);

        System.out.println("OK!");
    }

    @Test
    public void noExceptionOfVoidMethodTest() throws Exception {
        System.out.print("-> noExceptionOfVoidMethod() - ");

        Product product = null;
        productService.add(product);
        productService.update(product);
        productService.remove(product);

        product = getProduct();
        productService.add(product);
        productService.update(product);
        productService.remove(product);

        List<Product> products = null;
        productService.add(products);
        productService.remove(products);

        products = new ArrayList<>();
        productService.add(products);
        productService.remove(products);

        products.add(product);
        productService.add(products);
        productService.remove(products);

        productService.remove(ID);
        productService.removeByUrl(URL);
        productService.removeByCategoryId(ID);
        productService.removeByCategoryUrl(URL);
        productService.removeByArticle(ARTICLE);
        productService.removeAll();

        System.out.println("OK!");
    }
}
