package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.interfaces.ProductDAO;
import ua.com.alexcoffee.model.Product;
import ua.com.alexcoffee.repository.ProductRepository;

import java.util.List;

/**
 * Класс реализует методы доступа объектов
 * класса {@link Product} в базе данных
 * интерфейса {@link ProductDAO}, наследует
 * родительский абстрактній класс
 * {@link DataDAOImpl}, в котором реализованы
 * основные методы. Для работы методы
 * используют объект-репозиторий интерфейса
 * {@link ProductRepository}.
 * Класс помечена аннотацией @Repository
 * (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически
 * зарегестрировать компонент в своём
 * контексте для последующей инъекции.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.2
 * @see DataDAOImpl
 * @see ProductDAO
 * @see Product
 * @see ProductRepository
 */
@Repository
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class ProductDAOImpl
        extends DataDAOImpl<Product>
        implements ProductDAO {
    /**
     * Реализация репозитория {@link ProductRepository}
     * для работы с товаров базой данных.
     */
    private final ProductRepository repository;

    /**
     * Конструктор для инициализации основных
     * переменных. Помечаный
     * аннотацией @Autowired, которая
     * позволит Spring автоматически
     * инициализировать объект.
     *
     * @param repository Реализация репозитория
     *                   {@link ProductRepository}
     *                   для работы с товаров
     *                   базой данных.
     */
    @Autowired
    public ProductDAOImpl(final ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает товар из базы данных,
     * у которого совпадает параметр url.
     *
     * @param url URL товара для возврата.
     * @return Объект класса {@link Product} -
     * товара с уникальным url полем.
     */
    @Override
    public Product getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Возвращает товар из базы даных,
     * у которого совпадает уникальный
     * артикль с значением входящего
     * параметра.
     *
     * @param article Артикль товара для возврата.
     * @return Объект класса {@link Product} -
     * товара с уникальным артиклем.
     */
    @Override
    public Product getByArticle(final int article) {
        return this.repository.findByArticle(article);
    }

    /**
     * Удаляет товар из базы данных,
     * у которого совпадает параметр url.
     *
     * @param url URL товара для удаления.
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }

    /**
     * Удаляет товар из базы данных,
     * у которого совпадает параметр article.
     *
     * @param article Артикль товара для удаления.
     */
    @Override
    public void removeByArticle(final int article) {
        this.repository.deleteByArticle(article);
    }

    /**
     * Удаляет товары из базы даных,
     * которые пренадлежат категории
     * с уникальным кодом -
     * входным параметром.
     *
     * @param id Уникальный код категории,
     *           товары котрой будут удалены.
     */
    @Override
    public void removeByCategoryId(final long id) {
        List<Product> productList = this.repository.findByCategoryId(id);
        this.repository.delete(productList);
    }

    /**
     * Возвращает список товаров,
     * которые пренадлежат категории
     * с уникальным кодом -
     * входным параметром.
     *
     * @param id Уникальный код категории.
     * @return Объект типа List -
     * список товаров.
     */
    @Override
    public List<Product> getListByCategoryId(final long id) {
        return this.repository.findByCategoryId(id);
    }
}
