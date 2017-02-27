package ua.com.alexcoffee.config;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.alexcoffee.controller.admin.AdminCategoriesController;
import ua.com.alexcoffee.controller.admin.AdminOrdersController;
import ua.com.alexcoffee.controller.admin.AdminProductsController;
import ua.com.alexcoffee.controller.admin.AdminUsersController;
import ua.com.alexcoffee.controller.advice.AdviceController;
import ua.com.alexcoffee.controller.client.HomeController;
import ua.com.alexcoffee.controller.manager.ManagerOrdersController;
import ua.com.alexcoffee.controller.manager.ManagerUsersController;
import ua.com.alexcoffee.controller.seo.SEOController;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class WebConfigTest {
    @Autowired
    private AdminCategoriesController adminCategoriesController;

    @Autowired
    private AdminOrdersController adminOrdersController;

    @Autowired
    private AdminProductsController adminProductsController;

    @Autowired
    private AdminUsersController adminUsersController;

    @Autowired
    private HomeController homeController;

    @Autowired
    private ManagerOrdersController managerOrdersController;

    @Autowired
    private ManagerUsersController managerUsersController;

    @Autowired
    private AdviceController adviceController;

    @Autowired
    private SEOController seoController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"WebConfig\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"WebConfig\" - FINISH.\n");
    }

    @Test
    public void adminCategoriesControllerNotNull() {
        System.out.print("-> adminCategoriesController Not Null - ");
        assertNotNull(adminCategoriesController);
        System.out.println("OK!");
    }

    @Test
    public void adminOrdersControllerNotNull() {
        System.out.print("-> adminOrdersController Not Null - ");
        assertNotNull(adminOrdersController);
        System.out.println("OK!");
    }

    @Test
    public void seoControllerNotNull() {
        System.out.print("-> seoController Not Null( - ");
        assertNotNull(seoController);
        System.out.println("OK!");
    }

    @Test
    public void adminProductsControllerNotNull() {
        System.out.print("-> adminProductsController Not Null - ");
        assertNotNull(adminProductsController);
        System.out.println("OK!");
    }

    @Test
    public void adminUsersControllerNotNull() {
        System.out.print("-> adminUsersController Not Null - ");
        assertNotNull(adminUsersController);
        System.out.println("OK!");
    }

    @Test
    public void homeControllerNotNull() {
        System.out.print("-> homeController Not Null - ");
        assertNotNull(homeController);
        System.out.println("OK!");
    }

    @Test
    public void managerOrdersControllerNotNull() {
        System.out.print("-> managerOrdersController Not Null - ");
        assertNotNull(managerOrdersController);
        System.out.println("OK!");
    }

    @Test
    public void managerUsersControllerNotNull() {
        System.out.print("-> managerUsersController Not Null - ");
        assertNotNull(managerUsersController);
        System.out.println("OK!");
    }

    @Test
    public void adviceControllerNotNull() {
        System.out.print("-> adviceController Not Null - ");
        assertNotNull(adviceController);
        System.out.println("OK!");
    }

    @Test
    public void viewResolverTest() {
        System.out.print("-> viewResolver() - ");
        WebConfig webConfig = new WebConfig();
        assertNotNull(webConfig.viewResolver());
        System.out.println("OK!");
    }
}
