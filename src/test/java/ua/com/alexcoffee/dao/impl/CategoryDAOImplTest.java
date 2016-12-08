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
import ua.com.alexcoffee.dao.CategoryDAO;
import ua.com.alexcoffee.model.Category;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class CategoryDAOImplTest {

    @Autowired
    private CategoryDAO categoryDAO;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"CategoryDAOImpl\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"CategoryDAOImpl\" - FINISH.\n");
    }

    @Test
    public void categoryDAONotNull() throws Exception {
        System.out.print("-> categoryDAO Not Null - ");

        assertNotNull(categoryDAO);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetByUrlAndRemoveByUrlTest() throws Exception {
        System.out.print("-> Add, Get and Remove by URL - ");

        Category category1 = new Category("Some category", "category_url", "desc", null);
        categoryDAO.add(category1);
        Category category2 = categoryDAO.get(category1.getUrl());

        assertNotNull(category2);
        assertEquals(category1, category2);

        categoryDAO.remove(category2.getUrl());
        assertNull(categoryDAO.get(category2.getUrl()));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetAndRemoveListTest() throws Exception {
        System.out.print("-> Add, Get and Remove List - ");

        List<Category> categories1 = new ArrayList<>();
        categories1.add(new Category("t1", "u1", "d1", null));
        categories1.add(new Category("t2", "u2", "d2", null));
        categoryDAO.add(categories1);
        List<Category> categories2 = categoryDAO.getAll();

        assertNotNull(categories2);
        assertTrue(categories2.size() >= categories1.size());
        assertTrue(categories2.containsAll(categories1));

        categoryDAO.remove(categories1);
        categories2 = categoryDAO.getAll();

        assertNotNull(categories2);
        assertTrue(categories2.size() >= 0);
        assertFalse(categories2.containsAll(categories1));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        System.out.print("-> Get by Id - ");

        if (categoryDAO.getAll().size() > 0) {
            assertNotNull(categoryDAO.get((long) 1));
        }

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndUpdateTest() throws Exception {
        System.out.print("-> Add and Update - ");

        Category category1 = new Category("T", "u", "d", null);
        categoryDAO.add(category1);

        String url = "URL";
        category1.initialize("Title", url, "Description", null);
        categoryDAO.update(category1);

        Category category2 = categoryDAO.get(url);
        assertNotNull(category2);
        assertEquals(category1, category2);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void removeAllTest() throws Exception {
        System.out.print("-> Remove All - ");

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("t1", "u1", "d1", null));
        categories.add(new Category("t2", "u2", "d2", null));

        categoryDAO.add(categories);
        assertNotNull(categoryDAO.getAll());
        assertTrue(categoryDAO.getAll().size() > 0);

        categoryDAO.removeAll();
        assertNotNull(categoryDAO.getAll());
        assertTrue(categoryDAO.getAll().size() == 0);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndRemoveModel() throws Exception {
        System.out.print("-> addAndRemoveModel() - ");

        Category category = new Category();
        categoryDAO.add(category);

        Category category1 = categoryDAO.get(category.getUrl());
        assertNotNull(category1);
        assertEquals(category, category1);

        categoryDAO.remove(category);

        System.out.println("OK!");
    }
}