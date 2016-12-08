package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.model.User;

import java.util.List;

/**
 * Интерфейс описывает набор методов для работы объектов класса {@link User} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 *
 * @author Yurii Salimov
 * @see DataDAO
 * @see ua.com.alexcoffee.dao.impl.DataDAOImpl
 * @see User
 * @see Role
 */
public interface UserDAO extends DataDAO<User> {
    /**
     * Возвращает пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для возврата.
     * @return Объект класса {@link User} - пользователь.
     */
    User getByName(String name);

    /**
     * Возвращает пользователя из базы даных, у которого совпадает уникальный
     * логин с значением входящего параметра.
     *
     * @param username Логин пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с уникальным логином.
     */
    User getByUsername(String username);

    /**
     * Возвращает главного администратора сайта.
     *
     * @return Объект класса {@link User} - главный администратор.
     */
    User getMainAdministrator();

    /**
     * Возвращает список всех администраторов сайта.
     *
     * @return Объект типа {@link List} - список администраторов.
     */
    List<User> getAdministrators();

    /**
     * Возвращает список всех менеджеров сайта.
     *
     * @return Объект типа {@link List} - список менеджеров.
     */
    List<User> getManagers();

    /**
     * Возвращает список всех клиентов сайта.
     *
     * @return Объект типа {@link List} - список клиентов.
     */
    List<User> getClients();

    /**
     * Возвращает авторизированого пользователя.
     *
     * @return Объект класса {@link User} - авторизированый пользователь.
     */
    User getAuthenticatedUser();

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     */
    void remove(String name);

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     *
     * @param role Роль пользователя для удаления.
     */
    void remove(Role role);
}
