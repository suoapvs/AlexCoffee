package ua.com.alexcoffee.controller.admin;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.tools.MockController;

import static ua.com.alexcoffee.tools.MockModel.ID;
import static ua.com.alexcoffee.tools.ModelAndViews.checkModelAndView;


public class AdminUsersControllerTest {

    private static AdminUsersController adminUsersController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"AdminUsersController\" - START.\n");

        adminUsersController = MockController.getAdminUsersController();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"AdminUsersController\" - FINISH.\n");
    }

    @Test
    public void viewAllPersonneGetTest() throws Exception {
        System.out.print("-> viewAllPersonne() - ");

        ModelAndView modelAndView = adminUsersController.viewAllPersonnel(new ModelAndView());
        String[] keys = {"users", "admin_role", "manager_role", "auth_user"};
        String viewName = "admin/user/all";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void viewUserTest() throws Exception {
        System.out.print("-> viewUser() - ");

        ModelAndView modelAndView = adminUsersController.viewUser(ID, new ModelAndView());
        String[] keys = {"user", "admin_role", "manager_role", "auth_user"};
        String viewName = "/admin/user/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void getAddUserPageTest() throws Exception {
        System.out.print("-> getAddUserPage() - ");

        ModelAndView modelAndView = adminUsersController.getAddUserPage(new ModelAndView());
        String[] keys = {"roles", "auth_user"};
        String viewName = "/admin/user/add";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void saveUserTest() throws Exception {
        System.out.print("-> saveUser() - ");

        ModelAndView modelAndView = adminUsersController.saveUser("name", ID, "username", "password", "email",
                "phone", "vkontakte", "facebook", "skype", "description", new ModelAndView());
        String viewName = "redirect:/admin/users";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void saveUserGetTest() throws Exception {
        System.out.print("-> saveUser() RequestMethod.GET - ");

        adminUsersController.saveUser();

        System.out.println("OK!");
    }

    @Test
    public void getEditUserPageTest() throws Exception {
        System.out.print("-> getEditUserPage() - ");

        ModelAndView modelAndView = adminUsersController.getEditUserPage(ID, new ModelAndView());
        String[] keys = {"user", "roles", "auth_user"};
        String viewName = "/admin/user/edit";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void updateUserTest() throws Exception {
        System.out.print("-> updateUser() - ");

        ModelAndView modelAndView = adminUsersController.updateUser(ID, "name", ID, "username", "password",
                "email", "phone", "vkontakte", "facebook", "skype", "description", new ModelAndView());
        String viewName = "redirect:/admin/view_user_" + ID;
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void updateUserGet() throws Exception {
        System.out.print("-> updateUser() RequestMethod.GET - ");

        adminUsersController.updateUser();

        System.out.println("OK!");
    }

    @Test
    public void deleteUserTest() throws Exception {
        System.out.print("-> deleteUser() - ");

        ModelAndView modelAndView = adminUsersController.deleteUser(ID, new ModelAndView());
        String viewName = "redirect:/admin/users";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }

    @Test
    public void deleteAllTest() throws Exception {
        System.out.print("-> deleteAll() - ");

        ModelAndView modelAndView = adminUsersController.deleteAll(new ModelAndView());
        String viewName = "redirect:/admin/users";
        checkModelAndView(modelAndView, viewName);

        System.out.println("OK!");
    }
}
