package ua.com.alexcoffee.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.service.OrderService;
import ua.com.alexcoffee.tools.MockService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.alexcoffee.tools.MockModel.*;

public class OrderServiceImplTest {

    private static OrderService orderService;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"OrderServiceImpl\" - START.\n");

        orderService = MockService.getOrderService();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"OrderServiceImpl\" - FINISH.\n");
    }

    @Test
    public void getByIdTest() throws Exception {
        System.out.print("-> getById() - ");

        Order order = orderService.get(ID);
        assertNotNull(order);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void getByNullIdTest() throws Exception {
        System.out.println("-> getByNullId() - OK!");

        Long id = null;
        Order order = orderService.get(id);
    }

    @Test(expected = BadRequestException.class)
    public void getByUnknownIdTest() throws Exception {
        System.out.println("-> getByUnknownId() - OK!");

        Order order = orderService.get(UNKNOWN_ID);
    }

    @Test
    public void getByNumberTest() throws Exception {
        System.out.print("-> getByNumber() - ");

        Order order = orderService.get(NUMBER);
        assertNotNull(order);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void getByNullNumberTest() throws Exception {
        System.out.println("-> getByNullNumber() - OK!");

        String number = null;
        Order order = orderService.get(number);
    }

    @Test(expected = WrongInformationException.class)
    public void getByEmptyNumberTest() throws Exception {
        System.out.println("-> getByEmptyNumber() - OK!");

        String number = "";
        Order order = orderService.get(number);
    }

    @Test(expected = BadRequestException.class)
    public void getByUnknownNumberTest() throws Exception {
        System.out.println("-> getByUnknownNumber() - OK!");

        Order order = orderService.get(ANY_STRING);
    }

    @Test(expected = WrongInformationException.class)
    public void removeByNullIdTest() throws Exception {
        System.out.println("-> removeByNullId() - OK!");

        Long id = null;
        orderService.remove(id);
    }

    @Test
    public void removeByNumberTest() throws Exception {
        System.out.print("-> removeByNumber() - ");

        orderService.remove(NUMBER);

        System.out.println("OK!");
    }

    @Test(expected = WrongInformationException.class)
    public void removeByNullNumberTest() throws Exception {
        System.out.println("-> removeByNullUrl() - OK!");

        String number = null;
        orderService.remove(number);
    }

    @Test(expected = WrongInformationException.class)
    public void removeByEmptyNumberTest() throws Exception {
        System.out.println("-> removeByEmptyUrl() - OK!");

        String number = "";
        orderService.remove(number);
    }

    @Test
    public void getAllTest() throws Exception {
        System.out.println("-> getAll() - ");

        List<Order> orders = orderService.getAll();
        assertNotNull(orders);
        assertTrue(orders.size() >= 0);

        System.out.println("OK!");
    }

    @Test
    public void noExceptionOfVoidMethodTest() throws Exception {
        System.out.print("-> noExceptionOfVoidMethod() - ");

        Order order = null;
        orderService.add(order);
        orderService.update(order);
        orderService.remove(order);

        order = getOrder();
        orderService.add(order);
        orderService.update(order);
        orderService.remove(order);

        List<Order> orders = null;
        orderService.add(orders);
        orderService.remove(orders);

        orders = new ArrayList<>();
        orderService.add(orders);
        orderService.remove(orders);

        orders.add(order);
        orderService.add(orders);
        orderService.remove(orders);

        System.out.println("OK!");
    }
}
