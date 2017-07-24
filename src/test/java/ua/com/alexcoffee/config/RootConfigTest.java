package ua.com.alexcoffee.config;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.alexcoffee.repository.*;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public class RootConfigTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SalePositionRepository salePositionRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"RootConfig\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"RootConfig\" - FINISH.\n");
    }

    @Test
    public void categoryRepositoryNotNull() throws Exception {
        System.out.print("-> categoryRepository Not Null - ");
        assertNotNull(categoryRepository);
        System.out.println("OK!");
    }

    @Test
    public void orderRepositoryNotNull() throws Exception {
        System.out.print("-> orderRepository Not Null - ");
        assertNotNull(orderRepository);
        System.out.println("OK!");
    }

    @Test
    public void photoRepositoryNotNull() throws Exception {
        System.out.print("-> photoRepository Not Null - ");
        assertNotNull(photoRepository);
        System.out.println("OK!");
    }

    @Test
    public void productRepositoryNotNull() throws Exception {
        System.out.print("-> productRepository Not Null - ");
        assertNotNull(productRepository);
        System.out.println("OK!");
    }

    @Test
    public void salePositionRepositoryNotNull() throws Exception {
        System.out.print("-> salePositionRepository Not Null - ");
        assertNotNull(salePositionRepository);
        System.out.println("OK!");
    }

    @Test
    public void userRepositoryNotNull() throws Exception {
        System.out.print("-> userRepository Not Null - ");
        assertNotNull(userRepository);
        System.out.println("OK!");
    }



    @Test
    public void persistenceTranslationTest() {
        System.out.print("-> persistenceTranslation() - ");
        RootConfig rootConfig = new RootConfig();
        assertNotNull(rootConfig.persistenceTranslation());
        System.out.println("OK!");
    }

    @Test
    public void multipartResolverTest() {
        System.out.print("-> multipartResolver() - ");
        RootConfig rootConfig = new RootConfig();
        assertNotNull(rootConfig.multipartResolver());
        System.out.println("OK!");
    }
}