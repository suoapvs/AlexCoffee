package ua.com.alexcoffee.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.interfaces.DataDAO;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Model;
import ua.com.alexcoffee.service.interfaces.MainService;

import java.util.List;

/**
 * Класс сервисного слоя, который реализует основные методы доступа к
 * объектам наследникам родительского класса {@link Model}, описаные в
 * интерфейсе {@link MainService}. Класс должен наследоваться дочерними классами,
 * которые будут описывать поведение объектов-наследников родительского класса {@link Model}.
 * Для работы методы используют объект DAO интерфейса {@link DataDAO}, возвращаемый абстрактным
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
 * @see DataDAO
 */
public abstract class MainServiceImpl<T extends Model>
        implements MainService<T> {
    /**
     * Реализация интерфейса {@link DataDAO}
     * для работы моделей с базой данных.
     */
    private final DataDAO<T> dao;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     *
     * @param dao Реализация интерфейса {@link DataDAO}
     *            для работы моделей с базой данных.
     */
    public MainServiceImpl(final DataDAO<T> dao) {
        super();
        this.dao = dao;
    }

    /**
     * Добавление модели в базу данных.
     *
     * @param model Модель для добавления.
     */
    @Override
    @Transactional
    public void add(final T model) {
        if (model != null) {
            this.dao.add(model);
        }
    }

    /**
     * Добавление коллекции моделей в базу данных.
     *
     * @param models Коллекция моделей для добавления.
     */
    @Override
    @Transactional
    public void add(final List<T> models) {
        if (models != null && !models.isEmpty()) {
            this.dao.add(models);
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
        if (model != null) {
            this.dao.update(model);
        }
    }

    /**
     * Получение модели по уникальному коду id в базе данных.
     * Режим только для чтения.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link Model} -  м
     * одель с кодом id.
     * @throws WrongInformationException Бросает исключение,
     *                                   если пустой входной параметр id.
     * @throws BadRequestException       Бросает исключение,
     *                                   если не найдена модель с входящим параметром id.
     */
    @Override
    @Transactional(readOnly = true)
    public T get(final Long id)
            throws WrongInformationException, BadRequestException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }
        final T model = this.dao.get(id);
        if (model == null) {
            throw new BadRequestException(
                    "Can't find model by id " + id + "!"
            );
        }
        return this.dao.get(id);
    }

    /**
     * Получение всех моделей из базы данных.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} -
     * список всех моделей.
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return this.dao.getAll();
    }

    /**
     * Удаление модели из базы данных.
     *
     * @param model Модель для удаления.
     */
    @Override
    @Transactional
    public void remove(final T model) {
        if (model != null) {
            this.dao.remove(model);
        }
    }

    /**
     * Удаление модели из базы данных по уникальному коду.
     *
     * @param id Уникальный код модели.
     * @throws WrongInformationException Бросает исключение,
     *                                   если пустой входной параметр id.
     */
    @Override
    @Transactional
    public void remove(final Long id)
            throws WrongInformationException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }
        this.dao.remove(id);
    }

    /**
     * Удаление коллекции моделей из базы данных.
     *
     * @param models Коллекция моделей для удаления.
     */
    @Override
    @Transactional
    public void remove(final List<T> models) {
        if (models != null && !models.isEmpty()) {
            this.dao.remove(models);
        }
    }

    /**
     * Удаление всех моделей из базы данных.
     */
    @Override
    @Transactional
    public void removeAll() {
        this.dao.removeAll();
    }
}
