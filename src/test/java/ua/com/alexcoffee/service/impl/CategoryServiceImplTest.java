package ua.com.alexcoffee.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.service.CategoryService;
import ua.com.alexcoffee.tools.MockService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.alexcoffee.tools.MockModel.*;

public class CategoryServiceImplTest {

    private static CategoryService categoryService;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"CategoryServiceImpl\" - START.\n");

        categoryService = MockService.getCategoryService();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"CategoryServiceImpl\" - FINISH.\n");
    }

    @Test
    public void getByIdTest() throws Exception {
        System.out.print("-> getById() - ");

        Category category = categoryService.get(ID);
        assertNotNull(category);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void getByNullIdTest() throws Exception {
        System.out.println("-> getByNullId() - OK!");

        Long id = null;
        Category category = categoryService.get(id);
    }

    @Test(expected = BadRequestException.class)
    public void getByUnknownIdTest() throws Exception {
        System.out.println("-> getByUnknownId() - OK!");

        Category category = categoryService.get(UNKNOWN_ID);
    }

    @Test
    public void getByUrlTest() throws Exception {
        System.out.print("-> getByUrl() - ");

        Category category = categoryService.get(URL);
        assertNotNull(category);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void getByNullUrlTest() throws Exception {
        System.out.println("-> getByNullUrl() - OK!");

        String url = null;
        Category category = categoryService.get(url);
    }

    @Test(expected = WrongInformationException.class)
    public void getByEmptyUrlTest() throws Exception {
        System.out.println("-> getByEmptyUrl() - OK!");

        String url = "";
        Category category = categoryService.get(url);
    }

    @Test(expected = BadRequestException.class)
    public void getByUnknownURLTest() throws Exception {
        System.out.println("-> getByUnknownURL() - OK!");

        Category category = categoryService.get(ANY_STRING);
    }

    @Test(expected = WrongInformationException.class)
    public void removeByNullIdTest() throws Exception {
        System.out.println("-> removeByNullId() - OK!");

        Long id = null;
        categoryService.remove(id);
    }

    @Test
    public void removeByUrlTest() throws Exception {
        System.out.print("-> removeByUrl() - ");

        categoryService.remove(URL);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void removeByNullUrlTest() throws Exception {
        System.out.println("-> removeByNullUrl() - OK!");

        String url = null;
        categoryService.remove(url);
    }

    @Test(expected = WrongInformationException.class)
    public void removeByEmptyUrlTest() throws Exception {
        System.out.println("-> removeByEmptyUrl() - OK!");

        String url = "";
        categoryService.remove(url);
    }

    @Test
    public void getAllTest() throws Exception {
        System.out.println("-> getAll() - ");

        List<Category> categories = categoryService.getAll();
        assertNotNull(categories);
        assertTrue(categories.size() >= 0);

        System.out.println("OK!");
    }

    @Test
    public void noExceptionOfVoidMethodTest() throws Exception {
        System.out.print("-> noExceptionOfVoidMethod() - ");

        Category category = null;
        categoryService.add(category);
        categoryService.update(category);
        categoryService.remove(category);

        category = getCategory();
        categoryService.add(category);
        categoryService.update(category);
        categoryService.remove(category);

        List<Category> categories = null;
        categoryService.add(categories);
        categoryService.remove(categories);

        categories = new ArrayList<>();
        categoryService.add(categories);
        categoryService.remove(categories);

        categories.add(category);
        categoryService.add(categories);
        categoryService.remove(categories);

        categoryService.remove(URL);
        categoryService.remove(ID);
        categoryService.removeAll();

        System.out.println("OK!");
    }
}