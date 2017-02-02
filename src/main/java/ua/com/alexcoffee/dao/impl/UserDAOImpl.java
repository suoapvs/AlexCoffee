package ua.com.alexcoffee.dao.impl;

import org.springframework.context.annotation.ComponentScan;
import ua.com.alexcoffee.dao.interfaces.UserDAO;
import ua.com.alexcoffee.repository.RoleRepository;
import ua.com.alexcoffee.repository.UserRepository;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Класс реализует методы доступа объектов
 * класса {@link User} в базе данных интерфейса
 * {@link UserDAO}, наследует родительский
 * абстрактній класс {@link DataDAOImpl},
 * в котором реализованы основные методы.
 * Для работы методы используют
 * объект-репозиторий интерфейса
 * {@link UserRepository}. Класс помечена
 * аннотацией @Repository (наследник Spring'овой
 * аннотации @Component). Это позволяет Spring
 * автоматически зарегестрировать
 * компонент в своём контексте для
 * последующей инъекции.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see DataDAOImpl
 * @see UserDAO
 * @see User
 * @see UserRepository
 */
@Repository
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class UserDAOImpl
        extends DataDAOImpl<User>
        implements UserDAO {

    /**
     * ID роли клиента в базе данных.
     */
    private final static Long CLIENT_ROLE_ID = 1L;

    /**
     * ID роли адмиистратора в базе данных.
     */
    private final static Long ADMIN_ROLE_ID = 2L;

    /**
     * ID роли менеджера в базе данных.
     */
    private final static Long MANAGER_ROLE_ID = 3L;
    /**
     * Реализация репозитория {@link UserRepository}
     * для работы пользователей
     * с базой данных.
     */
    private final UserRepository userRepository;

    /**
     * Реализация репозитория {@link RoleRepository}
     * для работы  ролями пользователей
     * с базой данных.
     */
    private final RoleRepository roleRepository;

    /**
     * Конструктор для инициализации основных
     * переменных. Помечаный аннотацией @Autowired,
     * которая позволит Spring автоматически
     * инициализировать объект.
     *
     * @param userRepository Реализация репозитория
     *                       {@link UserRepository}
     *                       для работы пользователей
     *                       с базой данных.
     * @param roleRepository Реализация репозитория
     *                       {@link RoleRepository}
     *                       для работы ролями
     *                       пользователей
     *                       с базой данных.
     */
    @Autowired
    public UserDAOImpl(
            final UserRepository userRepository,
            final RoleRepository roleRepository
    ) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Возвращает пользователя из базы даных,
     * у которого совпадает имя с значением
     * входящего параметра.
     *
     * @param name Имя пользователя для возврата.
     * @return Объект класса {@link User} -
     * пользователь.
     */
    @Override
    public User getByName(final String name) {
        return this.userRepository.findByName(name);
    }

    /**
     * Возвращает пользователя из базы даных,
     * у которого совпадает уникальный
     * логин с значением входящего параметра.
     *
     * @param username Логин пользователя для
     *                 возврата.
     * @return Объект класса {@link User} -
     * пользователь с уникальным логином.
     */
    @Override
    public User getByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    /**
     * Возвращает главного администратора
     * сайта.
     *
     * @return Объект класса {@link User} -
     * главный администратор.
     */
    @Override
    public User getMainAdministrator() {
        return this.userRepository.findAllByRole(
                this.roleRepository.findOne(ADMIN_ROLE_ID)
        ).get(0);
    }

    /**
     * Возвращает список всех администраторов
     * сайта.
     *
     * @return Объект типа {@link List} -
     * список администраторов.
     */
    @Override
    public List<User> getAdministrators() {
        return this.userRepository.findAllByRole(
                this.roleRepository.findOne(ADMIN_ROLE_ID)
        );
    }

    /**
     * Возвращает список всех менеджеров
     * сайта.
     *
     * @return Объект типа {@link List} -
     * список менеджеров.
     */
    @Override
    public List<User> getManagers() {
        return this.userRepository.findAllByRole(
                this.roleRepository.findOne(MANAGER_ROLE_ID)
        );
    }

    /**
     * Возвращает список всех клиентов сайта.
     *
     * @return Объект типа {@link List} -
     * список клиентов.
     */
    @Override
    public List<User> getClients() {
        return this.userRepository.findAllByRole(
                this.roleRepository.findOne(CLIENT_ROLE_ID)
        );
    }

    /**
     * Возвращает авторизированого
     * пользователя.
     *
     * @return Объект класса {@link User} -
     * авторизированый пользователь.
     */
    @Override
    public User getAuthenticatedUser() {
        User user;
        try {
            user = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
        } catch (Exception ex) {
            ex.printStackTrace();
            user = null;
        }
        return user;
    }

    /**
     * Удаляет пользователя из базы даных,
     * у которого совпадает имя с значением
     * входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     */
    @Override
    public void remove(final String name) {
        this.userRepository.deleteByName(name);
    }

    /**
     * Удаляет пользователя из базы даных,
     * у которого совпадает роль с
     * значением входящего параметра.
     *
     * @param role Роль пользователя для удаления.
     */
    @Override
    public void remove(final Role role) {
        this.userRepository.deleteAllByRole(role);
    }
}
