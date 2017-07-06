package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.model.user.UserRole;
import ua.com.alexcoffee.repository.UserRepository;
import ua.com.alexcoffee.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ua.com.alexcoffee.util.validator.ObjectValidator.*;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link User}
 * в базе данных интерфейса {@link UserService}, наследует родительский
 * класс {@link MainServiceImpl}, в котором реализованы основные методы,
 * а также методы интерфейса {@link UserDetailsService}.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainServiceImpl
 * @see UserService
 * @see User
 * @see UserRepository
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class UserServiceImpl extends MainServiceImpl<User> implements UserService, UserDetailsService {

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
     * Реализация интерфейса {@link UserRepository}
     * для работы пользователей с базой данных.
     */
    private final UserRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository     Реализация интерфейса {@link UserRepository}
     *                       для работы пользователей с базой данных.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public UserServiceImpl(final UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает пользователя, у которого совпадает имя с
     * значением входящего параметра. Режим только для чтения.
     *
     * @param name Имя пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с именем name.
     * @throws IllegalArgumentException Бросает исключение,
     *                                  когда пустой входной параметр name.
     * @throws NullPointerException     Бросает исключение,
     *                                  если не найден пользователь с входящим параметром name.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByName(final String name) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(name)) {
            throw new IllegalArgumentException("No user name!");
        }
        final User user = this.repository.findByName(name);
        if (isNull(user)) {
            throw new NullPointerException("Can't find user by name " + name + "!");
        }
        return user;
    }

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра. Режим только для чтения.
     *
     * @param username Логин пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с логином username.
     * @throws IllegalArgumentException Бросает исключение,
     *                                  если пустой входной параметр username.
     * @throws NullPointerException     Бросает исключение,
     *                                  если не найден пользователь с входящим параметром username.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByUsername(final String username)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(username)) {
            throw new IllegalArgumentException("No username!");
        }
        final User user = this.repository.findByUsername(username);
        if (isNull(user)) {
            throw new NullPointerException("Can't find user by username " + username + "!");
        }
        return user;
    }

    /**
     * Возвращает главного администратора сайта.
     * Режим только для чтения.
     *
     * @return Объект класса {@link User} - главный администратор.
     * @throws NullPointerException Бросает исключение,
     *                              если не найден пользователь-админ.
     */
    @Override
    @Transactional(readOnly = true)
    public User getMainAdministrator() throws NullPointerException {
        final User user = new ArrayList<>(getAdministrators()).get(0);
        if (isNull(user)) {
            throw new NullPointerException("Can't find administrator!");
        }
        return user;
    }

    /**
     * Возвращает список всех администраторов сайта.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} - список администраторов.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getAdministrators() {
        return this.repository.findAllByRole(UserRole.ADMIN);
    }

    /**
     * Возвращает список всех менеджеров сайта.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} - список менеджеров.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getManagers() {
        return this.repository.findAllByRole(UserRole.MANAGER);
    }

    /**
     * Возвращает список всех клиентов сайта.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} - список клиентов.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getClients() {
        return this.repository.findAllByRole(UserRole.CLIENT);
    }

    /**
     * Возвращает список персонала сайта.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} -
     * список персонала.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getPersonnel() {
        final List<User> users = new ArrayList<>();
        users.addAll(getAdministrators());
        users.addAll(getManagers());
        return users;
    }

    /**
     * Возвращает авторизированого пользователя.
     * Режим только для чтения.
     *
     * @return Объект класса {@link User} - авторизированый пользователь.
     */
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        User user;
        try {
            final SecurityContext context = SecurityContextHolder.getContext();
            final Authentication authentication = context.getAuthentication();
            user = (User) authentication.getPrincipal();
        } catch (Exception ex) {
            ex.printStackTrace();
            user = null;
        }
        return user;
    }

    /**
     * Удаляет пользователя, у которого совпадает имя с значением входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     */
    @Override
    @Transactional
    public void removeByName(final String name) {
        if (isNotEmpty(name)) {
            this.repository.deleteByName(name);
        }
    }

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     *
     * @param role Роль пользователя для удаления.
     */
    @Override
    @Transactional
    public void removeByRole(final UserRole role) {
        if (isNotNull(role)) {
            this.repository.deleteAllByRole(role);
        }
    }

    /**
     * Удаляет список персонала сайта.
     */
    @Override
    @Transactional
    public void removePersonnel() {
        final Collection<User> personnel = getPersonnel();
        if (isNotEmpty(personnel)) {
            final User mainAdmin = getMainAdministrator();
            personnel.remove(mainAdmin);
            this.repository.delete(personnel);
        }
    }

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра. Режим только для чтения.
     * Реализованый метод интерфейса {@link UserDetailsService}.
     *
     * @param username Логин пользователя для возврата
     * @return Объект класса {@link User} - пользователь с логином username.
     * @throws UsernameNotFoundException Бросает исключеник,
     *                                   если пользователь с логином username не найден.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            return getByUsername(username);
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
    }
}
