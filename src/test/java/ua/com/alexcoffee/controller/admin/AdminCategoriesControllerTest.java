package ua.com.alexcoffee.controller.admin;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.tools.MockController;

import static org.junit.Assert.assertEquals;
import static ua.com.alexcoffee.tools.MockModel.ID;
import static ua.com.alexcoffee.tools.ModelAndViews.checkModelAndView;

public class AdminCategoriesControllerTest {

    private static AdminCategoriesController adminCategoriesController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"AdminCategoriesController\" - START.\n");

        adminCategoriesController = MockController.getAdminCategoriesController();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"AdminCategoriesController\" - FINISH.\n");
    }

    @Test
    public void viewAllCategoriesTest() throws Exception {
        System.out.print("-> viewAllCategories() - ");

        ModelAndView modelAndView = adminCategoriesController.viewAllCategories();
        String[] keys = { "categories" };
        checkModelAndView(modelAndView, "category/admin/all", keys);

        System.out.println("OK!");
    }

    @Test
    public void viewCategoryTest() throws Exception {
        System.out.print("-> viewCategory() - ");

        ModelAndView modelAndView = adminCategoriesController.viewCategory(ID);
        String[] keys = { "category" };
        String viewName = "category/admin/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void getAddCategoryPageTest() throws Exception {
        System.out.print("-> getAddCategoryPage() - ");

        ModelAndView modelAndView = adminCategoriesController.getAddCategoryPage();
        String[] keys = { "photos" };
        String viewName = "category/admin/add";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void saveCategoryTest() throws Exception {
        System.out.print("-> saveCategory() - ");

        String view = adminCategoriesController.saveCategory(
                "Title", "url", "Description", "Photo", null
        );
        String viewName = "redirect:/admin/category/all";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    public void saveCategoryGetTest() throws Exception {
        System.out.print("-> saveCategory() RequestMethod.GET- ");

        adminCategoriesController.saveCategory();

        System.out.println("OK!");
    }

    @Test
    public void getEditCategoryPageTest() throws Exception {
        System.out.print("-> getEditCategoryPage() - ");

        ModelAndView modelAndView = adminCategoriesController.getEditCategoryPage(ID);
        String[] keys = { "category", "photos" };
        String viewName = "category/admin/edit";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void updateCategoryTest() throws Exception {
        System.out.print("-> updateCategory() - ");

        String view = adminCategoriesController.updateCategory(ID, "Title", "url", "Description", ID, "Photo", null);
        String viewName = "redirect:/admin/view/1";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    public void updateCategoryGetTest() throws Exception {
        System.out.print("-> updateCategory() RequestMethod.GET - ");

        adminCategoriesController.updateCategory();

        System.out.println("OK!");
    }

    @Test
    public void deleteCategoryTest() throws Exception {
        System.out.print("-> deleteCategory() - ");

        String view = adminCategoriesController.deleteCategory(ID);
        String viewName = "redirect:/admin/category/all";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test
    public void deleteAllCategoriesTest() throws Exception {
        System.out.print("-> deleteAllCategories() - ");

        String view = adminCategoriesController.deleteAllCategories();
        String viewName = "redirect:/admin/category/all";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }
}