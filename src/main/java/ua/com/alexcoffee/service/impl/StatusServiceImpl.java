package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.enums.StatusEnum;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.repository.StatusRepository;
import ua.com.alexcoffee.service.interfaces.StatusService;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNull;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Status}
 * в базе данных интерфейса {@link StatusService}, наследует родительский
 * класс {@link MainServiceImpl}, в котором реализованы основные методы.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainServiceImpl
 * @see StatusService
 * @see Status
 * @see StatusRepository
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class StatusServiceImpl extends MainServiceImpl<Status> implements StatusService {
    /**
     * Реализация интерфейса {@link StatusRepository}
     * для работы статусов заказов с базой данных.
     */
    private final StatusRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Реализация интерфейса {@link StatusRepository}
     *                   для работы статусов заказов с базой данных.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public StatusServiceImpl(final StatusRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Добавляет статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     * @throws IllegalArgumentException Бросает исключение,
     *                                  если пустой входной параметр title.
     * @throws DuplicateException       Бросает исключение,
     *                                  если в БД уже есть такой объект.
     */
    @Override
    @Transactional
    public void add(final StatusEnum title, final String description)
            throws IllegalArgumentException, DuplicateException {
        if (isNull(title)) {
            throw new IllegalArgumentException("No status title!");
        }
        final Status savingStatus = this.repository.findByTitle(title);
        if (isNotNull(savingStatus)) {
            throw new DuplicateException("Duplicate status with title  " + title + "!");
        }
        final Status newStatus = new Status(title, description);
        add(newStatus);
    }

    /**
     * Возвращает статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     * @return Объект класса {@link Status} - статус с названием title.
     * @throws IllegalArgumentException Бросает исключение,
     *                                  если пустой входной параметр title.
     * @throws NullPointerException     Бросает исключение,
     *                                  если не найден статус с входящим параметром title.
     */
    @Override
    @Transactional(readOnly = true)
    public Status get(final StatusEnum title)
            throws IllegalArgumentException, NullPointerException {
        if (isNull(title)) {
            throw new IllegalArgumentException("No status title!");
        }
        final Status status = this.repository.findByTitle(title);
        if (isNull(status)) {
            throw new NullPointerException("Can't find status by title " + title + "!");
        }
        return status;
    }

    /**
     * Возвращает статус по-умолчанию.
     *
     * @return Объект класса {@link Status} - статус по-умолчание.
     * @throws NullPointerException Бросает исключение,
     *                              если не найден статус по-умолчание.
     */
    @Override
    @Transactional(readOnly = true)
    public Status getDefault() throws NullPointerException {
        final Status status = this.repository.findOne((long) 1);
        if (isNull(status)) {
            throw new NullPointerException("Can't find default status!");
        }
        return status;
    }

    /**
     * Удаляет статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса для удаления.
     */
    @Override
    @Transactional
    public void remove(final StatusEnum title) {
        if (isNotNull(title)) {
            this.repository.deleteByTitle(title);
        }
    }
}
