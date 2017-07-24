package ua.com.alexcoffee.model.category;

import ua.com.alexcoffee.model.model.Model;
import ua.com.alexcoffee.model.photo.Photo;
import ua.com.alexcoffee.model.product.Product;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * Класс описывает сущность "Категория товаров", наследует класс {@link Model}.
 * Категория описывает набор товаров, которые имеют общие характеристики.
 * Аннотация @Entity говорит о том что объекты этого класса будет
 * обрабатываться hibernate.
 * Аннотация @Table(name = "categories") указывает на таблицу "categories",
 * в которой будут храниться объекты.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see Product
 * @see Photo
 */
@Entity
@Table(name = "categories")
public final class Category extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название категории. Значение поля сохраняется
     * в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    private String title = "";

    /**
     * URL категории. Значение поля сохраняется
     * в колонке "url". Не может быть null.
     */
    @Column(name = "url", nullable = false)
    private String url = "";

    /**
     * Описание категории.
     * Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description = "";

    /**
     * Изображение категории. Значение поля (id объекта photo) сохраняется
     * в колонке "photo_id". Между объектами классов {@link Category} и {@link Photo}
     * связь один-к-одному, а именно каждая запись в одной таблице напрямую связана
     * с отдельной записью в другой таблице. Выборка объекта photo до первого доступа нему,
     * при первом доступе к текущему объекту. Сущности связаны полностью каскадным обновлением
     * записей в базе данных.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    /**
     * Список товаров, которые относятся к данной категории. К текущей категории
     * можно добраться через поле "category" в объекте класса {@link Category}.
     * Выборка объектов products при первом доступе к нему.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    private Collection<Product> products = new HashSet<>();

    protected Category() {
    }

    /**
     * Возвращает описание категории.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} -
     * строка описание категории (название, URL, описание).
     */
    @Override
    public String toString() {
        return "Category{" + super.toString() +
                ", title: " + this.title +
                ", url: " + this.url +
                ", description: " + this.description +
                '}';
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
            final Category other = (Category) object;
            result = this.title.equalsIgnoreCase(other.title) &&
                    this.url.equalsIgnoreCase(other.url);
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
        int result = this.title.hashCode();
        result = 31 * result + this.url.hashCode();
        result = 31 * result + this.description.hashCode();
        return result;
    }

    /**
     * Добавляет товар в список текущей категории.
     *
     * @param product Товар, который будет добавлен в текущую категорию.
     */
    public void addProduct(final Product product) {
        if (isNotNull(product)) {
            this.products.add(product);
        }
    }

    /**
     * Добавляет список товаров в список текущей категории.
     *
     * @param products Список товаров, которые будут добавлены
     *                 в текущую категорию.
     */
    public void addProducts(final Collection<Product> products) {
        if (isNotEmpty(products)) {
            this.products.addAll(products);
        }
    }

    /**
     * Удаляет товар из списка текущей категории.
     *
     * @param product Товар, который будет удален из данной категории.
     */
    public void removeProduct(final Product product) {
        if (isNotNull(product)) {
            this.products.remove(product);
        }
    }

    /**
     * Удаляет список товаров из списка текущей категории.
     *
     * @param products Список товаров, которые будут удалены из текущей категории.
     */
    public void removeProducts(final Collection<Product> products) {
        if (isNotEmpty(products)) {
            this.products.removeAll(products);
        }
    }

    /**
     * Очищает список товаров products.
     */
    public void clearProducts() {
        this.products.clear();
    }

    /**
     * Конвертирует список товаров products данной категории  в список только
     * для чтений и возвращает его.
     *
     * @return Объект типа {@link List} - список товаров только для чтения
     * или пустой список.
     */
    public Collection<Product> getProducts() {
        return getUnmodifiableList(this.products);
    }

    /**
     * Устанавливает список товаров products, которые будут относиться к данной категории.
     *
     * @param products Список товаров .
     */
    public void setProducts(final Collection<Product> products) {
        clearProducts();
        addProducts(products);
    }

    /**
     * Возвращает название категории.
     *
     * @return Значение типа {@link String} - название категории.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Устанавливает название категории.
     *
     * @param title Название категории.
     */
    public void setTitle(final String title) {
        this.title = isNotEmpty(title) ? title : "";
    }

    /**
     * Возвращает URL категории.
     *
     * @return Значение типа {@link String} - URL категории.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Устанавливает URL категории.
     *
     * @param url URL категории.
     */
    public void setUrl(final String url) {
        this.url = isNotEmpty(url) ? url : "";
    }

    /**
     * Возвращает описание категории.
     *
     * @return Значение типа {@link String} - описание категории.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Устанавливает описание категории.
     *
     * @param description Описание категории.
     */
    public void setDescription(final String description) {
        this.description = isNotEmpty(description) ? description : "";
    }

    /**
     * Возвращает изображение категории.
     *
     * @return Объект класса {@link Photo} - изображение категории.
     */
    public Photo getPhoto() {
        return this.photo;
    }

    /**
     * Устанавливает изображение категории.
     *
     * @param photo Изображение категории.
     */
    public void setPhoto(final Photo photo) {
        this.photo = photo;
    }

    public static CategoryBuilder getBuilder() {
        return new CategoryBuilder();
    }
}
