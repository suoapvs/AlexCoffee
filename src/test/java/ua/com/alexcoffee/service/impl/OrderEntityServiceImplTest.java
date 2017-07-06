package ua.com.alexcoffee.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.order.Order;
import ua.com.alexcoffee.service.interfaces.OrderService;
import ua.com.alexcoffee.tools.MockService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.alexcoffee.tools.MockModel.*;

public class OrderEntityServiceImplTest {

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

        Order orderEntity = orderService.get(ID);
        assertNotNull(orderEntity);

        System.out.println("OK!");
    }

    @Test(expected = NullPointerException.class)
    public void getByUnknownIdTest() throws Exception {
        System.out.println("-> getByUnknownId() - OK!");

        Order orderEntity = orderService.get(UNKNOWN_ID);
    }

    @Test
    public void getByNumberTest() throws Exception {
        System.out.print("-> getByNumber() - ");

        Order orderEntity = orderService.get(NUMBER);
        assertNotNull(orderEntity);

        System.out.println("OK!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByNullNumberTest() throws Exception {
        System.out.println("-> getByNullNumber() - OK!");

        String number = null;
        Order orderEntity = orderService.get(number);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByEmptyNumberTest() throws Exception {
        System.out.println("-> getByEmptyNumber() - OK!");

        String number = "";
        Order orderEntity = orderService.get(number);
    }

    @Test(expected = NullPointerException.class)
    public void getByUnknownNumberTest() throws Exception {
        System.out.println("-> getByUnknownNumber() - OK!");

        Order orderEntity = orderService.get(ANY_STRING);
    }

    @Test
    public void removeByNumberTest() throws Exception {
        System.out.print("-> removeByNumber() - ");

        orderService.remove(NUMBER);

        System.out.println("OK!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeByNullNumberTest() throws Exception {
        System.out.println("-> removeByNullUrl() - OK!");

        String number = null;
        orderService.remove(number);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeByEmptyNumberTest() throws Exception {
        System.out.println("-> removeByEmptyUrl() - OK!");

        String number = "";
        orderService.remove(number);
    }

    @Test
    public void getAllTest() throws Exception {
        System.out.println("-> getAll() - ");

        Collection<Order> orderEntities = orderService.getAll();
        assertNotNull(orderEntities);
        assertTrue(orderEntities.size() >= 0);

        System.out.println("OK!");
    }

    @Test
    public void noExceptionOfVoidMethodTest() throws Exception {
        System.out.print("-> noExceptionOfVoidMethod() - ");

        Order orderEntity = null;
        orderService.add(orderEntity);
        orderService.update(orderEntity);
        orderService.remove(orderEntity);

        orderEntity = getOrderEntity();
        orderService.add(orderEntity);
        orderService.update(orderEntity);
        orderService.remove(orderEntity);

        List<Order> orderEntities = null;
        orderService.add(orderEntities);
        orderService.remove(orderEntities);

        orderEntities = new ArrayList<>();
        orderService.add(orderEntities);
        orderService.remove(orderEntities);

        orderEntities.add(orderEntity);
        orderService.add(orderEntities);
        orderService.remove(orderEntities);

        System.out.println("OK!");
    }
}
