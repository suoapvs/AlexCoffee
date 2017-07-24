package ua.com.alexcoffee.util.translator;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods for translate
 * to ASCII and from ASCII.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class AsciiImpl implements Ascii {

    /**
     * The string to translate.
     */
    private final String value;

    /**
     * Constructor.
     *
     * @param value the string to translate.
     */
    public AsciiImpl(final String value) {
        this.value = value;
    }

    /**
     * Constructor.
     *
     * @param value the string to translate.
     */
    public AsciiImpl(final int value) {
        this(Integer.toString(value));
    }

    /**
     * Translates value to ASCII.
     * If value is blank then returns empty string.
     *
     * @return The translated string or empty string.
     */
    @Override
    public String to() {
        final String result;
        if (isNotEmpty(this.value)) {
            result = convertToAscii();
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Translates value from ASCII.
     * If value is blank then returns empty string.
     *
     * @return The translated string or empty string.
     */
    @Override
    public String from() {
        String result;
        if (isNotEmpty(this.value)) {
            try {
                result = convertFromAscii();
            } catch (NumberFormatException ex) {
                result = "";
            }
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Translates this value to ASCII.
     *
     * @return The translated string.
     */
    private String convertToAscii() {
        final char[] charArray = getValueChars();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            sb.append(charToInt(charArray[i]));
            if (i != charArray.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * Translates this value from ASCII.
     *
     * @return The translated string or empty string.
     */
    private String convertFromAscii() {
        final StringBuilder sb = new StringBuilder();
        for (String number : this.value.split(",")) {
            sb.append(numberToChar(number));
        }
        return sb.toString();
    }

    /**
     * Returns a string to translate.
     *
     * @return The string to translate.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Converts this value to a new character array and returns it.
     *
     * @return The char array.
     */
    private char[] getValueChars() {
        return this.value.toCharArray();
    }

    /**
     * Parses the incoming char to integer.
     *
     * @param character the char to parse
     * @return the integer.
     */
    private int charToInt(final char character) {
        return (int) character;
    }

    /**
     * Parses the incoming number to char.
     *
     * @param number the string number to parse (newer null).
     * @return The char.
     */
    private char numberToChar(final String number) {
        final int integer = Integer.parseInt(number);
        return intToChar(integer);
    }

    /**
     * Parses the incoming number to char.
     *
     * @param number the integer number to parse.
     * @return The char.
     */
    private char intToChar(final int number) {
        return (char) number;
    }
}
