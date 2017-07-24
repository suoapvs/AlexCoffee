package ua.com.alexcoffee.model.user;

import ua.com.alexcoffee.model.model.ModelBuilder;
import ua.com.alexcoffee.model.order.Order;

import java.util.ArrayList;
import java.util.Collection;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class UserBuilder extends ModelBuilder<User, UserBuilder> {

    private String name;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String vkontakte;

    private String facebook;

    private String skype;

    private String description;

    private UserRole role;

    private Collection<Order> clientOrders = new ArrayList<>();

    private Collection<Order> managerOrders = new ArrayList<>();

    UserBuilder() {
    }

    @Override
    public User build() {
        final User user = new User();
        user.setName(getName());
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setEmail(getEmail());
        user.setPhone(getPhone());
        user.setVkontakte(getVkontakte());
        user.setFacebook(getFacebook());
        user.setSkype(getSkype());
        user.setDescription(getDescription());
        user.setRole(getRole());
        user.setClientOrders(getClientOrders());
        user.setManagerOrders(getManagerOrders());
        return super.build(user);
    }

    public UserBuilder addName(final String name) {
        this.name = name;
        return this;
    }

    public UserBuilder addUsername(final String username) {
        this.username = username;
        return this;
    }

    public UserBuilder addPassword(final String password) {
        this.password = password;
        return this;
    }

    public UserBuilder addEmail(final String email) {
        this.email = email;
        return this;
    }

    public UserBuilder addPhone(final String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder addVkontakte(final String vkontakte) {
        this.vkontakte = vkontakte;
        return this;
    }

    public UserBuilder addFacebook(final String facebook) {
        this.facebook = facebook;
        return this;
    }

    public UserBuilder addSkype(final String skype) {
        this.skype = skype;
        return this;
    }

    public UserBuilder addDescription(final String description) {
        this.description = description;
        return this;
    }

    public UserBuilder addRole(final UserRole role) {
        this.role = role;
        return this;
    }

    public UserBuilder addClientOrder(final Order order) {
        if (isNotNull(order)) {
            this.clientOrders.add(order);
        }
        return this;
    }

    public UserBuilder addClientOrders(final Collection<Order> orders) {
        if (isNotEmpty(orders)) {
            orders.forEach(this::addClientOrder);
        }
        return this;
    }

    public UserBuilder removeClientOrder(final Order order) {
        if (isNotNull(order)) {
            this.clientOrders.remove(order);
        }
        return this;
    }

    public UserBuilder removeClientOrders(final Collection<Order> orders) {
        if (isNotEmpty(orders)) {
            orders.forEach(this::removeClientOrder);
        }
        return this;
    }

    public UserBuilder clearClientOrders() {
        this.clientOrders.clear();
        return this;
    }

    public UserBuilder addManagerOrder(final Order order) {
        if (isNotNull(order)) {
            this.managerOrders.add(order);
        }
        return this;
    }

    public UserBuilder addManagerOrders(final Collection<Order> orders) {
        if (isNotEmpty(orders)) {
            orders.forEach(this::addManagerOrder);
        }
        return this;
    }

    public UserBuilder removeManagerOrder(final Order order) {
        if (isNotNull(order)) {
            this.managerOrders.remove(order);
        }
        return this;
    }

    public UserBuilder removeManagerOrders(final Collection<Order> orders) {
        if (isNotEmpty(orders)) {
            orders.forEach(this::removeManagerOrder);
        }
        return this;
    }

    public UserBuilder clearManagerOrders() {
        this.managerOrders.clear();
        return this;
    }

    private String getName() {
        return isNotEmpty(this.name) ? this.name : "";
    }

    private String getUsername() {
        return isNotEmpty(this.username) ? this.username : "";
    }

    private String getPassword() {
        return isNotEmpty(this.password) ? this.password : "";
    }

    private String getEmail() {
        return isNotEmpty(this.email) ? this.email : "";
    }

    private String getPhone() {
        return isNotEmpty(this.phone) ? this.phone : "";
    }

    public String getVkontakte() {
        return isNotEmpty(this.vkontakte) ? this.vkontakte : "";
    }

    private String getFacebook() {
        return isNotEmpty(this.facebook) ? this.facebook : "";
    }

    private String getSkype() {
        return isNotEmpty(this.skype) ? this.skype : "";
    }

    private String getDescription() {
        return isNotEmpty(this.description) ? this.description : "";
    }

    private UserRole getRole() {
        return isNotNull(this.role) ? this.role : UserRole.CLIENT;
    }

    private Collection<Order> getClientOrders() {
        return this.clientOrders;
    }

    private Collection<Order> getManagerOrders() {
        return this.managerOrders;
    }
}
