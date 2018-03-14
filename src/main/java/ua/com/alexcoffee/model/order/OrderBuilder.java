package ua.com.alexcoffee.model.order;

import ua.com.alexcoffee.model.model.ModelBuilder;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.model.user.UserBuilder;
import ua.com.alexcoffee.model.user.UserRole;
import ua.com.alexcoffee.util.generator.Generator;
import ua.com.alexcoffee.util.generator.StringGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class OrderBuilder extends ModelBuilder<Order, OrderBuilder> {

    private String number;

    private Date date;

    private String shippingAddress;

    private String shippingDetails;

    private String description;

    private OrderStatus status;

    private User client;

    private User manager;

    private final List<SalePosition> salePositions = new ArrayList<>();

    OrderBuilder() {
    }

    @Override
    public Order build() {
        final Order order = new Order();
        order.setNumber(getNumber());
        order.setDate(getDate());
        order.setShippingAddress(getShippingAddress());
        order.setShippingDetails(getShippingDetails());
        order.setDescription(getDescription());
        order.setStatus(getStatus());
        order.setClient(getClient());
        order.setManager(getManager());
        order.setSalePositions(getSalePositions());
        updateSalePositions(order);
        return super.build(order);
    }

    public OrderBuilder addNumber(final String number) {
        this.number = number;
        return this;
    }

    public OrderBuilder addDate(final Date date) {
        this.date = date;
        return this;
    }

    public OrderBuilder addShippingAddress(final String shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public OrderBuilder addShippingDetails(final String shippingDetails) {
        this.shippingDetails = shippingDetails;
        return this;
    }

    public OrderBuilder addDescription(final String description) {
        this.description = description;
        return this;
    }

    public OrderBuilder addStatus(final OrderStatus status) {
        this.status = status;
        return this;
    }

    public OrderBuilder addClient(final User client) {
        this.client = client;
        return this;
    }

    public OrderBuilder addManager(final User manager) {
        this.manager = manager;
        return this;
    }

    public OrderBuilder addSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            this.salePositions.add(position);
        }
        return this;
    }

    public OrderBuilder addSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            positions.forEach(this::addSalePosition);
        }
        return this;
    }

    public OrderBuilder removeSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            this.salePositions.remove(position);
        }
        return this;
    }

    public OrderBuilder removeSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            positions.forEach(this::removeSalePosition);
        }
        return this;
    }

    public OrderBuilder clearSalePositions() {
        this.salePositions.clear();
        return this;
    }

    public String getNumber() {
        final String number;
        if (isNotEmpty(this.number)) {
            number = this.number;
        } else {
            final Generator<String> stringGenerator = new StringGenerator();
            number = stringGenerator.generate();
        }
        return number;
    }

    private Date getDate() {
        return isNotNull(this.date) ? this.date : new Date();
    }

    private String getShippingAddress() {
        return isNotEmpty(this.shippingAddress) ? this.shippingAddress : "";
    }

    private String getShippingDetails() {
        return isNotEmpty(this.shippingDetails) ? this.shippingDetails : "";
    }

    private String getDescription() {
        return isNotEmpty(this.description) ? this.description : "";
    }

    private OrderStatus getStatus() {
        return isNotNull(this.status) ? this.status : OrderStatus.NEW;
    }

    private User getClient() {
        final User client;
        if (isNotNull(this.client)) {
            client = this.client;
        } else {
            final UserBuilder userBuilder = User.getBuilder();
            userBuilder.addRole(UserRole.CLIENT);
            client = userBuilder.build();
        }
        return client;
    }

    private User getManager() {
        return this.manager;
    }

    private Collection<SalePosition> getSalePositions() {
        return this.salePositions;
    }

    private void updateSalePositions(final Order order) {
        for (SalePosition position : this.salePositions) {
            position.setOrder(order);
        }
    }
}
