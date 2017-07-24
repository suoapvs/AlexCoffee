package ua.com.alexcoffee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.alexcoffee.model.order.Order;
import ua.com.alexcoffee.model.order.OrderStatus;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.product.Product;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.model.user.UserRole;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static ua.com.alexcoffee.tools.MockModel.getTenSalePositions;

public class OrderTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"Order\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"Order\" - FINISH.\n");
    }

    @Test
    public void ConstructorTest() {
        System.out.print("-> Order() - ");

        Order order = Order.getBuilder().build();
        assertNotNull(order.getNumber());
        assertNotNull(order.getDate());

        final User client = User.getBuilder().build();
        order = Order.getBuilder().build();
        order.setStatus(OrderStatus.NEW);
        order.setClient(client);
        assertNotNull(order.getStatus());
        assertNotNull(order.getClient());
        assertNotNull(order.getNumber());
        assertNotNull(order.getDate());

        System.out.println("OK!");
    }

    @Test
    public void toStringTest() {
        System.out.print("-> toString() - ");

        StringBuilder sb = new StringBuilder();

        Order order = Order.getBuilder().build();
        order.setStatus(OrderStatus.NEW);
        sb.append(order.getNumber()).append(", ").append(OrderStatus.NEW.name()).append(",\n").append(order.getDate());
        assertEquals(order.toString(), sb.toString());


        final User client = User.getBuilder().build();
        client.setName("Client");
        client.setEmail("email");
        client.setPhone("phone");
        order.setClient(client);
        sb.append("\n\nClient: ").append(client.getName())
                .append("\ne-mail: ").append(client.getEmail())
                .append("\nphone: ").append(client.getPhone()).append("\n");
        assertEquals(order.toString(), sb.toString());

        final User manager = User.getBuilder().build();
        manager.setName("Manager");
        manager.setEmail("email");
        manager.setPhone("phone");
        manager.setRole(UserRole.MANAGER);
        order.setManager(manager);
        sb.append("\n").append(manager.getRole().name())
                .append(" ").append(manager.getName()).append("\n");
        assertEquals(order.toString(), sb.toString());


        String address = "Some address";
        order.setShippingAddress(address);
        sb.append("\nShipping address: ").append(address);
        assertEquals(order.toString(), sb.toString());


        String details = "Details";
        order.setShippingDetails(details);
        sb.append("\nShipping details: ").append(details);
        assertEquals(order.toString(), sb.toString());

        String description = "description";
        order.setDescription(description);
        sb.append("\nDescription: ").append(description);
        assertEquals(order.toString(), sb.toString());

        final Product product = new Product();
        product.setTitle("title");
        product.setUrl("url");
        product.setPrice(10.0);
        product.setId(1L);
        final SalePosition position = new SalePosition();
        position.setProduct(product);
        position.setNumber(10);
        order.addSalePosition(position);
        sb.append("\nSale Positions: ").append("\n1) ").append(position.getProduct().getTitle())
                .append(", № ").append(position.getProduct().getId()).append(",\n")
                .append(position.getNumber()).append(" x ")
                .append(position.getProduct().getPrice()).append(" = ")
                .append(position.getPrice()).append(" UAH;")
                .append("\n\nPRICE = ").append(position.getPrice()).append(" UAH");
        assertEquals(order.toString(), sb.toString());

        System.out.println("OK!");
    }

    @Test
    public void equalsReflexiveTest() {
        System.out.print("-> Reflexive equals - ");

        Order order = Order.getBuilder().build();
        assertTrue(order.equals(order));

        System.out.println("OK!");
    }

    @Test
    public void equalsSymmetricTest() {
        System.out.print("-> Symmetric equals - ");

        Order order1 = Order.getBuilder().build();
        Order order2 = Order.getBuilder().build();
        order2.setNumber(order1.getNumber());

        assertTrue(order1.equals(order2));
        assertTrue(order2.equals(order1));

        System.out.println("OK!");
    }

    @Test
    public void equalsTransitiveTest() {
        System.out.print("-> Transitive equals - ");

        Order order1 = Order.getBuilder().build();
        Order order2 = Order.getBuilder().build();
        order2.setNumber(order1.getNumber());
        Order order3 = Order.getBuilder().build();
        order3.setNumber(order2.getNumber());

        assertTrue(order1.equals(order2));
        assertTrue(order2.equals(order3));
        assertTrue(order1.equals(order3));

        System.out.println("OK!");
    }

    @Test
    public void equalsConsistentTest() {
        System.out.print("-> Consistent equals - ");

        Order order1 = Order.getBuilder().build();
        Order order2 = Order.getBuilder().build();
        order2.setNumber(order1.getNumber());

        for (int i = 0; i < 10; i++) {
            assertTrue(order1.equals(order2));
        }

        System.out.println("OK!");
    }

    @Test
    public void initializerTest() {
        System.out.print("-> initializer() - ");

        final String number = "NUMBER";
        final Date date = new Date();
        final String shippingAddress = "address";
        final String shippingDetails = "details";
        final String description = "description";

        final User client = User.getBuilder().build();
        client.setName("Client");
        client.setEmail("email");
        client.setPhone("phone");
        final User manager = User.getBuilder().build();
        manager.setName("Manager");
        manager.setEmail("email");
        manager.setPhone("phone");

        final  Order order = Order.getBuilder().build();
        order.setNumber(number);
        order.setDate(date);
        order.setShippingAddress(shippingAddress);
        order.setShippingDetails(shippingDetails);
        order.setDescription(description);
        order.setStatus(OrderStatus.NEW);
        order.setClient(client);
        order.setManager(manager);

        assertEquals(order.getNumber(), number);
        assertEquals(order.getShippingAddress(), shippingAddress);
        assertEquals(order.getShippingDetails(), shippingDetails);
        assertEquals(order.getDescription(), description);
        assertEquals(order.getStatus(), OrderStatus.NEW);
        assertEquals(order.getClient(), client);
        assertEquals(order.getManager(), manager);

        System.out.println("OK!");
    }

    @Test
    public void addSalePositionTest() {
        System.out.print("-> addSalePosition() - ");

        Order order = Order.getBuilder().build();
        order.addSalePosition(new SalePosition());

        assertEquals(order.getSalePositions().size(), 1);

        System.out.println("OK!");
    }

    @Test
    public void addSalePositionsTest() {
        System.out.print("-> addSalePositions() - ");

        List<SalePosition> salePositions = getTenSalePositions();

        Order order = Order.getBuilder().build();
        order.addSalePositions(salePositions);

        assertEquals(order.getSalePositions().size(), 10);

        System.out.println("OK!");
    }

    @Test
    public void removeSalePositionTest() {
        System.out.print("-> removeSalePositions() - ");

        Order order = Order.getBuilder().build();

        SalePosition salePosition = new SalePosition();

        order.addSalePosition(salePosition);
        order.addSalePositions(getTenSalePositions());
        order.removeSalePosition(salePosition);

        assertEquals(order.getSalePositions().size(), 10);

        System.out.println("OK!");
    }

    @Test
    public void removeSalePositionsTest() {
        System.out.print("-> removeSalePositions() - ");

        Order order = Order.getBuilder().build();
        List<SalePosition> positions = getTenSalePositions();

        order.setSalePositions(positions);
        assertEquals(order.getSalePositions().size(), 10);

        order.removeSalePositions(positions);
        assertEquals(order.getSalePositions().size(), 0);

        System.out.println("OK!");
    }

    @Test
    public void clearSalePositionsTest() {
        System.out.print("-> clearSalePositions() - ");

        Order order = Order.getBuilder().build();
        order.setSalePositions(getTenSalePositions());
        order.clearSalePositions();

        assertEquals(order.getSalePositions().size(), 0);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetSalePositionsTest() {
        System.out.print("-> setAndGetSalePositions() - ");

        Order order = Order.getBuilder().build();
        order.setSalePositions(getTenSalePositions());

        assertNotNull(order.getSalePositions());
        assertEquals(order.getSalePositions().size(), 10);

        System.out.println("OK!");
    }

    @Test
    public void salePositionOrders() {
        System.out.print("-> Order of sale position - ");

        Order order = Order.getBuilder().build();
        SalePosition salePosition = new SalePosition();

        order.addSalePosition(salePosition);

        assertEquals(order, salePosition.getOrder());

        System.out.println("OK!");
    }

    @Test
    public void setAndGetNumberTest() {
        System.out.print("-> setAndGetNumber() - ");

        Order order = Order.getBuilder().build();
        order.setNumber(null);
        assertNotNull(order.getNumber());
        assertTrue(order.getNumber().isEmpty());

        String number = "NUMBER";
        order.setNumber(number);
        assertNotNull(order.getNumber());

        System.out.println("OK!");
    }

    @Test
    public void setAndGetDateTest() {
        System.out.print("-> setAndGetDate() - ");

        Order order = Order.getBuilder().build();
        order.setDate(null);
        assertNotNull(order.getDate());

        Date date = new Date();
        order.setDate(date);
        assertNotNull(order.getDate());

        System.out.println("OK!");
    }

    @Test
    public void setAndGetStatusTest() {
        System.out.print("-> setAndGetStatus() - ");

        Order order = Order.getBuilder().build();
        order.setStatus(OrderStatus.CLOSED);
        assertNotNull(order.getStatus());
        assertEquals(order.getStatus(), OrderStatus.CLOSED);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetClientTest() {
        System.out.print("-> setAndGetClient() - ");

        final Order order = Order.getBuilder().build();
        final User client = User.getBuilder().build();
        client.setName("Client");
        order.setClient(client);

        assertNotNull(order.getClient());
        assertEquals(order.getClient(), client);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetManagerTest() {
        System.out.print("-> getManager() - ");

        User manager = User.getBuilder().build();
        Order order = Order.getBuilder().build();
        order.setManager(manager);

        assertNotNull(order.getManager());
        assertEquals(order.getManager(), manager);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetShippingAddressTest() {
        System.out.print("-> setAndGetShippingAddress() - ");

        Order order = Order.getBuilder().build();
        order.setShippingAddress(null);
        assertNotNull(order.getShippingAddress());
        assertTrue(order.getShippingAddress().isEmpty());

        String address = "Some address.";
        order.setShippingAddress(address);
        assertEquals(order.getShippingAddress(), address);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetShippingDetailsTest() {
        System.out.print("-> setAndGetShippingDetails() - ");

        Order order = Order.getBuilder().build();
        order.setShippingDetails(null);
        assertNotNull(order.getShippingDetails());
        assertTrue(order.getShippingDetails().isEmpty());

        String details = "Some details.";
        order.setShippingDetails(details);
        assertEquals(order.getShippingDetails(), details);

        System.out.println("OK!");
    }

    @Test
    public void setAndGetDescriptionTest() {
        System.out.print("-> setAndGetProducts() - ");

        Order order = Order.getBuilder().build();
        order.setDescription(null);
        assertNotNull(order.getDescription());
        assertTrue(order.getDescription().isEmpty());

        String description = "Some description.";
        order.setDescription(description);
        assertEquals(order.getDescription(), description);

        System.out.println("OK!");
    }

    @Test
    public void getPriceTest() {
        System.out.print("-> getPrice() - ");

        Order order = Order.getBuilder().build();
        Product product;
        SalePosition salePosition;
        double price = 0;

        for (int i = 0; i < 10; i++) {
            product = new Product();
            product.setPrice(Math.random());

            salePosition = new SalePosition();
            salePosition.setProduct(product);

            order.addSalePosition(salePosition);

            price += product.getPrice();
        }

        assertTrue(order.getPrice() == price);

        System.out.println("OK!");
    }
}
