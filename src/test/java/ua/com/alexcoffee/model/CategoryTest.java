package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.category.Category;
import ua.com.alexcoffee.model.category.CategoryBuilder;
import ua.com.alexcoffee.model.photo.Photo;
import ua.com.alexcoffee.model.product.Product;

import java.util.Collection;
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
        Category category = Category.getBuilder().build();;
        category.setTitle(title);
        category.setUrl(url);
        category.setDescription(description);
        String line = "Title: " + title + "\nUrl: " + url + "\nDescription: " + description;
        assertEquals(category.toString(), line);

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Category category = Category.getBuilder().build();
        assertTrue(category.equals(category));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        final Category cat1 = Category.getBuilder().build();
        cat1.setTitle("Category");
        cat1.setUrl("cat_url");
        cat1.setDescription("Description about some category");

        final Category cat2 = Category.getBuilder().build();
        cat2.setTitle("Category");
        cat2.setUrl("cat_url");
        cat2.setDescription("");

        assertTrue(cat1.equals(cat2));
        assertTrue(cat2.equals(cat1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        final CategoryBuilder categoryBuilder = Category.getBuilder();
        final Category cat1 = categoryBuilder.build();
        cat1.setTitle("Category");
        cat1.setUrl("cat_url");
        cat1.setDescription("Description about some category");

        final Category cat2 = categoryBuilder.build();
        cat2.setTitle("Category");
        cat2.setUrl("cat_url");
        cat2.setDescription("Description about some category");

        final Category cat3 = categoryBuilder.build();
        cat3.setTitle("Category");
        cat3.setUrl("cat_url");
        cat3.setDescription("Description about some category");

        assertTrue(cat1.equals(cat2));
        assertTrue(cat2.equals(cat3));
        assertTrue(cat1.equals(cat3));

        System.out.println("OK");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        final CategoryBuilder categoryBuilder = Category.getBuilder();
        final Category cat1 = categoryBuilder.build();
        cat1.setTitle("Category");
        cat1.setUrl("cat_url");
        cat1.setDescription("Description about some category");

        final Category cat2 = categoryBuilder.build();
        cat2.setTitle("Category");
        cat2.setUrl("cat_url");
        cat2.setDescription("");

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
        Photo photo = Photo.getBuilder().build();

        final Category category = Category.getBuilder().build();
        category.setTitle(title);
        category.setUrl(url);
        category.setDescription(description);
        category.setPhoto(photo);

        assertEquals(category.getTitle(), title);
        assertEquals(category.getUrl(), url);
        assertEquals(category.getDescription(), description);
        assertEquals(category.getPhoto(), photo);

        System.out.println("OK!");
    }

    @Test
    public void addProductTest() {
        System.out.print("-> addProduct() - ");

        Category category = Category.getBuilder().build();
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

        Category category = Category.getBuilder().build();
        category.addProducts(products);
        assertEquals(category.getProducts().size(), 10);

        System.out.println("OK!");
    }

    @Test
    public void removeProductTest() {
        System.out.print("-> removeProduct() - ");

        Category category = Category.getBuilder().build();

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

        Category category = Category.getBuilder().build();
        List<Product> products = getTenProducts();

        category.addProducts(products);
        category.removeProducts(products);

        assertEquals(category.getProducts().size(), 0);

        System.out.println("OK!");
    }

    @Test
    public void clearProductsTest() {
        System.out.print("-> clearProducts() - ");

        Category category = Category.getBuilder().build();
        List<Product> products = getTenProducts();
        category.addProducts(products);
        category.clearProducts();

        assertEquals(category.getProducts().size(), 0);

        System.out.println("OK!");
    }

    @Test
    public void getProductsTest() {
        System.out.print("-> getProducts() - ");

        Category category = Category.getBuilder().build();
        Product product = new Product();

        category.addProduct(product);
        Collection<Product> products = category.getProducts();

        assertTrue(products != null);
        assertFalse(products.isEmpty());

        System.out.println("OK!");
    }

    @Test
    public void setProductsTest() {
        System.out.print("-> setProducts() - ");

        List<Product> products = getTenProducts();

        Category category = Category.getBuilder().build();
        category.setProducts(products);

        assertNotNull(category.getProducts());
        assertFalse(category.getProducts().isEmpty());

        System.out.println("OK!");
    }

    @Test
    public void setAndGetTitleTest() {
        System.out.print("-> setAndGetTitle() - ");

        Category category = Category.getBuilder().build();
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

        Category category = Category.getBuilder().build();
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

        Category category = Category.getBuilder().build();
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

        Category category = Category.getBuilder().build();
        category.setPhoto(Photo.getBuilder().build());

        assertNotNull(category.getPhoto());

        System.out.println("OK!");
    }
}
