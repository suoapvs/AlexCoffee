package ua.com.alexcoffee.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.model.user.UserRole;
import ua.com.alexcoffee.service.interfaces.UserService;
import ua.com.alexcoffee.tools.MockService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.alexcoffee.tools.MockModel.*;


public class UserServiceImplTest {

    private static UserService userService;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"UserServiceImpl\" - START.\n");

        userService = MockService.getUserService();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"UserServiceImpl\" - FINISH.\n");
    }

    @Test
    public void getByIdTest() throws Exception {
        System.out.print("-> getById() - ");

        User user = userService.get(ID);
        assertNotNull(user);

        System.out.println("OK!");
    }

    @Test(expected = NullPointerException.class)
    public void getByUnknownIdTest() throws Exception {
        System.out.println("-> getByUnknownId() - OK!");

        User user = userService.get(UNKNOWN_ID);
    }

    @Test
    public void getByNameTest() throws Exception {
        System.out.print("-> getByName() - ");

        User user = userService.getByName(NAME);
        assertNotNull(user);

        System.out.println("OK!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByNullNameTest() throws Exception {
        System.out.println("-> getByNullName() - OK!");

        String name = null;
        User user = userService.getByName(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByEmptyNameTest() throws Exception {
        System.out.println("-> getByEmptyName() - OK!");

        String name = "";
        User user = userService.getByName(name);
    }

    @Test(expected = NullPointerException.class)
    public void getByUnknownNameTest() throws Exception {
        System.out.println("-> getByUnknownName() - OK!");

        User user = userService.getByName(ANY_STRING);
    }

    @Test
    public void getByUsernameTest() throws Exception {
        System.out.print("-> getByUsername() - ");

        User user = userService.getByUsername(USERNAME);
        assertNotNull(user);

        System.out.println("OK!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByNullUsernameTest() throws Exception {
        System.out.println("-> getByNullUsername() - OK!");

        String username = null;
        User user = userService.getByUsername(username);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByEmptyUsernameTest() throws Exception {
        System.out.println("-> getByEmptyUsername() - OK!");

        String username = "";
        User user = userService.getByUsername(username);
    }

    @Test(expected = NullPointerException.class)
    public void getByUnknownUsernameTest() throws Exception {
        System.out.println("-> getByUnknownUsername() - OK!");
        User user = userService.getByUsername(ANY_STRING);
    }

    @Ignore
    @Test
    public void getMainAdministratorTest() throws Exception {
        System.out.print("-> getMainAdministrator() - ");

        User user = userService.getMainAdministrator();
        assertNotNull(user);

        System.out.println("OK!");
    }

    @Test
    public void getAllUsersTest() throws Exception {
        System.out.print("-> getAllUsers() - ");

        Collection<User> users = userService.getAdministrators();
        assertNotNull(users);

        users = userService.getManagers();
        assertNotNull(users);

        users = userService.getClients();
        assertNotNull(users);

        users = userService.getPersonnel();
        assertNotNull(users);

        System.out.println("OK!");
    }

    @Test
    public void getAuthenticatedUserTest() throws Exception {
        System.out.print("-> getAuthenticatedUser() - ");
        userService.getAuthenticatedUser();
        System.out.println("OK!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeByNullNameTest() throws Exception {
        System.out.print("-> removeByNullName() - OK!");
        userService.removeByName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeByNullRoleTest() throws Exception {
        System.out.print("-> removeByNullRole() - ");

        userService.removeByRole(null);
    }

    @Test
    public void getAllTest() throws Exception {
        System.out.println("-> getAll() - ");

        Collection<User> users = userService.getAll();
        assertNotNull(users);
        assertTrue(users.size() >= 0);

        System.out.println("OK!");
    }

    @Test
    public void noExceptionOfVoidMethodTest() throws Exception {
        System.out.print("-> noExceptionOfVoidMethod() - ");

        User user = null;
        userService.add(user);
        userService.update(user);
        userService.remove(user);

        user = getUser();
        userService.add(user);
        userService.update(user);
        userService.remove(user);

        List<User> users = null;
        userService.add(users);
        userService.remove(users);

        users = new ArrayList<>();
        userService.add(users);
        userService.remove(users);

        users.add(user);
        userService.add(users);
        userService.remove(users);

        userService.removeByName(NAME);
        userService.removeByRole(UserRole.ADMIN);
        userService.removePersonnel();

        System.out.println("OK!");
    }
}
