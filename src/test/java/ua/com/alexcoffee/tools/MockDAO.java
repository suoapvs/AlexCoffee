package ua.com.alexcoffee.tools;

import ua.com.alexcoffee.dao.*;
import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.enums.StatusEnum;
import ua.com.alexcoffee.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.alexcoffee.tools.MockModel.*;

public final class MockDAO {

    private static CategoryDAO categoryDAO;
    private static OrderDAO orderDAO;
    private static PhotoDAO photoDAO;
    private static ProductDAO productDAO;
    private static RoleDAO roleDAO;
    private static SalePositionDAO salePositionDAO;
    private static ShoppingCartDAO shoppingCartDAO;
    private static StatusDAO statusDAO;
    private static UserDAO userDAO;


    public static CategoryDAO getCategoryDAO() {
        if (categoryDAO == null) {
            categoryDAO = initCategoryDAO();
        }
        return categoryDAO;
    }


    public static OrderDAO getOrderDAO() {
        if (orderDAO == null) {
            orderDAO = initOrderDAO();
        }
        return orderDAO;
    }


    public static PhotoDAO getPhotoDAO() {
        if (photoDAO == null) {
            photoDAO = initPhotoDAO();
        }
        return photoDAO;
    }


    public static ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = initProductDAO();
        }
        return productDAO;
    }


    public static RoleDAO getRoleDAO() {
        if (roleDAO == null) {
            roleDAO = initRoleDAO();
        }
        return roleDAO;
    }


    public static SalePositionDAO getSalePositionDAO() {
        if (salePositionDAO == null) {
            salePositionDAO = initSalePositionDAO();
        }
        return salePositionDAO;
    }


    public static ShoppingCartDAO getShoppingCartDAO() {
        if (shoppingCartDAO == null) {
            shoppingCartDAO = initShoppingCartDAO();
        }
        return shoppingCartDAO;
    }


    public static StatusDAO getStatusDAO() {
        if (statusDAO == null) {
            statusDAO = initStatusDAO();
        }
        return statusDAO;
    }


    public static UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = initUserDAO();
        }
        return userDAO;
    }


    private static CategoryDAO initCategoryDAO() {
        Category category = getCategory();
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        CategoryDAO categoryDAO = mock(CategoryDAO.class);
        when(categoryDAO.get(ID)).thenReturn(category);
        when(categoryDAO.get(UNKNOWN_ID)).thenReturn(null);
        when(categoryDAO.get(URL)).thenReturn(category);
        when(categoryDAO.get(ANY_STRING)).thenReturn(null);
        when(categoryDAO.getAll()).thenReturn(categories);
        return categoryDAO;
    }

    private static OrderDAO initOrderDAO() {
        Order order = getOrder();
        List<Order> orders = getTenOrders();

        OrderDAO orderDAO = mock(OrderDAO.class);
        when(orderDAO.get(ID)).thenReturn(order);
        when(orderDAO.get(UNKNOWN_ID)).thenReturn(null);
        when(orderDAO.get(NUMBER)).thenReturn(order);
        when(orderDAO.get(ANY_STRING)).thenReturn(null);
        when(orderDAO.getAll()).thenReturn(orders);
        return orderDAO;
    }

    private static PhotoDAO initPhotoDAO() {
        Photo photo = getPhoto();
        List<Photo> photos = getTenPhoto();

        PhotoDAO photoDAO = mock(PhotoDAO.class);
        when(photoDAO.get(ID)).thenReturn(photo);
        when(photoDAO.get(UNKNOWN_ID)).thenReturn(null);
        when(photoDAO.get(TITLE)).thenReturn(photo);
        when(photoDAO.get(ANY_STRING)).thenReturn(null);
        when(photoDAO.getAll()).thenReturn(photos);
        return photoDAO;
    }

    private static ProductDAO initProductDAO() {
        Product product = getProduct();
        List<Product> products = getTenProducts();

        ProductDAO productDAO = mock(ProductDAO.class);
        when(productDAO.get(ID)).thenReturn(product);
        when(productDAO.get(UNKNOWN_ID)).thenReturn(null);
        when(productDAO.getByUrl(URL)).thenReturn(product);
        when(productDAO.getByUrl(ANY_STRING)).thenReturn(null);
        when(productDAO.getByArticle(ARTICLE)).thenReturn(product);
        when(productDAO.getByArticle(UNKNOWN_ARTICLE)).thenReturn(null);
        when(productDAO.getListByCategoryId(ID)).thenReturn(products);
        when(productDAO.getListByCategoryId(UNKNOWN_ID)).thenReturn(new ArrayList<>());
        when(productDAO.getAll()).thenReturn(products);
        return productDAO;
    }

    private static RoleDAO initRoleDAO() {
        Role role = getRole();
        List<Role> roles = getTenRoles();

        RoleDAO roleDAO = mock(RoleDAO.class);
        when(roleDAO.get(ID)).thenReturn(role);
        when(roleDAO.get(UNKNOWN_ID)).thenReturn(null);
        when(roleDAO.get(ROLE_ENUM)).thenReturn(role);
        when(roleDAO.get(RoleEnum.ADMIN)).thenReturn(role);
        when(roleDAO.get(RoleEnum.MANAGER)).thenReturn(role);
        when(roleDAO.get(RoleEnum.CLIENT)).thenReturn(null);
        when(roleDAO.getDefault()).thenReturn(role);
        when(roleDAO.getAll()).thenReturn(roles);
        return roleDAO;
    }

    private static SalePositionDAO initSalePositionDAO() {
        SalePosition salePosition = getSalePosition();
        List<SalePosition> salePositions = getTenSalePositions();

        SalePositionDAO salePositionDAO = mock(SalePositionDAO.class);
        when(salePositionDAO.get(ID)).thenReturn(salePosition);
        when(salePositionDAO.get(UNKNOWN_ID)).thenReturn(null);
        when(salePositionDAO.getAll()).thenReturn(salePositions);
        return salePositionDAO;
    }

    private static ShoppingCartDAO initShoppingCartDAO() {
        ShoppingCart shoppingCart = getShoppingCart();
        ShoppingCartDAO shoppingCartDAO = mock(ShoppingCartDAO.class);
        when(shoppingCartDAO.get()).thenReturn(shoppingCart).thenReturn(null);
        when(shoppingCartDAO.getSize()).thenReturn(SIZE);
        when(shoppingCartDAO.getPrice()).thenReturn(PRICE);
        when(shoppingCartDAO.getSalePositions()).thenReturn(shoppingCart.getSalePositions());
        return shoppingCartDAO;
    }

    private static StatusDAO initStatusDAO() {
        Status status = getStatus();
        List<Status> statuses = getTenStatuses();

        StatusDAO statusDAO = mock(StatusDAO.class);
        when(statusDAO.get(ID)).thenReturn(status);
        when(statusDAO.get(UNKNOWN_ID)).thenReturn(null);
        when(statusDAO.get(STATUS_ENUM)).thenReturn(status);
        when(statusDAO.get(StatusEnum.CLOSED)).thenReturn(null);
        when(statusDAO.getDefault()).thenReturn(status);
        when(statusDAO.getAll()).thenReturn(statuses);
        return statusDAO;
    }

    private static UserDAO initUserDAO() {
        User user = getUser();
        List<User> users = getTenUsers();

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.get(ID)).thenReturn(user);
        when(userDAO.get(UNKNOWN_ID)).thenReturn(null);
        when(userDAO.getByName(NAME)).thenReturn(user);
        when(userDAO.getByName(ANY_STRING)).thenReturn(null);
        when(userDAO.getByUsername(USERNAME)).thenReturn(user);
        when(userDAO.getByUsername(ANY_STRING)).thenReturn(null);
        when(userDAO.getMainAdministrator()).thenReturn(user);
        when(userDAO.getAdministrators()).thenReturn(users);
        when(userDAO.getManagers()).thenReturn(users);
        when(userDAO.getClients()).thenReturn(users);
        when(userDAO.getAll()).thenReturn(users);
        when(userDAO.getAuthenticatedUser()).thenReturn(user);
        return userDAO;
    }
}
