package ua.com.alexcoffee.exception;

/**
 * Исключение генерируется,
 * если вводимая информация об объекте
 * {@link ua.com.alexcoffee.model.Model}
 * не верна, равна запрещенному значению
 * и т.д.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 */
public final class WrongInformationException
        extends RuntimeException {
    /**
     * Конструктр без параметров.
     */
    public WrongInformationException() {
        super();
    }

    /**
     * Конструкто с параметром.
     *
     * @param message Сообщение исключения.
     */
    public WrongInformationException(final String message) {
        super(message);
    }
}
