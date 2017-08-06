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

        ModelAndView modelAndView = adminOrdersController.viewAllOrders();
        String[] keys = { "orders", "status_new" };
        String viewName = "order/admin/all";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void viewOrderTest() throws Exception {
        System.out.print("-> viewOrder() - ");

        ModelAndView modelAndView = adminOrdersController.viewOrder(ID);
        String[] keys = { "order", "sale_positions", "order_price", "status_new", "admin_role", "manager_role" };
        String viewName = "order/admin/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void getEditOrderPageTest() throws Exception {
        System.out.print("-> getEditOrderPage() - ");

        ModelAndView modelAndView = adminOrdersController.getEditOrderPage(ID);
        String[] keys = { "order", "sale_positions", "order_price", "statuses" };
        String viewName = "order/admin/edit";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void updateOrderTest() throws Exception {
        System.out.print("-> updateOrder() - ");

        String view = adminOrdersController.updateOrder(ID, ID, "number", "NEW", "name", "email", "phone",
                "shippingAddress", "shippingDetails", "description");
        String viewName = "redirect:/admin/order/view/1";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    public void updateOrderGetTest() throws Exception {
        System.out.print("-> updateOrder() RequestMethod.GET - ");

        adminOrdersController.updateOrder();

        System.out.println("OK!");
    }

    @Test
    public void deleteOrderTest() throws Exception {
        System.out.print("-> deleteOrder() - ");

        String view = adminOrdersController.deleteOrder(ID);
        String viewName = "redirect:/admin/order/all";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test
    public void deleteAllOrdersTest() throws Exception {
        System.out.print("-> deleteAllOrders() - ");

        String view = adminOrdersController.deleteAllOrders();
        String viewName = "redirect:/admin/order/all";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }
}
