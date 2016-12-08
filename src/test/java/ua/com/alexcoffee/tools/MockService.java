package ua.com.alexcoffee.tools;

import ua.com.alexcoffee.dao.*;
import ua.com.alexcoffee.service.*;
import ua.com.alexcoffee.service.impl.*;

import static ua.com.alexcoffee.tools.MockDAO.*;

public final class MockService {

    private static CategoryService categoryService;
    private static OrderService orderService;
    private static PhotoService photoService;
    private static ProductService productService;
    private static RoleService roleService;
    private static SalePositionService salePositionService;
    private static SenderService senderService;
    private static ShoppingCartService shoppingCartService;
    private static StatusService statusService;
    private static UserService userService;

    public static CategoryService getCategoryService() {
        if (categoryService == null) {
            categoryService = initCategoryService();
        }
        return categoryService;
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = initOrderService();
        }
        return orderService;
    }

    public static PhotoService getPhotoService() {
        if (photoService == null) {
            photoService = initPhotoService();
        }
        return photoService;
    }

    public static ProductService getProductService() {
        if (productService == null) {
            productService = initProductService();
        }
        return productService;
    }

    public static RoleService getRoleService() {
        if (roleService == null) {
            roleService = initRoleService();
        }
        return roleService;
    }

    public static SalePositionService getSalePositionService() {
        if (salePositionService == null) {
            salePositionService = initSalePositionService();
        }
        return salePositionService;
    }

    public static SenderService getSenderService() {
        if (senderService == null) {
            senderService = initSenderService();
        }
        return senderService;
    }

    public static ShoppingCartService getShoppingCartService() {
        if (shoppingCartService == null) {
            shoppingCartService = initShoppingCartService();
        }
        return shoppingCartService;
    }

    public static StatusService getStatusService() {
        if (statusService == null) {
            statusService = initStatusService();
        }
        return statusService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = initUserService();
        }
        return userService;
    }

    private static CategoryService initCategoryService() {
        CategoryDAO categoryDAO = getCategoryDAO();
        return new CategoryServiceImpl(categoryDAO);
    }

    private static OrderService initOrderService() {
        OrderDAO orderDAO = getOrderDAO();
        return new OrderServiceImpl(orderDAO);
    }

    private static PhotoService initPhotoService() {
        PhotoDAO photoDAO = getPhotoDAO();
        return new PhotoServiceImpl(photoDAO);
    }

    private static ProductService initProductService() {
        ProductDAO productDAO = getProductDAO();
        CategoryDAO categoryDAO = getCategoryDAO();
        return new ProductServiceImpl(productDAO, categoryDAO);
    }

    private static RoleService initRoleService() {
        RoleDAO roleDAO = getRoleDAO();
        return new RoleServiceImpl(roleDAO);
    }

    private static SalePositionService initSalePositionService() {
        SalePositionDAO salePositionDAO = getSalePositionDAO();
        return new SalePositionServiceImpl(salePositionDAO);
    }

    private static SenderService initSenderService() {
        UserService userService = getUserService();
        return new SenderServiceImpl(userService);
    }

    private static ShoppingCartService initShoppingCartService() {
        ShoppingCartDAO shoppingCartDAO = getShoppingCartDAO();
        return new ShoppingCartServiceImpl(shoppingCartDAO);
    }

    private static StatusService initStatusService() {
        StatusDAO statusDAO = getStatusDAO();
        return new StatusServiceImpl(statusDAO);
    }

    private static UserService initUserService() {
        UserDAO userDAO = getUserDAO();
        return new UserServiceImpl(userDAO);
    }
}
