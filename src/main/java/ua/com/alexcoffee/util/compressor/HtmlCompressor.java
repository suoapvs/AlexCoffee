package ua.com.alexcoffee.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

/**
 * The class implements a set of methods for compressing HTML data.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class HtmlCompressor extends AbstractCompressor implements Compressor {

    /**
     * Remove intertag spaces.
     */
    private final static boolean REMOVED_INTERTAG_SPACES = true;

    /**
     * The instance of the Compressor class.
     */
    private final Compressor compressor;

    /**
     * Constructor.
     */
    public HtmlCompressor() {
        compressor = createCompressor();
    }

    /**
     * Returns a compressor object
     * of the HtmlCompressor class.
     *
     * @return The HTML compressor instance.
     */
    Compressor getCompressor() {
        return this.compressor;
    }

    /**
     * Creates and returns a compressor object
     * of the HtmlCompressor class.
     *
     * @return The HTML compressor instance.
     */
    private Compressor createCompressor() {
        final com.googlecode.htmlcompressor.compressor.HtmlCompressor compressor =
                new com.googlecode.htmlcompressor.compressor.HtmlCompressor();
        compressor.setRemoveIntertagSpaces(REMOVED_INTERTAG_SPACES);
        return compressor;
    }
}
