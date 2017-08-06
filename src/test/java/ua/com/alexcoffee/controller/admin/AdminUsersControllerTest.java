package ua.com.alexcoffee.controller.admin;

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

        ModelAndView modelAndView = adminUsersController.viewAllPersonnel();
        String[] keys = { "users", "admin_role", "manager_role" };
        String viewName = "user/admin/all";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void viewUserTest() throws Exception {
        System.out.print("-> viewUser() - ");

        ModelAndView modelAndView = adminUsersController.viewUser(ID);
        String[] keys = { "user", "admin_role", "manager_role" };
        String viewName = "/user/admin/one";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void getAddUserPageTest() throws Exception {
        System.out.print("-> getAddUserPage() - ");

        ModelAndView modelAndView = adminUsersController.getAddUserPage();
        String[] keys = { "roles" };
        String viewName = "/user/admin/add";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void saveUserTest() throws Exception {
        System.out.print("-> saveUser() - ");

        String view = adminUsersController.saveUser("name", "ADMIN", "username", "password", "email",
                "phone", "vkontakte", "facebook", "skype", "description");
        String viewName = "redirect:/admin/user/all";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    public void saveUserGetTest() throws Exception {
        System.out.print("-> saveUser() RequestMethod.GET - ");

        adminUsersController.saveUser();

        System.out.println("OK!");
    }

    @Test
    public void getEditUserPageTest() throws Exception {
        System.out.print("-> getEditUserPage() - ");

        ModelAndView modelAndView = adminUsersController.getEditUserPage(ID);
        String[] keys = { "user", "roles" };
        String viewName = "/user/admin/edit";
        checkModelAndView(modelAndView, viewName, keys);

        System.out.println("OK!");
    }

    @Test
    public void updateUserTest() throws Exception {
        System.out.print("-> updateUser() - ");

        String view = adminUsersController.updateUser(ID, "name", "ADMIN", "username", "password",
                "email", "phone", "vkontakte", "facebook", "skype", "description");
        String viewName = "redirect:/admin/user/view/" + ID;
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test(expected = IllegalMappingException.class)
    public void updateUserGet() throws Exception {
        System.out.print("-> updateUser() RequestMethod.GET - ");

        adminUsersController.updateUser();

        System.out.println("OK!");
    }

    @Ignore
    @Test
    public void deleteUserTest() throws Exception {
        System.out.print("-> deleteUser() - ");

        String view = adminUsersController.deleteUser(ID);
        String viewName = "redirect:/admin/user/all";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }

    @Test
    public void deleteAllTest() throws Exception {
        System.out.print("-> deleteAll() - ");

        String view = adminUsersController.deleteAll();
        String viewName = "redirect:/admin/user/all";
        assertEquals(view, viewName);

        System.out.println("OK!");
    }
}
