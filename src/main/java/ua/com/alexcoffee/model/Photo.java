package ua.com.alexcoffee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Класс описывает сущность "Изображение", наследует класс {@link Model}.
 * Объект изображение имеет две ссылки на файли-изображение в файлвой системе.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "photos") указывает на таблицу "photos",
 * в которой будут храниться объекты.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
    public static final String PATH = System.getenv("CATALINA_HOME") +
            "/webapps/ROOT/resources/img/";

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
     * Конструктр без параметров.
     */
    public Photo() {
        this("", "", "");
    }

    /**
     * Конструктор для инициализации основных переменных изображения.
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
        setTitle(title);
        setPhotoLinkShort(photoLinkShort);
        setPhotoLinkLong(photoLinkLong);
    }

    /**
     * Конструктор для инициализации переменных изображения.
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
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} -
     * строка описание изображения (название, URL,
     * строки-ссылки на малое и большое изображения).
     */
    @Override
    public String toString() {
        return "Photo{" + super.toString() +
                ", title: " + this.title +
                ", photoLinkShort: " + this.photoLinkShort +
                ", photoLinkLong: " + this.photoLinkLong +
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
            final Photo photo = (Photo) object;
            result = this.title.equals(photo.title) &&
                    this.photoLinkShort.equals(photo.photoLinkShort) &&
                    this.photoLinkLong.equals(photo.photoLinkLong);
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
        result = 31 * result + this.photoLinkShort.hashCode();
        result = 31 * result + this.photoLinkLong.hashCode();
        return result;
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
     * @return Значение типа {@link String} - название изображения.
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
        this.title = isNotEmpty(title) ? title : "";
    }

    /**
     * Возвращает строку-ссылка на малое изображения.
     *
     * @return Значение типа {@link String} - строка-ссылка на малое изображения.
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
        this.photoLinkShort = isNotEmpty(photoLinkShort) ? photoLinkShort : "";
    }

    /**
     * Возвращает строку-ссылка на большое изображения.
     *
     * @return Значение типа {@link String} - строка-ссылка на большое изображения.
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
        this.photoLinkLong = isNotEmpty(photoLinkLong) ? photoLinkLong : "";
    }
}
