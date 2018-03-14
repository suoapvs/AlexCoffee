package ua.com.alexcoffee.model.user;

/**
 * Перечесление вожможных ролей пользователей.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 */
public enum UserRole {
    /**
     * Роль для клиента сайта.
     */
    CLIENT,

    /**
     * Роль для администратора сайта.
     */
    ADMIN,

    /**
     * Роль для менеджера сайта.
     */
    MANAGER;

    public String getDescription() {
        return toString();
    }
}
