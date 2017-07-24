package ua.com.alexcoffee.model.model;

import ua.com.alexcoffee.model.category.Category;
import ua.com.alexcoffee.model.order.Order;
import ua.com.alexcoffee.model.photo.Photo;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.product.Product;
import ua.com.alexcoffee.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * Класс представляет абстрактную модель сущностей, не описывает сущности как таковой.
 * Класс не отображается на отдельную таблицу в базе данных,
 * реализует интерфейс Serializable, может быть сериализован.
 * Аннотация @MappedSuperclass аннотация определяет класс, описанные
 * свойства и методы которого будут применены в классах-наследниках.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see Category
 * @see Order
 * @see Photo
 * @see Product
 * @see SalePosition
 * @see User
 */
@MappedSuperclass
public abstract class Model implements Serializable {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Набор вожможных для использованния символов по-умолчанию.
     */
    private static final char[] CODE_PATTERN = "ALEXCOFFEE1234567890".toCharArray();

    /**
     * Длина возвращаемой строки по-умолчанию 6.
     */
    private static final int CODE_LENGTH = 6;

    /**
     * Строка-формат для даты по-умолчанию "EEE, d MMM yyyy, HH:mm:ss".
     */
    private static final String DATE_PATTERN = "EEE, d MMM yyyy, HH:mm:ss";

    /**
     * Название (код) часового пояса по-умолчанию "GMT+3".
     */
    private static final String TIME_ZONE = "GMT+3";

    /**
     * Уникальный код обьекта.
     * Аннотация @Id говорит о том что поле
     * является ключем для текущего объекта,
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    protected Model() {
    }

    /**
     * Возвращает описание категории.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} -
     * строка описание категории (название, URL, описание).
     */
    @Override
    public String toString() {
        return "Model{id=" + this.id + '}';
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
        return isNotNull(object) && (super.equals(object) || (getClass() == object.getClass()));
    }

    /**
     * Возвращает хеш код объекта.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа int - уникальный номер объекта.
     */
    @Override
    public abstract int hashCode();

    /**
     * Конвертирует дату типа Date в строку используя для работы входящими параметрами
     * формат даты {@value DATE_PATTERN} и часовой пояс (@value TIME_ZONE} по-умолчанию.
     *
     * @param date Значение даты типа Date для обработки.
     * @return Значение типа {@link String} - дата в виде строки.
     */
    protected String dateToString(final Date date) {
        return dateToStringWithFormat(date,
                new SimpleDateFormat(DATE_PATTERN),
                TimeZone.getTimeZone(TIME_ZONE)
        );
    }

    /**
     * Возвращает номер версии класса необходимый для десериализации
     * и сериализации.
     *
     * @return Значение типа {@link Long} - значение поля serialVersionUID.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Устанавливает номер версии класса необходимый
     * для десериализации и сериализации.
     *
     * @param id Значение параметра будет записано в поле id объекта.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Конвертирует входящий список возращает лист только для чтений.
     * Если входной параметер - лист равен null или пустой,
     * тогда метод возвращает пустой список.
     *
     * @param collection Входной объект коллекции для обработки.
     * @param <T>        Возможный тип объектов в списке.
     * @return Значение типа {@link List} - список только для чтения или пустой список.
     */
    public <T extends Model> List<T> getUnmodifiableList(final Collection<T> collection) {
        final List<T> result;
        if (isNotEmpty(collection)) {
            result = Collections.unmodifiableList(new ArrayList<>(collection));
        } else {
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * Конвертирует дату типа Date в строку используя для работы входящими
     * параметрами формат даты и часовой пояс.
     *
     * @param date       Значение даты типа Date для обработки.
     * @param dateFormat Формат даты для обработки входного параметра date.
     * @param timeZone   Часовой пояс для обработки входного параметра date.
     * @return Значение типа {@link String} - дата в виде строки.
     */
    private static String dateToStringWithFormat(
            final Date date,
            final DateFormat dateFormat,
            final TimeZone timeZone
    ) {
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }
}
