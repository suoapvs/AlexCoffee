package ua.com.alexcoffee.service.impl;

import org.springframework.context.annotation.ComponentScan;
import ua.com.alexcoffee.dao.StatusDAO;
import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.enums.StatusEnum;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * @author Yurii Salimov
 * @see MainServiceImpl
 * @see StatusService
 * @see Status
 * @see StatusDAO
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.dao")
public class StatusServiceImpl extends MainServiceImpl<Status> implements StatusService {
    /**
     * Реализация интерфейса {@link StatusDAO} для работы статусов заказов с базой данных.
     */
    private final StatusDAO dao;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param dao Реализация интерфейса {@link StatusDAO} для работы статусов заказов с базой данных.
     */
    @Autowired
    public StatusServiceImpl(StatusDAO dao) {
        super(dao);
        this.dao = dao;
    }

    /**
     * Добавляет статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     * @throws DuplicateException        Бросает исключение, если в БД уже есть такой объект.
     */
    @Override
    @Transactional
    public void add(StatusEnum title, String description) throws WrongInformationException, DuplicateException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        if (this.dao.get(title) != null) {
            throw new DuplicateException("Duplicate status with title  " + title + "!");
        }
        this.dao.add(new Status(title, description));
    }

    /**
     * Возвращает статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     * @return Объект класса {@link Status} - статус с названием title.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     * @throws BadRequestException       Бросает исключение, если не найден статус с входящим параметром title.
     */
    @Override
    @Transactional(readOnly = true)
    public Status get(StatusEnum title) throws WrongInformationException, BadRequestException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        Status status = this.dao.get(title);
        if (status == null) {
            throw new BadRequestException("Can't find status by title " + title + "!");
        }
        return status;
    }

    /**
     * Возвращает статус по-умолчанию.
     *
     * @return Объект класса {@link Status} - статус по-умолчание.
     * @throws BadRequestException Бросает исключение, если не найден статус по-умолчание.
     */
    @Override
    @Transactional(readOnly = true)
    public Status getDefault() throws BadRequestException {
        Status status = this.dao.getDefault();
        if (status == null) {
            throw new BadRequestException("Can't find default status!");
        }
        return status;
    }

    /**
     * Удаляет статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса для удаления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр title.
     */
    @Override
    @Transactional
    public void remove(StatusEnum title) throws WrongInformationException {
        if (title == null) {
            throw new WrongInformationException("No status title!");
        }
        this.dao.remove(title);
    }
}
