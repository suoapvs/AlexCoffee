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
import ua.com.alexcoffee.dao.RoleDAO;
import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.model.Role;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class RoleDAOImplTest {

    @Autowired
    private RoleDAO roleDAO;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"RoleDAOImpl\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"RoleDAOImpl\" - FINISH.\n");
    }

    @Test
    public void roleDAONotNull() throws Exception {
        System.out.print("-> roleDAO Not Null - ");

        assertNotNull(roleDAO);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addTest() throws Exception {
        System.out.print("-> add() - ");

        roleDAO.remove(RoleEnum.ADMIN);
        assertNull(roleDAO.get(RoleEnum.ADMIN));

        roleDAO.add(RoleEnum.ADMIN, "ADMIN");
        assertNotNull(roleDAO.get(RoleEnum.ADMIN));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getAngRemoveByTitleTest() throws Exception {
        System.out.print("-> Add, Get and Remove by Title Not Null - ");

        Role role = roleDAO.get(RoleEnum.ADMIN);
        assertNotNull(role);

        roleDAO.remove(role.getTitle());
        assertNull(roleDAO.get(role.getTitle()));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getDefaultTest() throws Exception {
        System.out.print("-> getDefault() - ");

        assertNotNull(roleDAO.getDefault());

        System.out.println("OK!");
    }
}
