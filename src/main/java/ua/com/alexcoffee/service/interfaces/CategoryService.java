package ua.com.alexcoffee.service.interfaces;

import ua.com.alexcoffee.model.category.Category;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Category}.
 * Расширяет интерфейс {@link MainService}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
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
