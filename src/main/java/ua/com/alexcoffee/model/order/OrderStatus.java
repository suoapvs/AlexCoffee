package ua.com.alexcoffee.model.order;

/**
 * Перечесление вожможных статусов выполнения заказа.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 */
public enum OrderStatus {
    /**
     * Новый заказ.
     */
    NEW,

    /**
     * Заказ в работу.
     */
    WORK,

    /**
     * Доставка заказа.
     */
    DELIVERY,

    /**
     * Заказ закрыт.
     */
    CLOSED,

    /**
     * Отказ заказа.
     */
    REJECTION;

    public String getDescription() {
        return toString();
    }
}
