package ua.com.alexcoffee.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Класс основных конфигураций для Spring:
 * DataSource,
 * JpaVendorAdapter,
 * JpaTransactionManager,
 * BeanPostProcessor,
 * CommonsMultipartResolver.
 * Помечен аннотацией @Configuration -
 * класс является источником определения
 * бинов;
 * аннотацией @EnableTransactionManagement -
 * активирует возможности Spring транзакции
 * через @Transactional;
 * аннотацией @EnableJpaRepositories - активирует
 * Spring Data JPA, который будет создавать
 * конкретную реализацию для репозитория из
 * пакета "ua.com.alexcoffee.repository" и
 * настраивать на взаимодействие с БД в
 * памяти, используя JPA;
 * аннотацией @ComponentScan - указываем фреймворку
 * Spring, что компоненты надо искать внутри
 * пакета "ua.com.alexcoffee.model".
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ua.com.alexcoffee.repository")
@ComponentScan(basePackages = "ua.com.alexcoffee.model")
public class RootConfig {

    /**
     * Путь к базе данных.
     */
    private static final String DATABASE_URL =
            "jdbc:mysql://127.0.0.1:3306/alexcoffee?"
                    + "autoReconnect=true"
                    + "&useSSL=false&useUnicode=true"
                    + "&useJDBCCompliantTimezoneShift=true"
                    + "&useLegacyDatetimeCode=false"
                    + "&serverTimezone=GMT";

    /**
     * Драйвер для подключение к базе данных.
     */
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Логин для подключение к базе данных.
     */
    private static final String DATABASE_USERNAME = "root";

    /**
     * Пароль для подключение к базе данных.
     */
    private static final String DATABASE_PASSWORD = "admin";

    /**
     * Диалект Hibernate SQL для базы данных.
     */
    private static final String DATABASE_DIALECT =
            "org.hibernate.dialect.MySQLDialect";

    /**
     * Пакет сканирования для фабрики EntityManager.
     */
    private static final String PACKAGE_TO_SCAN =
            "ua.com.alexcoffee.model";

    /**
     * Начальный размер пула соединений.
     */
    private static final int INITIAL_SIZE = 5;

    /**
     * Максимальное количество активных соединений,
     * которые могут быть выделены в то же время.
     */
    private static final int MAX_ACTIVE = 20;

    /**
     * Это свойство определяет, будет ли пул проверки
     * объектов, прежде чем они заимствованы у бассейна.
     */
    private static final boolean TEST_ON_BORROW = true;

    /**
     * Запрос SQL, который будет использоваться
     * для проверки соединения из этого пула
     * перед их возвращением к вызывающему.
     */
    private static final String VALIDATION_QUERY = "SELECT 1";

    /**
     * Возвращает объект класса DataSource
     * с настройками подключения к базе данных.
     * Нужен для получения физического
     * соединения с базой данных.
     *
     * @return Объект класса DataSource -
     * настройки для базы данных.
     */
    @Bean
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(DATABASE_URL);
        dataSource.setDriverClassName(DATABASE_DRIVER);
        dataSource.setUsername(DATABASE_USERNAME);
        dataSource.setPassword(DATABASE_PASSWORD);
        dataSource.setInitialSize(INITIAL_SIZE);
        dataSource.setMaxActive(MAX_ACTIVE);
        dataSource.setTestOnBorrow(TEST_ON_BORROW);
        dataSource.setValidationQuery(VALIDATION_QUERY);
        return dataSource;
    }

    /**
     * Возвращает настройки адаптера
     * (JPA provider) для подключения
     * к базе данных.
     *
     * @return Объект класса HibernateJpaVendorAdapter -
     * адаптера для подключения к базе данных..
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter adapter =
                new HibernateJpaVendorAdapter();
        adapter.setShowSql(false);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform(DATABASE_DIALECT);
        return adapter;
    }

    /**
     * Создает фабрику EntityManager,
     * может быть передана в DAO,
     * JPA с помощью инъекции зависимостей.
     *
     * @param dataSource       Объект класса DataSource с
     *                         настройками подключения к базе данных.
     * @param jpaVendorAdapter Реализация интерфейса JpaVendorAdapter -
     *                         адаптера для подключения к базе данных.
     * @return Объект класса LocalContainerEntityManagerFactoryBean.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            final DataSource dataSource,
            final JpaVendorAdapter jpaVendorAdapter
    ) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactory =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPackagesToScan(PACKAGE_TO_SCAN);
        return entityManagerFactory;
    }

    /**
     * Возвращает менеджера транзакций, который
     * подходит для приложений, использующих единую
     * JPA EntityManagerFactory для транзакционного
     * доступа к данным.
     *
     * @param entityManagerFactory Реализация интерфейса EntityManagerFactory.
     * @return Объект класса JpaTransactionManager
     * с входящей фабрикой ентети менеджера factory.
     */
    @Bean
    public JpaTransactionManager transactionManager(
            final EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    /**
     * Переводит (перехватывает) любые JPA
     * или Hibernate исключения в Spring исключения.
     *
     * @return Реализация интерфейса
     * PersistenceExceptionTranslationPostProcessor.
     */
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * Возвращает объект класса CommonsMultipartResolver,
     * который сохраняет временные файлы
     * во временный каталог сервлет контейнера.
     *
     * @return Объект класса CommonsMultipartResolver
     * для временного сохранения файлов.
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }
}
