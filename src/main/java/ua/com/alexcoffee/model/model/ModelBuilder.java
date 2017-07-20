package ua.com.alexcoffee.model.model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */

import com.googlecode.htmlcompressor.compressor.Compressor;
import ua.com.alexcoffee.util.compressor.HtmlCompressor;
import ua.com.alexcoffee.util.generator.Generator;
import ua.com.alexcoffee.util.generator.StringGenerator;

/**
 * The abstract superclass implements a set of standard methods
 * for building an objects of the {@link Model} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Model
 */
public abstract class ModelBuilder<T extends Model, B extends ModelBuilder<T, B>> implements Builder<T> {

    /**
     * The unique identifier for each object.
     */
    private long id;

    /**
     * Constructor.
     */
    protected ModelBuilder() {
    }

    /**
     * Adds new identifier to a new model.
     *
     * @param id a new identifier to a new model.
     * @return the model builder.
     */
    public B addId(final long id) {
        this.id = id;
        return (B) this;
    }

    protected T build(final T model) {
        model.setId(getId());
        return model;
    }

    /**
     * Generate and returns a random string.
     *
     * @return The random string.
     */
    protected String generateRandomString() {
        final Generator<String> generator = new StringGenerator();
        return generator.generate();
    }

    /**
     * Compresses the given source and returns a compressed result.
     *
     * @param source The source to compress.
     * @return Compressed result.
     */
    protected String compress(final String source) {
        final Compressor compressor = new HtmlCompressor();
        return compressor.compress(source);
    }

    /**
     * Returns a unique identifier of a new model.
     * Returns 0 if the id is less 0.
     *
     * @return The unique identifier.
     */
    private long getId() {
        return (this.id >= 0) ? this.id : 0;
    }

}
