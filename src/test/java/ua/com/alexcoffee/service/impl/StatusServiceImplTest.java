package ua.com.alexcoffee.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.enums.StatusEnum;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.service.interfaces.StatusService;
import ua.com.alexcoffee.tools.MockService;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.alexcoffee.tools.MockModel.*;

public class StatusServiceImplTest {

    private static StatusService statusService;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"StatusServiceImpl\" - START.\n");

        statusService = MockService.getStatusService();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"StatusServiceImpl\" - FINISH.\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullTitleTest() throws Exception {
        System.out.print("-> getById() - ");

        statusService.add(null, TITLE);
    }

    @Test
    public void getByIdTest() throws Exception {
        System.out.print("-> getById() - ");

        Status status = statusService.get(ID);
        assertNotNull(status);

        System.out.println("OK!");
    }

    @Test(expected = NullPointerException.class)
    public void getByUnknownIdTest() throws Exception {
        System.out.println("-> getByUnknownId() - OK!");
        statusService.get(UNKNOWN_ID);
    }

    @Test
    public void getByTitleTest() throws Exception {
        System.out.print("-> getByTitle() - ");

        Status status = statusService.get(STATUS_ENUM);
        assertNotNull(status);

        System.out.println("OK!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByNullTitleTest() throws Exception {
        System.out.println("-> getByNullTitle() - OK!");

        StatusEnum title = null;
        Status status = statusService.get(title);
    }

    @Test(expected = NullPointerException.class)
    public void getByUnknownTitleTest() throws Exception {
        System.out.println("-> getByUnknownTitle() - OK!");

        Status status = statusService.get(StatusEnum.CLOSED);
    }

    @Test
    public void getDefaultTest() throws Exception {
        System.out.print("-> getDefault() - ");

        Status status = statusService.getDefault();
        assertNotNull(status);

        System.out.println("OK!");
    }

    @Test
    public void getAllTest() throws Exception {
        System.out.println("-> getAll() - ");

        Collection<Status> statuses = statusService.getAll();
        assertNotNull(statuses);
        assertTrue(statuses.size() >= 0);

        System.out.println("OK!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeByNullTitleTest() throws Exception {
        System.out.println("-> removeByNullTitle() - ");

        StatusEnum statusEnum = null;
        statusService.remove(statusEnum);

        System.out.println("OK!");
    }

    @Test
    public void noExceptionOfVoidMethodTest() throws Exception {
        System.out.print("-> noExceptionOfVoidMethod() - ");

        statusService.add(StatusEnum.CLOSED, ANY_STRING);
        statusService.remove(STATUS_ENUM);

        System.out.println("OK!");
    }

    @Test(expected = DuplicateException.class)
    public void add() throws Exception {
        System.out.print("-> add() - ");

        statusService.add(STATUS_ENUM, ANY_STRING);

        System.out.println("OK!");
    }
}
