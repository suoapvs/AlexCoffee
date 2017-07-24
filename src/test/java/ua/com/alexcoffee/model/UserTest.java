package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.model.user.UserRole;

import static org.junit.Assert.*;
import static ua.com.alexcoffee.tools.MockModel.getTenOrders;

public class UserTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"User\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"User\" - FINISH.\n");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        final String name = "Name";
        final String email = "Email";
        final String phone = "Phone";
        final User user = User.getBuilder().build();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(UserRole.ADMIN);
        final String line = "Name: " + name + "\nRole: " + UserRole.ADMIN.name() + "\nEmail: " + email
                + "\nPhone: " + phone;

        assertTrue(user.toString().equals(line));

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        final String name = "Name";
        final String email = "Email";
        final String phone = "Phone";
        final User user = User.getBuilder().build();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(UserRole.ADMIN);
        assertTrue(user.equals(user));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        final String name = "Name";
        final String email = "Email";
        final String phone = "Phone";
        final User user1 = User.getBuilder().build();
        user1.setName(name);
        user1.setEmail(email);
        user1.setPhone(phone);
        user1.setRole(UserRole.ADMIN);
        final User user2 = User.getBuilder().build();
        user2.setName(name);
        user2.setEmail(email);
        user2.setPhone(phone);
        user2.setRole(UserRole.ADMIN);

        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        final String name = "Name";
        final String email = "Email";
        final String phone = "Phone";
        final User user1 = User.getBuilder().build();
        user1.setName(name);
        user1.setEmail(email);
        user1.setPhone(phone);
        user1.setRole(UserRole.ADMIN);
        final User user2 = User.getBuilder().build();
        user2.setName(name);
        user2.setEmail(email);
        user2.setPhone(phone);
        user2.setRole(UserRole.ADMIN);
        final User user3 = User.getBuilder().build();
        user3.setName(name);
        user3.setEmail(email);
        user3.setPhone(phone);
        user3.setRole(UserRole.ADMIN);

        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user3));
        assertTrue(user1.equals(user3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        final String name = "Name";
        final String email = "Email";
        final String phone = "Phone";
        final User user1 = User.getBuilder().build();
        user1.setName(name);
        user1.setEmail(email);
        user1.setPhone(phone);
        user1.setRole(UserRole.ADMIN);
        final User user2 = User.getBuilder().build();
        user2.setName(name);
        user2.setEmail(email);
        user2.setPhone(phone);
        user2.setRole(UserRole.ADMIN);

        for (int i = 0; i < 10; i++) {
            assertTrue(user1.equals(user2));
        }

        System.out.println("OK!");
    }

    @Test
    public void userDetailsMethodTest() {
        System.out.print("-> userDetailsMethod() - ");

        User user = User.getBuilder().build();
        assertTrue(user.isEnabled());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());

        System.out.println("OK!");
    }

    @Test
    public void getAuthoritiesTest() {
        System.out.print("-> getAuthorities() - ");

        User user = User.getBuilder().build();
        user.setRole(UserRole.ADMIN);

        assertNotNull(user.getAuthorities());

        System.out.println("OK!");
    }

    @Test
    public void initializeTest() {
        System.out.print("-> initialize() - ");

        final String name = "name";
        final String username = "username";
        final String password = "pass";
        final String email = "email";
        final String phone = "phone";
        final String vkontakte = "vk";
        final String facebook = "fb";
        final String skype = "sk";
        final String description = "description";

        final User user = User.getBuilder().build();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setVkontakte(vkontakte);
        user.setFacebook(facebook);
        user.setSkype(skype);
        user.setDescription(description);
        user.setRole(UserRole.ADMIN);

        assertEquals(user.getName(), name);
        assertEquals(user.getUsername(), username);
        assertEquals(user.getPassword(), password);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getPhone(), phone);
        assertEquals(user.getVkontakte(), vkontakte);
        assertEquals(user.getFacebook(), facebook);
        assertEquals(user.getSkype(), skype);
        assertEquals(user.getDescription(), description);
        assertEquals(user.getRole(), UserRole.ADMIN);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetNameTest() {
        System.out.print("-> setAndGetName() - ");

        final User user = User.getBuilder().build();
        user.setName(null);
        assertNotNull(user.getName());
        assertTrue(user.getName().isEmpty());

        final String name = "NAME";
        user.setName(name);
        assertEquals(user.getName(), name);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetUsernameTest() {
        System.out.print("-> setAndGetUsername() - ");

        final User user = User.getBuilder().build();
        user.setUsername(null);
        assertNotNull(user.getUsername());
        assertTrue(user.getUsername().isEmpty());

        final String username = "USERNAME";
        user.setUsername(username);
        assertEquals(user.getUsername(), username);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetPasswordTest() {
        System.out.print("-> setAndGetPassword() - ");

        final User user = User.getBuilder().build();
        user.setPassword(null);
        assertNotNull(user.getPassword());
        assertTrue(user.getPassword().isEmpty());

        final String password = "pass";
        user.setPassword(password);
        assertEquals(user.getPassword(), password);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetEmailTest() {
        System.out.print("-> setAndGetEmail() - ");

        final User user = User.getBuilder().build();
        user.setEmail(null);
        assertNotNull(user.getEmail());
        assertTrue(user.getEmail().isEmpty());

        final String email = "email";
        user.setEmail(email);
        assertEquals(user.getEmail(), email);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetPhoneTest() {
        System.out.print("-> setAndGetPhone() - ");

        final User user = User.getBuilder().build();
        user.setPhone(null);
        assertNotNull(user.getPhone());
        assertTrue(user.getPhone().isEmpty());

        final String phone = "phone";
        user.setPhone(phone);
        assertEquals(user.getPhone(), phone);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetVkontakteTest() {
        System.out.print("-> setAndGetVkontakte() - ");

        final User user = User.getBuilder().build();
        user.setVkontakte(null);
        assertNotNull(user.getVkontakte());
        assertTrue(user.getVkontakte().isEmpty());

        final String vkontakte = "vk";
        user.setVkontakte(vkontakte);
        assertEquals(user.getVkontakte(), vkontakte);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetFacebookTest() {
        System.out.print("-> setAndGetFacebook() - ");

        final User user = User.getBuilder().build();
        user.setFacebook(null);
        assertNotNull(user.getFacebook());
        assertTrue(user.getFacebook().isEmpty());

        final String facebook = "fb";
        user.setFacebook(facebook);
        assertEquals(user.getFacebook(), facebook);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetSkypeTest() {
        System.out.print("-> setAndGetSkype() - ");

        final User user = User.getBuilder().build();
        user.setSkype(null);
        assertNotNull(user.getSkype());
        assertTrue(user.getSkype().isEmpty());

        final String skype = "skype";
        user.setSkype(skype);
        assertEquals(user.getSkype(), skype);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetDescriptionTest() {
        System.out.print("-> setAndGetDescription() - ");

        final User user = User.getBuilder().build();
        user.setDescription(null);
        assertNotNull(user.getDescription());
        assertTrue(user.getDescription().isEmpty());

        final String description = "description";
        user.setDescription(description);
        assertEquals(user.getDescription(), description);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetRoleTest() {
        System.out.print("-> setAndGetRole() - ");

        final User user = User.getBuilder().build();
        user.setRole(UserRole.ADMIN);
        assertNotNull(user.getRole());
        assertEquals(user.getRole(), UserRole.ADMIN);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetClientOrdersTest() {
        System.out.print("-> setAndGetClientOrders() - ");

        final User user = User.getBuilder().build();
        user.setClientOrders(getTenOrders());
        assertTrue(user.getClientOrders().size() == 10);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetManagerOrdersTest() {
        System.out.print("-> setAndGetManagerOrders() - ");

        final User user = User.getBuilder().build();
        user.setManagerOrders(getTenOrders());
        assertTrue(user.getManagerOrders().size() == 10);

        System.out.println("OK!");
    }
}
