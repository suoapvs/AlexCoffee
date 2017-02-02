package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.interfaces.PhotoDAO;
import ua.com.alexcoffee.model.Photo;
import ua.com.alexcoffee.repository.PhotoRepository;

/**
 * Класс реализует методы доступа объектов
 * класса {@link Photo} в базе данных интерфейса
 * {@link PhotoDAO}, наследует родительский
 * абстрактній класс {@link DataDAOImpl},
 * в котором реализованы основные методы.
 * Для работы методы используют
 * объект-репозиторий интерфейса
 * {@link PhotoRepository}.
 * Класс помечена аннотацией @Repository
 * (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически
 * зарегестрировать компонент в своём
 * контексте для последующей инъекции.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see DataDAOImpl
 * @see PhotoDAO
 * @see Photo
 * @see PhotoRepository
 */
@Repository
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class PhotoDAOImpl
        extends DataDAOImpl<Photo>
        implements PhotoDAO {
    /**
     * Реализация репозитория {@link PhotoRepository}
     * для работы изображений с базой данных.
     */
    private final PhotoRepository repository;

    /**
     * Конструктор для инициализации основных
     * переменных.
     * Помечаный аннотацией @Autowired,
     * которая позволит Spring автоматически
     * инициализировать объект.
     *
     * @param repository Реализация репозитория
     *                   {@link PhotoRepository}
     *                   для работы изображений
     *                   с базой данных.
     */
    @Autowired
    public PhotoDAOImpl(final PhotoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает объект-изображение
     * из базы даных, у которого
     * совпадает уникальное название
     * с значением входящего параметра.
     *
     * @param title Название объекта-изображения
     *              для возврата.
     * @return Объект класса {@link Photo} -
     * объекта-изображение.
     */
    @Override
    public Photo get(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Удаляет объект-изображение
     * из базы даных, у которого
     * совпадает уникальное название
     * с значением входящего параметра.
     *
     * @param title Название объекта-изображения
     *              для удаления.
     */
    @Override
    public void remove(final String title) {
        this.repository.deleteByTitle(title);
    }
}
