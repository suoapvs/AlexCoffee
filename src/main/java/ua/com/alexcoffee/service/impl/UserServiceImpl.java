package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.UserDAO;
import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.UserService;

import java.util.ArrayList;
import java.util.List;

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
 * @author Yurii Salimov
 * @see MainServiceImpl
 * @see UserService
 * @see User
 * @see UserDAO
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.dao")
public class UserServiceImpl extends MainServiceImpl<User> implements UserService, UserDetailsService {
    /**
     * Реализация интерфейса {@link UserDAO} для работы пользователей с базой данных.
     */
    private final UserDAO dao;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param dao Реализация интерфейса {@link UserDAO} для работы пользователей с базой данных.
     */
    @Autowired
    public UserServiceImpl(UserDAO dao) {
        super(dao);
        this.dao = dao;
    }

    /**
     * Возвращает пользователя, у которого совпадает имя с
     * значением входящего параметра. Режим только для чтения.
     *
     * @param name Имя пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с именем name.
     * @throws WrongInformationException Бросает исключение, когда пустой входной параметр name.
     * @throws BadRequestException       Бросает исключение, если не найден пользователь с входящим параметром name.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByName(String name) throws WrongInformationException, BadRequestException {
        if (name == null || name.isEmpty()) {
            throw new WrongInformationException("No user name!");
        }
        User user = this.dao.getByName(name);
        if (user == null) {
            throw new BadRequestException("Can't find user by name " + name + "!");
        }
        return user;
    }

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра. Режим только для чтения.
     *
     * @param username Логин пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с логином username.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр username.
     * @throws BadRequestException       Бросает исключение, если не найден пользователь с входящим параметром username.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) throws WrongInformationException, BadRequestException {
        if (username == null || username.isEmpty()) {
            throw new WrongInformationException("No username!");
        }
        User user = this.dao.getByUsername(username);
        if (user == null) {
            throw new BadRequestException("Can't find user by username " + username + "!");
        }
        return user;
    }

    /**
     * Возвращает главного администратора сайта. Режим только для чтения.
     *
     * @return Объект класса {@link User} - главный администратор.
     * @throws BadRequestException Бросает исключение, если не найден пользователь-админ.
     */
    @Override
    @Transactional(readOnly = true)
    public User getMainAdministrator() throws BadRequestException {
        User user = this.dao.getMainAdministrator();
        if (user == null) {
            throw new BadRequestException("Can't find administrator!");
        }
        return user;
    }

    /**
     * Возвращает список всех администраторов сайта. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список администраторов.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAdministrators() {
        return this.dao.getAdministrators();
    }

    /**
     * Возвращает список всех менеджеров сайта. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список менеджеров.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getManagers() {
        return this.dao.getManagers();
    }

    /**
     * Возвращает список всех клиентов сайта. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список клиентов.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getClients() {
        return this.dao.getClients();
    }

    /**
     * Возвращает список персонала сайта. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список персонала.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getPersonnel() {
        List<User> users = new ArrayList<>();
        users.addAll(getAdministrators());
        users.addAll(getManagers());
        return users;
    }

    /**
     * Возвращает авторизированого пользователя. Режим только для чтения.
     *
     * @return Объект класса {@link User} - авторизированый пользователь.
     */
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        return this.dao.getAuthenticatedUser();
    }

    /**
     * Удаляет пользователя, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр username.
     */
    @Override
    @Transactional
    public void removeByName(String name) throws WrongInformationException {
        if (name == null || name.isEmpty()) {
            throw new WrongInformationException("No username!");
        }
        this.dao.remove(name);
    }

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     *
     * @param role Роль пользователя для удаления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр role.
     */
    @Override
    @Transactional
    public void removeByRole(Role role) throws WrongInformationException {
        if (role == null) {
            throw new WrongInformationException("No user role!");
        }
        this.dao.remove(role);
    }

    /**
     * Удаляет список персонала сайта.
     */
    @Override
    @Transactional
    public void removePersonnel() {
        List<User> personnel = getPersonnel();
        if (personnel.isEmpty()) {
            return;
        }
        personnel.remove(getMainAdministrator());
        this.dao.remove(personnel);
    }

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра. Режим только для чтения.
     * Реализованый метод интерфейса {@link UserDetailsService}.
     *
     * @param username Логин пользователя для возврата
     * @return Объект класса {@link User} - пользователь с логином username.
     * @throws UsernameNotFoundException Бросает исключеник, если пользователь с логином username не найден.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }
}
