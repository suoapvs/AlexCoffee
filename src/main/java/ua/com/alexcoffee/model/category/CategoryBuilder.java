package ua.com.alexcoffee.model.category;

import com.googlecode.htmlcompressor.compressor.Compressor;
import ua.com.alexcoffee.model.model.Builder;
import ua.com.alexcoffee.model.model.ModelBuilder;
import ua.com.alexcoffee.model.photo.Photo;
import ua.com.alexcoffee.model.product.Product;
import ua.com.alexcoffee.util.compressor.HtmlCompressor;
import ua.com.alexcoffee.util.translator.Translator;

import java.util.Collection;
import java.util.HashSet;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for building an objects of the {@link Category} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Category
 */
public final class CategoryBuilder extends ModelBuilder<Category, CategoryBuilder> {

    private String title;

    private String url;

    private String description;

    private Photo photo;

    private Collection<Product> products;

    /**
     * Constructor.
     */
    CategoryBuilder() {
        this.products = new HashSet<>();
    }

    /**
     * Builds and returns a new category.
     *
     * @return The new category.
     * @see Category
     */
    @Override
    public Category build() {
        final Category category = new Category();
        super.build(category);
        category.setTitle(getTitle());
        category.setUrl(getUrl());
        category.setDescription(getDescription());
        category.setPhoto(getPhoto());
        category.setProducts(getProducts());
        return category;
    }

    public CategoryBuilder addTitle(final String title) {
        this.title = title;
        return this;
    }

    public CategoryBuilder addUrl(final String url) {
        this.url = url;
        return this;
    }

    public CategoryBuilder addDescription(final String description) {
        this.description = description;
        return this;
    }

    public CategoryBuilder addPhoto(final Photo photo) {
        this.photo = photo;
        return this;
    }

    public CategoryBuilder addProducts(final Collection<Product> products) {
        if (isNotEmpty(products)) {
            products.forEach(this::addProduct);
        }
        return this;
    }

    public CategoryBuilder addProduct(final Product product) {
        if (isNotNull(product)) {
            this.products.add(product);
        }
        return this;
    }

    public CategoryBuilder clearProducts() {
        this.products.clear();
        return this;
    }

    private String getTitle() {
        return isNotEmpty(this.title) ? this.title : "";
    }

    protected String getUrl() {
        final String url;
        if (isNotEmpty(this.url)) {
            url = this.url;
        } else {
            url = generateUrl();
        }
        return url;
    }

    private String generateUrl() {
        final String url;
        final String title = getTitle();
        if (isNotEmpty(title)) {
            url = Translator.fromCyrillicToLatin(title) + "_" + generateRandomString();
        } else {
            url = "";
        }
        return url;
    }

    private String getDescription() {
        final String description;
        if (isNotEmpty(this.description)) {
            final Compressor compressor = new HtmlCompressor();
            description  = compressor.compress(this.description);
        } else {
            description = "";
        }
        return description;
    }

    private Photo getPhoto() {
        final Photo photo;
        if (isNotNull(this.photo)) {
            photo = this.photo;
        } else {
            final Builder<Photo> photoBuilder = Photo.getBuilder();
            photo = photoBuilder.build();
        }
        return photo;
    }

    private Collection<Product> getProducts() {
        return this.products;
    }
}
