package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Category;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Category}. Расширяет интерфейс {@link MainService}.
 *
 * @author Yurii Salimov
 * @see Category
 * @see MainService
 * @see ua.com.alexcoffee.service.impl.CategoryServiceImpl
 */
public interface CategoryService extends MainService<Category> {
    /**
     * Возвращает категорию, у которой совпадает параметр url.
     *
     * @param url URL категории для возврата.
     * @return Объект класса {@link Category} - категория с уникальным url полем.
     */
    Category get(String url);

    /**
     * Удаляет категрию, у которого совпадает поле url.
     *
     * @param url URL категории для удаления.
     */
    void remove(String url);
}
