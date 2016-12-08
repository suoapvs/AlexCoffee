package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.model.User;

import java.util.List;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link User}. Расширяет интерфейс {@link MainService}.
 *
 * @author Yurii Salimov
 * @see User
 * @see MainService
 * @see ua.com.alexcoffee.service.impl.UserServiceImpl
 */
public interface UserService extends MainService<User> {
    /**
     * Возвращает пользователя, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с именем name.
     */
    User getByName(String name);

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра.
     *
     * @param username Логин пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с логином username.
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
     * Возвращает список персонала сайта.
     *
     * @return Объект типа {@link List} - список персонала.
     */
    List<User> getPersonnel();

    /**
     * Возвращает авторизированого пользователя.
     *
     * @return Объект класса {@link User} - авторизированый пользователь.
     */
    User getAuthenticatedUser();

    /**
     * Удаляет пользователя, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     */
    void removeByName(String name);

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     *
     * @param role Роль пользователя для удаления.
     */
    void removeByRole(Role role);

    /**
     * Удаляет список персонала сайта.
     */
    void removePersonnel();
}
