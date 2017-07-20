package ua.com.alexcoffee.model.model;

/**
 * The interface provides a set of methods for building objects.
 *
 * @param <T> object type.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface Builder<T> {

    /**
     * Builds and returns a new object.
     *
     * @return The new object.
     */
    T build();
}
