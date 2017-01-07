package ua.com.alexcoffee.model;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Класс описывает сущность "Изображение", наследует класс {@link Model}.
 * Объект изображение имеет две ссылки на файли-изображение в файлвой системе.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "photos") указывает на таблицу "photos",
 * в которой будут храниться объекты.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.2
 * @see Category
 * @see Product
 */
@Entity
@Table(name = "photos")
public final class Photo extends Model {
    /**
     * Номер версии класса необходимый
     * для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Путь к папке с изображениями в файловой системе.
     */
    public static final String PATH =
            System.getenv("CATALINA_HOME")
                    + "/webapps/ROOT/resources/img/";

    /**
     * Название изображения.
     * Значение поля сохраняется в колонке "title".
     * Не может быть null.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Строка-ссылка на малое изображения.
     * Значение поля сохраняется в колонке "photo_link_short".
     */
    @Column(name = "photo_link_short")
    private String photoLinkShort;

    /**
     * Строка-ссылка на большое изображения.
     * Значение поля сохраняется в колонке "photo_link_long".
     */
    @Column(name = "photo_link_long")
    private String photoLinkLong;

    /**
     * Товар, к которому относится данное изображение.
     * К даному объекту можно добраться
     * через поле "photo" в объекте класса {@link Product}.
     * Выборка объекта product при первом доступе к нему.
     */
    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "photo"
    )
    private Product product;

    /**
     * Категория, к которой относится данное изображение. К текущему изображению можно добраться
     * через поле "photo" в объекте класса {@link Category}.
     * Выборка объекта category при первом доступе к нему.
     */
    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "photo"
    )
    private Category category;

    /**
     * Конструктр без параметров.
     */
    public Photo() {
        this("", "", "");
    }

    /**
     * Конструктор для инициализации
     * основных переменных изображения.
     *
     * @param title          Название изображения.
     * @param photoLinkShort Строка-ссылка на малое изображения.
     * @param photoLinkLong  Строка-ссылка на большое изображения.
     */
    public Photo(
            final String title,
            final String photoLinkShort,
            final String photoLinkLong
    ) {
        super();
        this.title = title;
        this.photoLinkShort = photoLinkShort;
        this.photoLinkLong = photoLinkLong;
    }

    /**
     * Конструктор для инициализации
     * переменных изображения.
     *
     * @param title          Название изображения.
     * @param photoLinkShort Строка-ссылка на малое изображения.
     */
    public Photo(
            final String title,
            final String photoLinkShort
    ) {
        this(title, photoLinkShort, "");
    }

    /**
     * Возвращает описание изображения.
     * Переопределенный метод родительского
     * класса {@link Object}.
     *
     * @return Значение типа {@link String} -
     * строка описание изображения (название, URL,
     * строки-ссылки на малое и большое изображения).
     */
    @Override
    public String toString() {
        return "Title: " + this.title
                + "\nphoto short link: " + this.photoLinkShort
                + "\nphoto long link: " + this.photoLinkLong;
    }

    /**
     * Инициализация полей изображения.
     *
     * @param title          Название изображения.
     * @param photoLinkShort Строка-ссылка на малое изображения.
     * @param photoLinkLong  Строка-ссылка на большое изображения.
     */
    public void initialize(
            final String title,
            final String photoLinkShort,
            final String photoLinkLong
    ) {
        setTitle(title);
        setPhotoLinkShort(photoLinkShort);
        setPhotoLinkLong(photoLinkLong);
    }

    /**
     * Возвращает название изображения.
     *
     * @return Значение типа {@link String} -
     * название изображения.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Устанавливает название изображения.
     *
     * @param title Название изображения.
     */
    public void setTitle(final String title) {
        this.title = isNotBlank(title) ? title : "";
    }

    /**
     * Возвращает строку-ссылка на малое изображения.
     *
     * @return Значение типа {@link String} -
     * строка-ссылка на малое изображения.
     */
    public String getPhotoLinkShort() {
        return this.photoLinkShort;
    }

    /**
     * Устанавливает строку-ссылка на малое изображения.
     *
     * @param photoLinkShort Строка-ссылка на малое изображения.
     */
    public void setPhotoLinkShort(final String photoLinkShort) {
        this.photoLinkShort = isNotBlank(photoLinkShort) ? photoLinkShort : "";
    }

    /**
     * Возвращает строку-ссылка на большое изображения.
     *
     * @return Значение типа {@link String} -
     * строка-ссылка на большое изображения.
     */
    public String getPhotoLinkLong() {
        return this.photoLinkLong;
    }

    /**
     * Устанавливает строку-ссылка на большое изображения.
     *
     * @param photoLinkLong Строка-ссылка на большое изображения.
     */
    public void setPhotoLinkLong(final String photoLinkLong) {
        this.photoLinkLong = isNotBlank(photoLinkLong) ? photoLinkLong : "";
    }

    /**
     * Возвращает товар, к которому
     * относится данное изображение.
     *
     * @return Объект класса {@link Photo} -
     * товар, к которому относится данное
     * изображение.
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Устанавлевает товар, к которому будет
     * относиться данное изображение.
     *
     * @param product Товар, к которому будет
     *                относиться данное изображение.
     */
    public void setProduct(final Product product) {
        this.product = product;
    }

    /**
     * Возвращает категорию,
     * к которой относится данное изображение.
     *
     * @return Объект класса {@link Category} -
     * категория, к которой относится данное
     * изображение.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Устанавлевает категорию, которой
     * будет пренадлежать данное изображение.
     *
     * @param category Категория, которой будет
     *                 пренадлежать данное изображение.
     */
    public void setCategory(final Category category) {
        this.category = category;
    }
}
