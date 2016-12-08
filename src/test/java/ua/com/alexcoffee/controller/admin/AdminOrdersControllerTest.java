package ua.com.alexcoffee.controller.admin;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.tools.MockController;

import static ua.com.alexcoffee.tools.MockModel.ID;
import static ua.com.alexcoffee.tools.ModelAndViews.checkModelAndView;

public class AdminOrdersControllerTest {

    private static AdminOrdersController adminOrdersController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"AdminOrdersController\" - START.\n");

        adminOrdersController = MockController.getAdminOrdersController();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"AdminOrdersController\" - FINISH.\n");
    }

    @Test
    public void viewAllOrdersTest() throws Exception {
        System.out.print("-> viewAllOrders() - ");

        ModelAndView modelAndView = adminOrdersController.viewAllOrders(new ModelAndView());
        String[] keys = {"orders", "status_new", "auth_user"};
        String viewName = "admin/order/all";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void viewOrderTest() throws Exception {
        System.out.print("-> viewOrder() - ");

        ModelAndView modelAndView = adminOrdersController.viewOrder(ID, new ModelAndView());
        String[] keys = {"order", "sale_positions", "order_price", "status_new", "admin_role", "manager_role", "auth_user"};
        String viewName = "admin/order/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void getEditOrderPageTest() throws Exception {
        System.out.print("-> getEditOrderPage() - ");

        ModelAndView modelAndView = adminOrdersController.getEditOrderPage(ID, new ModelAndView());
        String[] keys = {"order", "sale_positions", "order_price", "statuses", "auth_user"};
        String viewName = "admin/order/edit";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void updateOrderTest() throws Exception {
        System.out.print("-> updateOrder() - ");

        ModelAndView modelAndView = adminOrdersController.updateOrder(ID, ID, "number", ID, "name", "email", "phone",
                "shippingAddress", "shippingDetails", "description", new ModelAndView());
        String viewName = "redirect:/admin/view_order_1";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void updateOrderGetTest() throws Exception {
        System.out.print("-> updateOrder() RequestMethod.GET - ");

        adminOrdersController.updateOrder();

        System.out.println("OK!");
    }

    @Test
    public void deleteOrderTest() throws Exception {
        System.out.print("-> deleteOrder() - ");

        ModelAndView modelAndView = adminOrdersController.deleteOrder(ID, new ModelAndView());
        String viewName = "redirect:/admin/orders";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test
    public void deleteAllOrdersTest() throws Exception {
        System.out.print("-> deleteAllOrders() - ");

        ModelAndView modelAndView = adminOrdersController.deleteAllOrders(new ModelAndView());
        String viewName = "redirect:/admin/orders";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }
}
