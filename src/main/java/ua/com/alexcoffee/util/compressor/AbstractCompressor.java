package ua.com.alexcoffee.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;

/**
 * The abstract class implements a set of methods for compressing data.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
abstract class AbstractCompressor implements Compressor {

    /**
     * Compresses the incoming source and returns a compressed result.
     * Compresses the source if it not null and not empty.
     * <pre>
     *     compress(null) = "" (empty string)
     *     compress("") = "" (empty string)
     *     compress(" ") = "" (empty string)
     *     compress("bob") = "bob"
     *     compress("bob") = "bob"
     * </pre>
     *
     * @param source the source to compress.
     * @return Compressed result or empty string (newer null).
     */
    @Override
    public String compress(final String source) {
        String result = "";
        if (isNotEmpty(source)) {
            final Compressor compressor = getCompressor();
            result = compressor.compress(source);
        }
        return result;
    }

    /**
     * Returns a compressor object.
     *
     * @return The compressor instance.
     */
    abstract Compressor getCompressor();
}
