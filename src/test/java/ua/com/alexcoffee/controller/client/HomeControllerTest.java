package ua.com.alexcoffee.controller.client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.exception.ForbiddenException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.tools.MockController;

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

        ModelAndView modelAndView = homeController.home(new ModelAndView());
        String[] keys = {"categories", "products", "cart_size"};
        String viewName = "client/home";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewProductsInCategoryTest() throws Exception {
        System.out.print("-> viewProductsInCategory() - ");

        ModelAndView modelAndView = homeController.viewProductsInCategory(URL, new ModelAndView());
        String[] keys = {"category", "products", "cart_size"};
        String viewName = "client/category";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewAllProductsTest() throws Exception {
        System.out.print("-> viewAllProducts() - ");

        ModelAndView modelAndView = homeController.viewAllProducts(new ModelAndView());
        String[] keys = {"products", "cart_size"};
        String viewName = "client/products";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewProductTest() throws Exception {
        System.out.print("-> viewProduct() - ");

        ModelAndView modelAndView1 = homeController.viewProduct(URL, new ModelAndView());
        String[] keys = {"product", "cart_size", "featured_products"};
        String viewName = "client/product";
        checkModelAndView(modelAndView1, viewName, keys);

        ModelAndView modelAndView2 = homeController.viewProduct(Integer.toString(ARTICLE), new ModelAndView());
        checkModelAndView(modelAndView2, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewCartTest() throws Exception {
        System.out.print("-> viewCart() - ");

        ModelAndView modelAndView = homeController.viewCart(new ModelAndView());
        String[] keys = {"sale_positions", "price_of_cart", "cart_size"};
        String viewName = "client/cart";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addProductToCartTest() throws Exception {
        System.out.print("-> addProductToCart() - ");

        ModelAndView modelAndView = homeController.addProductToCart(ID, new ModelAndView());
        String viewName = "redirect:/cart";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
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

        ModelAndView modelAndView = homeController.addProductToCartQuickly(ID, URL, new ModelAndView());
        String viewName = "redirect:" + URL;
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
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

        ModelAndView modelAndView = homeController.clearCart(new ModelAndView());
        String viewName = "redirect:/cart";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewCheckoutTest() throws Exception {
        System.out.print("-> viewCheckout() RequestMethod.GET - ");

        ModelAndView modelAndView = homeController.viewCheckout("Name", "email", "phone", new ModelAndView());
        String[] keys = {};
        String viewName = "client/checkout";

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void viewCheckoutGetTest() throws Exception {
        System.out.print("-> viewCheckout() RequestMethod.GET - ");

        ModelAndView modelAndView = homeController.viewCheckout(new ModelAndView());
        String viewName = "redirect:/cart";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test(expected = ForbiddenException.class)
    @Transactional
    public void getForbiddenExceptionTest() throws Exception {
        System.out.print("-> getForbiddenException() - ");

        homeController.getForbiddenException();

        System.out.println("OK!");
    }
}
