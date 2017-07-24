package ua.com.alexcoffee.util.translator;

/**
 * The class implements a set of methods for translate:
 * from Cyrillic to Latin, to ASCII, from ASCII.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class Translator {

    /**
     * Private constructor.
     */
    private Translator(){
    }

    /**
     * Translates value from cyrillic to latin.
     *
     * @param value the string to translate.
     * @return The translated string (newer null).
     */
    public static String fromCyrillicToLatin(final String value) {
        return new ToLatinImpl(value).fromCyrillic();
    }

    /**
     * Translates value to ASCII.
     *
     * @param value the string to translate.
     * @return The translated string (newer null).
     */
    public static String toAscii(final String value) {
        return new AsciiImpl(value).to();
    }

    /**
     * Translates value from ASCII.
     *
     * @param value the string to translate.
     * @return The translated string (newer null).
     */
    public static String fromAscii(final String value) {
        return new AsciiImpl(value).from();
    }

    /**
     * Translates value to ASCII.
     *
     * @param value the string to translate.
     * @return The translated string (newer null).
     */
    public static String toAscii(final int value) {
        return new AsciiImpl(value).to();
    }

    /**
     * Translates value from ASCII.
     *
     * @param value the string to translate.
     * @return The translated string (newer null).
     */
    public static String fromAscii(final int value) {
        return new AsciiImpl(value).from();
    }
}
