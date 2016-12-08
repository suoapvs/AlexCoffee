package ua.com.alexcoffee.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.service.RoleService;
import ua.com.alexcoffee.tools.MockService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.alexcoffee.tools.MockModel.*;

public class RoleServiceImplTest {

    private static RoleService roleService;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"RoleServiceImpl\" - START.\n");

        roleService = MockService.getRoleService();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"RoleServiceImpl\" - FINISH.\n");
    }

    @Test(expected = WrongInformationException.class)
    public void addNullTitleTest() throws Exception {
        System.out.print("-> getById() - ");

        roleService.add(null, TITLE);
    }

    @Test
    public void getByIdTest() throws Exception {
        System.out.print("-> getById() - ");

        Role role = roleService.get(ID);
        assertNotNull(role);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void getByNullIdTest() throws Exception {
        System.out.println("-> getByNullId() - OK!");

        Long id = null;
        Role role = roleService.get(id);
    }

    @Test(expected = BadRequestException.class)
    public void getByUnknownIdTest() throws Exception {
        System.out.println("-> getByUnknownId() - OK!");

        Role role = roleService.get(UNKNOWN_ID);
    }

    @Test
    public void getByTitleTest() throws Exception {
        System.out.print("-> getByTitle() - ");

        Role role = roleService.get(ROLE_ENUM);
        assertNotNull(role);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void getByNullTitleTest() throws Exception {
        System.out.println("-> getByNullTitle() - OK!");

        RoleEnum title = null;
        Role role = roleService.get(title);
    }

    @Test(expected = BadRequestException.class)
    public void getByUnknownTitleTest() throws Exception {
        System.out.println("-> getByvTitle() - OK!");

        Role role = roleService.get(RoleEnum.CLIENT);
    }

    @Test
    public void getAdministratorTest() throws Exception {
        System.out.print("-> getAdministrator() - ");

        Role role = roleService.getAdministrator();
        assertNotNull(role);

        System.out.println("OK!");
    }

    @Test
    public void getManagerTest() throws Exception {
        System.out.print("-> getManager() - ");

        Role role = roleService.getManager();
        assertNotNull(role);

        System.out.println("OK!");
    }

    @Test
    public void getDefaultTest() throws Exception {
        System.out.print("-> getDefault() - ");

        Role role = roleService.getDefault();
        assertNotNull(role);

        System.out.println("OK!");
    }

    @Test
    public void getPersonnelTest() throws Exception {
        System.out.print("-> getPersonnel() - ");

        List<Role> personel = roleService.getPersonnel();
        assertNotNull(personel);

        System.out.println("OK!");
    }

    @Test
    public void getAllTest() throws Exception {
        System.out.println("-> getAll() - ");

        List<Role> roles = roleService.getAll();
        assertNotNull(roles);
        assertTrue(roles.size() >= 0);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void removeByNullTitleTest() throws Exception {
        System.out.println("-> removeByNullTitle() - ");

        RoleEnum roleEnum = null;
        roleService.remove(roleEnum);

        System.out.println("OK!");
    }

    @Test
    public void noExceptionOfVoidMethodTest() throws Exception {
        System.out.print("-> noExceptionOfVoidMethod() - ");

        Role role = null;
        roleService.add(role);
        roleService.update(role);
        roleService.remove(role);

        role = getRole();
        roleService.add(role);
        roleService.update(role);
        roleService.remove(role);

        List<Role> roles = null;
        roleService.add(roles);
        roleService.remove(roles);

        roles = new ArrayList<>();
        roleService.add(roles);
        roleService.remove(roles);

        roles.add(role);
        roleService.add(roles);
        roleService.remove(roles);

        roleService.add(RoleEnum.CLIENT, ANY_STRING);
        roleService.remove(ROLE_ENUM);

        System.out.println("OK!");
    }

    @Test(expected = DuplicateException.class)
    public void add() throws Exception {
        System.out.print("-> add() - ");

        roleService.add(ROLE_ENUM, ANY_STRING);

        System.out.println("OK!");
    }
}
