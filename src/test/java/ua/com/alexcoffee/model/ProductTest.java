package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static ua.com.alexcoffee.tools.MockModel.getTenSalePositions;

public class ProductTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"Product\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"Product\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        String title = "Title";
        String parameters = "Parameters";
        String description = "Description";
        double price = 100.0;

        Product product = new Product();
        product.setTitle(title);
        product.setParameters(parameters);
        product.setDescription(description);
        product.setPrice(price);
        String line = "Title: " + title + "\nParameters: " + parameters + "\nDescription: "
                + description + "\nPrice = " + price + " UAH";

        assertEquals(product.toString(), line);

        Category category = new Category(title, null, null, null);
        product.setCategory(category);
        line += "\nCategory: " + category.getTitle();

        assertEquals(product.toString(), line);

        System.out.println("Ok!");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Product product = new Product("Title", "url", new Category(), new Photo(), 1000);
        assertEquals(product.toEquals(), product.getArticle() + "Titleurl" + product.getPrice());

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Product product = new Product();
        assertTrue(product.equals(product));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Product product1 = new Product();
        Product product2 = new Product();
        product2.setArticle(product1.getArticle());

        assertTrue(product1.equals(product2));
        assertTrue(product2.equals(product1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Product product1 = new Product();
        Product product2 = new Product();
        product2.setArticle(product1.getArticle());
        Product product3 = new Product();
        product3.setArticle(product2.getArticle());

        assertTrue(product1.equals(product2));
        assertTrue(product2.equals(product3));
        assertTrue(product1.equals(product3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Product product1 = new Product();
        Product product2 = new Product();
        product2.setArticle(product1.getArticle());

        for (int i = 0; i < 10; i++) {
            assertTrue(product1.equals(product2));
        }

        System.out.println("OK!");
    }

    @Test
    public void initializeTest() {
        System.out.print("-> initialize() - ");

        String title = "Title";
        String url = "Url";
        String parameters = "Parameters";
        String description = "Description";
        Category category = new Category();
        Photo photo = new Photo();
        double price = 10.0;

        Product product = new Product();
        product.initialize(title, url, parameters, description, category, photo, price);

        assertEquals(product.getTitle(), title);
        assertEquals(product.getUrl(), url);
        assertEquals(product.getParameters(), parameters);
        assertEquals(product.getDescription(), description);
        assertEquals(product.getCategory(), category);
        assertEquals(product.getPhoto(), photo);
        assertTrue(product.getPrice() == price);

        System.out.println("OK!");
    }

    @Test
    public void newArticleTest() {
        System.out.print("-> newArticle() - ");

        Product product = new Product();
        int article = product.getArticle();

        product.newArticle();
        int newArticle = product.getArticle();

        assertTrue(article != newArticle);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetArticleTest() {
        System.out.print("-> setAndGetArticle() - ");

        Product product = new Product();
        int article = 101010;
        product.setArticle(article);

        assertNotNull(product.getArticle());
        assertTrue(product.getArticle() == article);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetTitleTest() {
        System.out.print("-> setAndGetTitle() - ");

        Product product = new Product();
        product.setTitle(null);
        assertNotNull(product.getTitle());
        assertTrue(product.getTitle().isEmpty());

        String title = "Title";
        product.setTitle(title);
        assertEquals(product.getTitle(), title);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetUrlTest() {
        System.out.print("-> setAndGetUrl() - ");

        Product product = new Product();
        product.setUrl(null);
        assertNotNull(product.getUrl());
        assertTrue(product.getUrl().isEmpty());

        String url = "URL";
        product.setUrl(url);
        assertEquals(product.getUrl(), url);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetParametersTest() {
        System.out.print("-> setAndGetParameters() - ");

        Product product = new Product();
        product.setParameters(null);
        assertNotNull(product.getParameters());
        assertTrue(product.getParameters().isEmpty());

        String parameters = "Parameters";
        product.setParameters(parameters);
        assertEquals(product.getParameters(), parameters);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetDescriptionTest() {
        System.out.print("-> setAndGetDescription() - ");

        Product product = new Product();
        product.setDescription(null);
        assertNotNull(product.getDescription());
        assertTrue(product.getDescription().isEmpty());

        String description = "Description";
        product.setDescription(description);
        assertEquals(product.getDescription(), description);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetPhoto() {
        System.out.print("-> setAndGetPhoto() - ");

        Product product = new Product();
        Photo photo = new Photo();
        product.setPhoto(photo);

        assertNotNull(product.getPhoto());
        assertEquals(product.getPhoto(), photo);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetCategoryTest() {
        System.out.print("-> setAndGetCategory() - ");

        Product product = new Product();
        Category category = new Category();
        product.setCategory(category);

        assertNotNull(product.getCategory());
        assertEquals(product.getCategory(), category);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetPriceTest() {
        System.out.print("-> setAndGetPrice() - ");

        Product product = new Product();

        product.setPrice(123);
        assertTrue(product.getPrice() == 123);

        product.setPrice(-50);
        assertTrue(product.getPrice() == 0);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetSalePositionTest() {
        System.out.print("-> setSalePosition() - ");

        Product product = new Product();
        List<SalePosition> salePositions = getTenSalePositions();
        product.setSalePositions(salePositions);

        assertNotNull(product.getSalePositions());
        assertEquals(product.getSalePositions(), salePositions);

        System.out.println("OK!");
    }
}