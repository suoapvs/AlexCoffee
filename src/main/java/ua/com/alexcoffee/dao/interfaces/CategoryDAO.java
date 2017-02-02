package ua.com.alexcoffee.dao.interfaces;

import ua.com.alexcoffee.model.Category;

/**
 * Интерфейс описывает набор методов
 * для работы объектов класса
 * {@link Category} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see DataDAO
 * @see ua.com.alexcoffee.dao.impl.CategoryDAOImpl
 * @see Category
 */
public interface CategoryDAO extends DataDAO<Category> {
    /**
     * Возвращает категорию из базы данных,
     * у которой совпадает параметр url.
     *
     * @param url URL категории для возврата.
     * @return Объект класса {@link Category}
     * - категория с уникальным url полем.
     */
    Category get(String url);

    /**
     * Удаляет категрию из базы даных,
     * у которого совпадает поле url.
     *
     * @param url URL категории для удаления.
     */
    void remove(String url);
}
