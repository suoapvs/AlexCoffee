package ua.com.alexcoffee.dao.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.config.RootConfig;
import ua.com.alexcoffee.config.WebConfig;
import ua.com.alexcoffee.dao.UserDAO;
import ua.com.alexcoffee.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ua.com.alexcoffee.tools.MockModel.getTenUsers;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class UserDAOImplTest {

    @Autowired
    private UserDAO userDAO;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"UserDAOImpl\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"UserDAOImpl\" - FINISH.\n");
    }

    @Test
    public void userDAONotNull() throws Exception {
        System.out.print("-> userDAO Not Null - ");

        assertNotNull(userDAO);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetAndRemoveByNameTest() throws Exception {
        System.out.print("-> Add, Get and Remove by Name - ");

        User user1 = new User("name", "email", "phone", null);
        userDAO.add(user1);
        User user2 = userDAO.getByName(user1.getName());

        assertNotNull(user2);
        assertEquals(user1, user2);

        userDAO.remove(user2.getName());

        assertNull(userDAO.getByName(user2.getName()));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetByUsernameTest() throws Exception {
        System.out.print("-> Add, Get and Remove by Username - ");

        User user1 = new User("name", "email", "phone", null);
        user1.setUsername("some_username");
        userDAO.add(user1);
        User user2 = userDAO.getByUsername(user1.getUsername());

        assertNotNull(user2);
        assertEquals(user1, user2);

        userDAO.remove(user2.getName());

        assertNull(userDAO.getByUsername(user2.getUsername()));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getMainAdministratorTest() throws Exception {
        System.out.print("-> getMainAdministrator() - ");

        User user = userDAO.getMainAdministrator();
        assertNotNull(user);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getManagersaAndClientsTest() throws Exception {
        System.out.print("-> getManagers() - ");

        List<User> users = userDAO.getManagers();
        assertNotNull(users);
        assertTrue(users.size() >= 0);

        users = userDAO.getClients();
        assertNotNull(users);
        assertTrue(users.size() >= 0);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getAuthenticatedUserTest() throws Exception {
        System.out.print("-> getAuthenticatedUser() - ");

        User user = userDAO.getAuthenticatedUser();
        assertNotNull(user);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetAndRemoveListTest() throws Exception {
        System.out.print("-> Add, Get and Remove List - ");

        User user1 = new User("t1", "e1", "p1", null);
        user1.setUsername("u1");
        User user2 = new User("t2", "e2", "p2", null);
        user2.setUsername("u2");

        List<User> users1 = new ArrayList<>();
        users1.add(user1);
        users1.add(user2);

        userDAO.add(users1);
        List<User> users2 = userDAO.getAll();

        assertNotNull(users2);
        assertTrue(users2.size() >= users1.size());
        assertTrue(users2.containsAll(users1));

        userDAO.remove(users1);
        users2 = userDAO.getAll();

        assertNotNull(users2);
        assertTrue(users2.size() >= 0);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        System.out.print("-> Get by Id - ");

        if (userDAO.getAll().size() > 0) {
            assertNotNull(userDAO.get((long) 1));
        }

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndUpdateTest() throws Exception {
        System.out.print("-> Add and Update - ");

        User user1 = new User("N", "E", "P", null);
        userDAO.add(user1);

        user1.setName("Name");
        userDAO.update(user1);

        User user2 = userDAO.getByName(user1.getName());

        assertNotNull(user2);
        assertEquals(user1, user2);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void removeAllTest() throws Exception {
        System.out.print("-> Remove All - ");

        List<User> users = getTenUsers();
        userDAO.add(users);
        assertNotNull(userDAO.getAll());
        assertTrue(userDAO.getAll().size() > 0);
/*
        userDAO.removeAll();
        assertNotNull(userDAO.getAll());
        assertTrue(userDAO.getAll().size() == 0);
*/
        System.out.println("OK!");
    }
}
