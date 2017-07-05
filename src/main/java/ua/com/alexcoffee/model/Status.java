package ua.com.alexcoffee.model;

import ua.com.alexcoffee.enums.StatusEnum;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Класс описывает сущность "Статус заказов", наследует класс {@link Model}.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "statuses") указывает на таблицу "statuses",
 * в которой будут храниться объекты.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see StatusEnum
 * @see Order
 */
@Entity
@Table(name = "statuses")
public final class Status extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название статуса, может принимать одно
     * из значений перечисления {@link StatusEnum}.
     * Значение поля сохраняется в колонке "title".
     * Не может быть null.
     */
    @Column(
            name = "title",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private StatusEnum title;

    /**
     * Описание товара.
     * Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description;

    /**
     * Конструктр без параметров.
     */
    public Status() {
        this(null, "");
    }

    /**
     * Конструктор для инициализации основных переменных заказа.
     *
     * @param title       Название заказа, может принимать одно из значений
     *                    перечисления {@link StatusEnum}.
     * @param description Описание статуса.
     */
    public Status(
            final StatusEnum title,
            final String description
    ) {
        super();
        this.title = title;
        setDescription(description);
    }

    /**
     * Возвращает описание статуса.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} -
     * строка описание статуса (имя, описание).
     */
    @Override
    public String toString() {
        return "Status{" + super.toString() +
                ", title: " + this.title.name() +
                ", description: " + this.description +
                '}';
    }

    /**
     * Сравнивает текущий объект с объектом переданым как параметр.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @param object объект для сравнения с текущим объектом.
     * @return Значение типа boolean - результат сравнения текущего объекта
     * с переданным объектом.
     */
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Status status = (Status) object;
            result = (this.title.equals(status.title)) &&
                    this.description.equalsIgnoreCase(status.description);
        }
        return result;
    }

    /**
     * Возвращает хеш код объекта.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа int - уникальный номер объекта.
     */
    @Override
    public int hashCode() {
        int result = this.title.hashCode();
        result = 31 * result + this.description.hashCode();
        return result;
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
    public void setTitle(final StatusEnum title) {
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
    public void setDescription(final String description) {
        this.description = isNotEmpty(description) ? description : "";
    }
}
