package ua.com.alexcoffee.exception;

/**
 * Исключение генерируется, если данные не найдены в базе данных.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 */
public class BadRequestException extends RuntimeException {
    /**
     * Конструктр без параметров.
     */
    public BadRequestException() {
        super();
    }

    /**
     * Конструкто с параметром.
     *
     * @param message Сообщение исключения.
     */
    public BadRequestException(final String message) {
        super(message);
    }
}
