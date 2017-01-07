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
import ua.com.alexcoffee.dao.interfaces.StatusDAO;
import ua.com.alexcoffee.enums.StatusEnum;
import ua.com.alexcoffee.model.Status;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class StatusDAOImplTest {

    @Autowired
    private StatusDAO statusDAO;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"StatusDAOImpl\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"StatusDAOImpl\" - FINISH.\n");
    }

    @Test
    public void statusDAONotNull() throws Exception {
        System.out.print("-> statusDAO Not Null - ");

        assertNotNull(statusDAO);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addTest() throws Exception {
        System.out.print("-> add() - ");

        statusDAO.remove(StatusEnum.NEW);
        assertNull(statusDAO.get(StatusEnum.NEW));

        statusDAO.add(StatusEnum.NEW, "NEW");
        assertNotNull(statusDAO.get(StatusEnum.NEW));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getAngRemoveByTitleTest() throws Exception {
        System.out.print("-> Add, Get and Remove by Title Not Null - ");

        Status status = statusDAO.get(StatusEnum.CLOSED);
        assertNotNull(status);

        statusDAO.remove(status.getTitle());
        assertNull(statusDAO.get(status.getTitle()));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getDefaultTest() throws Exception {
        System.out.print("-> getDefault() - ");

        assertNotNull(statusDAO.getDefault());

        System.out.println("OK!");
    }
}
