package ua.com.alexcoffee.model;

import ua.com.alexcoffee.enums.StatusEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает сущность "Статус заказов", наследует класс {@link Model}.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "statuses") указывает на таблицу "statuses", в которой будут храниться объекты.
 *
 * @author Yurii Salimov
 * @see StatusEnum
 * @see Order
 */
@Entity
@Table(name = "statuses")
public class Status extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название статуса, может принимать одно из значений перечисления {@link StatusEnum}.
     * Значение поля сохраняется в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum title;

    /**
     * Описание товара. Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description;

    /**
     * Список заказов, которые имеют текущий статус.
     * К текущстатусу можно добраться через поле "status"
     * в объекте класса {@link Order}.
     * Выборка объектов orders при первом доступе к ним.
     * Сущности orders автоматически удаляется при удалении текущей.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    /**
     * Конструктр без параметров.
     */
    public Status() {
        this(null, "");
    }

    /**
     * Конструктор для инициализации основных переменных заказа.
     *
     * @param title       Название заказа, может принимать одно из значений перечисления {@link StatusEnum}.
     * @param description Описание статуса.
     */
    public Status(StatusEnum title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    /**
     * Возвращает описание статуса.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание статуса (имя, описание).
     */
    @Override
    public String toString() {
        return "Title: " + this.title.name() + "\nDescription: " + this.description;
    }

    /**
     * Добавляет заказы в список текущего статуса.
     *
     * @param order Заказ, который имеет текущий статус.
     */
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    /**
     * Добавляет список заказов в список заказов orders.
     *
     * @param orders Список заказов, которые будут иметь текущий статус.
     */
    public void addOrders(List<Order> orders) {
        this.orders.addAll(orders);
    }

    /**
     * Удаляет заказ из списка текущего статуса.
     *
     * @param order Заказ, у которого будет удаленен текущий статус.
     */
    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    /**
     * Метод удаляет список заказов из списка orders.
     *
     * @param orders Список заказов, у которых будет удаленен текущий статус.
     */
    public void removeOrders(List<Order> orders) {
        this.orders.removeAll(orders);
    }

    /**
     * Очищает список заказов текущего статуса.
     */
    public void clearOrders() {
        this.orders.clear();
    }

    /**
     * Конвертирует список заказов текущего статуса
     * в список только для чтений и возвращает его.
     *
     * @return Объект типа {@link List} - список заказов только для чтения или пустой список.
     */
    public List<Order> getOrders() {
        return getUnmodifiableList(this.orders);
    }

    /**
     * Устанавливает список заказов текущего статуса.
     *
     * @param orders Список заказов.
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * Возвращает название статуса.
     *
     * @return Объект перечисление {@link StatusEnum} - название статуса.
     */
    public StatusEnum getTitle() {
        return this.title;
    }

    /**
     * Устанавливает название статуса, которое может принимать одно из
     * значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     */
    public void setTitle(StatusEnum title) {
        this.title = title;
    }

    /**
     * Возвращает описание статуса.
     *
     * @return Значение типа {@link String} - описание статуса.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Устанавливает описание статуса.
     *
     * @param description Описание статуса.
     */
    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }
}
