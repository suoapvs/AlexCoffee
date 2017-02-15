package ua.com.alexcoffee.exception;

/**
 * Исключение генерируется, если пользователь не имеет достаточных
 * прав для доступа к странице.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 */
public final class ForbiddenException extends RuntimeException {
    /**
     * Конструктр без параметров.
     */
    public ForbiddenException() {
        super();
    }

    /**
     * Конструкто с параметром.
     *
     * @param message Сообщение исключения.
     */
    public ForbiddenException(final String message) {
        super(message);
    }
}
