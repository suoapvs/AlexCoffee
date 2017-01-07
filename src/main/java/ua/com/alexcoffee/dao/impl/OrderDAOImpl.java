package ua.com.alexcoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.dao.interfaces.OrderDAO;
import ua.com.alexcoffee.repository.OrderRepository;
import ua.com.alexcoffee.model.Order;

/**
 * Класс реализует методы доступа объектов
 * класса {@link Order} в базе данных
 * интерфейса {@link OrderDAO}, наследует
 * родительский абстрактній класс
 * {@link DataDAOImpl}, в котором реализованы
 * основные методы. Для работы методы
 * используют объект-репозиторий интерфейса
 * {@link OrderRepository}. Класс помечена
 * аннотацией @Repository (наследник Spring'овой
 * аннотации @Component). Это позволяет Spring
 * автоматически зарегестрировать
 * компонент в своём контексте для
 * последующей инъекции.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.2
 * @see DataDAOImpl
 * @see OrderDAO
 * @see Order
 * @see OrderRepository
 */
@Repository
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class OrderDAOImpl
        extends DataDAOImpl<Order>
        implements OrderDAO {
    /**
     * Реализация репозитория {@link OrderRepository}
     * для работы категорий с базой данных.
     */
    private final OrderRepository repository;

    /**
     * Конструктор для инициализации основных
     * переменных сервиса.
     * Помечаный аннотацией @Autowired,
     * которая позволит Spring автоматически
     * инициализировать объект.
     *
     * @param repository Реализация репозитория
     *                   {@link OrderRepository}
     *                   для работы категорий с
     *                   базой данных.
     */
    @Autowired
    public OrderDAOImpl(final OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает заказ из базы даных,
     * у которого совпадает уникальный
     * номером с значением входящего
     * параметра.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} -
     * заказ с уникальным номером.
     */
    @Override
    public Order get(final String number) {
        return this.repository.findByNumber(number);
    }

    /**
     * Удаляет заказ из базы даных,
     * у которого совпадает уникальный
     * номером с значением входящего
     * параметра.
     *
     * @param number Номер заказа для удаление.
     */
    @Override
    public void remove(final String number) {
        this.repository.deleteByNumber(number);
    }
}
