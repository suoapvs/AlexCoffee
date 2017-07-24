package ua.com.alexcoffee.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
public class DatabaseConfigTest {

    @Test
    public void dataSourceTest() throws Exception {
        System.out.print("-> dataSource() - ");
        DatabaseConfig databaseConfig = new DatabaseConfig();
        assertNotNull(databaseConfig.dataSource());
        System.out.println("OK!");
    }

    @Test
    public void jpaVendorAdapterTest() throws Exception {
        System.out.print("-> jpaVendorAdapter() - ");
        DatabaseConfig databaseConfig = new DatabaseConfig();
        assertNotNull(databaseConfig.hibernateJpaVendorAdapter());
        System.out.println("OK!");
    }

    @Test
    public void entityManagerFactoryTest() throws Exception {
        System.out.print("-> entityManagerFactory() - ");

        DatabaseConfig databaseConfig = new DatabaseConfig();
        DataSource dataSource = mock(DataSource.class);
        HibernateJpaVendorAdapter jpaVendorAdapter = mock(HibernateJpaVendorAdapter.class);

        assertNotNull(databaseConfig.entityManagerFactory(dataSource, jpaVendorAdapter));

        System.out.println("OK!");
    }

    @Test
    public void transactionManagerTest() {
        System.out.print("-> transactionManager() - ");
        DatabaseConfig databaseConfig = new DatabaseConfig();
        EntityManagerFactory factory = mock(EntityManagerFactory.class);
        assertNotNull(databaseConfig.transactionManager(factory));
        System.out.println("OK!");
    }
}
