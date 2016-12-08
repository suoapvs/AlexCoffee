package ua.com.alexcoffee.tools;

import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.enums.StatusEnum;
import ua.com.alexcoffee.model.*;

import java.util.ArrayList;
import java.util.List;

public final class MockModel {

    public static final Long ID = 1L;
    public static final int ARTICLE = 1;
    public static final int UNKNOWN_ARTICLE = -1;
    public static final int SIZE = 1;
    public static final double PRICE = 1.0;
    public static final String URL = "url";
    public static final String NUMBER = "number";
    public static final String TITLE = "title";
    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String ANY_STRING = "any";
    public static final Long UNKNOWN_ID = -345230L;
    public static final StatusEnum STATUS_ENUM = StatusEnum.NEW;
    public static final RoleEnum ROLE_ENUM = RoleEnum.ADMIN;

    private static Category category;
    private static Order order;
    private static Photo photo;
    private static Product product;
    private static Role role;
    private static SalePosition salePosition;
    private static ShoppingCart shoppingCart;
    private static Status status;
    private static User user;

    public static Category getCategory() {
        if (category == null) {
            category = initCategory();
        }
        return category;
    }

    public static Order getOrder() {
        if (order == null) {
            order = initOrder();
        }
        return order;
    }

    public static Photo getPhoto() {
        if (photo == null) {
            photo = initPhoto();
        }
        return photo;
    }

    public static Product getProduct() {
        if (product == null) {
            product = initProduct();
        }
        return product;
    }

    public static Role getRole() {
        if (role == null) {
            role = initRole();
        }
        return role;
    }

    public static SalePosition getSalePosition() {
        if (salePosition == null) {
            salePosition = initSalePosition();
        }
        return salePosition;
    }

    public static ShoppingCart getShoppingCart() {
        if (shoppingCart == null) {
            shoppingCart = initShoppingCart();
        }
        return shoppingCart;
    }

    public static Status getStatus() {
        if (status == null) {
            status = initStatus();
        }
        return status;
    }

    public static User getUser() {
        if (user == null) {
            user = initUser();
        }
        return user;
    }

    private static Category initCategory() {
        Photo photo = initPhoto();
        Category category = new Category(TITLE, URL, ANY_STRING, photo);
        category.setId(ID);
        return category;
    }

    private static Order initOrder() {
        User user = initUser();
        Status status = initStatus();
        List<SalePosition> salePositions = getTenSalePositions();
        Order order = new Order(status, user, salePositions);
        order.setId(ID);
        return order;
    }

    private static Photo initPhoto() {
        Photo photo = new Photo(TITLE, ANY_STRING, ANY_STRING);
        photo.setId(ID);
        return photo;
    }

    private static Product initProduct() {
        Category category = initCategory();
        Photo photo = initPhoto();
        Product product = new Product(TITLE, URL, category, photo, PRICE);
        product.setId(ID);
        return product;
    }

    private static Role initRole() {
        Role role = new Role(ROLE_ENUM, ROLE_ENUM.name());
        role.setId(ID);
        return role;
    }

    private static SalePosition initSalePosition() {
        Product product = initProduct();
        SalePosition salePosition = new SalePosition(product, SIZE);
        salePosition.setId(ID);
        return salePosition;
    }

    private static ShoppingCart initShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setSalePositions(getTenSalePositions());
        return shoppingCart;
    }

    private static Status initStatus() {
        Status status = new Status(STATUS_ENUM, STATUS_ENUM.name());
        status.setId(ID);
        return status;
    }

    private static User initUser() {
        Role role = initRole();
        User user = new User(TITLE, ANY_STRING, ANY_STRING, role);
        user.setId(ID);
        return user;
    }

    public static List<Category> getTenCategories() {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Category category = initCategory();
            category.setId((long) i);
            categories.add(category);
        }
        return categories;
    }

    public static List<Order> getTenOrders() {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Order order = initOrder();
            order.setId((long) i);
            orders.add(order);
        }
        return orders;
    }

    public static List<Photo> getTenPhoto() {
        List<Photo> photos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Photo photo = initPhoto();
            photo.setId((long) i);
            photos.add(photo);
        }
        return photos;
    }

    public static List<Product> getTenProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = initProduct();
            product.setId((long) i);
            products.add(product);
        }
        return products;
    }

    public static List<Role> getTenRoles() {
        List<Role> roles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Role role = initRole();
            role.setId((long) i);
            roles.add(role);
        }
        return roles;
    }

    public static List<SalePosition> getTenSalePositions() {
        List<SalePosition> salePositions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SalePosition salePosition = initSalePosition();
            salePosition.setId((long) i);
            salePositions.add(salePosition);
        }
        return salePositions;
    }

    public static List<Status> getTenStatuses() {
        List<Status> statuses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Status status = initStatus();
            status.setId((long) i);
            statuses.add(status);
        }
        return statuses;
    }

    public static List<User> getTenUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = initUser();
            user.setUsername(USERNAME + i);
            user.setId((long) i);
            users.add(user);
        }
        return users;
    }
}
