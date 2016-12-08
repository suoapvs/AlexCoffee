package ua.com.alexcoffee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает сущность "Категория товаров", наследует класс {@link Model}.
 * Категория описывает набор товаров, которые имеют общие характеристики.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "categories") указывает на таблицу "categories", в которой будут храниться объекты.
 *
 * @author Yurii Salimov
 * @see Product
 * @see Photo
 */
@Entity
@Table(name = "categories")
public class Category extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название категории. Значение поля сохраняется в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * URL категории. Значение поля сохраняется в колонке "url". Не может быть null.
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * Описание категории. Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description;

    /**
     * Изображение категории.
     * Значение поля (id объекта photo) сохраняется в колонке "photo_id".
     * Между объектами классов {@link Category}
     * и {@link Photo} связь один-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта photo до первого доступа нему, при первом доступе к текущему объекту.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    /**
     * Список товаров, которые относятся к данной категории.
     * К текущей категории можно добраться через поле "category"
     * в объекте класса {@link Category}.
     * Выборка объектов products при первом доступе к нему.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    /**
     * Конструктр без параметров.
     */
    public Category() {
        this("", "", "", null);
    }

    /**
     * Конструктор для инициализации основных переменных категории.
     *
     * @param title       Название категории.
     * @param url         URL категории.
     * @param description Описание категории.
     * @param photo       Изображение категории.
     */
    public Category(String title, String url, String description, Photo photo) {
        super();
        this.title = title;
        this.url = url;
        this.description = description;
        this.photo = photo;
    }

    /**
     * Возвращает описание категории.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание категории (название, URL, описание).
     */
    @Override
    public String toString() {
        return "Title: " + this.title + "\nUrl: " + this.url + "\nDescription: " + this.description;
    }

    /**
     * Генерирует строку для конечного сравнения категорий в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     *
     * @return Значение типа {@link String} - название + URL категории.
     */
    @Override
    public String toEquals() {
        if (this.title.isEmpty() || this.url.isEmpty()) {
            return super.toString();
        } else {
            return getTitle() + getUrl();
        }
    }

    /**
     * Инициализация полей категории.
     *
     * @param title       Название категории.
     * @param url         URL категории.
     * @param description Описание категории.
     * @param photo       Изображение категории.
     */
    public void initialize(String title, String url, String description, Photo photo) {
        setTitle(title);
        setUrl(url);
        setDescription(description);
        setPhoto(photo);
    }

    /**
     * Добавляет товар в список текущей категории.
     *
     * @param product Товар, который будет добавлен в текущую категорию.
     */
    public void addProduct(Product product) {
        this.products.add(product);
    }

    /**
     * Добавляет список товаров в список текущей категории.
     *
     * @param products Список товаров, которые будут добавлены в текущую категорию.
     */
    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    /**
     * Удаляет товар из списка текущей категории.
     *
     * @param product Товар, который будет удален из данной категории.
     */
    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    /**
     * Удаляет список товаров из списка текущей категории.
     *
     * @param products Список товаров, которые будут удалены из текущей категории.
     */
    public void removeProducts(List<Product> products) {
        this.products.removeAll(products);
    }

    /**
     * Очищает список товаров products.
     */
    public void clearProducts() {
        this.products.clear();
    }

    /**
     * Конвертирует список товаров products данной категории
     * в список только для чтений и возвращает его.
     *
     * @return Объект типа {@link List} - список товаров только для чтения или пустой список.
     */
    public List<Product> getProducts() {
        return getUnmodifiableList(this.products);
    }

    /**
     * Устанавливает список товаров products,
     * которые будут относиться к данной категории.
     *
     * @param products Список товаров .
     */
    public void setProducts(List<Product> products) {
        this.products = products;
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
    public void setTitle(String title) {
        this.title = title == null ? "" : title;
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
    public void setUrl(String url) {
        this.url = url == null ? "" : url;
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
    public void setDescription(String description) {
        this.description = description == null ? "" : description;
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
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
