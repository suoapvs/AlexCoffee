package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static ua.com.alexcoffee.tools.MockModel.getTenProducts;

public class CategoryTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"Category\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"Category\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        String title = "New Category";
        String url = "url";
        String description = "Some new category";

        Category category = new Category(title, url, description, null);

        String line = "\nTitle: " + title + "\nUrl: " + url + "\nDiscription: " + description;

        assertEquals(category.toString(), line);

        System.out.println("OK!");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        String title = "Category";
        String url = "cat_url";
        Category category = new Category(title, url, "", null);
        assertEquals(category.toEquals(), title + url);

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Category category = new Category();
        assertTrue(category.equals(category));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Category cat1 = new Category("Category", "cat_url", "Description about some category/", null);
        Category cat2 = new Category("Category", "cat_url", "", null);

        assertTrue(cat1.equals(cat2));
        assertTrue(cat2.equals(cat1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Category cat1 = new Category("Category", "cat_url", "", null);
        Category cat2 = new Category("Category", "cat_url", "", null);
        Category cat3 = new Category("Category", "cat_url", "", null);

        assertTrue(cat1.equals(cat2));
        assertTrue(cat2.equals(cat3));
        assertTrue(cat1.equals(cat3));

        System.out.println("OK");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Category cat1 = new Category("Category", "cat_url", "Description about some category/", null);
        Category cat2 = new Category("Category", "cat_url", "", null);

        for (int i = 0; i < 10; i++) {
            assertTrue(cat1.equals(cat2));
        }

        System.out.println("OK");
    }

    @Test
    public void initializeTest() {
        System.out.print("-> initialize() - ");

        String title = "Title";
        String url = "URL";
        String description = "Description";
        Photo photo = new Photo();

        Category category = new Category();
        category.initialize(title, url, description, photo);

        assertEquals(category.getTitle(), title);
        assertEquals(category.getUrl(), url);
        assertEquals(category.getDescription(), description);
        assertEquals(category.getPhoto(), photo);

        System.out.println("OK!");
    }

    @Test
    public void addProductTest() {
        System.out.print("-> addProduct() - ");

        Category category = new Category();
        for (int i = 0; i < 10; i++) {
            category.addProduct(new Product());
        }
        assertEquals(category.getProducts().size(), 10);

        System.out.println("OK!");
    }

    @Test
    public void addProductsTest() {
        System.out.print("-> addProducts() - ");

        List<Product> products = getTenProducts();

        Category category = new Category();
        category.addProducts(products);
        assertEquals(category.getProducts().size(), 10);

        System.out.println("OK!");
    }

    @Test
    public void removeProductTest() {
        System.out.print("-> removeProduct() - ");

        Category category = new Category();

        Product product = new Product();
        category.addProduct(product);
        category.addProducts(getTenProducts());

        category.removeProduct(product);

        assertEquals(category.getProducts().size(), 10);

        System.out.println("OK!");
    }

    @Test
    public void removeProductsTest() {
        System.out.print("-> removeProducts() - ");

        Category category = new Category();
        List<Product> products = getTenProducts();

        category.addProducts(products);
        category.removeProducts(products);

        assertEquals(category.getProducts().size(), 0);

        System.out.println("OK!");
    }

    @Test
    public void clearProductsTest() {
        System.out.print("-> clearProducts() - ");

        Category category = new Category();
        List<Product> products = getTenProducts();
        category.addProducts(products);
        category.clearProducts();

        assertEquals(category.getProducts().size(), 0);

        System.out.println("OK!");
    }

    @Test
    public void getProductsTest() {
        System.out.print("-> getProducts() - ");

        Category category = new Category();
        Product product = new Product();

        category.addProduct(product);
        List<Product> products = category.getProducts();

        assertTrue(products != null);
        assertFalse(products.isEmpty());

        System.out.println("OK!");
    }

    @Test
    public void setProductsTest() {
        System.out.print("-> setProducts() - ");

        List<Product> products = getTenProducts();

        Category category = new Category();
        category.setProducts(products);

        assertNotNull(category.getProducts());
        assertFalse(category.getProducts().isEmpty());

        System.out.println("OK!");
    }

    @Test
    public void setAndGetTitleTest() {
        System.out.print("-> setAndGetTitle() - ");

        Category category = new Category();
        category.setTitle(null);
        assertNotNull(category.getTitle());
        assertTrue(category.getTitle().isEmpty());

        String title = "New Category";
        category.setTitle(title);
        assertEquals(category.getTitle(), title);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetUrlTest() {
        System.out.print("-> setAndGetUrl() - ");

        Category category = new Category();
        category.setUrl(null);
        assertNotNull(category.getUrl());
        assertTrue(category.getUrl().isEmpty());

        String url = "url";
        category.setUrl(url);
        assertEquals(category.getUrl(), url);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetDescriptionTest() throws Exception {
        System.out.print("-> setDescription() - ");

        Category category = new Category();
        category.setDescription(null);
        assertNotNull(category.getDescription());
        assertTrue(category.getDescription().isEmpty());

        String description = "description";
        category.setDescription(description);
        assertEquals(category.getDescription(), description);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetPhotoTest() {
        System.out.print("-> setAndGetPhoto() - ");

        Category category = new Category();
        category.setPhoto(new Photo());

        assertNotNull(category.getPhoto());

        System.out.println("OK!");
    }
}
