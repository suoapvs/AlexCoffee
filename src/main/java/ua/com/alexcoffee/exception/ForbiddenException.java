package ua.com.alexcoffee.exception;

/**
 * Исключение генерируется, если пользователь не имеет достаточных прав для доступа к странице.
 */
public class ForbiddenException extends RuntimeException {
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
    public ForbiddenException(String message) {
        super(message);
    }
}
