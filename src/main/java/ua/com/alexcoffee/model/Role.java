package ua.com.alexcoffee.model;

import ua.com.alexcoffee.enums.RoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает сущность "Роль пользвателей", наследует класс {@link Model}.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "roles") указывает на таблицу "roles", в которой будут храниться объекты.
 *
 * @author Yurii Salimov
 * @see RoleEnum
 * @see User
 */
@Entity
@Table(name = "roles")
public class Role extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название роли, может принимать одно из значений перечисления {@link RoleEnum}.
     * Значение поля сохраняется в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum title;

    /**
     * Описание роли. Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Список пользвателей, которые относятся к данной роли.
     * К текущей роли можно добраться через поле "role"
     * в объекте класса {@link User}.
     * Выборка объектов users при первом доступе к ним.
     * Сущности users автоматически удаляется при удалении текущей.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<User> users = new ArrayList<>();

    /**
     * Конструктр без параметров.
     */
    public Role() {
        this(null, "");
    }

    /**
     * Конструктор для инициализации основных переменных роли.
     *
     * @param title       Название роли, может принимать одно из значений перечисления {@link RoleEnum}.
     * @param description Описание роли.
     */
    public Role(RoleEnum title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    /**
     * Возвращает описание роли.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание роли (имя, описание).
     */
    @Override
    public String toString() {
        return "Title: " + this.title.name() + "\nDescription: " + this.description;
    }

    /**
     * Генерирует строку для конечного сравнения роли в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     *
     * @return Значение типа {@link String} - название роли.
     */
    public String toEquals() {
        return this.title.name();
    }

    /**
     * Добавляет пользователя в список текущей роли.
     *
     * @param user Пользователь, который имеет текущую роль.
     */
    public void addUser(User user) {
        this.users.add(user);
    }

    /**
     * Добавляет список пользователей в список пользователей users.
     *
     * @param users Список пользователей, которые будут иметь текущую роль.
     */
    public void addUsers(List<User> users) {
        this.users.addAll(users);
    }

    /**
     * Удаляет пользователя из списка текущей роли.
     *
     * @param user Пользователь, у которого будет удалена текущая роль.
     */
    public void removeUser(User user) {
        this.users.remove(user);
    }

    /**
     * Метод удаляет список пользователей из списка users.
     *
     * @param users Список пользователей,  у которых будет удалена текущая роль.
     */
    public void removeUsers(List<User> users) {
        this.users.removeAll(users);
    }

    /**
     * Очищает список пользователей текущей роли.
     */
    public void clearUsers() {
        this.users.clear();
    }

    /**
     * Конвертирует список пользователей текущей роли
     * в список только для чтений и возвращает его.
     *
     * @return Объект типа {@link List} - список пользователей только для чтения или пустой список.
     */
    public List<User> getUsers() {
        return getUnmodifiableList(this.users);
    }

    /**
     * Устанавливает список пользователей текущей роли.
     *
     * @param users Список пользователей.
     */
    public void setUsers(List<User> users) {
        this.users = users;
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
    public void setTitle(RoleEnum title) {
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
    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }
}
