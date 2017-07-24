package ua.com.alexcoffee.model;

import org.junit.*;
import ua.com.alexcoffee.model.photo.Photo;

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
    public void toStringTest() {
        System.out.print("-> toString() - ");

        final String title = "Title";
        final String pls = "pls";
        final  String pll = "pll";

        final Photo photo = Photo.getBuilder().build();
        photo.setTitle(title);
        photo.setSmallUrl(pls);
        photo.setLongUrl(pll);
        final String line = "Title: " + title + "\nphoto short link: " + pls + "\nphoto long link: " + pll;

        assertEquals(photo.toString(), line);

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Photo photo = Photo.getBuilder().build();
        assertTrue(photo.equals(photo));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        final Photo photo1 = Photo.getBuilder().build();
        photo1.setTitle("Photo");
        photo1.setSmallUrl("link1");
        photo1.setLongUrl( "link2");
        final Photo photo2 = Photo.getBuilder().build();
        photo2.setTitle("Photo");
        photo2.setSmallUrl("link1");
        photo2.setLongUrl( "link2");

        assertTrue(photo1.equals(photo2));
        assertTrue(photo2.equals(photo1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        final Photo photo1 = Photo.getBuilder().build();
        photo1.setTitle("Photo");
        photo1.setSmallUrl("link1");
        photo1.setLongUrl( "link2");
        final Photo photo2 = Photo.getBuilder().build();
        photo2.setTitle("Photo");
        photo2.setSmallUrl("link1");
        photo2.setLongUrl( "link2");
        final Photo photo3 = Photo.getBuilder().build();
        photo3.setTitle("Photo");
        photo3.setSmallUrl("link1");
        photo3.setLongUrl( "link2");

        assertTrue(photo1.equals(photo2));
        assertTrue(photo2.equals(photo3));
        assertTrue(photo1.equals(photo3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        final Photo photo1 = Photo.getBuilder().build();
        photo1.setTitle("Photo");
        photo1.setSmallUrl("link1");
        photo1.setLongUrl( "link2");
        final Photo photo2 = Photo.getBuilder().build();
        photo2.setTitle("Photo");
        photo2.setSmallUrl("link1");
        photo2.setLongUrl( "link2");

        for (int i = 0; i < 10; i++) {
            assertTrue(photo1.equals(photo2));
        }

        System.out.println("OK!");
    }

    @Test
    public void setAndGetTitleTest() {
        System.out.print("-> setAndGetTitle() - ");

        Photo photo = Photo.getBuilder().build();
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

        Photo photo = Photo.getBuilder().build();
        photo.setSmallUrl(null);
        assertNotNull(photo.getSmallUrl());
        assertTrue(photo.getSmallUrl().isEmpty());

        String photoLinkShort = "Photo Link Short";
        photo.setSmallUrl(photoLinkShort);
        assertEquals(photo.getSmallUrl(), photoLinkShort);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetPhotoLinkLongTest() {
        System.out.print("-> setPhotoLinkLong() - ");

        Photo photo = Photo.getBuilder().build();
        photo.setLongUrl(null);
        assertNotNull(photo.getLongUrl());
        assertTrue(photo.getLongUrl().isEmpty());

        String photoLinkLong = "Photo Link Long";
        photo.setLongUrl(photoLinkLong);
        assertEquals(photo.getLongUrl(), photoLinkLong);

        System.out.println("OK!");
    }
}
