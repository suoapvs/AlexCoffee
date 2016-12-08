package ua.com.alexcoffee.model;

import org.junit.*;

import static org.junit.Assert.*;

public class PhotoTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"Photo\" - START.");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"Photo\" - FINISH.\n");
    }

    @Test
    public void ConstructorTest() {
        System.out.print("-> Photo() - ");

        Photo photo = new Photo();
        assertNotNull(photo.getTitle());
        assertNotNull(photo.getPhotoLinkShort());
        assertNotNull(photo.getPhotoLinkLong());

        photo = new Photo("Title", "qdeq", "dfvdv");
        assertNotNull(photo.getTitle());
        assertNotNull(photo.getPhotoLinkShort());
        assertNotNull(photo.getPhotoLinkLong());

        photo = new Photo("Title", "qdeq");
        assertNotNull(photo.getTitle());
        assertNotNull(photo.getPhotoLinkShort());
        assertNotNull(photo.getPhotoLinkLong());

        System.out.println("OK!");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        String title = "Title";
        String pls = "pls";
        String pll = "pll";

        Photo photo = new Photo(title, pls, pll);
        String line = "\nTitle: " + title + "\nphoto short link: " + pls + "\nphoto long link: " + pll;

        assertEquals(photo.toString(), line);

        System.out.println("OK!");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Photo photo = new Photo("Photo", "link1", "link2");
        assertEquals(photo.toEquals(), photo.toString());

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Photo photo = new Photo();
        assertTrue(photo.equals(photo));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Photo photo1 = new Photo("Photo", "link1", "link2");
        Photo photo2 = new Photo("Photo", "link1", "link2");

        assertTrue(photo1.equals(photo2));
        assertTrue(photo2.equals(photo1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Photo photo1 = new Photo("Photo", "link1", "link2");
        Photo photo2 = new Photo("Photo", "link1", "link2");
        Photo photo3 = new Photo("Photo", "link1", "link2");

        assertTrue(photo1.equals(photo2));
        assertTrue(photo2.equals(photo3));
        assertTrue(photo1.equals(photo3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Photo photo1 = new Photo("Photo", "link1", "link2");
        Photo photo2 = new Photo("Photo", "link1", "link2");

        for (int i = 0; i < 10; i++) {
            assertTrue(photo1.equals(photo2));
        }

        System.out.println("OK!");
    }

    @Test
    public void initializeTest() {
        System.out.print("-> initialize() - ");

        String title = "Title";
        String pls = "pls";
        String pll = "pll";

        Photo photo = new Photo();
        photo.initialize(title, pls, pll);

        assertEquals(photo.getTitle(), title);
        assertEquals(photo.getPhotoLinkShort(), pls);
        assertEquals(photo.getPhotoLinkLong(), pll);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetTitleTest() {
        System.out.print("-> setAndGetTitle() - ");

        Photo photo = new Photo();
        photo.setTitle(null);
        assertNotNull(photo.getTitle());
        assertTrue(photo.getTitle().isEmpty());

        String title = "New photo";
        photo.setTitle(title);
        assertEquals(photo.getTitle(), title);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetPhotoLinkShortTest() {
        System.out.print("-> setAndGetPhotoLinkShort() - ");

        Photo photo = new Photo();
        photo.setPhotoLinkShort(null);
        assertNotNull(photo.getPhotoLinkShort());
        assertTrue(photo.getPhotoLinkShort().isEmpty());

        String photoLinkShort = "Photo Link Short";
        photo.setPhotoLinkShort(photoLinkShort);
        assertEquals(photo.getPhotoLinkShort(), photoLinkShort);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetPhotoLinkLongTest() {
        System.out.print("-> setPhotoLinkLong() - ");

        Photo photo = new Photo();
        photo.setPhotoLinkLong(null);
        assertNotNull(photo.getPhotoLinkLong());
        assertTrue(photo.getPhotoLinkLong().isEmpty());

        String photoLinkLong = "Photo Link Long";
        photo.setPhotoLinkLong(photoLinkLong);
        assertEquals(photo.getPhotoLinkLong(), photoLinkLong);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetProductTest() {
        System.out.print("-> setAndGetProduct() - ");

        Photo photo = new Photo();
        Product product = new Product();
        photo.setProduct(product);

        assertNotNull(photo.getProduct());
        assertEquals(photo.getProduct(), product);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetCategoryTest() {
        System.out.print("-> setAndGetCategory() - ");

        Photo photo = new Photo();
        Category category = new Category();
        photo.setCategory(category);

        assertNotNull(photo.getCategory());
        assertEquals(photo.getCategory(), category);

        System.out.println("OK!");
    }
}
