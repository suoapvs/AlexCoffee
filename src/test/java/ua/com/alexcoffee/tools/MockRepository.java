package ua.com.alexcoffee.tools;

import ua.com.alexcoffee.model.basket.ShoppingCart;
import ua.com.alexcoffee.model.category.Category;
import ua.com.alexcoffee.model.order.Order;
import ua.com.alexcoffee.model.photo.Photo;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.product.Product;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.repository.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.alexcoffee.tools.MockModel.*;

public final class MockRepository {

    private static CategoryRepository categoryRepository;
    private static OrderRepository orderRepository;
    private static PhotoRepository photoRepository;
    private static ProductRepository productRepository;
    private static SalePositionRepository salePositionRepository;
    private static ShoppingCartRepository shoppingCartRepository;
    private static UserRepository userRepository;


    public static CategoryRepository getCategoryRepository() {
        if (categoryRepository == null) {
            categoryRepository = initCategoryRepository();
        }
        return categoryRepository;
    }


    public static OrderRepository getOrderRepository() {
        if (orderRepository == null) {
            orderRepository = initOrderRepository();
        }
        return orderRepository;
    }


    public static PhotoRepository getPhotoRepository() {
        if (photoRepository == null) {
            photoRepository = initPhotoRepository();
        }
        return photoRepository;
    }


    public static ProductRepository getProductRepository() {
        if (productRepository == null) {
            productRepository = initProductRepository();
        }
        return productRepository;
    }


    public static SalePositionRepository getSalePositionRepository() {
        if (salePositionRepository == null) {
            salePositionRepository = initSalePositionRepository();
        }
        return salePositionRepository;
    }


    public static ShoppingCartRepository getShoppingCartRepository() {
        if (shoppingCartRepository == null) {
            shoppingCartRepository = initShoppingCartRepository();
        }
        return shoppingCartRepository;
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = initUserRepository();
        }
        return userRepository;
    }


    private static CategoryRepository initCategoryRepository() {
        Category category = getCategory();
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findOne(ID)).thenReturn(category);
        when(categoryRepository.findOne(UNKNOWN_ID)).thenReturn(null);
        when(categoryRepository.findByUrl(URL)).thenReturn(category);
        when(categoryRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(categoryRepository.findAll()).thenReturn(categories);
        return categoryRepository;
    }

    private static OrderRepository initOrderRepository() {
        Order orderEntity = getOrderEntity();
        List<Order> orderEntities = getTenOrders();
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findOne(ID)).thenReturn(orderEntity);
        when(orderRepository.findOne(UNKNOWN_ID)).thenReturn(null);
        when(orderRepository.findByNumber(NUMBER)).thenReturn(orderEntity);
        when(orderRepository.findByNumber(ANY_STRING)).thenReturn(null);
        when(orderRepository.findAll()).thenReturn(orderEntities);
        return orderRepository;
    }

    private static PhotoRepository initPhotoRepository() {
        Photo photo = getPhoto();
        List<Photo> photos = getTenPhoto();
        PhotoRepository photoRepository = mock(PhotoRepository.class);
        when(photoRepository.findOne(ID)).thenReturn(photo);
        when(photoRepository.findOne(UNKNOWN_ID)).thenReturn(null);
        when(photoRepository.findByTitle(TITLE)).thenReturn(photo);
        when(photoRepository.findByTitle(ANY_STRING)).thenReturn(null);
        when(photoRepository.findAll()).thenReturn(photos);
        return photoRepository;
    }

    private static ProductRepository initProductRepository() {
        Product product = getProduct();
        List<Product> products = getTenProducts();
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findOne(ID)).thenReturn(product);
        when(productRepository.findOne(UNKNOWN_ID)).thenReturn(null);
        when(productRepository.findByUrl(URL)).thenReturn(product);
        when(productRepository.findByUrl(ANY_STRING)).thenReturn(null);
        when(productRepository.findByArticle(ARTICLE)).thenReturn(product);
        when(productRepository.findByArticle(UNKNOWN_ARTICLE)).thenReturn(null);
        when(productRepository.findByCategoryId(ID)).thenReturn(products);
        when(productRepository.findByCategoryId(UNKNOWN_ID)).thenReturn(new ArrayList<>());
        when(productRepository.findAll()).thenReturn(products);
        return productRepository;
    }

    private static SalePositionRepository initSalePositionRepository() {
        SalePosition salePosition = getSalePosition();
        List<SalePosition> salePositions = getTenSalePositions();
        SalePositionRepository salePositionRepository = mock(SalePositionRepository.class);
        when(salePositionRepository.findOne(ID)).thenReturn(salePosition);
        when(salePositionRepository.findOne(UNKNOWN_ID)).thenReturn(null);
        when(salePositionRepository.findAll()).thenReturn(salePositions);
        return salePositionRepository;
    }

    private static ShoppingCartRepository initShoppingCartRepository() {
        ShoppingCart shoppingCart = getShoppingCart();
        ShoppingCartRepository shoppingCartRepository = mock(ShoppingCartRepository.class);
        when(shoppingCartRepository.get()).thenReturn(shoppingCart).thenReturn(null);
        when(shoppingCartRepository.getSize()).thenReturn(SIZE);
        when(shoppingCartRepository.getPrice()).thenReturn(PRICE);
        when(shoppingCartRepository.getSalePositions()).thenReturn(shoppingCart.getSalePositions());
        return shoppingCartRepository;
    }

    private static UserRepository initUserRepository() {
        User user = getUser();
        List<User> users = getTenUsers();
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findOne(ID)).thenReturn(user);
        when(userRepository.findOne(UNKNOWN_ID)).thenReturn(null);
        when(userRepository.findByName(NAME)).thenReturn(user);
        when(userRepository.findByName(ANY_STRING)).thenReturn(null);
        when(userRepository.findByUsername(USERNAME)).thenReturn(user);
        when(userRepository.findByUsername(ANY_STRING)).thenReturn(null);
        when(userRepository.findAll()).thenReturn(users);
        return userRepository;
    }
}
