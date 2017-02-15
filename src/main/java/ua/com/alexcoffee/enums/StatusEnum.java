package ua.com.alexcoffee.enums;

/**
 * Перечесление вожможных статусов выполнения заказа.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 */
public enum StatusEnum {
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
    REJECTION
}
