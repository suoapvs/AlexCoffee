package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.enums.RoleEnum;

import java.util.List;

import static org.junit.Assert.*;
import static ua.com.alexcoffee.tools.MockModel.getTenUsers;

public class RoleTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"Role\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"Role\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        RoleEnum title = RoleEnum.ADMIN;
        String description = "Description";

        Role role = new Role(title, description);

        String line = "Title: " + title.name() + "\nDescription: " + description;

        assertEquals(role.toString(), line);

        System.out.println("OK!");
    }

    @Test
    public void toEqualsTest() {
        System.out.print("-> toEquals() - ");

        Role role = new Role(RoleEnum.ADMIN, "ADMIN");
        assertEquals(role.toEquals(), RoleEnum.ADMIN.name());

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Role role = new Role();
        assertEquals(role, role);

        role = new Role(RoleEnum.ADMIN, "ADMIN");
        assertTrue(role.equals(role));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Role role1 = new Role(RoleEnum.ADMIN, "ADMIN");
        Role role2 = new Role(RoleEnum.ADMIN, "");

        assertTrue(role1.equals(role2));
        assertTrue(role2.equals(role1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Role role1 = new Role(RoleEnum.ADMIN, "ADMIN");
        Role role2 = new Role(RoleEnum.ADMIN, "");
        Role role3 = new Role(RoleEnum.ADMIN, "ADMIN");

        assertTrue(role1.equals(role2));
        assertTrue(role2.equals(role3));
        assertTrue(role1.equals(role3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Role role1 = new Role(RoleEnum.ADMIN, "ADMIN");
        Role role2 = new Role(RoleEnum.ADMIN, "");

        for (int i = 0; i < 10; i++) {
            assertTrue(role1.equals(role2));
        }

        System.out.println("OK!");
    }

    @Test
    public void addUserTest() {
        System.out.print("-> addUser() - ");

        Role role = new Role();
        for (int i = 0; i < 10; i++) {
            role.addUser(new User());
        }
        assertTrue(role.getUsers().size() == 10);

        System.out.println("OK!");
    }

    @Test
    public void addUsersTest() {
        System.out.print("-> addUsers() - ");

        List<User> users = getTenUsers();

        Role role = new Role();
        role.addUsers(users);

        assertTrue(role.getUsers().size() == 10);

        System.out.println("OK!");
    }

    @Test
    public void removeUserTest() {
        System.out.print("-> removeUser() - ");

        Role role = new Role();

        User user = new User();
        role.addUser(user);
        role.addUsers(getTenUsers());

        role.removeUser(user);

        assertTrue(role.getUsers().size() == 10);

        System.out.println("OK!");
    }

    @Test
    public void addAndRemoveUsersTest() {
        System.out.print("-> addAndRemoveUsers() - ");

        Role role = new Role();

        List<User> users = getTenUsers();
        role.addUsers(users);
        assertTrue(role.getUsers().size() == 10);

        role.removeUsers(users);
        assertTrue(role.getUsers().size() == 0);

        System.out.println("OK!");
    }

    @Test
    public void clearUsersTest() {
        System.out.print("-> clearUsers() - ");

        Role role = new Role();
        List<User> users = getTenUsers();
        role.addUsers(users);

        role.clearUsers();

        assertTrue(role.getUsers().size() == 0);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetUsersTest() {
        System.out.print("-> setAndGetUsers() - ");

        Role role = new Role(RoleEnum.ADMIN, "");
        role.setUsers(getTenUsers());

        assertNotNull(role.getUsers());
        assertTrue(role.getUsers().size() == 10);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetTitleTest() {
        System.out.print("-> setAndGetTitle() - ");

        Role role = new Role();
        role.setTitle(RoleEnum.ADMIN);

        assertNotNull(role.getTitle());
        assertEquals(role.getTitle(), RoleEnum.ADMIN);

        System.out.println("OK!");
    }

    @Test
    public void setDescriptionTest() {
        System.out.print("-> setAndGetDescription() - ");

        Role role = new Role();
        role.setDescription(null);
        assertNotNull(role.getDescription());
        assertTrue(role.getDescription().isEmpty());

        String description = "Description";
        role.setDescription(description);
        assertEquals(role.getDescription(), description);

        System.out.println("OK!");
    }
}
