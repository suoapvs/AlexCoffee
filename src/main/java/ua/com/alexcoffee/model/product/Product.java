package ua.com.alexcoffee.model.product;

import ua.com.alexcoffee.model.category.Category;
import ua.com.alexcoffee.model.model.Model;
import ua.com.alexcoffee.model.photo.Photo;
import ua.com.alexcoffee.model.position.SalePosition;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс описывает сущность "Товар", наследует класс {@link Model}.
 * Аннотация @Entity говорит о том что объекты этого класса будет
 * обрабатываться hibernate.
 * Аннотация @Table(name = "products") указывает на таблицу "products",
 * в которой будут храниться объекты.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see Category
 * @see Photo
 * @see SalePosition
 */
@Entity
@Table(name = "products")
public final class Product extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Артикль товара.
     * Значение поля сохраняется в колонке "article".
     * Не может быть null.
     */
    @Column(
            name = "article",
            nullable = false
    )
    private int article = 0;

    /**
     * Название товара.
     * Значение поля сохраняется в колонке "title".
     * Не может быть null.
     */
    @Column(
            name = "title",
            nullable = false
    )
    private String title = "";

    /**
     * URL товара.
     * Значение поля сохраняется в колонке "url".
     * Не может быть null.
     */
    @Column(
            name = "url",
            nullable = false
    )
    private String url = "";

    /**
     * Параметры товара.
     * Значение поля сохраняется в колонке "parameters".
     */
    @Column(name = "parameters")
    private String parameters = "";

    /**
     * Описание товара.
     * Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description = "";

    /**
     * Категория товара.
     * Значение поля (id объекта category) сохраняется в колонке "category_id".
     * Не может быть null. Между объектами классов {@link Product} и {@link Category}
     * связь многие-к-одному, а именно каждая много заказов могут иметь одинаковый статус
     * выполнения. Выборка объекта category до первого доступа нему, при первом доступе к текущему объекту.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private Category category;

    /**
     * Изображение товара.
     * Значение поля (id объекта photo) сохраняется в колонке "photo_id".
     * Между объектами классов {@link Product} и {@link Photo} связь один-к-одному,
     * а именно каждая запись в одной таблице напрямую связана с отдельной записью в
     * другой таблице. Выборка объекта photo до первого доступа нему, при первом доступе
     * к текущему объекту. Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "photo_id",
            referencedColumnName = "id"
    )
    private Photo photo;

    /**
     * Цена товара. Значение поля сохраняется в колонке "description". Не может быть null.
     */
    @Column(
            name = "price",
            nullable = false
    )
    private double price = 0;

    /**
     * Изображение товара.
     * К текущему товару можно добраться через поле "product" в объекте
     * класса {@link SalePosition}. Выборка объекта salePosition при первом
     * доступе к нему. Сущность salePosition автоматически удаляется
     * при удалении текущей.
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "product",
            cascade = CascadeType.REMOVE
    )
    private List<SalePosition> salePositions = new ArrayList<>();

    /**
     * Возвращает описание товара.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание товара
     * (название, параметры, описание, название категории, цена).
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(this.title)
                .append("\nParameters: ")
                .append(this.parameters)
                .append("\nDescription: ")
                .append(this.description)
                .append("\nPrice = ")
                .append(this.price)
                .append(" UAH");
        if (this.category != null) {
            sb.append("\nCategory: ")
                    .append(this.category.getTitle());
        }
        return sb.toString();
    }

    /**
     * Сравнивает текущий объект с объектом переданым как параметр.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @param object объект для сравнения с текущим объектом.
     * @return Значение типа boolean - результат сравнения текущего объекта
     * с переданным объектом.
     */
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Product product = (Product) object;
            result = this.article != product.article &&
                    (this.price == product.price) &&
                    this.title.equals(product.title) &&
                    this.url.equals(product.url) &&
                    this.parameters.equals(product.parameters) &&
                    this.description.equals(product.description);
        }
        return result;
    }

    /**
     * Возвращает хеш код объекта.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа int - уникальный номер объекта.
     */
    @Override
    public int hashCode() {
        int result = this.article;
        result = 31 * result + this.title.hashCode();
        result = 31 * result + this.url.hashCode();
        result = 31 * result + this.parameters.hashCode();
        result = 31 * result + this.description.hashCode();
        long temp = Double.doubleToLongBits(this.price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Возвращает артикль товара.
     *
     * @return Значение типа int - артикль товара.
     */
    public int getArticle() {
        return this.article;
    }

    /**
     * Устанавливает артикль товара.
     *
     * @param article Артикль товара.
     */
    public void setArticle(final int article) {
        this.article = article;
    }

    /**
     * Возвращает название товара.
     *
     * @return Значение типа {@link String} - название товара.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Устанавливает название товара.
     *
     * @param title Название товара.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Возвращает URL товара.
     *
     * @return Значение типа {@link String} - URL товара.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Устанавливает URL товара.
     *
     * @param url URL товара.
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Возвращает параметры товара.
     *
     * @return Значение типа {@link String} - параметры товара.
     */
    public String getParameters() {
        return this.parameters;
    }

    /**
     * Устанавливает параметры товара.
     *
     * @param parameters Параметры товара.
     */
    public void setParameters(final String parameters) {
        this.parameters = parameters;
    }

    /**
     * Возвращает описание товара.
     *
     * @return Значение типа {@link String} - описание товара.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Устанавливает описание товара.
     *
     * @param description Описание товара.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Возвращает изображение товара.
     *
     * @return Объект класса {@link Photo} - изображение товара.
     */
    public Photo getPhoto() {
        return this.photo;
    }

    /**
     * Устанавливает изображение товара.
     *
     * @param photo Изображене товара.
     */
    public void setPhoto(final Photo photo) {
        this.photo = photo;
    }

    /**
     * Возвращает категорию товара.
     *
     * @return Объект класса {@link Category} - категория товара.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Устанавливает категорию товара.
     *
     * @param category Категорию товара.
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

    /**
     * Возвращает цену товара.
     *
     * @return Значение типа double - цена товара.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Устанавливает цену товара.
     *
     * @param price Цена товара.
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * Возвращает список торговых позиций, для которых пренадлежит текущий товара.
     *
     * @return Объект класса {@link SalePosition} - торговая позиция.
     */
    public Collection<SalePosition> getSalePositions() {
        return this.salePositions;
    }

    /**
     * Устанавливает список торговых позиций, для которых пренадлежит текущий товара.
     *
     * @param positions Торговые позиции.
     */
    public void setSalePositions(final Collection<SalePosition> positions) {
        this.salePositions = new ArrayList<>(positions);
    }

    public static ProductBuilder getBuilder() {
        return new ProductBuilder();
    }
}