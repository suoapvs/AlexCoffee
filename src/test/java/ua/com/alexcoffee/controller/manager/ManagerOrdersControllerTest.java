package ua.com.alexcoffee.controller.manager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.tools.MockController;

import static org.junit.Assert.assertEquals;
import static ua.com.alexcoffee.tools.MockModel.ID;
import static ua.com.alexcoffee.tools.ModelAndViews.checkModelAndView;

public class ManagerOrdersControllerTest {

    private static ManagerOrdersController managerOrdersController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"ManagerOrdersController\" - START.\n");

        managerOrdersController = MockController.getManagerOrdersController();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"ManagerOrdersController\" - FINISH.\n");
    }

    @Test
    public void viewAllOrdersTest() throws Exception {
        System.out.print("-> viewAllOrders() - ");

        ModelAndView modelAndView = managerOrdersController.viewAllOrders();
        String[] keys = { "orders", "status_new" };
        String viewName = "order/manager/all";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Ignore
    public void viewOrderTest() throws Exception {
        System.out.print("-> viewOrder() - ");

        ModelAndView modelAndView = managerOrdersController.viewOrder(ID);
        String[] keys = { "order", "sales", "order_price", "status_new", "manager_role", "admin_role" };
        String viewName = "manager/order/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Ignore
    @Test
    public void getEditOrderPageTest() throws Exception {
        System.out.print("-> getEditOrderPage() - ");

        ModelAndView modelAndView = managerOrdersController.getEditOrderPage(ID);
        String[] keys = { "order", "sale_positions", "order_price", "statuses" };
        String viewName = "order/manager/edit";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void updateOrderTest() throws Exception {
        System.out.print("-> updateOrder() - ");

        String view = managerOrdersController.updateOrder(ID, ID, "number", "NEW",
                "name", "email", "phone", "shippingAddress", "shippingDetails", "description");
        String viewName = "redirect:/manager/order/view/1";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    public void updateOrderGetTest() throws Exception {
        System.out.print("-> updateOrder() RequestMethod.GET - ");

        managerOrdersController.updateOrder();

        System.out.println("OK!");
    }
}
