package ua.com.alexcoffee.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
