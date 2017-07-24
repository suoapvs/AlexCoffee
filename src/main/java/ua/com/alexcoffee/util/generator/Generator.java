package ua.com.alexcoffee.util.generator;

/**
 * The interface describes a set of methods for generating Object.
 *
 * @param <T> extends Object.
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface Generator<T> {

    /**
     * Generates object.
     *
     * @return The generated object.
     */
    T generate();
}
