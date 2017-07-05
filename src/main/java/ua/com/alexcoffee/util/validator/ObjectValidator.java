package ua.com.alexcoffee.util.validator;

import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * The class implements a set of methods for validating an incoming object.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ObjectValidator {

    /**
     * Private Constructor.
     */
    private ObjectValidator() {
    }

    /**
     * Checks if a Object is null.
     * <pre>
     *     isNull(null) = true
     *     isNull(new Object()) = false
     * </pre>
     *
     * @param object the Object to check, may be null
     * @return true if the Object is null, false otherwise.
     */
    public static boolean isNull(final Object object) {
        return (object == null);
    }

    /**
     * Checks if a Object is not null.
     * <pre>
     *     isNotNull(null) = false
     *     isNotNull(new Object()) = true
     * </pre>
     *
     * @param object the Object to check, may be null
     * @return true if the Object is not null, false otherwise.
     */
    public static boolean isNotNull(final Object object) {
        return !isNull(object);
    }

    /**
     * Checks if a Collection is null or empty.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty(new ArrayList()) = true
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new Object);
     *     isEmpty(collection) = false
     * </pre>
     *
     * @param collection the Collection to check, may be null
     * @return true if the Collection is null or empty, false otherwise.
     */
    public static boolean isEmpty(final Collection collection) {
        return isNull(collection) || collection.isEmpty();
    }

    /**
     * Checks if a Collection is not null and not empty.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty(new ArrayList()) = false
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new Object);
     *     isNotEmpty(collection) = true
     * </pre>
     *
     * @param collection the Collection to check, may be null
     * @return true if the Collection is not null and not empty,
     * false otherwise.
     */
    public static boolean isNotEmpty(final Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * Checks if a Map is null or empty.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty(new HashMap()) = true
     *
     *     Map map = new HashMap();
     *     map.put(new Object, new Object);
     *     isEmpty(map) = false
     * </pre>
     *
     * @param map the Map to check, may be null
     * @return true if the Map is is null or empty, false otherwise.
     */
    public static boolean isEmpty(final Map map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * Checks if a Map is not empty or not null.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty(new HashMap()) = false
     *
     *     Map map = new HashMap();
     *     map.put(new Object, new Object);
     *     isNotEmpty(map) = true
     * </pre>
     *
     * @param map the Map to check, may be null
     * @return true if the Map is not empty or not null,
     * false otherwise.
     */
    public static boolean isNotEmpty(final Map map) {
        return !isEmpty(map);
    }

    /**
     * Checks if a CharSequence is whitespace, empty ("") or null.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty("") = true
     *     isEmpty(" ") = true
     *     isEmpty("bob") = false
     *     isEmpty("  bob  ") = false
     * </pre>
     *
     * @param string the CharSequence to check, may be null
     * @return true if the CharSequence is null, empty or whitespace,
     * false otherwise.
     */
    public static boolean isEmpty(final String string) {
        return isBlank(string);
    }

    /**
     * Checks if a CharSequence is not empty (""), not null and not whitespace only.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty("") = false
     *     isNotEmpty(" ") = false
     *     isNotEmpty("bob") = true
     *     isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param string the CharSequence to check, may be null
     * @return true if the CharSequence is not empty and not null
     * and not whitespace, false otherwise.
     */
    public static boolean isNotEmpty(final String string) {
        return !isEmpty(string);
    }

    /**
     * Return whether the uploaded file is null or empty, that is, either no file has
     * been chosen in the multipart form or the chosen file has no content.
     *
     * @param file the incoming multipart file to check.
     * @return true if file is null or empty, false otherwise.
     */
    public static boolean isEmpty(final MultipartFile file) {
        return isNull(file) || file.isEmpty();
    }

    /**
     * Return whether the uploaded file is not null or not empty.
     *
     * @param file the incoming multipart file to check.
     * @return true if file is null or empty, false otherwise.
     */
    public static boolean isNotEmpty(final MultipartFile file) {
        return !isEmpty(file);
    }

    /**
     * Checks if a array is null or empty.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty(new Object[]{}) = true
     *     isEmpty(new Object[]{new Object()}) = false
     *     isEmpty(new Object[]{new Object(), new Object()}) = false
     * </pre>
     *
     * @param array the array to check, may be null
     * @param <T>   extends the Object class.
     * @return true if the array is null or empty, false otherwise.
     */
    public static <T> boolean isEmpty(final T[] array) {
        return isNull(array) || (array.length == 0);
    }

    /**
     * Checks if a array is not null and not empty.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty(new Object[]{}) = false
     *     isNotEmpty(new Object[]{new Object()}) = true
     *     isNotEmpty(new Object[]{new Object(), new Object()}) = true
     * </pre>
     *
     * @param array the array to check, may be null
     * @param <T>   extends the Object class.
     * @return true if the array is not null and not empty,
     * false otherwise.
     */
    public static <T> boolean isNotEmpty(final T[] array) {
        return !isEmpty(array);
    }

    /**
     * Checks if a array is null or empty.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty(new byte[]{}) = true
     *     isEmpty(new byte[]{1}) = false
     *     isEmpty(new byte[]{1, 2, 3}) = false
     * </pre>
     *
     * @param array the array to check, may be null
     * @return true if the array is null or empty, false otherwise.
     */
    public static boolean isEmpty(final byte[] array) {
        return isNull(array) || (array.length == 0);
    }

    /**
     * Checks if a array is not null and not empty.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty(new byte[]{}) = false
     *     isNotEmpty(new byte[]{1}) = true
     *     isNotEmpty(new byte[]{1, 2, 3}) = true
     * </pre>
     *
     * @param array the array to check, may be null
     * @return true if the array is not null and not empty,
     * false otherwise.
     */
    public static boolean isNotEmpty(final byte[] array) {
        return !isEmpty(array);
    }

    /**
     * Checks if a array is null or empty.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty(new short[]{}) = true
     *     isEmpty(new short[]{1}) = false
     *     isEmpty(new short[]{1, 2, 3}) = false
     * </pre>
     *
     * @param array the array to check, may be null
     * @return true if the array is null or empty, false otherwise.
     */
    public static boolean isEmpty(final short[] array) {
        return isNull(array) || (array.length == 0);
    }

    /**
     * Checks if a array is not null and not empty.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty(new short[]{}) = false
     *     isNotEmpty(new short[]{1}) = true
     *     isNotEmpty(new short[]{1, 2, 3}) = true
     * </pre>
     *
     * @param array the array to check, may be null
     * @return true if the array is not null and not empty,
     * false otherwise.
     */
    public static boolean isNotEmpty(final short[] array) {
        return !isEmpty(array);
    }

    /**
     * Checks if a array is null or empty.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty(new char[]{}) = true
     *     isEmpty(new char[]{1}) = false
     *     isEmpty(new char[]{1, 2, 3}) = false
     * </pre>
     *
     * @param array the array to check, may be null
     * @return true if the array is null or empty, false otherwise.
     */
    public static boolean isEmpty(final char[] array) {
        return isNull(array) || (array.length == 0);
    }

    /**
     * Checks if a array is not null and not empty.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty(new char[]{}) = false
     *     isNotEmpty(new char[]{1}) = true
     *     isNotEmpty(new char[]{1, 2, 3}) = true
     * </pre>
     *
     * @param array the array to check, may be null
     * @return true if the array is not null and not empty,
     * false otherwise.
     */
    public static boolean isNotEmpty(final char[] array) {
        return !isEmpty(array);
    }

    /**
     * Checks if a int array is null or empty.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty(new int[]{}) = true
     *     isEmpty(new int[]{1}) = false
     *     isEmpty(new int[]{1, 2, 3}) = false
     * </pre>
     *
     * @param array the int array to check, may be null
     * @return true if the array is null or empty, false otherwise.
     */
    public static boolean isEmpty(final int[] array) {
        return isNull(array) || (array.length == 0);
    }

    /**
     * Checks if a array is not null and not empty.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty(new int[]{}) = false
     *     isNotEmpty(new int[]{1}) = true
     *     isNotEmpty(new int[]{1, 2, 3}) = true
     * </pre>
     *
     * @param array the array to check, may be null
     * @return true if the array is not null and not empty,
     * false otherwise.
     */
    public static boolean isNotEmpty(final int[] array) {
        return !isEmpty(array);
    }

    /**
     * Checks if a long array is null or empty.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty(new long[]{}) = true
     *     isEmpty(new long[]{1}) = false
     *     isEmpty(new long[]{1, 2, 3}) = false
     * </pre>
     *
     * @param array the long array to check, may be null
     * @return true if the array is null or empty, false otherwise.
     */
    public static boolean isEmpty(final long[] array) {
        return isNull(array) || (array.length == 0);
    }

    /**
     * Checks if a long array is not null and not empty.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty(new long[]{}) = false
     *     isNotEmpty(new long[]{1}) = true
     *     isNotEmpty(new long[]{1, 2, 3}) = true
     * </pre>
     *
     * @param array the long array to check, may be null
     * @return true if the array is not null and not empty,
     * false otherwise.
     */
    public static boolean isNotEmpty(final long[] array) {
        return !isEmpty(array);
    }

    /**
     * Checks if a float array is null or empty.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty(new float[]{}) = true
     *     isEmpty(new float[]{1.0}) = false
     *     isEmpty(new float[]{1.1, 2.2, 3.3}) = false
     * </pre>
     *
     * @param array the float array to check, may be null
     * @return true if the array is null or empty, false otherwise.
     */
    public static boolean isEmpty(final float[] array) {
        return isNull(array) || (array.length == 0);
    }

    /**
     * Checks if a float array is not null and not empty.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty(new float[]{}) = false
     *     isNotEmpty(new float[]{1.0}) = true
     *     isNotEmpty(new float[]{1.1, 2.2, 3.3}) = true
     * </pre>
     *
     * @param array the float array to check, may be null
     * @return true if the array is not null and not empty,
     * false otherwise.
     */
    public static boolean isNotEmpty(final float[] array) {
        return !isEmpty(array);
    }

    /**
     * Checks if a double array is null or empty.
     * <pre>
     *     isEmpty(null) = true
     *     isEmpty(new double[]{}) = true
     *     isEmpty(new double[]{1.0}) = false
     *     isEmpty(new double[]{1.1, 2.2, 3.3}) = false
     * </pre>
     *
     * @param array the double array to check, may be null
     * @return true if the array is null or empty, false otherwise.
     */
    public static boolean isEmpty(final double[] array) {
        return isNull(array) || (array.length == 0);
    }

    /**
     * Checks if a double array is not null and not empty.
     * <pre>
     *     isNotEmpty(null) = false
     *     isNotEmpty(new double[]{}) = false
     *     isNotEmpty(new double[]{1.0}) = true
     *     isNotEmpty(new double[]{1.1, 2.2, 3.3}) = true
     * </pre>
     *
     * @param array the double array to check, may be null
     * @return true if the array is not null and not empty,
     * false otherwise.
     */
    public static boolean isNotEmpty(final double[] array) {
        return !isEmpty(array);
    }
}
