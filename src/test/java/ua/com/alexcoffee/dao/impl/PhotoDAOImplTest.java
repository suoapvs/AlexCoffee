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
import ua.com.alexcoffee.dao.PhotoDAO;
import ua.com.alexcoffee.model.Photo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class PhotoDAOImplTest {

    @Autowired
    private PhotoDAO photoDAO;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"PhotoDAOImpl\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"PhotoDAOImpl\" - FINISH.\n");
    }

    @Test
    public void photoDAONotNull() throws Exception {
        System.out.print("-> photoDAO Not Null - ");

        assertNotNull(photoDAO);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetByTitleAndRemoveByTitleTest() throws Exception {
        System.out.print("-> Add, Get and Remove by Title - ");

        Photo photo1 = new Photo("Title", "pls", "pll");
        photoDAO.add(photo1);
        Photo photo2 = photoDAO.get(photo1.getTitle());

        assertNotNull(photo2);
        assertEquals(photo1, photo2);

        photoDAO.remove(photo2.getTitle());
        assertNull(photoDAO.get(photo2.getTitle()));
        ;

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetAndRemoveListTest() throws Exception {
        System.out.print("-> Add, Get and Remove List - ");

        List<Photo> photos1 = new ArrayList<>();
        photos1.add(new Photo("t1", "pls1", "pll1"));
        photos1.add(new Photo("t2", "pls2", "pll2"));

        photoDAO.add(photos1);
        List<Photo> photos2 = photoDAO.getAll();

        assertNotNull(photos2);
        assertTrue(photos2.size() >= photos1.size());
        assertTrue(photos2.containsAll(photos1));

        photoDAO.remove(photos1);
        photos2 = photoDAO.getAll();

        assertNotNull(photos2);
        assertTrue(photos2.size() >= 0);
        assertFalse(photos2.containsAll(photos1));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        System.out.print("-> Get by Id - ");

        if (photoDAO.getAll().size() > 0) {
            assertNotNull(photoDAO.get((long) 1));
        }

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndUpdateTest() throws Exception {
        System.out.print("-> Add and Update - ");

        Photo photo1 = new Photo();
        photoDAO.add(photo1);

        photo1.initialize("Title", "pls", "pll");
        photoDAO.update(photo1);

        Photo photo2 = photoDAO.get(photo1.getTitle());

        assertNotNull(photo2);
        assertEquals(photo1, photo2);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void removeAllTest() throws Exception {
        System.out.print("-> Remove All - ");

        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo("t1", "pls1", "pll1"));
        photos.add(new Photo("t2", "pls2", "pll2"));

        photoDAO.add(photos);
        assertNotNull(photoDAO.getAll());
        assertTrue(photoDAO.getAll().size() > 0);

        photoDAO.removeAll();

        assertNotNull(photoDAO.getAll());

        System.out.println("OK!");
    }
}