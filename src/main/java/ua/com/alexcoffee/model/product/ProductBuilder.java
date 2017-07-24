package ua.com.alexcoffee.model.product;

import com.googlecode.htmlcompressor.compressor.Compressor;
import ua.com.alexcoffee.model.category.Category;
import ua.com.alexcoffee.model.model.Builder;
import ua.com.alexcoffee.model.model.ModelBuilder;
import ua.com.alexcoffee.model.photo.Photo;
import ua.com.alexcoffee.util.compressor.HtmlCompressor;
import ua.com.alexcoffee.util.generator.Generator;
import ua.com.alexcoffee.util.generator.StringGenerator;
import ua.com.alexcoffee.util.translator.Translator;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ProductBuilder extends ModelBuilder<Product, ProductBuilder> {

    private static final char[] CODE_PATTERN = "1234567890".toCharArray();

    private static final int CODE_LENGTH = 5;

    private int article = 0;

    private String title = "";

    private String url = "";

    private String parameters = "";

    private String description = "";

    private Category category;

    private Photo photo;

    private double price = 0;

    ProductBuilder() {
    }

    @Override
    public Product build() {
        final Product product = new Product();
        product.setArticle(getArticle());
        product.setTitle(getTitle());
        product.setUrl(getUrl());
        product.setParameters(getParameters());
        product.setDescription(getDescription());
        product.setCategory(getCategory());
        product.setPhoto(getPhoto());
        product.setPrice(getPrice());
        return super.build(product);
    }

    public ProductBuilder addArticle(final int article) {
        this.article = article;
        return this;
    }

    public ProductBuilder addTitle(final String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder addUrl(final String url) {
        this.url = url;
        return this;
    }

    public ProductBuilder addParameters(final String parameters) {
        this.parameters = parameters;
        return this;
    }

    public ProductBuilder addDescription(final String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder addCategory(final Category category) {
        this.category = category;
        return this;
    }

    public ProductBuilder addPhoto(final Photo photo) {
        this.photo = photo;
        return this;
    }

    public ProductBuilder addPrice(final double price) {
        this.price = price;
        return this;
    }

    private int getArticle() {
        final int article;
        if (this.article > 0) {
          article = this.article;
        } else {
            final Generator<String> stringGenerator = new StringGenerator(CODE_PATTERN, CODE_LENGTH);
            final String strintArticle = stringGenerator.generate();
            article = Integer.parseInt(strintArticle);
        }
        return article;
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

    private String getParameters() {
        return prepareString(this.parameters);
    }

    private String getDescription() {
        return prepareString(this.description);
    }

    private String prepareString(final String value) {
        final String prepareValue;
        if (isNotEmpty(value)) {
            final Compressor compressor = new HtmlCompressor();
            prepareValue  = compressor.compress(value);
        } else {
            prepareValue = "";
        }
        return prepareValue;
    }

    private Category getCategory() {
        final Category category;
        if (isNotNull(this.category)) {
            category = this.category;
        } else {
            final Builder<Category> categoryBuilder = Category.getBuilder();
            category = categoryBuilder.build();
        }
        return category;
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

    private double getPrice() {
        return (this.price > 0) ? this.price : 0;
    }
}
