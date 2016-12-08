package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.model.User;

import java.util.List;

/**
 * Репозиторий для объектов класса {@link User}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Yurii Salimov
 * @see MainRepository
 * @see User
 */
public interface UserRepository extends MainRepository<User, Long> {
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
     * Возвращает пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     *
     * @param role Роль пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с ролью role.
     */
    User findByRole(Role role);

    /**
     * Возвращает всех пользователей из базы данных, у которых
     * совпадают роли с значением входящего параметра.
     *
     * @param role Роль пользователей для возврата.
     * @return Объект типа {@link List} - список пользователей, которые имеют роль role.
     */
    List<User> findAllByRole(Role role);

    /**
     * Удаляет всех пользователей из базы данных, у которых
     * совпадают роли с значением входящего параметра.
     *
     * @param role Роль пользователей для удаления.
     */
    void deleteAllByRole(Role role);

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     */
    void deleteByName(String name);
}
