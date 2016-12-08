package ua.com.alexcoffee.exception;

/**
 * Исключение генерируется, если данные не найдены в базе данных.
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
    public BadRequestException(String message) {
        super(message);
    }
}
