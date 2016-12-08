package ua.com.alexcoffee.controller.admin;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.tools.MockController;

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

        ModelAndView modelAndView = adminProductsController.viewAllProducts(new ModelAndView());
        String[] keys = {"products", "auth_user"};
        String viewName = "admin/product/all";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void viewProductTest() throws Exception {
        System.out.print("-> viewProduct() - ");

        ModelAndView modelAndView = adminProductsController.viewProduct(ID, new ModelAndView());
        String[] keys = {"product", "auth_user"};
        String viewName = "admin/product/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void getAddProductPageTest() throws Exception {
        System.out.print("-> getAddProductPage() - ");

        ModelAndView modelAndView = adminProductsController.getAddProductPage(new ModelAndView());
        String[] keys = {"categories", "photos", "auth_user"};
        String viewName = "admin/product/add";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void saveProductTest() throws Exception {
        System.out.print("-> saveProduct() - ");

        ModelAndView modelAndView = adminProductsController.saveProduct("title", "url", "parameters", "description",
                ID, "photoTitle", null, null, 1.0, new ModelAndView());
        String viewName = "redirect:/admin/products";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void saveProductGetTest() throws Exception {
        System.out.print("-> saveProduct() RequestMethod.GET - ");

        adminProductsController.saveProduct();

        System.out.println("OK!");
    }

    @Test
    public void getEditProductPageTest() throws Exception {
        System.out.print("-> getEditProductPage() - ");

        ModelAndView modelAndView = adminProductsController.getEditProductPage(ID, new ModelAndView());
        String[] keys = {"product", "categories", "photos", "auth_user"};
        String viewName = "admin/product/edit";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void updateProductTest() throws Exception {
        System.out.print("-> updateProduct() - ");

        ModelAndView modelAndView = adminProductsController.updateProduct(ID, "title", "url", "parameters",
                "description", ID, ID, "photoTitle", null, null, 1.0, new ModelAndView());
        String viewName = "redirect:/admin/view_product_" + ID;
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void updateProductGetTest() throws Exception {
        System.out.print("-> updateProduct1() RequestMethod.GET - ");

        adminProductsController.updateProduct();

        System.out.println("OK!");
    }

    @Test
    public void deleteProductTest() throws Exception {
        System.out.print("-> deleteProduct() - ");

        ModelAndView modelAndView = adminProductsController.deleteProduct(ID, new ModelAndView());
        String viewName = "redirect:/admin/products";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test
    public void deleteAllProductsTest() throws Exception {
        System.out.print("-> deleteAllProducts() - ");

        ModelAndView modelAndView = adminProductsController.deleteAllProducts(new ModelAndView());
        String viewName = "redirect:/admin/products";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }
}
