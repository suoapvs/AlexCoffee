package ua.com.alexcoffee.tools;

import ua.com.alexcoffee.model.basket.ShoppingCart;
import ua.com.alexcoffee.model.category.Category;
import ua.com.alexcoffee.model.order.Order;
import ua.com.alexcoffee.model.order.OrderStatus;
import ua.com.alexcoffee.model.photo.Photo;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.product.Product;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.model.user.UserRole;

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
    public static final OrderStatus STATUS_ENUM = OrderStatus.NEW;
    public static final UserRole ROLE_ENUM = UserRole.ADMIN;

    private static Category category;
    private static Order order;
    private static Photo photo;
    private static Product product;
    private static SalePosition salePosition;
    private static ShoppingCart shoppingCart;
    private static User user;

    public static Category getCategory() {
        if (category == null) {
            category = initCategory();
        }
        return category;
    }

    public static Order getOrderEntity() {
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

    public static User getUser() {
        if (user == null) {
            user = initUser();
        }
        return user;
    }

    private static Category initCategory() {
        final Category category = Category.getBuilder().build();
        category.setId(ID);
        category.setTitle(TITLE);
        category.setUrl(URL);
        category.setDescription(ANY_STRING);
        final Photo photo = initPhoto();
        category.setPhoto(photo);
        return category;
    }

    private static Order initOrder() {
        final Order order = Order.getBuilder().build();
        order.setId(ID);
        order.setStatus(STATUS_ENUM);
        final User user = initUser();
        final List<SalePosition> positions = getTenSalePositions();
        order.setClient(user);
        order.setSalePositions(positions);
        return order;
    }

    private static Photo initPhoto() {
        final Photo photo = Photo.getBuilder().build();
        photo.setId(ID);
        photo.setTitle(TITLE);
        photo.setSmallUrl(ANY_STRING);
        photo.setLongUrl(ANY_STRING);
        return photo;
    }

    private static Product initProduct() {
        final Product product = new Product();
        product.setId(ID);
        product.setTitle(TITLE);
        product.setUrl(URL);
        product.setPrice(PRICE);
        final Category category = initCategory();
        product.setCategory(category);
        final Photo photo = initPhoto();
        product.setPhoto(photo);
        return product;
    }

    private static SalePosition initSalePosition() {
        final SalePosition position = new SalePosition();
        position.setId(ID);
        position.setNumber(SIZE);
        final Product product = initProduct();
        position.setProduct(product);
        return position;
    }

    private static ShoppingCart initShoppingCart() {
        final ShoppingCart shoppingCart = new ShoppingCart();
        final List<SalePosition> positions = getTenSalePositions();
        shoppingCart.setSalePositions(positions);
        return shoppingCart;
    }

    private static User initUser() {
        User user = User.getBuilder().build();
        user.setId(ID);
        user.setName(TITLE);
        user.setUsername(TITLE);
        user.setPassword(ANY_STRING);
        user.setEmail(ANY_STRING);
        user.setPhone(ANY_STRING);
        user.setVkontakte(ANY_STRING);
        user.setFacebook(ANY_STRING);
        user.setSkype(ANY_STRING);
        user.setRole(ROLE_ENUM);
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
        List<Order> orderEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Order order = initOrder();
            order.setId((long) i);
            orderEntities.add(order);
        }
        return orderEntities;
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

    public static List<SalePosition> getTenSalePositions() {
        List<SalePosition> salePositions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SalePosition salePosition = initSalePosition();
            salePosition.setId((long) i);
            salePositions.add(salePosition);
        }
        return salePositions;
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
