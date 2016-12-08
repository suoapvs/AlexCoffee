package ua.com.alexcoffee.exception;

/**
 * Исключение генерируется, если вводимая информация оо объекте {@link ua.com.alexcoffee.model.Model} не верна,
 * равна запрещенному значению и т.д.
 */
public class WrongInformationException extends RuntimeException {
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
    public WrongInformationException(String message) {
        super(message);
    }
}
