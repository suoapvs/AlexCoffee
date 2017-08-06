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

public class AdminProductsControllerTest {

    private static AdminProductsController adminProductsController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"AdminProductsController\" - START.\n");

        adminProductsController = MockController.getAdminProductsController();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"AdminProductsController\" - FINISH.\n");
    }

    @Test
    public void viewAllProductsTest() throws Exception {
        System.out.print("-> viewAllOrders() - ");

        ModelAndView modelAndView = adminProductsController.viewAllProducts();
        String[] keys = { "products" };
        String viewName = "product/admin/all";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void viewProductTest() throws Exception {
        System.out.print("-> viewProduct() - ");

        ModelAndView modelAndView = adminProductsController.viewProduct(ID);
        String[] keys = { "product" };
        String viewName = "product/admin/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void getAddProductPageTest() throws Exception {
        System.out.print("-> getAddProductPage() - ");

        ModelAndView modelAndView = adminProductsController.getAddProductPage();
        String[] keys = { "categories", "photos" };
        String viewName = "product/admin/add";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void saveProductTest() throws Exception {
        System.out.print("-> saveProduct() - ");

        String view = adminProductsController.saveProduct("title", "url", "parameters", "description",
                ID, "photoTitle", null, null, 1.0);
        String viewName = "redirect:/admin/product/all";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    public void saveProductGetTest() throws Exception {
        System.out.print("-> saveProduct() RequestMethod.GET - ");

        adminProductsController.saveProduct();

        System.out.println("OK!");
    }

    @Test
    public void getEditProductPageTest() throws Exception {
        System.out.print("-> getEditProductPage() - ");

        ModelAndView modelAndView = adminProductsController.getEditProductPage(ID);
        String[] keys = { "product", "categories", "photos" };
        String viewName = "product/admin/edit";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void updateProductTest() throws Exception {
        System.out.print("-> updateProduct() - ");

        String view = adminProductsController.updateProduct(ID, "title", "url", "parameters",
                "description", ID, ID, "photoTitle", null, null, 1.0);
        String viewName = "redirect:/admin/product/view/" + ID;
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    public void updateProductGetTest() throws Exception {
        System.out.print("-> updateProduct1() RequestMethod.GET - ");

        adminProductsController.updateProduct();

        System.out.println("OK!");
    }

    @Test
    public void deleteProductTest() throws Exception {
        System.out.print("-> deleteProduct() - ");

        String view = adminProductsController.deleteProduct(ID);
        String viewName = "redirect:/admin/products";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test
    public void deleteAllProductsTest() throws Exception {
        System.out.print("-> deleteAllProducts() - ");

        String view = adminProductsController.deleteAllProducts();
        String viewName = "redirect:/admin/product/all";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }
}
