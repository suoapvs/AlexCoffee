package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.repository.OrderRepository;
import ua.com.alexcoffee.service.interfaces.OrderService;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Order}
 * в базе данных интерфейса {@link OrderService}, наследует родительский
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
 * @see OrderService
 * @see Order
 * @see OrderRepository
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class OrderServiceImpl extends MainServiceImpl<Order> implements OrderService {
    /**
     * Реализация интерфейса {@link OrderRepository}
     * для работы заказов с базой данных.
     */
    private final OrderRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Реализация интерфейса {@link OrderRepository}
     *                   для работы категорий с базой данных.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public OrderServiceImpl(final OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает заказ из базы даных, у которого совпадает
     * уникальный номером с значением входящего параметра.
     * Режим только для чтения.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером.
     * @throws WrongInformationException Бросает исключение,
     *                                   если пустой входной параметр number.
     * @throws BadRequestException       Бросает исключение,
     *                                   если не найден заказ с входящим параметром number.
     */
    @Override
    @Transactional(readOnly = true)
    public Order get(final String number) throws WrongInformationException, BadRequestException {
        if (isBlank(number)) {
            throw new WrongInformationException("No order number!");
        }
        final Order order = this.repository.findByNumber(number);
        if (order == null) {
            throw new BadRequestException("Can't find order by number " + number + "!");
        }
        return order;
    }

    /**
     * Удаляет заказ из базы даных, у которого совпадает
     * уникальный номером с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     * @throws WrongInformationException Бросает исключение,
     *                                   если пустой входной параметр number.
     */
    @Override
    @Transactional
    public void remove(final String number) throws WrongInformationException {
        if (isBlank(number)) {
            throw new WrongInformationException("No order number!");
        }
        this.repository.deleteByNumber(number);
    }
}
