package ua.com.alexcoffee.controller.manager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.tools.MockController;

import static ua.com.alexcoffee.tools.MockModel.ID;
import static ua.com.alexcoffee.tools.ModelAndViews.checkModelAndView;

public class ManagerUsersControllerTest {

    private static ManagerUsersController managerUsersController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"CategoryServiceImpl\" - START.\n");

        managerUsersController = MockController.getManagerUsersController();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"CategoryServiceImpl\" - FINISH.\n");
    }

    @Test
    public void viewAllPersonnelTest() throws Exception {
        System.out.print("-> viewAllPersonnel() - ");

        ModelAndView modelAndView = managerUsersController.viewAllPersonnel(new ModelAndView());
        String[] keys = {"users", "admin_role", "manager_role", "auth_user"};
        String viewName = "manager/user/all";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void viewUserTest() throws Exception {
        System.out.print("-> viewUser() - ");

        ModelAndView modelAndView = managerUsersController.viewUser(ID, new ModelAndView());
        String[] keys = {"user", "admin_role", "manager_role", "auth_user"};
        String viewName = "manager/user/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }
}
