package ua.com.alexcoffee.tools;

import ua.com.alexcoffee.controller.admin.AdminCategoriesController;
import ua.com.alexcoffee.controller.admin.AdminOrdersController;
import ua.com.alexcoffee.controller.admin.AdminProductsController;
import ua.com.alexcoffee.controller.admin.AdminUsersController;
import ua.com.alexcoffee.controller.advice.AdviceController;
import ua.com.alexcoffee.controller.client.HomeController;
import ua.com.alexcoffee.controller.manager.ManagerOrdersController;
import ua.com.alexcoffee.controller.manager.ManagerUsersController;
import ua.com.alexcoffee.controller.seo.SEOController;
import ua.com.alexcoffee.service.interfaces.*;

import static ua.com.alexcoffee.tools.MockService.*;

public final class MockController {

    private static AdminCategoriesController adminCategoriesController;
    private static AdminOrdersController adminOrdersController;
    private static AdminProductsController adminProductsController;
    private static AdminUsersController adminUsersController;
    private static HomeController homeController;
    private static ManagerOrdersController managerOrdersController;
    private static ManagerUsersController managerUsersController;
    private static AdviceController adviceController;
    private static SEOController seoController;

    public static AdminCategoriesController getAdminCategoriesController() {
        if (adminCategoriesController == null) {
            adminCategoriesController = initAdminCategoriesController();
        }
        return adminCategoriesController;
    }

    public static AdminOrdersController getAdminOrdersController() {
        if (adminOrdersController == null) {
            adminOrdersController = initAdminOrdersController();
        }
        return adminOrdersController;
    }

    public static AdminProductsController getAdminProductsController() {
        if (adminProductsController == null) {
            adminProductsController = initAdminProductsController();
        }
        return adminProductsController;
    }

    public static AdminUsersController getAdminUsersController() {
        if (adminUsersController == null) {
            adminUsersController = initAdminUsersController();
        }
        return adminUsersController;
    }

    public static HomeController getHomeController() {
        if (homeController == null) {
            homeController = initHomeController();
        }
        return homeController;
    }

    public static ManagerOrdersController getManagerOrdersController() {
        if (managerOrdersController == null) {
            managerOrdersController = initManagerOrdersController();
        }
        return managerOrdersController;
    }

    public static ManagerUsersController getManagerUsersController() {
        if (managerUsersController == null) {
            managerUsersController = initManagerUsersController();
        }
        return managerUsersController;
    }

    public static AdviceController getAdviceController() {
        if (adviceController == null) {
            adviceController = initAdviceController();
        }
        return adviceController;
    }

    public static SEOController getSeoController() {
        if (seoController == null) {
            seoController = initSeoController();
        }
        return seoController;
    }

    private static AdminCategoriesController initAdminCategoriesController() {
        CategoryService categoryService = getCategoryService();
        PhotoService photoService = getPhotoService();
        UserService userService = getUserService();
        return new AdminCategoriesController(categoryService, photoService, userService);
    }

    private static AdminOrdersController initAdminOrdersController() {
        OrderService orderService = getOrderService();
        StatusService statusService = getStatusService();
        UserService userService = getUserService();
        RoleService roleService = getRoleService();
        return new AdminOrdersController(orderService, statusService, userService, roleService);
    }

    private static AdminProductsController initAdminProductsController() {
        ProductService productService = getProductService();
        CategoryService categoryService = getCategoryService();
        PhotoService photoService = getPhotoService();
        UserService userService = getUserService();
        return new AdminProductsController(productService, categoryService, photoService, userService);
    }

    private static AdminUsersController initAdminUsersController() {
        UserService userService = getUserService();
        RoleService roleService = getRoleService();
        return new AdminUsersController(userService, roleService);
    }

    private static HomeController initHomeController() {
        ProductService productService = getProductService();
        CategoryService categoryService = getCategoryService();
        ShoppingCartService shoppingCartService = getShoppingCartService();
        OrderService orderService = getOrderService();
        StatusService statusService = getStatusService();
        RoleService roleService = getRoleService();
        SenderService senderService = getSenderService();
        return new HomeController(productService, categoryService, shoppingCartService,
                orderService, statusService, roleService, senderService);
    }

    private static ManagerOrdersController initManagerOrdersController() {
        UserService userService = getUserService();
        OrderService orderService = getOrderService();
        StatusService statusService = getStatusService();
        RoleService roleService = getRoleService();
        return new ManagerOrdersController(userService, orderService, statusService, roleService);
    }

    private static ManagerUsersController initManagerUsersController() {
        UserService userService = getUserService();
        RoleService roleService = getRoleService();
        return new ManagerUsersController(userService, roleService);
    }

    private static AdviceController initAdviceController() {
        ShoppingCartService shoppingCartService = getShoppingCartService();
        UserService userService = getUserService();
        return new AdviceController(shoppingCartService, userService);
    }

    private static SEOController initSeoController() {
        ProductService productService = getProductService();
        CategoryService categoryService = getCategoryService();
        return new SEOController(productService, categoryService);
    }
}
