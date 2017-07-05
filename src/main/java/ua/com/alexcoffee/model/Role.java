package ua.com.alexcoffee.model;

import ua.com.alexcoffee.enums.RoleEnum;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Класс описывает сущность "Роль пользователей", наследует класс {@link Model}.
 * Аннотация @Entity говорит о том что объекты этого класса будет
 * обрабатываться hibernate. Аннотация @Table(name = "roles") указывает
 * на таблицу "roles", в которой будут храниться объекты.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see RoleEnum
 * @see User
 */
@Entity
@Table(name = "roles")
public final class Role extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название роли, может принимать одно из значений перечисления {@link RoleEnum}.
     * Значение поля сохраняется в колонке "title".
     * Не может быть null.
     */
    @Column(
            name = "title",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private RoleEnum title;

    /**
     * Описание роли.
     * Значение поля сохраняется в колонке "description".
     * Не может быть null.
     */
    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    /**
     * Конструктр без параметров.
     */
    public Role() {
        this(null, "");
    }

    /**
     * Конструктор для инициализации основных переменных роли.
     *
     * @param title       Название роли, может принимать одно
     *                    из значений перечисления {@link RoleEnum}.
     * @param description Описание роли.
     */
    public Role(
            final RoleEnum title,
            final String description
    ) {
        super();
        setTitle(title);
        setDescription(description);
    }

    /**
     * Возвращает описание роли.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание роли (имя, описание).
     */
    @Override
    public String toString() {
        return "Role{" + super.toString() +
                ", Title: " + this.title.name() +
                ", Description: " + this.description +
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
            final Role role = (Role) object;
            result = this.title.equals(role.title) &&
                    this.description.equalsIgnoreCase(role.description);
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
        result = 31 * result + this.description.hashCode();
        return result;
    }

    /**
     * Возвращает название роли.
     *
     * @return Объект перечисление {@link RoleEnum} - название роли.
     */
    public RoleEnum getTitle() {
        return this.title;
    }

    /**
     * Устанавливает название роли, которое может принимать одно из
     * значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    public void setTitle(final RoleEnum title) {
        this.title = title;
    }

    /**
     * Возвращает описание роли.
     *
     * @return Значение типа {@link String} - описание роли.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Устанавливает описание роли.
     *
     * @param description Описание роли.
     */
    public void setDescription(final String description) {
        this.description = isNotEmpty(description) ? description : "";
    }
}
