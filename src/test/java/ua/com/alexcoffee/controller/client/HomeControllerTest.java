package ua.com.alexcoffee.controller.client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.tools.MockController;

import static org.junit.Assert.assertEquals;
import static ua.com.alexcoffee.tools.MockModel.*;
import static ua.com.alexcoffee.tools.ModelAndViews.checkModelAndView;

public class HomeControllerTest {

    private static HomeController homeController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"HomeController\" - START.\n");
        homeController = MockController.getHomeController();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"HomeController\" - FINISH.\n");
    }

    @Test
    @Transactional
    public void homeTest() throws Exception {
        System.out.print("-> home() - ");

        ModelAndView modelAndView = homeController.home();
        String[] keys = {"categories", "products", "cart_size"};
        String viewName = "home/home";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewProductsInCategoryTest() throws Exception {
        System.out.print("-> viewProductsInCategory() - ");

        ModelAndView modelAndView = homeController.viewProductsInCategory(URL);
        String[] keys = {"category", "products", "cart_size"};
        String viewName = "category/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewAllProductsTest() throws Exception {
        System.out.print("-> viewAllProducts() - ");

        ModelAndView modelAndView = homeController.viewAllProducts();
        String[] keys = {"products", "cart_size"};
        String viewName = "product/all";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewProductTest() throws Exception {
        System.out.print("-> viewProduct() - ");

        ModelAndView modelAndView1 = homeController.viewProduct(URL);
        String[] keys = {"product", "cart_size", "featured_products"};
        String viewName = "product/one";
        checkModelAndView(modelAndView1, viewName, keys);

        ModelAndView modelAndView2 = homeController.viewProduct(Integer.toString(ARTICLE));
        checkModelAndView(modelAndView2, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewCartTest() throws Exception {
        System.out.print("-> viewCart() - ");

        ModelAndView modelAndView = homeController.viewCart();
        String[] keys = {"sale_positions", "price_of_cart", "cart_size"};
        String viewName = "cart/cart";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addProductToCartTest() throws Exception {
        System.out.print("-> addProductToCart() - ");

        String view = homeController.addProductToCart(ID);
        String viewName = "redirect:/cart";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    @Transactional
    public void addProductToCartGetTest() throws Exception {
        System.out.print("-> addProductToCart() RequestMethod.GET - ");

        homeController.addProductToCart();

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addProductToCartQuicklyTest() throws Exception {
        System.out.print("-> addProductToCartQuickly() - ");

        String view = homeController.addProductToCartQuickly(ID, URL);
        String viewName = "redirect:" + URL;
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    @Transactional
    public void addProductToCartQuicklyGetTest() throws Exception {
        System.out.print("-> addProductToCartQuickly() RequestMethod.GET - ");

        homeController.addProductToCartQuickly();

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void clearCartTest() throws Exception {
        System.out.print("-> clearCart() - ");

        String view = homeController.clearCart();
        String viewName = "redirect:/cart";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewCheckoutTest() throws Exception {
        System.out.print("-> viewCheckout() RequestMethod.GET - ");

        ModelAndView modelAndView = homeController.viewCheckout("Name", "email", "phone");
        String[] keys = {};
        String viewName = "client/checkout";

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewCheckoutGetTest() throws Exception {
        System.out.print("-> viewCheckout() RequestMethod.GET - ");

        String view = homeController.viewCheckout();
        String viewName = "redirect:/cart";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    @Transactional
    public void getIllegalMappingExceptionTest() throws Exception {
        System.out.print("-> getForbiddenException() - ");

        homeController.getIllegalMappingException();

        System.out.println("OK!");
    }
}
