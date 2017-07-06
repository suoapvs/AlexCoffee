package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.model.user.UserRole;

import java.util.Collection;
import java.util.List;

/**
 * Репозиторий для объектов класса {@link User}, предоставляющий
 * набор методов JPA для работы с БД.
 * Наследует интерфейс {@link MainRepository}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainRepository
 * @see User
 */
public interface UserRepository extends MainRepository<User> {
    /**
     * Возвращает пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с именем name.
     */
    User findByName(String name);

    /**
     * Возвращает пользователя из базы даных, у которого совпадает уникальный
     * логин с значением входящего параметра.
     *
     * @param username Логин пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с логином username.
     */
    User findByUsername(String username);

    /**
     * Возвращает пользователя из базы даных, у которого совпадает роль с значением
     * входящего параметра.
     *
     * @param role Роль пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с ролью role.
     */
    User findByRole(UserRole role);

    /**
     * Возвращает всех пользователей из базы данных, у которых
     * совпадают роли с значением входящего параметра.
     *
     * @param role Роль пользователей для возврата.
     * @return Объект типа {@link List} - список пользователей,
     * которые имеют роль role.
     */
    Collection<User> findAllByRole(UserRole role);

    /**
     * Удаляет всех пользователей из базы данных, у которых
     * совпадают роли с значением входящего параметра.
     *
     * @param role Роль пользователей для удаления.
     */
    void deleteAllByRole(UserRole role);

    /**
     * Удаляет пользователя из базы даных, у которого совпадает имя с значением
     * входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     */
    void deleteByName(String name);
}
