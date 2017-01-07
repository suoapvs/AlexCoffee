package ua.com.alexcoffee.exception;

/**
 * Исключение генерируется,
 * если данные в базе данных дублируются.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.2
 */
public final class DuplicateException
        extends RuntimeException {
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
    public DuplicateException(final String message) {
        super(message);
    }
}
