package ua.com.alexcoffee.service.interfaces;

import ua.com.alexcoffee.model.order.Order;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Order}.
 * Расширяет интерфейс {@link MainService}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see Order
 * @see MainService
 * @see ua.com.alexcoffee.service.impl.OrderServiceImpl
 */
public interface OrderService extends MainService<Order> {
    /**
     * Возвращает заказ, у которого совпадает уникальный номером
     * с значением входящего  параметра.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером.
     */
    Order get(String number);

    /**
     * Удаляет заказ, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     */
    void remove(String number);
}
