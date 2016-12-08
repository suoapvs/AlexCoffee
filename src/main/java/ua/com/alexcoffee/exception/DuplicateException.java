package ua.com.alexcoffee.exception;

/**
 * Исключение генерируется, если данные в базе данных дублируются.
 */
public class DuplicateException extends RuntimeException {
    /**
     * Конструктр без параметров.
     */
    public DuplicateException() {
        super();
    }

    /**
     * Конструкто с параметром.
     *
     * @param message Сообщение исключения.
     */
    public DuplicateException(String message) {
        super(message);
    }
}
