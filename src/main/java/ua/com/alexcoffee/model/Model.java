package ua.com.alexcoffee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
 * @see Role
 * @see SalePosition
 * @see Status
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
    public abstract int hashCode() ;

    /**
     * Возвращает рандомную строку из набор символов и длинны по-умолчанию.
     *
     * @return Значение типа {@link String} - рандомная строка из набора
     * символов CODE_PATTERN длиной {@value CODE_LENGTH}.
     */
    String createRandomString() {
        return createRandomString(CODE_PATTERN, CODE_LENGTH);
    }

    /**
     * Возвращает рандомную строку используя набор символов pattern длиной length.
     *
     * @param pattern Набор вожможных для использованния символов.
     * @param length  Длина возвращаемой строки.
     * @return Значение типа {@link String} - рандомная строка из набора символов
     * pattern длиной length.
     */
    String createRandomString(
            final char[] pattern,
            final int length
    ) {
        final StringBuilder sb = new StringBuilder();
        final Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(pattern[random.nextInt(pattern.length)]);
        }
        return sb.toString();
    }

    /**
     * Конвертирует дату типа Date в строку используя для работы входящими параметрами
     * формат даты {@value DATE_PATTERN} и часовой пояс (@value TIME_ZONE} по-умолчанию.
     *
     * @param date Значение даты типа Date для обработки.
     * @return Значение типа {@link String} - дата в виде строки.
     */
    String dateToString(final Date date) {
        return dateToStringWithFormat(date,
                new SimpleDateFormat(DATE_PATTERN),
                TimeZone.getTimeZone(TIME_ZONE)
        );
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
     * @param list Входной объект коллекции для обработки.
     * @param <T>  Возможный тип объектов в списке.
     * @return Значение типа {@link List} - список только для чтения или пустой список.
     */
    public <T extends Model> List<T> getUnmodifiableList(final List<T> list) {
        final List<T> result;
        if ((list != null) && !list.isEmpty()) {
            result = Collections.unmodifiableList(list);
        } else {
            result = new ArrayList<>();
        }
        return result;
    }
}
