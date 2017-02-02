package ua.com.alexcoffee.dao.impl;

import org.springframework.context.annotation.ComponentScan;
import ua.com.alexcoffee.dao.interfaces.CategoryDAO;
import ua.com.alexcoffee.repository.CategoryRepository;
import ua.com.alexcoffee.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Класс реализует методы доступа
 * объектов класса {@link Category}
 * в базе данных интерфейса {@link CategoryDAO},
 * наследует родительский абстрактній
 * класс {@link DataDAOImpl}, в котором
 * реализованы основные методы.
 * Для работы методы используют
 * объект-репозиторий интерфейса
 * {@link CategoryRepository}.
 * Класс помечена аннотацией @Repository
 * (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически
 * зарегестрировать компонент в своём
 * контексте для последующей инъекции.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see DataDAOImpl
 * @see CategoryDAO
 * @see Category
 * @see CategoryRepository
 */
@Repository
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class CategoryDAOImpl
        extends DataDAOImpl<Category>
        implements CategoryDAO {
    /**
     * Реализация репозитория {@link CategoryRepository}
     * для работы категорий с базой данных.
     */
    private final CategoryRepository repository;

    /**
     * Конструктор для инициализации
     * основных переменных.
     * Помечаный аннотацией @Autowired,
     * которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Реализация интерфейса
     *                   {@link CategoryRepository}
     *                   для работы категорий с базой данных.
     */
    @Autowired
    public CategoryDAOImpl(
            final CategoryRepository repository
    ) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает категорию из базы данных,
     * у которой совпадает параметр url.
     *
     * @param url URL категории для возврата.
     * @return Объект класса {@link Category} -
     * категория с уникальным url полем.
     */
    @Override
    public Category get(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Удаляет категрию из базы даных,
     * у которого совпадает поле url.
     *
     * @param url URL категории для удаления.
     */
    @Override
    public void remove(final String url) {
        this.repository.deleteByUrl(url);
    }
}
