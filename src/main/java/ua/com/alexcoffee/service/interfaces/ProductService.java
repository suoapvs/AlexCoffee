package ua.com.alexcoffee.service.interfaces;

import ua.com.alexcoffee.model.product.Product;

import java.util.Collection;
import java.util.List;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Product}.
 * Расширяет интерфейс {@link MainService}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see Product
 * @see MainService
 * @see ua.com.alexcoffee.service.impl.ProductServiceImpl
 */
public interface ProductService extends MainService<Product> {
    /**
     * Возвращает товар, у которого совпадает параметр url.
     *
     * @param url URL товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным url полем.
     */
    Product getByUrl(String url);

    /**
     * Возвращает товар, у которого совпадает уникальный
     * артикль с значением входящего параметра.
     *
     * @param article Артикль товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным артиклем.
     */
    Product getByArticle(int article);

    /**
     * Возвращает список товаров, которые относятся к категории
     * с уникальным URL - входным параметром.
     *
     * @param url Уникальный URL категории, товары которой будут возвращены.
     * @return Объект типа {@link List} - список товаров.
     */
    Collection<Product> getByCategoryUrl(String url);

    /**
     * Возвращает список товаров, которые относятся к категории
     * с уникальным кодом id - входным параметром.
     *
     * @param id Уникальный код категории, товары которой будут возвращены.
     * @return Объект типа {@link List} - список товаров.
     */
    Collection<Product> getByCategoryId(long id);

    /**
     * Возвращает список рандомных товаров, которые относятся к категории
     * с уникальным кодом id - входным параметром.
     *
     * @param size               Количество товаров в списке.
     * @param categoryId         Код категории, товары которой будут возвращены.
     * @param differentProductId Код товара, который точно не будет включен в список.
     * @return Объект типа {@link List} - список товаров.
     */
    Collection<Product> getRandomByCategoryId(
            int size,
            long categoryId,
            long differentProductId
    );

    /**
     * Возвращает список рандомных товаров,
     * которые относятся к категории с уникальным
     * кодом id - входным параметром.
     *
     * @param size Количество товаров в списке.
     * @param id   Код категории, товары которой будут возвращены.
     * @return Объект типа {@link List} - список товаров.
     */
    Collection<Product> getRandomByCategoryId(int size, long id);

    /**
     * Возвращает список рандомных товаров.
     *
     * @param size Количество товаров в списке.
     * @return Объект типа {@link List} - список товаров.
     */
    Collection<Product> getRandom(int size);

    /**
     * Удаляет товар, у которого совпадает параметр url.
     *
     * @param url URL товара для удаления.
     */
    void removeByUrl(String url);

    /**
     * Удаляет товар, у которого совпадает параметр article.
     *
     * @param article Артикль товара для удаления.
     */
    void removeByArticle(int article);

    /**
     * Удаляет товары, которые пренадлежат
     * категории с уникальным URL - входным параметром.
     *
     * @param url URL категории, товары которой будут удалены.
     */
    void removeByCategoryUrl(String url);

    /**
     * Удаляет товары, которые пренадлежат
     * категории с уникальным кодом - входным параметром.
     *
     * @param id Код категории, товары котрой будут удалены.
     */
    void removeByCategoryId(long id);
}
