package ua.com.alexcoffee.controller.manager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.tools.MockController;

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

        ModelAndView modelAndView = managerOrdersController.viewAllOrders(new ModelAndView());
        String[] keys = {"orders", "status_new", "auth_user"};
        String viewName = "manager/order/all";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Ignore
    public void viewOrderTest() throws Exception {
        System.out.print("-> viewOrder() - ");

        ModelAndView modelAndView = managerOrdersController.viewOrder(ID, new ModelAndView());
        String[] keys = {"order", "sales", "order_price", "status_new", "auth_user", "manager_role", "admin_role"};
        String viewName = "manager/order/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void getEditOrderPageTest() throws Exception {
        System.out.print("-> getEditOrderPage() - ");

        ModelAndView modelAndView = managerOrdersController.getEditOrderPage(ID, new ModelAndView());
        String[] keys = {"order", "sales", "order_price", "statuses", "auth_user"};
        String viewName = "manager/order/edit";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void updateOrderTest() throws Exception {
        System.out.print("-> updateOrder() - ");

        ModelAndView modelAndView = managerOrdersController.updateOrder(ID, ID, "number", ID,
                "name", "email", "phone", "shippingAddress", "shippingDetails", "description", new ModelAndView());
        String viewName = "redirect:/manager/view_order_1";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void updateOrderGetTest() throws Exception {
        System.out.print("-> updateOrder() RequestMethod.GET - ");

        managerOrdersController.updateOrder();

        System.out.println("OK!");
    }
}
