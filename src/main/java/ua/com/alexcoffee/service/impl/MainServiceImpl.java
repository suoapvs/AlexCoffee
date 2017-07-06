package ua.com.alexcoffee.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.model.model.Model;
import ua.com.alexcoffee.repository.MainRepository;
import ua.com.alexcoffee.service.interfaces.MainService;

import java.util.Collection;
import java.util.List;

import static ua.com.alexcoffee.util.validator.ObjectValidator.*;

/**
 * Класс сервисного слоя, который реализует основные методы доступа к
 * объектам наследникам родительского класса {@link Model}, описаные в
 * интерфейсе {@link MainService}. Класс должен наследоваться дочерними классами,
 * которые будут описывать поведение объектов-наследников родительского класса {@link Model}.
 * Для работы методы используют объект интерфейса {@link MainRepository}, возвращаемый абстрактным
 * методом dao, реализацию которого каждый наследник берет на себя.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @param <T> Класс-наследник класса {@link Model}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see Model
 * @see MainService
 * @see CategoryServiceImpl
 * @see OrderServiceImpl
 * @see PhotoServiceImpl
 * @see ProductServiceImpl
 * @see RoleServiceImpl
 * @see SalePositionServiceImpl
 * @see MainServiceImpl
 * @see UserServiceImpl
 * @see MainRepository
 */
public abstract class MainServiceImpl<T extends Model>
        implements MainService<T> {
    /**
     * Реализация интерфейса {@link MainRepository}
     * для работы моделей с базой данных.
     */
    private final MainRepository<T> repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     *
     * @param repository Реализация интерфейса {@link MainRepository}
     *                   для работы моделей с базой данных.
     */
    public MainServiceImpl(final MainRepository<T> repository) {
        super();
        this.repository = repository;
    }

    /**
     * Добавление модели в базу данных.
     *
     * @param model Модель для добавления.
     */
    @Override
    @Transactional
    public void add(final T model) {
        if (isNotNull(model)) {
            this.repository.save(model);
        }
    }

    /**
     * Добавление коллекции моделей в базу данных.
     *
     * @param models Коллекция моделей для добавления.
     */
    @Override
    @Transactional
    public void add(final Collection<T> models) {
        if (isNotEmpty(models)) {
            this.repository.save(models);
        }
    }

    /**
     * Обновление существующей модели в базе данных.
     *
     * @param model Обновленная модель.
     */
    @Override
    @Transactional
    public void update(final T model) {
        add(model);
    }

    /**
     * Получение модели по уникальному коду id в базе данных.
     * Режим только для чтения.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link Model} -  модель с кодом id.
     * @throws NullPointerException Бросает исключение,
     *                              если не найдена модель с входящим параметром id.
     */
    @Override
    @Transactional(readOnly = true)
    public T get(final long id) throws NullPointerException {
        final T model = this.repository.findOne(id);
        if (isNull(model)) {
            throw new NullPointerException("Can't find model by id " + id + "!");
        }
        return model;
    }

    /**
     * Получение всех моделей из базы данных.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} - список всех моделей.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<T> getAll() {
        return this.repository.findAll();
    }

    /**
     * Удаление модели из базы данных.
     *
     * @param model Модель для удаления.
     */
    @Override
    @Transactional
    public void remove(final T model) {
        if (isNotNull(model)) {
            this.repository.delete(model);
        }
    }

    /**
     * Удаление модели из базы данных по уникальному коду.
     *
     * @param id Уникальный код модели.
     */
    @Override
    @Transactional
    public void remove(final long id) {
        this.repository.delete(id);
    }

    /**
     * Удаление коллекции моделей из базы данных.
     *
     * @param models Коллекция моделей для удаления.
     */
    @Override
    @Transactional
    public void remove(final Collection<T> models) {
        if (isNotEmpty(models)) {
            this.repository.delete(models);
        }
    }

    /**
     * Удаление всех моделей из базы данных.
     */
    @Override
    @Transactional
    public void removeAll() {
        this.repository.deleteAll();
    }
}
