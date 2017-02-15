package ua.com.alexcoffee.dao.impl;

import ua.com.alexcoffee.dao.interfaces.DataDAO;
import ua.com.alexcoffee.model.Model;
import ua.com.alexcoffee.repository.MainRepository;

import java.util.Collection;
import java.util.List;

/**
 * Абстрактный класс, который реализует основные методы доступа к базе данных
 * интерфейса {@link DataDAO}. Класс должен наследоваться дочерними классами,
 * которые будут описывать поведение объектов-наследников родительского
 * класса {@link Model}. Для работы методы используют объект-репозиторий интерфейса
 * {@link MainRepository}, возвращаемый абстрактным методом repository, реализацию
 * которого каждый наследник берет на себя.
 *
 * @param <T> Класс-наследник класса {@link Model}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see CategoryDAOImpl
 * @see PhotoDAOImpl
 * @see ProductDAOImpl
 * @see RoleDAOImpl
 * @see SalePositionDAOImpl
 * @see StatusDAOImpl
 * @see DataDAOImpl
 * @see DataDAO
 */
public abstract class DataDAOImpl<T extends Model> implements DataDAO<T> {
    /**
     * Реализация репозитория {@link MainRepository}
     * для работы моделей с базой данных.
     */
    private final MainRepository<T, Long> repository;

    /**
     * Конструктор для инициализации основных переменных.
     *
     * @param repository Реализация репозитория {@link MainRepository}
     *                   для работы категорий с базой данных.
     */
    DataDAOImpl(final MainRepository<T, Long> repository) {
        this.repository = repository;
    }

    /**
     * Добавление модели в базу данных.
     *
     * @param model Модель для добавления.
     */
    @Override
    public void add(final T model) {
        this.repository.save(model);
    }

    /**
     * Добавление коллекции моделей в базу данных.
     *
     * @param models Коллекция моделей для добавления.
     */
    @Override
    public void add(final Collection<T> models) {
        this.repository.save(models);
    }

    /**
     * Обновление существующей модели в базе данных.
     *
     * @param model Обновленная модель.
     */
    @Override
    public void update(final T model) {
        this.repository.save(model);
    }

    /**
     * Получение модели по уникальному коду id в базе данных.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link ua.com.alexcoffee.model.Model} -
     * модель с кодом id.
     */
    @Override
    public T get(final Long id) {
        return this.repository.findOne(id);
    }

    /**
     * Получение всех моделей из базы данных.
     *
     * @return Объект типа List - список всех моделей.
     */
    @Override
    public List<T> getAll() {
        return this.repository.findAll();
    }

    /**
     * Удаление модели из базы данных.
     *
     * @param model Модель для удаления.
     */
    @Override
    public void remove(final T model) {
        this.repository.delete(model);
    }

    /**
     * Удаление модели из базы данных по уникальному коду.
     *
     * @param id Уникальный код модели.
     */
    @Override
    public void remove(final Long id) {
        this.repository.delete(id);
    }

    /**
     * Удаление коллекции моделей из базы данных.
     *
     * @param models Коллекция моделей для удаления.
     */
    @Override
    public void remove(final Collection<T> models) {
        this.repository.delete(models);
    }

    /**
     * Удаление всех моделей из базы данных.
     */
    @Override
    public void removeAll() {
        this.repository.deleteAll();
    }
}
