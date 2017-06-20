package ua.com.alexcoffee.tools;

import ua.com.alexcoffee.repository.*;
import ua.com.alexcoffee.service.impl.*;
import ua.com.alexcoffee.service.interfaces.*;

import static ua.com.alexcoffee.tools.MockRepository.*;

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
        CategoryRepository categoryRepository = getCategoryRepository();
        return new CategoryServiceImpl(categoryRepository);
    }

    private static OrderService initOrderService() {
        OrderRepository orderRepository = getOrderRepository();
        return new OrderServiceImpl(orderRepository);
    }

    private static PhotoService initPhotoService() {
        PhotoRepository photoRepository = getPhotoRepository();
        return new PhotoServiceImpl(photoRepository);
    }

    private static ProductService initProductService() {
        ProductRepository productRepository = getProductRepository();
        return new ProductServiceImpl(productRepository);
    }

    private static RoleService initRoleService() {
        RoleRepository roleRepository = getRoleRepository();
        return new RoleServiceImpl(roleRepository);
    }

    private static SalePositionService initSalePositionService() {
        SalePositionRepository salePositionRepository = getSalePositionRepository();
        return new SalePositionServiceImpl(salePositionRepository);
    }

    private static SenderService initSenderService() {
        UserService userService = getUserService();
        return new SenderServiceImpl(userService);
    }

    private static ShoppingCartService initShoppingCartService() {
        ShoppingCartRepository shoppingCartRepository = getShoppingCartRepository();
        return new ShoppingCartServiceImpl(shoppingCartRepository);
    }

    private static StatusService initStatusService() {
        StatusRepository statusRepository = getStatusRepository();
        return new StatusServiceImpl(statusRepository);
    }

    private static UserService initUserService() {
        UserRepository userRepository = getUserRepository();
        RoleRepository roleRepository = getRoleRepository();
        return new UserServiceImpl(userRepository, roleRepository);
    }
}
