package ua.com.alexcoffee.util.translator;

/**
 * The interface describes a set of methods for translate to Latin.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface ToLatin {

    /**
     * Translates value from cyrillic to latin.
     * If value is blank then returns null.
     *
     * @return The translated string or null.
     */
    String fromCyrillic();
}
