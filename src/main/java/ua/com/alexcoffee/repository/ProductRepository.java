package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.product.Product;

import java.util.Collection;
import java.util.List;

/**
 * Репозиторий для объектов класса {@link Product}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainRepository
 * @see Product
 */
public interface ProductRepository extends MainRepository<Product> {
    /**
     * Возвращает товар из базы данных, у которого совпадает параметр url.
     *
     * @param url URL товара для возврата.
     * @return Объект класса {@link Product} - товар с уникальным url полем.
     */
    Product findByUrl(String url);

    /**
     * Возвращает товар из базы даных, у которого совпадает уникальный
     * артикль с значением входящего параметра.
     *
     * @param article Артикль товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным артиклем.
     */
    Product findByArticle(int article);

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр url.
     *
     * @param url URL товара для удаления.
     */
    void deleteByUrl(String url);

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр article.
     *
     * @param article Артикль товара для удаления.
     */
    void deleteByArticle(int article);

    /**
     * Удаляет товар из базы данных, которые пренадлежат категории
     * с уникальным URL - входным параметром.
     *
     * @param url URL категории.
     */
    void deleteByCategoryUrl(String url);

    /**
     * Удаляет товар из базы данных, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     *
     * @param id Код категории.
     */
    void deleteByCategoryId(long id);

    /**
     * Возвращает список товаров, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     *
     * @param id Код категории.
     * @return Объект типа {@link List} - список товаров.
     */
    Collection<Product> findByCategoryId(long id);

    /**
     * Возвращает список товаров, которые пренадлежат категории
     * с уникальным URL - входным параметром.
     *
     * @param url URL категории.
     * @return Объект типа {@link List} - список товаров.
     */
    Collection<Product> findByCategoryUrl(String url);
}
