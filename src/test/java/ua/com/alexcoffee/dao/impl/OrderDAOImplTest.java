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
import ua.com.alexcoffee.dao.OrderDAO;
import ua.com.alexcoffee.model.Order;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class OrderDAOImplTest {

    @Autowired
    private OrderDAO orderDAO;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"OrderDAOImpl\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"OrderDAOImpl\" - FINISH.\n");
    }

    @Test
    public void orderDAONotNull() throws Exception {
        System.out.print("-> orderDAO Not Null - ");

        assertNotNull(orderDAO);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetByUrlAndRemoveByNumberTest() throws Exception {
        System.out.print("-> Add, Get and Remove by URL - ");

        Order order1 = new Order();
        orderDAO.add(order1);
        Order order2 = orderDAO.get(order1.getNumber());

        assertNotNull(order2);
        assertEquals(order1, order2);

        orderDAO.remove(order2.getNumber());
        assertNull(orderDAO.get(order2.getNumber()));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndGetAndRemoveListTest() throws Exception {
        System.out.print("-> Add, Get and Remove List - ");

        List<Order> orders1 = new ArrayList<>();
        orders1.add(new Order());
        orders1.add(new Order());
        orderDAO.add(orders1);
        List<Order> orders2 = orderDAO.getAll();

        assertNotNull(orders2);
        assertTrue(orders2.size() >= orders1.size());
        assertTrue(orders2.containsAll(orders1));

        orderDAO.remove(orders1);
        orders2 = orderDAO.getAll();

        assertNotNull(orders2);
        assertTrue(orders2.size() >= 0);
        assertFalse(orders2.containsAll(orders1));

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        System.out.print("-> Get by Id - ");

        List<Order> orders = orderDAO.getAll();
        if (orders.size() > 0) {
            assertNotNull(orderDAO.get(orders.get(0).getId()));
        }

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void addAndUpdateTest() throws Exception {
        System.out.print("-> Add and Update - ");

        Order order1 = new Order();
        orderDAO.add(order1);

        order1.newNumber();
        orderDAO.update(order1);

        Order order2 = orderDAO.get(order1.getNumber());

        assertNotNull(order2);
        assertEquals(order1, order2);

        System.out.println("OK!");
    }

    @Test
    @Transactional
    public void removeAllTest() throws Exception {
        System.out.print("-> Remove All - ");

        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());

        orderDAO.add(orders);
        assertNotNull(orderDAO.getAll());
        assertTrue(orderDAO.getAll().size() > 0);

        orderDAO.removeAll();
        assertNotNull(orderDAO.getAll());
        assertTrue(orderDAO.getAll().size() == 0);

        System.out.println("OK!");
    }
}
